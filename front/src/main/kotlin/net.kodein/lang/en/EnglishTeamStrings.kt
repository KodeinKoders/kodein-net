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
        status = "Founder",
        bio = {
            p {
                +"""
                    Salomon has been coding for more than 20 years (he started at age 12), and working on mobile applications as soon as smartphone existed.
                    Deeply rooted in the open source philosophy, he loves making tools and libraries, always contributing to the industry's state of the art.
                    He's always looking for the future of programming, and has been contributing to Kotlin & Kotlin/Native since their inception.
                """
            }
            br {}
            p {
                +"He dances 6 beats rock, flies small planes, and loooves boardgames."
            }
        }
    )
    override val romain = MemberStrings.romain.copy(
        status = "Founder",
        bio = {
            p {
                +"""Romain has been coding for almost 15 years. For some times, he has been working on 
                    |server side architectures with Java, then with Kotlin since its early days. 
                    |He added a new string to its bow at KotlinConf 2018, where he met Salomon and started contributing 
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
    override val joinUs: String = "Join us!"
    override val jobs: List<JobDescription> = listOf(
        JobDescription("Kotlin/Multiplatform developer") {
            p {
                +"You want to work with Kotlin everywhere it goes: Android, iOS, Server, even maybe Web/JS."
                br {}
                +"You want develop tools & libraries that will advance the state of the art of mobile multiplatform programming."
                br {}
                +"You want to work on different projects and help create tailored applications."
            }
            br {}
            p {
                +"But also..."
                br {}
                +"You're happy to work fully remotely, and on a time schedule of your own."
                br {}
                +"You share our humanist values: kindness, openness, financial transparency."
                br {}
                +"You have a european passport or work permit."
            }
        },
        JobDescription("Part time evangelist") {
            p {
                +"You understand what Kotlin/Multiplatform brings to the mobile development industry."
                br {}
                +"You want to address the market bottom-up: talking to developers and trusting them to convince their management."
                br {}
                +"You want to create with us a renowned european center of excellency in mobile application development."
            }
            br {}
            p {
                +"But also..."
                br {}
                +"You're happy to work fully remotely, and on a time schedule of your own."
                br {}
                +"You share our humanist values: kindness, openness, financial transparency."
                br {}
                +"You have a european passport or work permit."
            }
        }
    )
}
