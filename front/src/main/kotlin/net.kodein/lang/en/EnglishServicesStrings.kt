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
            +"Meet your users expectations, "
            br {}
            +"with natives applications."
        }

        override val chapo: TextHandler = {
            p {
                +"""We honestly believe that natives applications are the best answer to a better user experience. 
                    |That is why we use Kotlin to build nice looking and robust multiplatform applications. 
                """.trimMargin()
            }
            br {}
            p {
                +"Thanks to our strong experience, we shall "
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
                |we can provide you with a team of  
            """.trimMargin()
            span("nowrap") {
                b { +"highly qualified engineers" }
            }
            +", where the "
            span("nowrap") { b { +"humankind" } }
            +" is at the center of their shared values."
        }
        br {}
        p {
            +"That you need "
            span("nowrap") { b { +"technical guidance" } }
            +", back-end or mobile "
            span("nowrap") { b { +"architecture" } }
            +" advice, or "
            span("nowrap") { b { +"development support" } }
            +", you can trust us "
            span("nowrap") { +"to help you reach your goals." }
        }
    }

    override val projectDevelopment: ServiceDescription = ServiceDescription("Project Development") {
        p {
            +"""That engineering is not part of your business, or that you are running out of resources, 
                |we can help you meet your business expectations by taking care of the technical challenges you have. 
                |Using  
            """.trimMargin()
            b { +"Scrum" }
            +""" methodology, we will provide you a nice and easy way to follow up on your project, 
                |while we are taking care of everything 
            """.trimMargin()
            span("nowrap") { b { +"sprint by sprint" } }
            +"."
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
            +"Focused on running Kotlin code everywhere, our experts take advantage of "
            b { +"Kotlin/Multiplatform" }
            +" to elegantly factorize your business logic in "
            span("nowrap") { b { +"one common code" } }
            +", that we write, test and run once while allowing us to stick to native development "
            b { +"for each targeted system" }
            +" (Backend / Mobile / Web / Desktop)."
        }
    }
}