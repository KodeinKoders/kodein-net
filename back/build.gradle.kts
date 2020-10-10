import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

kotlin.target.compilations.all {
    kotlinOptions.jvmTarget = "11"
}

val invoker by configurations.creating

dependencies {
    compileOnly("com.google.cloud.functions:functions-framework-api:1.0.2")
    invoker("com.google.cloud.functions.invoker:java-function-invoker:1.0.0")

    implementation("com.sendgrid:sendgrid-java:4.0.1")
}

tasks.named<ShadowJar>("shadowJar") {
    mergeServiceFiles()
    minimize()

    (this as org.gradle.jvm.tasks.Jar).apply {
        archiveBaseName.set("kodein-net-back")
        archiveClassifier.set("")
        archiveVersion.set("")
    }
}

fun getSgKey(): String {
    val sgKeyFile = rootDir.resolve("sendgrid.key")
    if (!sgKeyFile.exists()) error("Please add a sendgrid.key file to the root of the project containing Sendgrid API KEY")
    return sgKeyFile.readText().trim()
}

val runFunction by tasks.creating(JavaExec::class) {
    doFirst {
        environment("SENDGRID_API_KEY", getSgKey())
    }

    dependsOn(kotlin.target.compilations["main"].compileAllTaskName)
    group = "run"
    classpath(invoker)
    main = "com.google.cloud.functions.invoker.runner.Invoker"
    inputs.files(configurations.runtimeClasspath, kotlin.target.compilations["main"].output)
    args(
            "--target", "net.kodein.Main",
            "--port", 8082
    )
    doFirst {
        args("--classpath", files(configurations.runtimeClasspath, kotlin.target.compilations["main"].output).asPath)
    }
}

val deployFunctionToGoogleCloud by tasks.creating(Exec::class) {
    group = "publishing"
    dependsOn("shadowJar")

    doFirst {
        args(
                "--project=kodein-net-website",
                "functions", "deploy",
                "kodein-net-back",
                "--entry-point=net.kodein.Main",
                "--runtime=java11",
                "--trigger-http", "--allow-unauthenticated",
                "--set-env-vars", "SENDGRID_API_KEY=${getSgKey()}",
                "--source=build/libs"
        )
    }

    workingDir = projectDir
    executable = "gcloud"
}
