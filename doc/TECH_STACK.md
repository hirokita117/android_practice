このドキュメントは、日記アプリ開発練習プロジェクトで使用する主要な技術要素をまとめたものです。

## 概要

*   **プラットフォーム:** Android ネイティブアプリ (Kotlin), Kotlin Multiplatform (KMP) により iOS と一部コード共有
*   **言語:** Kotlin を主に使用
*   **ビルドシステム:** Gradle
*   **データ保存:** ローカルデータベース (Room)

## 詳細

### プログラミング言語

*   **Kotlin:** Android アプリ、共有ロジック (KMP) の主要言語
    *   バージョン: 2.0.0 (例: `build.gradle.kts` で確認)
*   **Java:** Android アプリの一部やライブラリで使用される可能性あり
    *   バージョン: 1.8 (例: `app/build.gradle.kts` で確認)

### プラットフォーム

*   **Android:**
    *   `minSdk`: 28 (Android 9.0) (例: `app/build.gradle.kts` で確認)
    *   `compileSdk`: 34 (Android 14) (例: `app/build.gradle.kts` で確認)
    *   `targetSdk`: 34 (Android 14) (例: `app/build.gradle.kts` で確認)
*   **Kotlin Multiplatform (KMP):**
    *   ターゲット: Android, iOS (iosX64, iosArm64, iosSimulatorArm64)
    *   共有モジュール: 例: `shared/`
    *   共通コード: 例: `shared/src/commonMain/kotlin`

### ビルドシステム

*   **Gradle:**
    *   バージョン: 8.9 (例: `gradle/wrapper/gradle-wrapper.properties` で確認)
    *   Kotlin DSL (`*.gradle.kts`) を使用
    *   バージョン管理: 各 `build.gradle.kts` ファイル内でバージョンを定義 (libs.versions.toml は未使用)

### 主要ライブラリ・フレームワーク (Android)

バージョンはビルドファイル (例: `app/build.gradle.kts`) で確認が必要なものを記載しています。

*   **UI:**
    *   Jetpack Compose: 1.6.8 (`ui`, `foundation`, `material3`, etc.) - 主要 UI フレームワーク (BOM `2024.06.00` 時点)
    *   Material Design Components: 1.2.1 (`material3-android`) - Compose Material 3 を使用 (BOM 利用推奨)
    *   Navigation Component: 2.7.7 (`navigation-compose`) - 画面遷移
    *   Activity: 1.9.0
    *   AppCompat: 1.7.0
    *   Core KTX: 1.13.1
*   **アーキテクチャ:**
    *   MVVM (ViewModel): Jetpack ViewModel 2.8.3 (`lifecycle-viewmodel-compose`)
    *   LiveData / StateFlow: Jetpack LiveData 2.8.3 (`lifecycle-livedata-ktx`) または Kotlin Coroutines StateFlow を UI 状態管理に使用
    *   Lifecycle: 2.8.3
*   **DI (Dependency Injection):**
    *   `kotlin-kapt` プラグインを使用 (Hilt や Dagger などを想定、シンプルな場合は手動 DI も可)
*   **非同期処理:**
    *   Kotlin Coroutines: 1.8.1 (`kotlinx-coroutines-core`, `kotlinx-coroutines-android`)
*   **データ永続化:**
    *   Room: 2.6.1 (`room-runtime`, `room-compiler` は kapt または ksp で使用, `room-ktx`) - 日記データのローカル保存
*   **画像読み込み:**
    *   (Coil, Glide などのライブラリを必要に応じて追加)
*   **テスト:**
    *   JUnit 5: (`de.mannodermaus.android-junit5` プラグイン: 1.10.0.0)
    *   Compose UI Testing
    *   ViewModel Testing
    *   Room Testing
*   **その他:**
    *   OSS Licenses Plugin: 0.10.6 (`com.google.android.gms.oss-licenses-plugin`)

### 主要ライブラリ・フレームワーク (KMP)

バージョンはビルドファイル (例: `shared/build.gradle.kts`) で確認できたものを記載しています。

*   **ネットワーク:**
    *   Ktor Client: 2.3.12 (`ktor-client-core`, `ktor-client-logging`, `ktor-client-okhttp`, `ktor-client-darwin`) - (必要に応じて使用)
    *   Content Negotiation: 2.3.12 (`ktor-client-content-negotiation`)
*   **シリアライゼーション:**
    *   Kotlinx Serialization: 2.0.0 (プラグイン), Ktor 連携 (`ktor-serialization-kotlinx-json:2.3.12`, コアライブラリ `kotlinx-serialization-json`: 1.7.1)
*   **非同期処理:**
    *   Kotlinx Coroutines Core: 1.8.1
*   **DI (Dependency Injection):**
    *   Koin: 3.5.6 (`koin-core`) - (Android 側と共通化する場合などに検討)
*   **日付/時刻:**
    *   Kotlinx DateTime: 0.6.0.1
*   **ロギング:**
    *   Napier: 2.7.1
*   **API 生成:**
    *   OpenAPI Generator Gradle Plugin: 7.8.0 - (外部 API を利用する場合)
*   **ビルド設定共有:**
    *   BuildKonfig: 0.15.1
*   **iOS 連携改善:**
    *   SKIE: 0.9.5 (Enum Interop 有効)

### その他

*   **CI/CD:** (必要に応じて設定)
*   **API 仕様:** OpenAPI (外部 API を利用する場合)
*   **静的解析/フォーマッター:** Ktlint
