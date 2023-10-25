import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    id("io.github.shakelang.shake.conventions.mpp.publishing")
}

group = "io.github.shakelang.shake"
version = "0.1.0"
description = "Shasp is a very simple programming language that outputs shasambly bytecode."

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":shake:shasambly:shasambly"))
        implementation(project(":shake:shasambly:shastools"))
        testImplementation(kotlin("test"))
    }
}

val projectName = name
tasks.named<Jar>("jvmJar") {
    archiveBaseName.set("shake-$projectName")
}
tasks.named<Jar>("jsJar") {
    archiveBaseName.set("shake-$projectName")
}
//tasks.named<Jar>("metadataJar") {
//    archiveBaseName.set("shake-$projectName")
//}