package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.pages.home.HomeStrings
import react.dom.b
import react.dom.br
import react.dom.p
import react.dom.span

object EnglishHomeStrings : HomeStrings {

    override val pageTitle: String = ""

    override val title: TextHandler = {
        +"Everywhere "
        span("nowrap") { +"Kotlin goes," }
        br {}
        +"you will find"
        br {}
        +"the experts "
        span("nowrap") { +"you need!" }
    }

    override val subTitle: TextHandler = {
        +"We are Kodein Koders, "
        br("mobile") {}
        +"a tech company "
        br("desktop") {}
        +"driven by "
        br("mobile") {}
        +"our ideas for multiplatform "
        br {}
        +"and our passion "
        span("nowrap") { +"for craftsmanship." }
    }

    override val scroll: String = "SCROLL"

    override val advisoryDepartment = HomeStrings.TitledContent("ADVISORY", "CONSULTING SERVICES") {
        p { +"Starting a new project or looking for reinforcement and expertise?" }
        br{}
        p {
            +"""
                We can help you validate and boost your development process 
                with technical guidance, architecture or even development support, 
            """.trimIndent()
            span("nowrap") { +"using Kotlin." }
        }
    }
    override val trainingDepartment = HomeStrings.TitledContent("TRAINING", "TRAINING PROGRAMS") {
        p { +"Want to give to your team the tools to be even more efficient with Kotlin?" }
        br {}
        p {
            +"""
                We have developed a set of training that focuses on providing technical teams
                the most practical knowledge in a couple of days.
            """.trimIndent()

        }
    }
    override val developmentDepartment = HomeStrings.TitledContent("DEVELOPMENT", "PROJECT DEVELOPMENT") {
        p { +"You do not have the resources to take on your next project?" }
        br {}
        p {
            +"We can help you meet your business expectations by taking care of the development of your "
             span("nowrap") { +"application mobile," }
            +" for both Android and iOS, using Kotlin/Multiplatform."
         }
    }


    override val kodeinKodersDescription = HomeStrings.TitledContent("Who are the Kodein Koders?", "OUR SERVICES") {
        p {
            +"""
                We are tech lovers & experts driven by our passion for Kotlin/Multiplatform.
            """
            br {}
            +"""
                With this technology, we develop complex applications and share meaningful code between platforms,
                whether the backend server (based on Spring or Ktor) or the multiple frontends (Android, iOS, Desktop, Web).
            """
        }
        p {
            +"""
                We create multiplatform mobile application architectures, modernize existing systems,
                or help teams reach their development goals with Kotlin, everywhere you need.
            """
        }
    }

    override val humanistDescription = HomeStrings.TitledContent("Work for humans", "OUR TEAM") {
        p {
            +"""
                Before companies, we are working with and for persons.
                We are proud of the partnership we created with each of them.
            """
            br {}
            +"""
                We advertise the humanist values that shape our work and relationships, both inside Kodein Koders and with our clients.
            """
        }
        p {
            +"""
                In both our trainings and consulting services, we approach each mission with humans first:
                How can we communicate efficiently? What is the best way to help you progress?
            """
        }
        p {
            +"""
                Humans change their minds, and their vision keep evolving.
                We take pride in promoting the agile methods to help you clear your very own path.
            """
        }
    }

    override val openSourceDescription = HomeStrings.TitledContent("Kodein Framework: Open Source at our core", "OUR OPEN-SOURCE EFFORT") {
        p {
            +"The "
            b { +"KODEIN" }
            +"Framework"
            +"""
                is the first Open-Source multiplatform mobile framework written specifically for Kotlin & Kotlin/Multiplatform.
                It allows mobile application creators to focus on their application business and quality.
            """
        }

        p {
            +"""
                We firmly believe that open-sourcing non business-critical components and contributing to existing open-source projects leads to better software.
            """
            br {}
            +"""
                Kodein Koders is heavily invested in using and contributing to Open Source initiatives.
                The Kotlin language & runtime as well as the Kodein Framework: everything is open!
            """
        }
    }

    override val trainingDescription = HomeStrings.TitledContent("Training: Jetbrains Certified", "OUR TRAINING PROGRAMS") {
        p {
            +"""
                The Kotlin language, coroutines asynchronicity, the Kotlin/Multiplatform infrastructure, Kotlin/Native specifics: trying to understand them all can be daunting.
            """
        }
        p {
            b { +"KODEIN" }
            +"Koders"
            +"""
                is the first European company to be certified by JetBrains for Kotlin training.
                It is now the first training provider in Europe specialised on Kotlin/Multiplatform technologies. 
            """
        }
        p {
            +"""
                We always tailor our training workshops for the team we are addressing,
                and use precise teaching material to make teams efficient in a matter of days.
            """
        }
    }

    override val humansTitle = "Humans trust us!"
    override val humansSubtitle = "Are you next?"
}
