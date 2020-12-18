package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.JobDescription
import net.kodein.pages.team.MemberStrings
import net.kodein.pages.team.TeamStrings
import react.dom.br

object EnglishTeamStrings  : TeamStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"our team" }
        override val title: TextHandler = { +"We are humans, after all!" }
        override val chapo: TextHandler = {}
    }
    override val salomon = MemberStrings.salomon.copy(
        position = "Founder",
        bio = { +"bio en" }
    )
    override val romain = MemberStrings.romain.copy(
        position = "Founder",
        bio = { +"bio en" }
    )
    override val verdOn: String = "Nerd on"
    override val jobs: List<JobDescription> = listOf(JobDescription("Kotlin developer") {
        // TODO change this! copy / paste from Touchlab!
        +"""We are looking for a Mobile Developer, with Android/Kotlin experience, 
            |who is eager to dive into Kotlin Multiplatform Mobile (KMM) development.""".trimMargin()
    })
}