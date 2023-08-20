allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    kotlin("multiplatform") apply true
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

group = "com.kotlinization"
version = rootProject.extra["versionName"].toString()

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }
}

kotlin {
    jvm()
    android { publishAllLibraryVariants() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinxCoroutinesCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

