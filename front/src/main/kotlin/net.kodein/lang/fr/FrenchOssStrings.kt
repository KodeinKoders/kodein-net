package net.kodein.lang.fr

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


object FrenchOssStrings : OssStrings {

    override val pageTitle: String = "Open Source"

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Kodein Framework" }
        override val title: TextHandler = {
            +"Découvrez la puissance de"
            br {}
            +"nos outils Open Source."
        }
        override val chapo: TextHandler = {
            +"""
                Le saviez-vous ? Kodein Koders est le créateur de la toute première librairie communautaire Open Source, pour Kotlin/Multiplatform.
                Nous sommes toujours à l'affût de nouvelles manières de contribuer à l'histoire du multiplateforme !
            """
        }
    }

    override val layerKodein: TextHandler = {
        +"Le "
        styledB {
            css.fontWeight = FontWeight.semiBold
            +"KODEIN"
        }
        +"Framework"
        br {}
        +"se concentre sur le code Métier Applicatif."
    }
    override val layerUI: TextHandler = { +"UI spécifique à la plateforme" }
    override val layerKF_DI: String = "Injection de Dépendance"
    override val layerKF_DB: String = "Base de Données"
    override val layerKF_Log: String = "Logs & Rapports"
    override val layerKF_MVI: String = "Conduite des vues"
    override val layerKX_Coroutines: String = "Coroutines"
    override val layerKX_Atomic: String = "Opérations Atomiques"
    override val layerKX_Serialization: String = "Sérialisation"
    override val layerKX_Platform: String = "APIs des plateformes"
    override val layerLow: TextHandler = { +"Android, iOS & Web bas niveau" }

    override val onionTitle: TextHandler = {
        +"Le "
        styledB {
            css.fontWeight = FontWeight.semiBold
            +"KODEIN"
        }
        +"Framework"
        br {}
        +"facilite la création d'applications multiplateformes."
    }
    override val onionLayerAndroid: TextHandler = {
        +"""
            Kotlin est le langage de développement officiel pour Android depuis 2019.
            Kotlin/Multiplatform et le Kodein Framework s'intègrent nativement dans Android.
            Les apps multiplateformes Android ne sont en réalité que des apps Android, avec une UI et une intégration native.
        """
    }
    override val onionLayerDesktop: TextHandler = {
        +"""
            Si vous avez besoin de la puissance d'un ordinateur, Kotlin et le Kodein Framework sont là pour vous.
            Tous les outils Open Source du Kodein Framework que nous développons sont également compatibles avec les JVMs "desktop" (Linux, MacOS, Windows).
        """
    }
    override val onionLayerIos: TextHandler = {
        +"""
            Le langage Kotlin est non seulement proche de Swift (le langage iOS natif), il s'y intègre aussi aisément.
            Le Kodein Framework fournit des outils faciles à utiliser aussi bien dans une librairie multiplateforme, que dans le contexte d'une app iOS.
            Les apps multiplatformes iOS ne sont en réalité que des apps iOS, avec une UI et une intégration native.
        """
    }
    override val onionLayerJs: TextHandler = {
        +"""
            Javascript fut la deuxième plateforme majeure ciblée par Kotlin (dès la version 1.1).
            L'équipe Kotlin fournit un ensemble d'outils permettant de partager facilement du code métier avec JavaScript.
        """
    }
    override val onionLayerJvm: TextHandler = {
        +"""
            La "Java Virtual Machine" est la cible historique du premier compilateur Kotlin.
            Grâce à la JVM, Kotlin assure non seulement la compatibilité avec Android et les JVMs "desktop" ou serveur ;
            mais profite également de plus d'une dizaine d'années d'opimisation, de recherche en sécurité, et d'amélioration de stabilité.
        """
    }
    override val onionLayerKodein: TextHandler = {
        +"""
            Le Kodein Framework fournit un ensemble de composants haut niveau vous permettant de vous concentrer sur le métier de votre application.
            Il représente notre opinion, ainsi que des années de bonnes pratiques en développement applicatif mobile, web, et logiciel.
            Entièrement Open Source, il est utilisé dans de nombreuses applications Kotlin par une communauté grandissante.
        """
    }
    override val onionLayerKotlin: TextHandler = {
        +"""
            Le langage Kotlin est la "lingua franca" du développement multiplateforme.
            KotlinX de Jetbrains propose de nombreuses librairies multiplateformes nécéssaires au développement bas niveau, comme la concurrence, l'atomicité, la sérialisation, etc.
            Le Kodein Framework réutilise autant que possible ces primitives, s'intégrant ainsi nativement dans l'écosystème Kotlin/Multiplatform.
        """
    }
    override val onionLayerNative: TextHandler = {
        +"""
            Kotlin/Native est le compilateur Kotlin qui produit des exécutables natifs pour de multiples cibles (iOS étant l'une d'entre elles).
            Bien qu'étant une toute nouvelle technologie, de nombreuses grandes entreprises utilisent déjà Kotlin/Native pour compiler du code métier commun.
        """
    }
    override val onionLayerWeb: TextHandler = {
        +"""
            le web est une plateforme souvent négligée, à tort.
            Cibler le web signifie cibler des millions d'utilisateurs ayant accès au web mais non à un smartphone moderne.
            Cela permet également au contenu de votre app d'être indexé par les moteurs de recherche.
        """
    }

    override val wantMore: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Plus de contenus ?" }
        override val title: TextHandler = {
            +"Rendez vous sur notre "
            a(href = "https://github.com/Kodein-Framework", target = "_blank") { attrs.rel = "noopener" ; +"Github" }
            +"."
        }
        override val chapo: TextHandler = {
            +"Questionnez nous sur le "
            b { +"KODEIN" }
            +"Framework Open Source "
            br {}
            +"sur "
            a(href = "https://stackoverflow.com/tags/kodein", target = "_blank") { attrs.rel = "noopener" ; +"Stack Overflow" }
            +", "
            a(href = "https://kotlinlang.slack.com/archives/C0BLU9K96", target = "_blank") { attrs.rel = "noopener" ; +"Slack" }
            +" ou "
            a(href = "https://twitter.com/KodeinKoders", target = "_blank") { attrs.rel = "noopener" ; +"Twitter" }
            +" !"
        }
    }

}
