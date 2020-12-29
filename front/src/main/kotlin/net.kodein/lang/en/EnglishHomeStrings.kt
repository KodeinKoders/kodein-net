package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.pages.home.HomeStrings
import react.dom.br
import react.dom.p
import react.dom.span

object EnglishHomeStrings : HomeStrings {

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

    override val advisoryDepartment = HomeStrings.TitledContent("ADVISORY") {
        +"..." // TODO
    }
    override val trainingDepartment = HomeStrings.TitledContent("TRAINING") {
        +"..."  // TODO
    }
    override val developmentDepartment = HomeStrings.TitledContent("DEVELOPMENT") {
        +"..."  // TODO
    }

    override val readMore: String = "READ MORE"

    override val kodeinKodersDescription = HomeStrings.TitledContent("Who are the Kodein Koders?") {
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

    override val humanistDescription = HomeStrings.TitledContent("Work for humans") {
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

    override val openSourceDescription = HomeStrings.TitledContent("Kodein Framework: Open Source at our core") {
        p {
            +"""
                ...
            """
        }
    }

    override val trainingDescription = HomeStrings.TitledContent("Training: Jetbrains Certified") {
        p {
            +"""
                ...
            """
        }
    }

    override val humansTitle = "Humans trust us!"
    override val humansSubtitle = "Are you next?"
}
