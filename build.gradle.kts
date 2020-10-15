buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.google.cloud.tools:appengine-gradle-plugin:2.2.0")
    }
}

allprojects {
    repositories {
        jcenter()
        maven( url = "https://kotlin.bintray.com/kotlin-js-wrappers")
    }
}
