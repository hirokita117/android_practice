plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.0.0" // Use your Kotlin version
    id("com.android.library")
    // id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" // If using kotlinx.serialization
    // id("com.google.devtools.ksp") version "2.0.0-1.0.21" // If using KSP
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions { jvmTarget = "1.8" }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
                // implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
                // implementation("io.insert-koin:koin-core:3.5.6")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.core:core-ktx:1.13.1")
                implementation("androidx.room:room-runtime:2.6.1")
                implementation("androidx.room:room-ktx:2.6.1")
                implementation("androidx.datastore:datastore-preferences:1.1.1")
                // ksp("androidx.room:room-compiler:2.6.1")
            }
        }
        val iosMain by getting {
            // Link commonMain to iosMain before defining dependents
            dependsOn(commonMain)
            dependencies {
                // iOS specific dependencies
            }
        }
        val iosX64Main by getting { dependsOn(iosMain) }
        val iosArm64Main by getting { dependsOn(iosMain) }
        val iosSimulatorArm64Main by getting { dependsOn(iosMain) }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
            }
        }
        val androidUnitTest by getting {
             dependencies {
                 implementation("androidx.test.ext:junit:1.2.1")
                 implementation("androidx.test:core:1.6.1")
                 implementation("org.robolectric:robolectric:4.13")
                 implementation("androidx.room:room-testing:2.6.1")
             }
        }
        val iosTest by getting { dependsOn(commonTest) }
        val iosX64Test by getting { dependsOn(iosTest) }
        val iosArm64Test by getting { dependsOn(iosTest) }
        val iosSimulatorArm64Test by getting { dependsOn(iosTest) }
    }
}

android {
    namespace = "com.example.mydiary.shared" // TODO: Change if needed
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // Required for Room Schemas with KSP
    // sourceSets["main"].java.srcDirs("src/androidMain/kotlin", "build/generated/ksp/android/debug/kotlin", "build/generated/ksp/android/release/kotlin")
}

// Room schema location (adjust path if using KAPT instead of KSP)
// android {
//     defaultConfig {
//         javaCompileOptions {
//             annotationProcessorOptions {
//                 arguments["room.schemaLocation"] = "$projectDir/schemas"
//             }
//         }
//     }
// }
// // Or for KSP:
// ksp {
//     arg("room.schemaLocation", "$projectDir/schemas")
// } 
