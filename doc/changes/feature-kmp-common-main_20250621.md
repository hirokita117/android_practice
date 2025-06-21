## 2025/06/21 MyDiary KMP 共有モジュール commonMain 実装

### 修正内容
- `shared/build.gradle.kts`
    - `kotlinx-serialization` プラグインおよびライブラリの依存関係を有効化しました。
- `shared/src/commonMain/kotlin/com/example/mydiary/shared/model/`
    - `DiaryEntry.kt`: 日記エントリを表すデータクラス `DiaryEntry` を定義しました。
    - `Mood.kt`: 気分を表す enum クラス `Mood` を定義しました。
    - `Weather.kt`: 天気を表す enum クラス `Weather` を定義しました。
- `shared/src/commonMain/kotlin/com/example/mydiary/shared/repository/`
    - `DiaryRepository.kt`: 日記データの CRUD 操作のためのインターフェース `DiaryRepository` を定義しました。

### 理由
`doc/task.md` に記載の `1.1. commonMain` のタスクを実行するため。
具体的には、KMP共有モジュールに必要なデータモデルとリポジトリインターフェースを定義しました。
`kotlinx-serialization` はデータモデルをシリアライズ可能にするために導入しました。
