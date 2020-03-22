
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.1")
    }
}

plugins {
    kotlin("multiplatform") version "1.3.70"
    id("maven-publish")
}

apply {
    plugin("com.android.library")
}

group = "org.github.MikiBeMiki"
version = "0.1.0-alpha01"

repositories {
    mavenCentral()
}

val coroutinesVersion = "1.3.5"

the<com.android.build.gradle.LibraryExtension>().apply {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(16)
    }
}

kotlin {
    jvm()
    android {
        publishLibraryVariants("debug", "release")
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
            }
        }
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