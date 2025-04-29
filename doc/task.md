# MyDiary アプリ 実装タスクリスト

このリストは主な実装タスクを示します。

## 0. プロジェクト初期設定
- [ ] `.gitignore` ファイル作成・設定 (Android/Kotlin 用)
- [ ] Gradle プロジェクトセットアップ
    - [ ] `:app` モジュール作成
    - [ ] `:shared` KMP モジュール作成
    - [ ] ルート `settings.gradle.kts` 設定
    - [ ] 各モジュール `build.gradle.kts` に基本プラグイン・依存関係追加

## 1. KMP共有モジュール (`:shared`) の実装

### 1.1. `commonMain`
- [ ] データモデル定義 (`DiaryEntry`, `Mood`, `Weather`)
- [ ] リポジトリインターフェース定義 (`DiaryRepository`)
- [ ] `kotlinx-datetime` の導入と設定
- [ ] `kotlinx-serialization` の導入と設定 (必要に応じて)
- [ ] 共通の UseCase 定義 (必要に応じて)

### 1.2. `androidMain`
- [ ] Room データベース定義 (`MyDiaryDatabase`, `DiaryEntryEntity`, `DiaryDao`)
- [ ] Room TypeConverter 実装 (`LocalDate` <-> String, `Instant` <-> Long)
- [ ] DataStore 実装 (`UserPreferencesRepository` - PINハッシュ, テーマ設定用)
- [ ] リポジトリ実装 (`DiaryRepositoryImpl`)
- [ ] DI設定 (`androidMain` 向け, Koin or 手動)

## 2. Androidアプリモジュール (`:app`) の実装

### 2.1. 基本設定
- [ ] `:shared` モジュールへの依存関係設定
- [ ] Navigation Component (Compose Navigation) の設定
- [ ] DI設定 (`:app` 向け, Hilt/Koin or 手動)
- [ ] Application クラスの作成 (必要に応じてDI初期化など)

### 2.2. ViewModel レイヤー
- [ ] 各画面に対応する ViewModel の作成
    - [ ] `SplashViewModel`
    - [ ] `PinViewModel`
    - [ ] `CalendarViewModel`
    - [ ] `DiaryDetailViewModel`
    * [ ] `DiaryEditViewModel`
    * [ ] `SettingsViewModel`
- [ ] UI State の定義 (Data class or Sealed class)
- [ ] ViewModel と Repository/UseCase の連携
- [ ] Coroutine を使用した非同期処理の実装

### 2.3. UI レイヤー (Compose Screens)
- [ ] 各画面の Composable 関数の作成 (初期レイアウト)
    - [ ] `SplashScreen`
    - [ ] `PinScreen`
    - [ ] `CalendarScreen` (カレンダー表示ライブラリ or カスタム実装)
    - [ ] `DiaryDetailScreen`
    * [ ] `DiaryEditScreen`
    * [ ] `SettingsScreen`
- [ ] Navigation Graph の構築
- [ ] 各画面の UI 実装と ViewModel との接続
    - [ ] `SplashScreen`: 初期化処理、画面遷移
    - [ ] `PinScreen`: PIN 入力 UI、認証ロジック連携、エラー表示
    - [ ] `CalendarScreen`: カレンダー表示、日記マーク表示、日付選択、FAB、メニュー
    - [ ] `DiaryDetailScreen`: 日記内容表示、編集ボタン
    * [ ] `DiaryEditScreen`: 入力フォーム、気分・天気選択、保存・キャンセル処理
    * [ ] `SettingsScreen`: PIN 設定/変更 UI、テーマ選択 UI
- [ ] テーマ実装 (Light/Dark/System)

## 3. テスト

- [ ] 単体テスト (ViewModel, Repository, UseCase, DAO)
- [ ] UIテスト (Compose Screen, Navigation)

## 4. その他

- [ ] エラーハンドリング実装 (DBエラー、PIN認証失敗時の表示など)
- [ ] OSS ライセンス表示画面の実装
- [ ] アプリアイコンの設定

このタスクリストは開発を進める中で、必要に応じて更新・詳細化してください。 
