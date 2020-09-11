plugins {
    kotlin("js") version "1.4.10"
}

version = "1.0"

repositories {
    jcenter()
    maven( url = "https://kotlin.bintray.com/kotlin-js-wrappers")
}

kotlin {
    js {
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
        }
    }
}

task<Sync>("publish") {
    dependsOn("browserDistribution")
    from("$buildDir/distributions")
    into("$rootDir/docs")
}
