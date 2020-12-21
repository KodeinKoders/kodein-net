package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.JobDescription
import net.kodein.pages.team.MemberStrings
import net.kodein.pages.team.TeamStrings

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
        bio = { +"bio fr" }
    )
    override val nerdOn: String = "Retrouvez moi sur "
    override val jobs: List<JobDescription> = listOf(JobDescription("Développeur Kotlin"){
        // TODO change this! copy / paste from Touchlab!
        +"""Nous recherchons des développeurs mobile, avec une expérience sur Kotlin & Android, 
            |un interet pour Kotlin Multiplatform Mobile (KMM) est un plus.""".trimMargin()
    })
}