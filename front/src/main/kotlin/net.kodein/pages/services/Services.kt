package net.kodein.pages.services

import kotlinx.css.Color
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.ContactUs
import net.kodein.components.Cover
import net.kodein.components.Footer
import net.kodein.components.MenuTop
import net.kodein.pages.services.fragment.Descriptions
import react.RProps
import react.child
import react.dom.b
import react.dom.br
import react.functionalComponent

val Services = functionalComponent<RProps>("Services") {
        child(MenuTop) {
            attrs {
                animated = true
                topMargin = 1.5.rem
                backgroundColor = Color.kodein.dark
            }
        }

        child(Cover) {
            attrs {
                backgroundColor = Color.kodein.dark
                overTitle = "Our services"
                title = {
                    +"Bringing your business"
                    br {}
                    +"to the next level,"
                    br {}
                    +"with strong and robust"
                    br {}
                    +"multi-platform applications."
                }
            }

            b { +"KODEIN" }
            +"""Koders is a tech company that is driven by
                |our passion for Kotlin. 
                |This technology allows us to develop applications 
                |and share code between systems; backend (based on Spring / Ktor) 
                |or frontend (iOS / Android / Web / Desktop). 
                |We are able to guide you to modernize your applications 
                |or reinforce your existing teams to help you reach your goals, 
                |with Kotlin, everywhere you need.""".trimMargin()
        }

        child(Descriptions)

        child(ContactUs)

        child(Footer)
}
