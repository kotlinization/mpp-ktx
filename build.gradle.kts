import com.android.build.gradle.LibraryExtension

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    kotlin("multiplatform") version "1.8.0"
    `maven-publish`
}

apply {
    plugin("com.android.library")
}

group = "org.github.kotlinizer"
version = "0.1.5"

repositories {
    mavenCentral()
}

the<LibraryExtension>().apply {
    compileSdkVersion(33)
    defaultConfig {
        minSdkVersion(16)
    }
    lintOptions {
        isAbortOnError = false
        isCheckReleaseBuilds = false
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
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting { }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val androidMain by getting { }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
    }
}

task("install") {
    dependsOn("publishToMavenLocal")
}