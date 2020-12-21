package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.services.ServiceDescription
import net.kodein.pages.services.ServicesStrings
import react.dom.b
import react.dom.br
import react.dom.p
import react.dom.span

object EnglishServicesStrings : ServicesStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"professional companions" }

        override val title: TextHandler = {
            +"Meet your audience, "
            br {}
            +"with native applications."
        }

        override val chapo: TextHandler = {
            p {
                +"We honestly believe that only native applications can fulfill the gap between you and your users. "
                br {}
                +"That is why we use Kotlin to build strong and robust "
                span("nowrap") { +"multi-platform" }
                +" applications."
            }
            br {}
            p {
                +"Thanks to our strong experience, we can "
                span("nowrap") { +"guide you through." }
            }
        }
    }

    override val consultancy: ServiceDescription = ServiceDescription("Consultancy") {
        p {
            +"Empowering your engineering teams to take on challenges with Kotlin, either it's back-end or mobile."
        }
        br {}
        p {
            +"""That you are starting a new project, looking for team reinforcement and expertise, or in the middle of one other, 
                |considering taking some accompaniment to validate or boost your development process, 
                |we can provide you with 
            """.trimMargin()
            span("nowrap") { b { +"truly valuable persons" } }
            +" and "
            span("nowrap") {
                b { +"highly qualified engineers" }
                +"."
            }
        }
        br {}
        p {
            +"That you need technical guidance, advices on back-end or mobile architecture, or just development support, you can trust us "
            span("nowrap") { +"to help you reach your goals." }
        }
    }

    override val projectDevelopment: ServiceDescription = ServiceDescription("Project Development") {
        p {
            +"""That engineering is not at the core of your business, or that you ran out of resources, 
                |we can help you meet your business expectations by taking care of the technical challenges you have. 
                |Using Scrum methodology, we will provide you a nice and easy way to follow up on your project 
                |while we are taking care of everything, sprint by sprint.
            """.trimMargin()
        }
        br {}
        p {
            +"""Kotlin is the first industry language to be developed with multiplatform in mind.
                |This means that, rather than trying to squeeze an existing language and their unfitted runtime into another target, 
                |the language and its runtime has been thought from its very inception for multiplatform and portability.
            """.trimMargin()
        }
        br {}
        p {
            +"""Focused on running Kotlin code everywhere, our experts take advantage of Kotlin/Multiplatform 
                |to elegantly factorize your business logic in one common code, that we write, test and run once 
                |while allowing us to stick to native development for each targeted system (Backend / Mobile / Web / Desktop).
            """.trimMargin()
        }
    }
}