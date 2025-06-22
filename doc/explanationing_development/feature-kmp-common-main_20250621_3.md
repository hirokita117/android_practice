# KMP共有モジュール(commonMain)の実装解説

今回の開発は、AndroidとiOSのアプリケーションで共通して利用する「共有モジュール」の土台を構築したものです。PHPでの開発経験がある方に分かりやすいように、具体的な内容を解説します。

## 背景：なぜ「共有モジュール」を作るのか？

日記アプリをAndroidとiOSの両方で開発する場合を想像してみてください。

-   日記データをどういう構造で持つか (データモデル)
-   日記を保存したり読み込んだりする処理 (ビジネスロジック)

これらの部分は、AndroidでもiOSでも全く同じはずです。もし別々に開発すると、同じようなコードを二度書くことになり、修正があった場合も両方を直さなければならず、非効率でバグの原因にもなります。

そこで、**Kotlin Multiplatform (KMP)** という技術を使い、これらの共通部分を「共有モジュール」として一箇所にまとめて開発します。今回の作業は、その第一歩となります。

## 具体的な開発内容

### 1. 日記データの「型」を定義 (データモデル)

PHPで開発を始める際、まずデータベースのテーブル設計をしたり、扱うデータの構造をクラスとして定義したりするかと思います。それと同じように、日記アプリで扱うデータの「型」を定義しました。

-   **`DiaryEntry.kt`**: 一つの日記エントリーを表します。
    -   PHPのクラスで表現すると、以下のようなイメージです。
        ```php
        class DiaryEntry {
            public string $id;
            public string $title;
            public string $content;
            public Mood $mood; // 「気分」を表す型
            public Weather $weather; // 「天気」を表す型
            public DateTimeImmutable $createdAt;
            public DateTimeImmutable $updatedAt;
        }
        ```
-   **`Mood.kt`, `Weather.kt`**: 気分や天気を表します。これらは選択肢が決まっているデータなので、PHP 8.1以降で導入された `enum` (列挙型) と同じものです。
    ```php
    // Mood.kt のイメージ
    enum Mood {
        case HAPPY;
        case SAD;
        case ANGRY;
        // ...
    }
    ```
    これにより、`'happy'` のような文字列ではなく、型として安全に気分や天気を扱うことができます。

### 2. データをJSONに変換できるようにする (シリアライズ)

定義したデータモデル(日記データ)を、APIサーバーと通信したり、デバイス内にファイルとして保存したりするには、JSONのような汎用的な形式に変換する必要があります。

この変換処理のために、`kotlinx-serialization` というライブラリを導入しました。これは、PHPで `json_encode()` や `json_decode()` を使ってオブジェクトとJSON文字列を相互に変換するのと同じ役割を果たします。

データクラスに `@Serializable` というアノテーション（PHP 8の `Attribute` に似た機能）を付けるだけで、自動的にJSONへの変換が可能になります。

### 3. データ操作の「ルール」を定義 (リポジトリインターフェース)

次に、日記データをどのように操作（保存、読み込み、更新、削除など）するかの「ルール」を定義しました。これを「リポジトリパターン」と呼び、PHPでもよく使われる設計パターンです。

作成したのは `DiaryRepository.kt` という **インターフェース** です。

-   PHPの `interface` で表現すると、以下のようなイメージです。
    ```php
    interface DiaryRepository {
        public function getDiaryEntries(LocalDate $date): array;
        public function getDiaryEntry(string $id): ?DiaryEntry;
        public function addDiaryEntry(DiaryEntry $entry): void;
        public function updateDiaryEntry(DiaryEntry $entry): void;
        public function deleteDiaryEntry(string $id): void;
    }
    ```

**重要なのは、これが「インターフェース（契約）」であり、具体的な処理内容はまだ書かれていない点です。**

例えば、「日記を保存する」というルールは定義しましたが、「具体的にどうやって（SQLiteに？APIサーバーに？）保存するのか」という実装は、この先のAndroid固有、iOS固有のコードでそれぞれ記述します。

これにより、共有モジュール側では「日記の操作にはこれらのルールが必ず存在する」ことを保証しつつ、具体的な実装方法はプラットフォームごとに柔軟に変えることができるようになります。

## まとめ

今回の作業をPHPのフレームワーク (例: Laravel) での開発に例えるなら、以下のような初期実装を行ったとイメージしてください。

1.  `app/Models/DiaryEntry.php` のようなEloquentモデルを定義した。
2.  `app/Enums/Mood.php`, `app/Enums/Weather.php` を作成した。
3.  `app/Repositories/DiaryRepositoryInterface.php` を作成し、基本的なCRUD操作のメソッドを定義した。

この土台の上に、今後Android/iOSそれぞれの画面や、具体的なデータベース処理を実装していくことになります。
