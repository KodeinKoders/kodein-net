package net.kodein.pages.team

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.pages.team.fragment.Jobs
import net.kodein.pages.team.fragment.Members
import net.kodein.utils.maxSize
import react.RProps
import react.child
import react.dom.br
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledP


val Team = functionalComponent<RProps> {
    child(MenuTop) {
        attrs {
            animated = true
            topMargin = 1.5.rem
            backgroundColor = Color.kodein.cute
        }
    }

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.cute,
                title = Color.kodein.kinzolin,
                overTitle = Color.kodein.korail,
                text = Color.kodein.orange
            )
            overTitle = "Our team"
            title = {
                +"We are Kotlin experts..."
            }
            overrideContentRuleSet = {
                width = 100.pct
                padding(horizontal = 0.rem)
                padding(vertical = 2.rem)
                maxSize(768) {
                    width = 100.pct
                    padding(horizontal = 0.rem)
                    padding(vertical = 1.rem)
                }
            }
        }

        styledP {
            css {
                width = 75.pct
                margin(LinearDimension.auto)
                maxSize(768) { width = 85.pct }
            }

            +"""...and humans, after all!""".trimMargin()
            br {}
           +"""Viens on se fera un petit Qake à l'ancienne! :)""".trimMargin()
        }

        styledDiv { css.height = 5.rem }

        child(Members)
    }

    child(Jobs)

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)
}
