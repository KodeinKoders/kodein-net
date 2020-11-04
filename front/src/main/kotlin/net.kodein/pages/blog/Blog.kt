package net.kodein.pages.blog

import kotlinx.css.Color
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.pages.blog.fragment.EntryList
import react.RProps
import react.child
import react.dom.br
import react.functionalComponent
import styled.styledDiv


val Blog = functionalComponent<RProps> {
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
            overTitle = "Some read"
            title = {
                +"We are technology lovers,"
                br {}
                +"passionates & experts."
            }
        }

        +"""Sharing is caring, which is why it is our mission to spread 
                |our passion for multiplatform development. 
                |When we acquire new experience, discover new cool stuff,
                |create new piece of tech, or simply want to share our passion,
                |we write an article or shoot a video.
                |Have a look!""".trimMargin()
    }

    child(EntryList)

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.dark,
                secondary = Color.kodein.orange,
//                layers = listOf(Color.kodein.orange, Color.kodein.kinzolin, Color.kodein.kaumon)
                layers = listOf(Color.kodein.orange, Color.kodein.kaumon, Color.kodein.kinzolin)
            )
            overTitle = "Want more?"
            title = {
                +"Check our Medium."
            }
        }

        +"Keep us close through our social media accounts."
        br {}
        +"Follow us on Twitter & LinkedIn."
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)

}
