allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    kotlin("multiplatform") apply false
    id("com.android.library") apply false
    id("com.vanniktech.maven.publish.base") apply false
}