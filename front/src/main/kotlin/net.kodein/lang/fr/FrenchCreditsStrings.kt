package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.credits.CreditsStrings
import react.dom.a
import react.dom.b
import react.dom.br
import react.dom.p


object FrenchCreditsStrings : CreditsStrings {
    override val cover = object : CoverStrings {
        override val overTitle: TextHandler = { +"un super projet" }
        override val title: TextHandler = { +"On a collaboré avec des super artistes !" }
        override val chapo: TextHandler = {
            p {
                +"Le design de ce site web à été créé par le très talentueux "
                a(href = "https://www.crc-studio.fr/") {
                    +"Rémi B. Loizeau"
                }
                +"."
            }
            br {}
            p {
                +"Les magnifiques illustrations viennent de l'imagination sans limites de "
                a(href = "https://www.behance.net/using-art") {
                    +"Thimothée Boubay"
                }
                +"."
            }
        }
    }

    override val technicalCredits: TextHandler = {
        b { +"Crédits techniques:" }
        br {}
        +"Ce site web est écrit en "
        a(href = "https://kotlinlang.org/docs/reference/js-overview.html", target = "_blank") { +"Kotlin/JS" }
        +" avec les "
        a(href = "https://github.com/JetBrains/kotlin-wrappers", target = "_blank") {
            +"Kotlin/React wrappers"
        }
        +", qui utilisent "
        a(href = "https://reactjs.org", target = "_blank") {
            +"ReactJS"
        }
        +" et "
        a(href = "https://styled-components.com", target = "_blank") {
            +"Styled Components"
        }
        +"."
        br {}
        +"Il est hébergé par "
        a(href = "https://pages.github.com", target = "_blank") {
            +"Github pages"
        }
        +"."
        br {}
        +"Le formulaire de contacts utilise "
        a(href = "https://cloud.google.com/functions", target = "_blank") {
            +"Google Cloud Functions"
        }
        +" et "
        a(href = "https://sendgrid.com", target = "_blank") {
            +"Sendgrid"
        }
        +"."
    }
}
