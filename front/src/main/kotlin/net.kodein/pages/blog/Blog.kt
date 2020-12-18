package net.kodein.pages.blog

import kotlinx.css.*
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.blog.fragment.EntryList
import net.kodein.useText
import react.RProps
import react.child
import react.dom.a
import react.dom.br
import react.functionalComponent
import styled.styledDiv


val Blog = functionalComponent<RProps> {

    val strings = useText().blog

    child(MenuTop) {
        attrs {
            animated = true
            topMargin = 1.5.rem
            backgroundColor = Color.kodein.kyzantium
        }
    }

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.kyzantium,
                layers = listOf(Color.kodein.kinzolin, Color.kodein.kuivre)
            )
            content = strings.cover
        }
    }

    child(EntryList)

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.dark,
                title = Color.kodein.orange,
                layers = listOf(Color.kodein.orange, Color.kodein.kaumon, Color.kodein.kinzolin)
            )
            content = strings.wantMore
        }
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)

}
