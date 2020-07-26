defaultTasks("build")

tasks.wrapper {
    description = "Regenerates the Gradle Wrapper Files"
    gradleVersion = "6.5.1"
    distributionUrl = "https://services.gradle.org/distributions/gradle-${gradleVersion}-all.zip"
}