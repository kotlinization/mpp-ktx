allprojects {
  repositories {
    mavenCentral()
    google()
  }
}

plugins {
  kotlin("multiplatform") apply true
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.mavenPublish) apply true
}

group = "org.github.kotlinizer"
version = rootProject.extra["versionName"].toString()

android {
  compileSdk = libs.versions.compileSdk.get().toInt()
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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