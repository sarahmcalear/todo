import com.moowork.gradle.node.yarn.YarnTask

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.github.node-gradle:gradle-node-plugin:2.2.4")
    }
}

plugins {
    base
    id("com.github.node-gradle.node") version "2.2.4"
}

node {
    version = "13.8.0"
    yarnVersion = "1.22.4"

    download = true
}

tasks.named<YarnTask>("yarn_run_build") {
    inputs.files(fileTree("public"))
    inputs.files(fileTree("src"))

    inputs.file("package.json")
    inputs.file("yarn.lock")

    outputs.dir("build")
}

val packageWebApp by tasks.registering(Jar::class) {
//    dependsOn("yarn_run_lint")
    dependsOn("yarn_run_build")
    baseName = "web"
    extension = "jar"
    destinationDir = file("${projectDir}/build_packageWebApp")
    from("build") {
        into("static")
    }
}

val yarnResources by configurations.creating

configurations.named("default").get().extendsFrom(yarnResources)

artifacts {
    add(yarnResources.name, packageWebApp.get().archivePath) {
        builtBy(packageWebApp)
        type = "jar"
    }
}

tasks.assemble {
    dependsOn(packageWebApp)
}

val test by tasks.registering(YarnTask::class) {
    dependsOn("assemble")

    setEnvironment(mapOf("CI" to "true"))
    args = listOf("run", "test")

    inputs.files(fileTree("src"))
    inputs.file("package.json")
    inputs.file("yarn.lock")
}

tasks.check {
    dependsOn(test)
}

tasks.clean {
    delete(packageWebApp.get().archivePath)
}
