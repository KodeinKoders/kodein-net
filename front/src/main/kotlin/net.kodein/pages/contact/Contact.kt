package net.kodein.pages.contact

import kotlinx.css.Color
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.useText
import react.RProps
import react.child
import react.functionalComponent
import styled.styledDiv


val Contact = functionalComponent<RProps> {
    val strings = useText().contact

    child(MenuTop) {
        attrs {
            animated = true
            topMargin = 1.5.rem
            backgroundColor = Color.kodein.kaumon
        }
    }

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.kaumon,
                title = Color.kodein.kinzolin,
                layers = listOf(Color.kodein.orange, Color.kodein.kinzolin, Color.kodein.krouille)
            )
            content = strings.cover
            overrideContentRuleSet = {
                "a" {
                    put("text-shadow", "none")
                }
            }
        }
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs) { attrs.big = true }

    child(Footer)
}
