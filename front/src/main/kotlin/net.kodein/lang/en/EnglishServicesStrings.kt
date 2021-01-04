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

    override val pageTitle: String = "Services"

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"professional companions" }

        override val title: TextHandler = {
            +"Meet your users expectations, "
            br {}
            +"with native applications."
        }

        override val chapo: TextHandler = {
            p {
                +"""We believe that native applications always provide the best user experience. 
                    |We use Kotlin to build nice looking, integrated and robust multiplatform applications. 
                """.trimMargin()
            }
            br {}
            p {
                +"Thanks to our strong experience, we will "
                span("nowrap") { +"guide you through." }
            }
        }
    }

    override val consultancy: ServiceDescription = ServiceDescription("Consultancy") {
        p {
            +"Whatever the platform, empower your engineering team to take on their challenges with Kotlin."
        }
        br {}
        p {
            +"""Whether you are starting a new project or looking for reinforcement and expertise, 
                |we can help you validate and/or boost your development process. 
                |We can provide you with a team of  
            """.trimMargin()
            span("nowrap") {
                b { +"highly qualified engineers" }
            }
            +", with our "
            span("nowrap") { b { +"humanist philosophy" } }
            +" being at the center of their work."
        }
        br {}
        p {
            +"Whether you are looking for "
            span("nowrap") { b { +"technical guidance" } }
            +", back-end or application "
            span("nowrap") { b { +"architecture" } }
            +" expertise, or just a simple need of "
            span("nowrap") { b { +"development support" } }
            +", you can trust us to be with you "
            span("nowrap") { +"during your project full lifetime." }
        }
    }

    override val projectDevelopment: ServiceDescription = ServiceDescription("Project Development") {
        p {
            +"""Whether engineering is not part of your business, or you are running out of resources, 
                |we can help you meet your business expectations by taking care of your technical challenges. 
                |Using the  
            """.trimMargin()
            b { +"Scrum" }
            +""" methodology, we provide you with a nice and easy way to follow up on your project, 
                |while we take care of everything 
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
            span("nowrap") { b { +"one common shared code" } }
            +", that we can write and test once while sticking to native development "
            b { +"for each targeted platform" }
            +" (Backend / Mobile / Web / Desktop)."
        }
    }
}