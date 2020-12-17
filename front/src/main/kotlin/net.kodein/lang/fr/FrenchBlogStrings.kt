package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.blog.BlogStrings
import react.dom.a
import react.dom.br

object FrenchBlogStrings : BlogStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"lisez & regardez" }
        override val title: TextHandler = {
            +"Nous aimons la technologie,"
            br {}
            +"et nous voulons le partager avec vous."
        }
        override val chapo: TextHandler = {
            +"""Nous sommes des experts passionnés. 
                |Notre mission est de propager notre passion pour le développement multi-plateforme.
                |Lorsque nous acquierons de nouvelles expériences, découvrons de nouveaux horizons,
                |créons une librairie, ou simplement que nous voulons partager notre passion,
                |nous écrivons un article ou tournons une vidéo.
                |Jetez-y un oeil!""".trimMargin()
        }
    }

    override val wantMore: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Plus de contenus?" }
        override val title: TextHandler = {
            +"Rendez vous sur notre "
            a(href = "https://medium.com/kodein-koders", target = "_blank") { +"page Medium" }
            +"."
        }
        override val chapo: TextHandler = {
            +"""Suivez notre actualité via les réseaux sociaux."""
            br {}
            +"""Retrouvez-nous sur """
            a(href = "https://twitter.com/KodeinKoders") { +"Twitter" }
            +" & "
            a(href = "https://www.linkedin.com/company/kodein") { +"LinkedIn" }
            +"!"
        }
    }

    override val errorLoadingFeed: String = "Il y a eu une erreur pendant le chargement des données :("
}