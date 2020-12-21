package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.pages.home.DepartmentStrings
import net.kodein.pages.home.DescriptionStrings
import net.kodein.pages.home.HomeStrings
import react.dom.b
import react.dom.br
import react.dom.p
import react.dom.span

object EnglishHomeStrings : HomeStrings {

    override val everywhere: TextHandler = {
        +"Everywhere "
        span("nowrap") { +"Kotlin goes," }
        br {}
        +"you will find"
        br {}
        +"the experts "
        span("nowrap") { +"you need!" }
    }

    override val advisory: DepartmentStrings = DepartmentStrings("ADVISORY", "advisory") {
        +"..." // TODO
    }
    override val training: DepartmentStrings = DepartmentStrings("TRAINING", "training") {
        +"..."  // TODO
    }
    override val development: DepartmentStrings = DepartmentStrings("DEVELOPMENT", "development") {
        +"..."  // TODO
    }

    override val readMore: String = "READ MORE"

//    override val kodeinKoders: DescriptionStrings = DescriptionStrings("Who are the Kodein Koders ?", "services") {
//        b { +"KODEIN" }
//        +"""Koders is a tech company that is driven by
//                            |our passion for Kotlin.
//                            |This technology allows us to develop applications
//                            |and share code between systems; backend (based on Spring / Ktor)
//                            |or frontend (iOS / Android / Web / Desktop).
//                            |We are able to guide you to modernize your applications
//                            |or reinforce your existing teams to help you reach your goals,
//                            |with Kotlin, everywhere you need.""".trimMargin()
//    }

}
