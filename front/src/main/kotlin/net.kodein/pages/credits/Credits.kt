package net.kodein.pages.credits

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.components.Cover
import net.kodein.components.CoverPalette
import net.kodein.components.Footer
import net.kodein.components.MenuTop
import net.kodein.useText
import net.kodein.utils.flexColumn
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledP


val Credits = functionalComponent<RProps>("Credits") {
    val strings = useText().credits

    child(MenuTop) {
        attrs {
            animated = true
            topMargin = 1.5.rem
            backgroundColor = Color.kodein.klycine
        }
    }

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.klycine,
                title = Color.kodein.dark,
                overTitle = Color.kodein.kamethiste,
                text = Color.kodein.kinzolin,
                layers = listOf(Color.kodein.korail, Color.kodein.kinzolin)
            )
            content = strings.cover
            overrideContentRuleSet = {
                "a" {
                    color = Color.kodein.orange
                    put("text-shadow", "none")
                }
            }
        }
    }

    flexColumn(alignItems = Align.center) {
        css {
            paddingTop = 10.em
            paddingBottom = 5.em
            backgroundColor = Color.kodein.cute
        }

        styledP {
            css {
                +kodein.body
                textAlign = TextAlign.left
                padding(horizontal = 1.rem)

                "a" {
                    color = Color.kodein.purple
                }
            }
            strings.technicalCredits(this)
        }
    }

    child(Footer)

}