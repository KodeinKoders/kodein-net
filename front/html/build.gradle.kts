import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsExec

plugins {
    kotlin("js")
}

kotlin {
    js {
        nodejs()
        useCommonJs()
    }
}

val nodeExecutable = rootProject.extensions
        .getByType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>()
        .requireConfigured().nodeExecutable

val mainCompilation = kotlin.js().compilations["main"]

val generateSsrHtml by tasks.creating(Exec::class) {
    group = "kotlin node"

    executable = nodeExecutable

    val outputFile = file("$buildDir/generated/ssr/index.html")
//    outputs.file(outputFile)

    dependsOn(mainCompilation.compileKotlinTask)
    args(mainCompilation.compileKotlinTask.outputFile.absolutePath, "ssr")

    doFirst {
        outputFile.parentFile.mkdirs()
        standardOutput = outputFile.outputStream()
    }
}

val generateBareHtml by tasks.creating(Exec::class) {
    group = "kotlin node"

    executable = nodeExecutable

    val outputFile = file("$buildDir/generated/bare/index.html")
//    outputs.file(outputFile)

    dependsOn(mainCompilation.compileKotlinTask)
    args(mainCompilation.compileKotlinTask.outputFile.absolutePath, "bare")

    doFirst {
        outputFile.parentFile.mkdirs()
        standardOutput = outputFile.outputStream()
    }
}

dependencies {
    implementation(project(":front"))
}

// https://github.com/Kotlin/kotlinx.html/issues/159#issuecomment-706689630
tasks.withType<org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile> {
    doLast {
        val content = outputFile.readText()
        outputFile.writer().buffered().use {
            it.write("if (typeof(HTMLLegendElement) == 'undefined') HTMLLegendElement = {};\n")
            it.write(content)
        }
    }
}
