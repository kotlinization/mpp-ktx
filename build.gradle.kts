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
    alias(libs.plugins.nexusPublish) apply true
}

group = "com.kotlinization"
version = rootProject.extra["versionName"].toString()

nexusPublishing {
    this.repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}