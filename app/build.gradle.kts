plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt") // Or id("com.google.devtools.ksp") depending on annotation processors
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.mydiary" // TODO: Change to your actual namespace
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mydiary" // TODO: Change to your actual application ID
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Set to true for release builds
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        // Make sure the version is compatible with your Kotlin version
        // Check https://developer.android.com/jetpack/androidx/releases/compose-kotlin
        kotlinCompilerExtensionVersion = "1.5.14" // Example: Use the correct version
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Project Modules
    implementation(project(":shared"))

    // Core & UI
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0") // If using AppCompat themes/components
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3") // Material 3 components
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")
    // implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3") // Optional: if using LiveData

    // Testing
    testImplementation("junit:junit:4.13.2") // JUnit 4 for local unit tests
    // For JUnit 5 (requires extra setup)
    // testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    // testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    androidTestImplementation("androidx.test.ext:junit:1.2.1") // AndroidJUnitRunner
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1") // Espresso for UI tests
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00")) // Compose BOM for testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4") // Compose UI testing

    // Debugging
    debugImplementation("androidx.compose.ui:ui-tooling") // Tools like Layout Inspector
    debugImplementation("androidx.compose.ui:ui-test-manifest") // Test manifest generation

    // Kapt/KSP dependencies (Add based on your needs, e.g., Room, Hilt)
    // kapt("androidx.room:room-compiler:2.6.1")
    // ksp("androidx.room:room-compiler:2.6.1")
    // kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    // ksp("com.google.dagger:hilt-compiler:2.51.1")
}

// Apply Kapt or KSP plugin if annotation processors are used
// kapt {
//     correctErrorTypes = true
// } 
