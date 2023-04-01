import com.android.build.gradle.LibraryExtension

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    kotlin("multiplatform") apply true
    id("com.android.library") apply true
    `maven-publish` apply true
}

group = "org.github.kotlinizer"
version = "0.1.7"

the<LibraryExtension>().apply {
    compileSdk = 33
    defaultConfig {
        minSdk = 16
    }
    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }
}

kotlin {
    jvm()
    android {
        publishLibraryVariants("debug", "release")
    }
    js {
        browser()
        nodejs()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

task("install") {
    dependsOn("publishToMavenLocal")
}