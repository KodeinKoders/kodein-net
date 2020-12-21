package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.JobDescription
import net.kodein.pages.team.MemberStrings
import net.kodein.pages.team.TeamStrings
import react.dom.br
import react.dom.p

object FrenchTeamStrings : TeamStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Nous sommes humains" }
        override val title: TextHandler = { +"Animés par notre passion pour Kotlin." }
        override val chapo: TextHandler = {}
    }
    override val salomon = MemberStrings.salomon.copy(
        position = "Fondateur",
        bio = { +"bio fr" }
    )
    override val romain = MemberStrings.romain.copy(
        position = "Fondateur",
        bio = {
            p {
                +"""Ro main parle aux ordinateurs depuis presque 15 ans. Pendant quelques temps, il a travaillé sur des 
                    |architectures serveur en Java, puis en Kotlin, dès ses premières versions. 
                    |Il a une nouvelle corde à son arc Depuis la KotlinConf'18, oú il a commencer ses contributions 
                    |a Kodein-DI, la première librairie communautaire Kotlin/Multiplatorm.""".trimMargin()
            }
            br {}
            p {
                +"He loves wild and quiet spaces, mountain hikes and climbing."
            }
        }
    )
    override val nerdOn: String = "Retrouvez moi sur "
    override val jobs: List<JobDescription> = listOf(JobDescription("Développeur Kotlin"){
        // TODO change this! copy / paste from Touchlab!
        +"""Nous recherchons des développeurs mobile, avec une expérience sur Kotlin & Android, 
            |un interet pour Kotlin Multiplatform Mobile (KMM) est un plus.""".trimMargin()
    })
}