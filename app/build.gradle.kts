import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    compileSdk = 36
    namespace = "com.yifeplayte.norelaunch"

    defaultConfig {
        applicationId = "com.yifeplayte.norelaunch"
        minSdk = 34
        targetSdk = 36
        versionCode = 3
        versionName = "1.1.1"

        applicationVariants.configureEach {
            outputs.configureEach {
                if (this is com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
                    outputFileName = outputFileName.replace("app", rootProject.name)
                        .replace(Regex("debug|release"), versionName)
                }
            }
        }
    }

    buildTypes {
        named("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
        named("debug") {
            versionNameSuffix = "-debug-" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now())
        }
    }

    androidResources {
        additionalParameters += "--allow-reserved-package-id"
        additionalParameters += "--package-id"
        additionalParameters += "0x45"
        generateLocaleConfig = true
    }

    kotlin {
        jvmToolchain(21)
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
    debugImplementation("androidx.compose.ui:ui-tooling-preview-android:1.8.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.8.3")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.compose.foundation:foundation-android:1.8.3")
    implementation("androidx.compose.runtime:runtime-android:1.8.3")
    implementation("androidx.navigation:navigation-compose:2.9.0")
    implementation("com.github.kyuubiran:EzXHelper:2.2.1")
    implementation("dev.chrisbanes.haze:haze-android:1.6.6")
    implementation("top.yukonga.miuix.kmp:miuix-android:0.4.7")
}
