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
                +"""Romain parle "ordinateur 2e langue" depuis presque 15 ans. Pendant ses premières années, 
                    |il a travaillé sur des architectures serveur en Java, puis en Kotlin, dès ses premières versions. 
                    |Il a une nouvelle corde à son arc depuis la KotlinConf'18, oú il a rentré Salomon et commencé à 
                    |contribuer a Kodein-DI, la toute première librairie communautaire Kotlin/Multiplatorm.
                """.trimMargin()
            }
            br {}
            p {
                +"Il aime les grands espaces, la randonnée en montagne et l'escalade."
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