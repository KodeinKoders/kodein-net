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

    override val advisory = HomeStrings.TitledContent("ADVISORY") {
        +"..." // TODO
    }
    override val training = HomeStrings.TitledContent("TRAINING") {
        +"..."  // TODO
    }
    override val development = HomeStrings.TitledContent("DEVELOPMENT") {
        +"..."  // TODO
    }

    override val readMore: String = "READ MORE"

    override val kodeinKoders = HomeStrings.TitledContent("Who are the Kodein Koders?") {
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

    override val workForHumans = HomeStrings.TitledContent("Work for humans") {
        p {
            +"""
                ...
            """
        }
    }

    override val oss = HomeStrings.TitledContent("Kodein Framework: Open Source at our core") {
        p {
            +"""
                ...
            """
        }
    }

    override val trainings = HomeStrings.TitledContent("Training: Jetbrains Certified") {
        p {
            +"""
                ...
            """
        }
    }
}
