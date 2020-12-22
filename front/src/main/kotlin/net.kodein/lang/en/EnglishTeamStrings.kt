package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.JobDescription
import net.kodein.pages.team.MemberStrings
import net.kodein.pages.team.TeamStrings
import react.dom.br
import react.dom.p

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
        bio = {
            p {
                +"""Romain has been talking to computers for almost 15 years. For some times, he has been working on 
                    |server side architectures with Java, then with Kotlin, since its early days. 
                    |He has a new string to its bow since KotlinConf'18, where he met Salomon and started contributing 
                    |to Kodein-DI, the very first Kotlin/Multiplatform community library.
                """.trimMargin()
            }
            br {}
            p {
                +"He loves wild spaces, mountain hikes and climbing."
            }
        }
    )
    override val nerdOn: String = "Nerd on "
    override val jobs: List<JobDescription> = listOf(JobDescription("Kotlin developer") {
        +"""We are looking for Kotlin Developers, craftmanship lovers, with or without experience, 
            |who wish to deeply get into Kotlin Multiplatform Mobile (KMM) development.""".trimMargin()
    })
}