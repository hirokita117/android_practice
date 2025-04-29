# MyDiary - Android/KMP 日記アプリ練習プロジェクト

Android アプリ開発の練習のためのリポジトリです。

## プロジェクト概要

シンプルな日記アプリ「MyDiary」を Android (Jetpack Compose) と Kotlin Multiplatform (KMP) を使用して開発します。

主な機能:
*   日記の作成・編集・表示
*   カレンダー表示
*   気分・天気の記録
*   PIN によるロック機能

## 技術スタック

主要な技術スタックは `doc/TECH_STACK.md` を参照してください。

*   **言語:** Kotlin
*   **UI:** Jetpack Compose
*   **アーキテクチャ:** MVVM
*   **非同期処理:** Kotlin Coroutines
*   **データベース:** Room (Android)
*   **設定保存:** DataStore (Android)
*   **共有ロジック:** Kotlin Multiplatform (KMP)
*   **ビルド:** Gradle (Kotlin DSL)

## プロジェクト構造

```
MyDiary/
├── .gitignore
├── README.md
├── settings.gradle.kts
├── app/              # Android アプリケーションモジュール
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/mydiary/
│           └── res/
│               └── values/
│                   └── themes.xml
├── shared/           # KMP 共有モジュール
│   ├── build.gradle.kts
│   └── src/
│       ├── commonMain/kotlin/
│       ├── androidMain/kotlin/
│       └── iosMain/kotlin/
└── doc/              # ドキュメント
    ├── DesignDoc.md
    ├── RequirementDefinition.md
    ├── TECH_STACK.md
    └── task.md
```

## セットアップ手順

1.  リポジトリをクローンします。
2.  Android Studio でプロジェクトを開きます。
3.  Gradle が自動的に同期されます。

## ライセンス

(TBD)
