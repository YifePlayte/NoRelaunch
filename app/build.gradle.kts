import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34
    namespace = "com.yifeplayte.norelaunch"

    defaultConfig {
        applicationId = "com.yifeplayte.norelaunch"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
    implementation("com.github.kyuubiran:EzXHelper:2.1.2")
    implementation(project(":blockmiui"))
}
