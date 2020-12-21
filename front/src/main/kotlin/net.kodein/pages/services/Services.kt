package net.kodein.pages.services

import kotlinx.css.Color
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.services.fragment.Descriptions
import net.kodein.useText
import react.RProps
import react.child
import react.dom.b
import react.dom.br
import react.functionalComponent
import styled.styledDiv

val Services = functionalComponent<RProps>("Services") {

    val strings = useText().services

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
                content = strings.cover
            }
        }

        child(Descriptions)

        styledDiv { css.height = 10.rem }

        child(ContactUs)

        child(Footer)
}
