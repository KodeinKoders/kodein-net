plugins {
    kotlin("js")
}

kotlin {
    js {
        browser {
            webpackTask { outputFileName = "kodein-net.js" }
            runTask { outputFileName = "kodein-net.js" }
        }
        useCommonJs()

        sourceSets["main"].dependencies {
            implementation(project(":front"))
        }
    }
}

(tasks[kotlin.js().compilations["main"].processResourcesTaskName] as ProcessResources).apply {
    dependsOn(":front:html:generateSsrHtml")
    from("$rootDir/front/html/build/generated/ssr")
    from("$rootDir/front/src/main/resources")
}
