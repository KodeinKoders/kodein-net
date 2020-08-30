plugins {
    kotlin("js") version "1.4.0"
}

version = "1.0"

repositories {
    jcenter()
    maven( url = "https://kotlin.bintray.com/kotlin-js-wrappers")
}

kotlin {
    target {
        browser()
        useCommonJs()

        sourceSets["main"].dependencies {
            implementation(kotlin("stdlib-js"))

            val reactVersion = "16.13.1"
            val reactRouterVersion = "5.1.2"
            val kotlinWrapperVersion = "pre.112-kotlin-1.4.0"

            implementation("org.jetbrains:kotlin-react-dom:$reactVersion-$kotlinWrapperVersion")
            implementation("org.jetbrains:kotlin-react-router-dom:$reactRouterVersion-$kotlinWrapperVersion")
            implementation("org.jetbrains:kotlin-styled:1.0.0-$kotlinWrapperVersion")

//            implementation(npm("react", "^$reactVersion"))
//            implementation(npm("react-dom", "^$reactVersion"))
//            implementation(npm("react-router", "^$reactRouterVersion"))
//            implementation(npm("react-router-dom", "^$reactRouterVersion"))
//
//            implementation(npm("css-in-js-utils", "3.0.2"))
//            implementation(npm("inline-style-prefixer", "5.1.0"))
//            implementation(npm("styled-components", "4.3.2"))
//            implementation(npm("core-js", "3.2.0"))
        }
    }
}