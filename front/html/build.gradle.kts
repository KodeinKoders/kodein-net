plugins {
    kotlin("js")
}

kotlin {
    js {
        nodejs()
        useCommonJs()
    }
}

dependencies {
    implementation(project(":front"))
    implementation("org.jetbrains.kotlinx:kotlinx-nodejs:0.0.7")
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

val nodeExecutable = rootProject.extensions
        .getByType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>()
        .requireConfigured().nodeExecutable

val mainCompilation = kotlin.js().compilations["main"]

val generateSsrHtml by tasks.creating(Exec::class) {
    group = "kotlin node"
    dependsOn(mainCompilation.compileKotlinTask)
    executable = nodeExecutable
    args(mainCompilation.compileKotlinTask.outputFile.absolutePath, "$buildDir/generated/ssr", "ssr")
    doFirst { file("$buildDir/generated/ssr").mkdirs() }
}

val generateBareHtml by tasks.creating(Exec::class) {
    group = "kotlin node"
    dependsOn(mainCompilation.compileKotlinTask)
    executable = nodeExecutable
    args(mainCompilation.compileKotlinTask.outputFile.absolutePath, "$buildDir/generated/bare", "bare")
    doFirst { file("$buildDir/generated/bare").mkdirs() }
}
