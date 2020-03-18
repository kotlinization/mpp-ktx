plugins {
    kotlin("multiplatform") version "1.3.70"
}

group = "org.github.mikibemiki"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
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