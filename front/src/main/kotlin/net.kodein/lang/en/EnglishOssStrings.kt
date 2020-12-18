package net.kodein.lang.en

import kotlinx.css.FontWeight
import kotlinx.css.fontWeight
import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.oss.OssStrings
import net.kodein.utils.semiBold
import react.dom.a
import react.dom.b
import react.dom.br
import styled.styledB


object EnglishOssStrings : OssStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Kodein Framework" }
        override val title: TextHandler = {
            +"Discover our powerful"
            br {}
            +"Open Source Software."
        }
        override val chapo: TextHandler = {
            +"""
                Did you know? Kodein Koders released the very first Open Source Kotlin/Multiplatform community library.
                We are always looking for new ways to contribute to the multiplatform narrative!
            """
        }
    }

    override val layerKodein: TextHandler = {
        +"The "
        styledB {
            css.fontWeight = FontWeight.semiBold
            +"KODEIN"
        }
        +"Framework"
        br {}
        +"focuses on Application Business Code."
    }
    override val layerUI: TextHandler = { +"Platform specific UI" }
    override val layerKF_DI: String = "Dependency Injection"
    override val layerKF_DB: String = "Embedded Database"
    override val layerKF_Log: String = "Logging & Reporting"
    override val layerKF_MVI: String = "Presentation Behaviour"
    override val layerKX_Coroutines: String = "Coroutines"
    override val layerKX_Atomic: String = "Atomic Operations"
    override val layerKX_Serialization: String = "Serialization"
    override val layerKX_Platform: String = "Platform APIs"
    override val layerLow: TextHandler = { +"Low-level Android, iOS & Web" }

    override val onionTitle: TextHandler = {
        +"The "
        styledB {
            css.fontWeight = FontWeight.semiBold
            +"KODEIN"
        }
        +"Framework"
        br {}
        +"empowers multiplatform applications."
    }
    override val onionLayerAndroid: TextHandler = {
        +"""
            Kotlin is the official main development language for Android since 2019.
            Both Kotlin/Multiplatform & the Kodein Framework integrate natively with Android.
            Multiplatform Android apps are really just Android apps, with native look, feel and integration.
        """
    }
    override val onionLayerDesktop: TextHandler = {
        +"""
            When you need the full power of a computer, Kotlin & Kodein have you covered.
            All the Kodein Open Sources tools we develop are also compatible with all desktop JVMs (Linux, MacOS, Windows).
        """
    }
    override val onionLayerIos: TextHandler = {
        +"""
            The Kotlin language is not only close to Swift (the native iOS language), it also integrates nicely with it.
            The Kodein Framework provides tools that are easy to use both in a Multiplatform shared library, and in the context of an iOS application.
            Multiplatform iOS apps are really just Android apps, with native look, feel and integration.
        """
    }
    override val onionLayerJs: TextHandler = {
        +"""
            Javascript was the second major platform that Kotlin targetted (as soon as version 1.1).
            The Kotlin team provides a lot of tools to make shared business code available easily as JS libraries.
        """
    }
    override val onionLayerJvm: TextHandler = {
        +"""
            The Java Virtual Machine is the historical target of the first Kotlin compiler.
            By targeting the JVM, Kotlin not only ensures compatibility with Android, desktop and server JVMs;
            it also benefits from a decade of opimization, security research & stability improvements.
        """
    }
    override val onionLayerKodein: TextHandler = {
        +"""
            The Kodein Framework provides a set of high level components allowing you to focus on your application business.
            It is opinionated as it represents years of best practices in mobile, web & desktop application development.
            Being entirely Open Source, it is used in many Kotlin applications by a thriving community.
        """
    }
    override val onionLayerKotlin: TextHandler = {
        +"""
            Kotlin is the lingua franca of multiplatform development.
            Jetbrains' KotlinX brings lots of multiplatform libraries needed for low-level work, such as concurrency, atomicity, serialization, etc.
            The Kodein Framework re-uses as much of these primitives as possible, integrating itself into the Kotlin/Multiplatform ecosystem.
        """
    }
    override val onionLayerNative: TextHandler = {
        +"""
            Kotlin/Native is the Kotlin compiler that produces native executable for multiple targets (iOS being just one of them).
            While being cutting edge, many large companies are already using Kotlin/Native to compile shared business code.
        """
    }
    override val onionLayerWeb: TextHandler = {
        +"""
            The web is a platform that is often overlooked, when it shouldn't.
            Targeting the web means targeting millions of users that have access to the web but not to an up to date smartphone.
            It also allows you app's content to be indexed by search engines.
        """
    }

    override val wantMore: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Want more?" }
        override val title: TextHandler = {
            +"Check our "
            a(href = "https://github.com/Kodein-Framework", target = "_blank") { +"Github" }
            +"."
        }
        override val chapo: TextHandler = {
            +"Ask us anything about the Open Source "
            b { +"KODEIN" }
            +"Framework on "
            a(href = "https://stackoverflow.com/tags/kodein", target = "_blank") { +"Stack Overflow" }
            +", "
            a(href = "https://kotlinlang.slack.com/archives/C0BLU9K96", target = "_blank") { +"Slack" }
            +" or "
            a(href = "https://twitter.com/KodeinKoders", target = "_blank") { +"Twitter" }
            +"!"
        }
    }
}
