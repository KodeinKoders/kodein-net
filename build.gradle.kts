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
            val styledVersion = "5.2.0"
            val kotlinWrapperVersion = "pre.118-kotlin-1.4.10"

            api("org.jetbrains:kotlin-react-dom:$reactVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-react-router-dom:$reactRouterVersion-$kotlinWrapperVersion")
            implementation("org.jetbrains:kotlin-styled:$styledVersion-$kotlinWrapperVersion")
        }
    }
}

task<Sync>("publish") {
    dependsOn("browserDistribution")
    from("$buildDir/distributions")
    into("$rootDir/docs")
}
