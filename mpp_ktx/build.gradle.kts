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
    androidTarget() { publishAllLibraryVariants() }
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

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                name.set("mpp_ktx")
                description.set("Utilities components for various Kotlinization projects.")
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://opensource.org/license/apache-2-0/")
                    }
                }
                url.set("https://kotlinization.com/mpp-ktx")
                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/kotlinization/mpp-ktx/issues")
                }
                scm {
                    connection.set("https://github.com/kotlinization/mpp-ktx.git")
                    url.set("https://github.com/kotlinization/mpp-ktx")
                }
                developers {
                    developer {
                        name.set("Milan Jovic")
                        email.set("milanjovic92@live.com")
                    }
                }
            }
        }
    }
}