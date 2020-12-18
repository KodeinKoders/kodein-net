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
