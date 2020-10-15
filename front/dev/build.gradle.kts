plugins {
    kotlin("js")
}

kotlin {
    js {
        browser {
            webpackTask { doFirst { error("This is for dev only!") } }
            runTask { outputFileName = "kodein-net.js" }
        }
        useCommonJs()

        sourceSets["main"].dependencies {
            implementation(project(":front"))
        }
    }
}

(tasks[kotlin.js().compilations["main"].processResourcesTaskName] as ProcessResources).apply {
    dependsOn(":front:html:generateBareHtml")
    from("$rootDir/front/html/build/generated/bare")
    from("$rootDir/front/src/main/resources")
}
