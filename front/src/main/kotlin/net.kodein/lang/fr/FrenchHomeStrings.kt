package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.pages.home.HomeStrings
import react.dom.b
import react.dom.br
import react.dom.p
import react.dom.span

object FrenchHomeStrings : HomeStrings {

    override val pageTitle: String = ""

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
        +"nos idées pour le multiplateforme "
        br("desktop") {}
        +"et notre passion "
        span("nowrap") { +"pour l'état de l'art." }
    }

    override val scroll: String = "DÉFILER"

    override val advisoryDepartment = HomeStrings.TitledContent("CONSEIL", "SERVICES DE CONSEIL") {
        p {
            +"Vous démarrez un projet ou vous souhaitez renforcer vos équipes "
            span("nowrap") { +"et votre expertise ?" }
        }
        br{}
        p {
            +"""
                Nous pouvons vous aider à valider et améliorer votre process de développement
                grâce à du conseil technique, d'architecture, ou de support en développement,
            """
            span("nowrap") { +"grâce à Kotlin." }
        }
    }
    override val trainingDepartment = HomeStrings.TitledContent("FORMATION", "PROGRAMMES DE FORMATION") {
        p {
            +"Vous voulez donner à votre équipe les outils pour être encore plus efficiente "
            span("nowrap") { +"avec Kotlin ?" }
        }
        br {}
        p {
            +"""
                Nous avons developpé un ensemble de formations permettant à une équipe technique 
                d'acquérir des connaissances pratiques en quelques jours.
            """.trimIndent()
        }
    }
    override val developmentDepartment = HomeStrings.TitledContent("DÉVELOPPEMENT", "DÉVELOPPEMENT DE PROJETS") {
        p {
            +"Vous êtes en manque de ressources pour réaliser "
            span("nowrap") { +"votre prochain projet ?" }
        }
        br {}
        p {
            +"Nous pouvons vous aider à atteindre vos objectifs en prenant en charge le développement de votre "
            span("nowrap") { +"application mobile," }
            +" pour Android et iOS, à l'aide de Kotlin/Multiplatform."
        }
    }

    override val kodeinKodersDescription = HomeStrings.TitledContent("Qui sont les Kodein Koders?", "NOS SERVICES") {
        p {
            +"""
                Nous sommes experts et amoureux de technologie, motivés par notre passion pour Kotlin/Multiplatform.
            """
            br {}
            +"""
                Grâce à cette technologie, nous développons des applications complexes et partageons le code significatif entre les plateformes,
                que ce soit le backend (basé sur Spring ou Ktor) ou les multiples frontends (Android, iOS, Desktop, Web).
            """
        }
        p {
            +"""
                Nous créons les architectures d'applications mobiles multiplateformes, modernisons les systèmes existants,
                ou aidons les équipes à réaliser leur objectifs de développement avec Kotlin, où que vous en ayez besoin.
            """
        }
    }

    override val humanistDescription = HomeStrings.TitledContent("Travailler pour les Humains", "NOTRE ÉQUIPE") {
        p {
            +"""
                Plus que les entreprises, nous travaillons pour et avec des personnes.
                Nous sommes fiers des partenariats que nous avons créé avec chacune d'entre elles.
            """
            br {}
            +"Nous promouvons les valeures humanistes qui régissent notre travail et nos relations, aussi bien chez "
            span("nowrap") { +"Kodein Koders" }
            +" qu'avec nos clients."
        }
        p {
            +"""
                Que ça soit dans nos formations ou nos services de consulting, nous approchons chaque mission d'abord par l'humain :
                Comment pouvons nous communiquer éfficacement ? Quelle est la meilleure manière de vous aider à progresser ?
            """
        }
        p {
            +"""
                Les humains changent d'avis et leur vision ne cesse d'évoluer.
                Nous sommes fiers de promouvoir les méthodes agiles pour vous aider à former votre propre chemin.
            """
        }
    }

    override val openSourceDescription = HomeStrings.TitledContent("Kodein Framework: L'Open Source au cœur de nos travaux", "NOS TRAVAUX OPEN-SOURCE") {
        p {
            +"Le "
            b { +"KODEIN" }
            +"Framework"
            +"""
                est le premier framework mobile Open-Source écrit spécifiquement pour Kotlin & Kotlin/Multiplatform.
                Il permet aux créateurs d'applications mobiles de se concentrer sur le métier et la qualité de leur application.
            """
        }

        p {
            +"""
                Nous sommes convaincus qu'open-sourcer les composants non critiques pour le métier ainsi que contribuer aux projets open-source existants mène à de meilleurs logiciels.
            """
        }
        p {
            +"""
                Kodein Koders est grandement investi dans l'utilisation et la contribution à des initiatives Open Source.
                Le langage Kotlin et sa runtime ainsi que le Kodein Framework : tout est ouvert !
            """
        }
    }

    override val trainingDescription = HomeStrings.TitledContent("Formation : Certifiée par Jetbrains", "NOS PROGRAMMES DE FORMATION") {
        p {
            +"""
                Le langage Kotlin, l'asynchronicité des coroutines, l'infrastructure Kotlin/Multiplatform, les spécificités de Kotlin/Native : essayer de tout comprendre peut être intimidant.
            """
        }
        p {
            b { +"KODEIN" }
            +"Koders"
            +"""
                est la première société Européenne à être certifiée par JetBrains pour ses formations Kotlin.
                Elle est maintenant le premier fournisseur de formations en Europe spécialisé sur les technologies Kotlin/Multiplatform. 
            """
        }
        p {
            +"""
                Nous affinons toujours sur mesure nos workshops de formation pour l'équipe concernée
                et utilisons un matériel pédagogique précis pour rendre les équipes efficientes en quelques jours.
            """
        }
    }

    override val humansTitle = "Des humains nous font confiance !"
    override val humansSubtitle = "Pourquoi pas vous ?"
}
