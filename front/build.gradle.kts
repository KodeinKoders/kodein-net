plugins {
    kotlin("js")
    id("org.ajoberstar.git-publish") version "3.0.0"
    id("org.ajoberstar.grgit") version "4.1.0"
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "kodein-net.js"
            }
        }
        nodejs()
        useCommonJs()

        sourceSets["main"].dependencies {
            val reactVersion = "17.0.0"
            val reactRouterVersion = "5.2.0"
            val styledVersion = "5.2.0"
            val kotlinWrapperVersion = "pre.130-kotlin-1.4.21"

            api("org.jetbrains:kotlin-react-dom:$reactVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-react-router-dom:$reactRouterVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-styled:$styledVersion-$kotlinWrapperVersion")

//            implementation(npm("styled-components", "4.4.1"))
        }
    }
}

val auth = (project.findProperty("com.github.http.auth") as? String)?.split(":")
if (auth != null) {
    System.setProperty("org.ajoberstar.grgit.auth.username", auth[0])
    System.setProperty("org.ajoberstar.grgit.auth.password", auth[1])
}

gitPublish {
    repoUri.set("https://github.com/KodeinKoders/next-kodein-net.git")
    branch.set("gh-pages")
    contents.apply {
        from("$projectDir/ssr/build/distributions")
    }
    val head = grgit.head()
    commitMessage.set("${head.abbreviatedId}: ${head.fullMessage}")
}

task("deployWebsiteToGithubPages") {
    group = "publishing"
    dependsOn("gitPublishPush")
}

tasks["gitPublishCopy"].dependsOn(":front:ssr:browserDistribution")

tasks["gitPublishCommit"].doFirst {
    if (!grgit.status().isClean) {
        error("Refusing to commit new pages on a non-clean repo. Please commit first.\n${grgit.status()}")
    }
}
