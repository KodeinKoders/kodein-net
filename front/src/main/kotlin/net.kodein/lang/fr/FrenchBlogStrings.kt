package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.blog.BlogStrings
import react.dom.a
import react.dom.br
import react.dom.span

object FrenchBlogStrings : BlogStrings {

    override val pageTitle: String = "Blog"

    override val pageDescription = "Les articles et vidéos que nous avons posté."

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"plaisirs partagés" }
        override val title: TextHandler = {
            +"Des experts passionnés,"
            br {}
            +"au service de la technologie."
        }
        override val chapo: TextHandler = {
            +"Notre mission est de partager notre passion pour le développement multiplateforme."

            br {}
            br {}

            +"""Lorsque nous acquérons de nouvelles expériences, découvrons de nouveaux horizons,
                |ou créons des outils, nous écrivons un article """.trimMargin()
            span("nowrap") { +" ou tournons une vidéo." }

            br {}

            +"Jetez-y un oeil !"
        }
    }

    override val wantMore: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Plus de contenus ?" }
        override val title: TextHandler = {
            +"Rendez vous sur notre "
            a(href = "https://medium.com/kodein-koders", target = "_blank") { attrs.rel = "noopener" ; +"page Medium" }
            br {}
            +" ou sur notre "
            a(href = "https://www.youtube.com/channel/UCbTrSXEB8flZ3XM9t3j4D8A", target = "_blank") { attrs.rel = "noopener" ; +"chaine Youtube" }
            +"."
        }
        override val chapo: TextHandler = {
            +"Suivez notre actualité "
            span("nowrap") { +"via les réseaux sociaux." }
            br {}
            +"Retrouvez-nous sur "
            span("nowrap") {
                a(href = "https://twitter.com/KodeinKoders") { +"Twitter" }
                +" & "
                a(href = "https://www.linkedin.com/company/kodein") { +"LinkedIn" }
                +" !"
            }
        }
    }

    override val errorLoadingFeed: String = "Il y a eu une erreur pendant le chargement des données :("
}