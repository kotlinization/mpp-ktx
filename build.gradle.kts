allprojects {
  repositories {
    mavenCentral()
    google()
  }
}

plugins {
  kotlin("multiplatform") version libs.versions.kotlin apply false
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.mavenPublish) apply false
}