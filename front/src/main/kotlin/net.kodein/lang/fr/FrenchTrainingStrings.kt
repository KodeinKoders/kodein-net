package net.kodein.lang.fr

import kotlinx.css.height
import kotlinx.css.marginTop
import kotlinx.css.rem
import kotlinx.css.width
import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.training.TrainingStrings
import net.kodein.withBasePath
import react.dom.b
import react.dom.br
import react.dom.p
import react.dom.small
import styled.css
import styled.styledImg


object FrenchTrainingStrings : TrainingStrings {

    override val pageTitle: String = "Formation"

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"partageons le savoir" }
        override val title: TextHandler = {
            +"Nous sommes"
            br {}
            +"Formateurs Certifiés JetBrains."
            br {}
            withBasePath { path ->
                styledImg(src = "$path/imgs/badge-orange.svg", alt = "Logo certificaton") {
                    attrs {
                        width = "150"
                        height = "150"
                    }
                    css {
                        width = 10.rem
                        height = 10.rem
                        marginTop = 1.rem
                    }
                }
            }
        }
        override val chapo: TextHandler = {
            +"""
                Nous proposons des formations et workshops pour entreprises et évenements à travers le monde,
                comme les conférences Kotlin/Everywhere Paris et KotlinConf'19.
                Nous fournissons des formations sur les diffèrents niveaux & cibles de Kotlin.
            """
        }
    }

    override val descriptionTitle: TextHandler = {
        +"Votre équipe au plus haut niveau de qualité, productivité & cohérence."
    }

    override val descriptionText: TextHandler = {
        p {
            +"Parce-que chaque équipe est unique en sa composition, son historique, et ses objectifs, "
            b { +"chaque workshop est taillé sur mesure" }
            +" pour les besoins spécifiques de l'équipe addressée, rendant ainsi chaque formation "
            b { +"unique à son client" }
            +"."
        }
        p {
            +"""
                Grâce à notre expérience en pedagogie ainsi qu'en architecture et développement,
                nous avons developpé un ensemble de formations et d'exercises permettant à une équipe technique d'intégrer
            """
            b { +"des connaissances pratiques en quelques jours" }
            +"."
            br {}
            +"""
                Chaque ensemble de notions est toujours mis en perspective avec un exercice de programmation.
                Nous mettons l'accent sur la productivité et la qualité, développant non seulement les notions techniques, mais aussi et surtout leurs bonnes pratiques.
            """
        }
    }

    override val trainingTitle: TextHandler = { +"Voici quelques programmes de formations." }

    override val courseCoroutines = TrainingStrings.CourseStrings(
        "Concurence structurée avec les coroutines de Kotlin",
        "1 jour",
    ) {
        +"""
            Les coroutines permettent de modéliser des scénarios de concurrence complexes d'une manière expressive et structurée.
            Apprenez à modéliser une tâche concurrente, et à utiliser la puissance de la librairie KotlinX Coroutines (pour le scoping, l'annulation, etc).
        """
    }

    override val courseAdvanced = TrainingStrings.CourseStrings(
        "Kotlin avancé pour le développement de librarie et de SDK",
        "2 jours",
    ) {
        +"""
            Kotlin propose beaucoup de fonctionalités de languages avancés, typiquement utilisées par les développeurs de librairies, leur permettant de fournir aux développeurs applicatifs de puissants outils.
            Découvrez ces fonctionalités avancées, et maîtrisez les pour créer des APIs Kotlin élégantes et puissantes.
        """
    }

    override val courseIos = TrainingStrings.CourseStrings(
        "Le language Kotlin pour les développeurs iOS",
        "1 jour",
    ) {
        +"""
            La syntaxe Kotlin est très similaire à celle de Swift, mais leurs philosophies divergent sur plusieurs points.
            Cette formation est destinée aux développeurs Swift iOS qui souhaitent pouvoir intervenir sur un projet Kotlin/Multiplatform.
        """
    }

    override val courseKmm = TrainingStrings.CourseStrings(
        "KMM: Koltin Mobile Multiplatform pour Android & iOS",
        "2 jours",
    ) {
        +"""
            Partagez du code métier et comportemental significatif entre vos apps Android & iOS, tout en conservant leurs vues natives & spècifiques. Quel rêve !
            Apprenez comment implémenter ce rêve, comment architecturer votre application en conséquence, et comment communiquer proprement entre un noyeau partagé et une UI native.
        """
        small {
            +"""Nécéssite: "Concurence structurée avec les coroutines de Kotlin""""
            br {}
            + """et éventuellement "Le language Kotlin pour les développeurs iOS".""""
        }
    }

    override val courseNative = TrainingStrings.CourseStrings(
        "Koltin/Native bas niveau avancé",
        "1 jour",
    ) {
        +"""
            Kotlin/Native est le compilateur Kotlin qui produit des exécutables natifs pour de multiples cibles (iOS etant l'une d'entre elles).
            Ce compilateur offre des APIs Kotlin permettant au code natif d'accéder aux librairies C, à la mémoire bas niveau, et aux APIs systèmes de la plateforme (comme POSIX).
        """
        small { +"Nécéssite: \"KMM: Koltin Mobile Multiplatform pour Android & iOS\"." }
    }

    override val courseKtor = TrainingStrings.CourseStrings(
        "Développememt d'applications serveur en pur Kotlin",
        "1 jour",
    ) {
        +"""
            Apprenez comment créer un serveur HTTP concurrent, léger, en Kotlin pur avec le framework dédié de Jetbrains: Ktor.
        """
        small { +"Nécéssite: \"Concurence structurée avec les coroutines de Kotlin\"." }
    }

    override val courseReact = TrainingStrings.CourseStrings(
        "Kotlin/React pour le web",
        "1 jour",
    ) {
        +"""
            React est le framework du web, permettant de créer des composants et applications web efficaces.
            Cette formation est adressée à ceux qui souhaitent créer des interfaces web sans renoncer au langage structuré et sécurisé qu'ils pratiquent déjà : Kotlin!
        """
    }

    override val duration = "Durée"

}
