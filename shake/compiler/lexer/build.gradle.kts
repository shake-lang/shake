import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    id("com.github.node-gradle.node") version "3.1.1"
    id("maven-publish")
}

group = "io.github.shakelang.shake"
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

repositories {
    mavenCentral()
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        testImplementation(kotlin("test"))
    }
}
