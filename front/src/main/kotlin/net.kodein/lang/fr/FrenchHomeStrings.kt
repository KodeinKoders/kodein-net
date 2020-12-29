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

    override val advisoryDepartment = HomeStrings.TitledContent("ACCOMPAGNEMENT") {
        +"..." // TODO
    }
    override val trainingDepartment = HomeStrings.TitledContent("FORMATION") {
        +"..." // TODO
    }
    override val developmentDepartment = HomeStrings.TitledContent("DÉVELOPPEMENT") {
        +"..." // TODO
    }

    override val readMore: String = "EN SAVOIR PLUS"

    override val kodeinKodersDescription = HomeStrings.TitledContent("Qui sont les Kodein Koders?") {
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

    override val humanistDescription = HomeStrings.TitledContent("Travailler pour les Humains") {
        p {
            +"""
                Plus que les entreprises, nous travaillons pour et avec des personnes.
                Nous sommes fiers des partenariats que nous avons créé avec chacun d'entre eux.
            """
            br {}
            +"""
                Nous promouvons les valeures humanistes qui régissent notre travail et nos relations, aussi bien chez Kodein Koders qu'avec nos clients.
            """
        }
        p {
            +"""
                Que ça soit dans nos formations ou nos services de consulting, nous approchons chaque mission d'abord par l'humain :
                Comment pouvons nous communiquer éfficacement ? Quelle est la meilleure manière de vous aider à progresser ?
            """
        }
        p {
            +"""
                Les humains changent d'avis, et leur vision ne cesse d'évoluer.
                Nous sommes fièrs de promouvoir les méthodes agiles pour vous aider à former votre propre chemin.
            """
        }
    }

    override val openSourceDescription = HomeStrings.TitledContent("Kodein Framework: L'Open Source au cœur de nos travaux") {
        p {
            +"""
                ...
            """
        }
    }

    override val trainingDescription = HomeStrings.TitledContent("Formation : Certifié par Jetbrains") {
        p {
            +"""
                ...
            """
        }
    }

    override val humansTitle = "Des humains nous font confiance !"
    override val humansSubtitle = "Pourquoi pas vous ?"
}
