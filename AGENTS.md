# AGENTS — 貢献ガイドライン
本ファイルは本リポジトリのコミュニケーションおよび貢献ルールをまとめたものです。以下をご確認ください。

# コミュニケーション方針

- コミュニケーションは日本語でお願いします。
  - プルリクエストの description も日本語で記載してください。

# プロジェクト概要

- 本リポジトリは、Android (Jetpack Compose) と Kotlin Multiplatform (KMP) で開発されたシンプルな日記アプリ「MyDiary」です。
- 主要な技術スタックは [doc/TECH_STACK.md](doc/TECH_STACK.md) を参照してください。

# 開発プロセス

- 修正をおこなう場合は、その修正内容の理由を記したファイルを別途追加するようにしてください。

## 2024/07/24 MyDiary KMP 共有モジュール commonMain 実装

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
