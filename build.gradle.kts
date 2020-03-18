plugins {
    kotlin("multiplatform") version "1.3.70"
    id("maven-publish")
}


group = "org.github.mikibemiki"
version = "0.0.1-alpha04"

repositories {
    mavenCentral()
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

task("install") {
    dependsOn("publishToMavenLocal")
}