package net.kodein.pages.services

import kotlinx.css.Color
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.services.fragment.Descriptions
import react.RProps
import react.child
import react.dom.b
import react.dom.br
import react.functionalComponent
import styled.styledDiv

val Services = functionalComponent<RProps>("Services") {

    // TODO refactor this
    val coverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"our services" }
        override val title: TextHandler = {
            +"Bringing your business"
            br {}
            +"to the next level,"
            br {}
            +"with strong and robust"
            br {}
            +"multi-platform applications."
        }
        override val chapo: TextHandler = {
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
    }


    child(MenuTop) {
            attrs {
                animated = true
                topMargin = 1.5.rem
                backgroundColor = Color.kodein.dark
            }
        }

        child(Cover) {
            attrs {
                colors = CoverPalette(backgroundColor = Color.kodein.dark)
                content = coverStrings
            }

            coverStrings.chapo(this)
        }

        child(Descriptions)

        styledDiv { css.height = 10.rem }

        child(ContactUs)

        child(Footer)
}
