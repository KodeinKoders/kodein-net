package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.pages.home.HomeStrings
import react.dom.br
import react.dom.p
import react.dom.span

object FrenchHomeStrings : HomeStrings {

    override val title: TextHandler = {
        +"Où que "
        span("nowrap") { +"Kotlin aille," }
        br {}
        +"vous trouverez"
        br {}
        +"les experts "
        span("nowrap") { +"qu'il vous faut!" }
    }

    override val subTitle: TextHandler = {
        +"Nous sommes Kodein Koders, "
        br("mobile") {}
        +"une startup tech "
        br("desktop") {}
        +"motivée par "
        br("mobile") {}
        +"nos idées pour le multi-platformes "
        br("desktop") {}
        +"et notre passion "
        span("nowrap") { +"pour l'état de l'art." }
    }

    override val scroll: String = "DÉFILER"

    override val advisory = HomeStrings.TitledContent("ACCOMPAGNEMENT") {
        +"..." // TODO
    }
    override val training = HomeStrings.TitledContent("FORMATION") {
        +"..." // TODO
    }
    override val development = HomeStrings.TitledContent("DÉVELOPPEMENT") {
        +"..." // TODO
    }

    override val readMore: String = "EN SAVOIR PLUS"

    override val kodeinKoders = HomeStrings.TitledContent("Qui sont les Kodein Koders?") {
        p {
            +"""
                Nous sommes experts et amoureux de technologie, motivés par notre passion pour Kotlin/Multiplatform.
            """
            br {}
            +"""
                Grâce à cette technologie, nous développons des applications complexes et partageons le code significatif entre les platformes,
                que ce soit le serveur backend (basé sur Spring ou Ktor) ou les multiples frontends (Android, iOS, Desktop, Web).
            """
        }
        p {
            +"""
                Nous créons les architectures d'applications mobiles multiplatformes, modernizons les systèmes existants,
                ou aidons les équipes à réaliser leur objectifs de développement avec Kotlin, où que vous en ayez besoin.
            """
        }
    }

    override val workForHumans = HomeStrings.TitledContent("Travailler pour les Humains") {
        p {
            +"""
                ...
            """
        }
    }

    override val oss = HomeStrings.TitledContent("Kodein Framework: L'Open Source au cœur de nos travaux") {
        p {
            +"""
                ...
            """
        }
    }

    override val trainings = HomeStrings.TitledContent("Formation : Certifié par Jetbrains") {
        p {
            +"""
                ...
            """
        }
    }
}
