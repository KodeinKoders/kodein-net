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
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Kodein Framework" }
        override val title: TextHandler = {
            +"Découvrez la puissance de"
            br {}
            +"nos outils Open Source."
        }
        override val chapo: TextHandler = {
            +"""
                Le saviez-vous ? Kodein Koders est le créateur de la toute première librairie communautaire Open Source pour Kotlin/Multiplatform.
                Nous sommes toujours à l'affut de nouvelles manière de contribuer à l'histoire du multiplatformes !
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
    override val layerKX_Serialization: String = "Serialisation"
    override val layerKX_Platform: String = "APIs des plateformes"
    override val layerLow: TextHandler = { +"Android, iOS & Web bas niveau" }

    override val wantMore: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Plus de contenus ?" }
        override val title: TextHandler = {
            +"Rendez vous sur notre "
            a(href = "https://github.com/Kodein-Framework", target = "_blank") { +"Github" }
            +"."
        }
        override val chapo: TextHandler = {
            +"Questionnez nous sur le "
            b { +"KODEIN" }
            +"Framework Open Source sur "
            a(href = "https://stackoverflow.com/tags/kodein", target = "_blank") { +"Stack Overflow" }
            +", "
            a(href = "https://kotlinlang.slack.com/archives/C0BLU9K96", target = "_blank") { +"Slack" }
            +" ou "
            a(href = "https://twitter.com/KodeinKoders", target = "_blank") { +"Twitter" }
            +" !"
        }
    }

}
