package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.JobDescription
import net.kodein.pages.team.MemberStrings
import net.kodein.pages.team.TeamStrings
import react.dom.br

object EnglishTeamStrings  : TeamStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"we are humans" }
        override val title: TextHandler = { +"Driven by our passion for Kotlin." }
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
    override val nerdOn: String = "Nerd on "
    override val jobs: List<JobDescription> = listOf(JobDescription("Kotlin developer") {
        // TODO change this! copy / paste from Touchlab!
        +"""We are looking for a Mobile Developer, with Android/Kotlin experience, 
            |who is eager to dive into Kotlin Multiplatform Mobile (KMM) development.""".trimMargin()
    })
}