package net.kodein.pages.team

import kotlinx.css.*
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.fragment.Jobs
import net.kodein.pages.team.fragment.Members
import net.kodein.useText
import net.kodein.utils.maxSize
import react.RProps
import react.child
import react.dom.br
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledP


val Team = functionalComponent<RProps> {
    val strings = useText().team

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
            content = strings.cover
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

        child(Members)
    }

    child(Jobs)

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)
}
