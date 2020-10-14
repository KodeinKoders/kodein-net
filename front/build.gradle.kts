plugins {
    kotlin("js")
}

kotlin {
    js {
        browser {
            webpackTask { outputFileName = "kodein-net.js" }
            runTask { outputFileName = "kodein-net.js" }
        }
        nodejs()
        useCommonJs()

        sourceSets["main"].dependencies {
            val reactVersion = "16.13.1"
            val reactRouterVersion = "5.1.2"
            val styledVersion = "5.2.0"
            val kotlinWrapperVersion = "pre.124-kotlin-1.4.10"

            api("org.jetbrains:kotlin-react-dom:$reactVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-react-router-dom:$reactRouterVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-styled:$styledVersion-$kotlinWrapperVersion")

//            implementation(npm("styled-components", "4.4.1"))
        }
    }
}
