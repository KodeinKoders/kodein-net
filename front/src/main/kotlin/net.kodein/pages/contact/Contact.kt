package net.kodein.pages.contact

import kotlinx.css.Color
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.*
import react.RProps
import react.child
import react.dom.br
import react.functionalComponent
import styled.styledDiv


val Contact = functionalComponent<RProps> {
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
            overTitle = "Contact us!"
            title = {
                +"Check our nice"
                br {}
                +"but dummy contact form."
            }
        }

        +"""Keep us close through our social media accounts.""".trimMargin()
        br {}
        +"""Follow us on Twitter & LinkedIn""".trimMargin()
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)
}
