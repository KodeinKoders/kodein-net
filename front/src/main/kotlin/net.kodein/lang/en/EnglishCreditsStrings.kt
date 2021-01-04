package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.credits.CreditsStrings
import react.dom.a
import react.dom.b
import react.dom.br
import react.dom.p


object EnglishCreditsStrings : CreditsStrings {

    override val pageTitle: String = "Credits"

    override val cover = object : CoverStrings {
        override val overTitle: TextHandler = { +"a great project" }
        override val title: TextHandler = { +"We collaborated with great artists!" }
        override val chapo: TextHandler = {
            p {
                +"This web site's design has been created by the very talented "
                a(href = "https://www.crc-studio.fr/") {
                    +"Rémi B. Loizeau"
                }
                +"."
            }
            br {}
            p {
                +"The beautiful illustrations are from the limitless imagination of "
                a(href = "https://www.behance.net/using-art") {
                    +"Thimothée Boubay"
                }
                +"."
            }
        }
    }

    override val technicalCredits: TextHandler = {
        b { +"Technical credits:" }
        br {}
        +"This web site is written in "
        a(href = "https://kotlinlang.org/docs/reference/js-overview.html", target = "_blank") {
            attrs.rel = "noopener"
            +"Kotlin/JS"
        }
        +" using the "
        a(href = "https://github.com/JetBrains/kotlin-wrappers", target = "_blank") {
            attrs.rel = "noopener"
            +"Kotlin/React wrappers"
        }
        +", which uses "
        a(href = "https://reactjs.org", target = "_blank") {
            attrs.rel = "noopener"
            +"ReactJS"
        }
        +" and "
        a(href = "https://styled-components.com", target = "_blank") {
            attrs.rel = "noopener"
            +"Styled Components"
        }
        +"."
        br {}
        +"It is hosted on "
        a(href = "https://pages.github.com", target = "_blank") {
            attrs.rel = "noopener"
            +"Github pages"
        }
        +"."
        br {}
        +"The contact form uses "
        a(href = "https://cloud.google.com/functions", target = "_blank") {
            attrs.rel = "noopener"
            +"Google Cloud Functions"
        }
        +" and "
        a(href = "https://sendgrid.com", target = "_blank") {
            attrs.rel = "noopener"
            +"Sendgrid"
        }
        +"."
    }
}
