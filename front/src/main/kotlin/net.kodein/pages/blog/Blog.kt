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

    // TODO refactor this
    val coverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Some read" }
        override val title: TextHandler = {
            +"We are technology lovers,"
            br {}
            +"passionates & experts."
        }
        override val chapo: TextHandler = {
            +"""Sharing is caring, which is why it is our mission to spread 
                    |our passion for multiplatform development. 
                    |When we acquire new experience, discover new cool stuff,
                    |create new piece of tech, or simply want to share our passion,
                    |we write an article or shoot a video.
                    |Have a look!""".trimMargin()
        }
    }

    // TODO refactor this
    val wantMoreStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Want more?" }
        override val title: TextHandler = {
            +"Check our "
            a(href = "https://medium.com/kodein-koders", target = "_blank") { +"Medium page" }
            +"."
        }
        override val chapo: TextHandler = {
            +"Keep us close through our social media accounts."
            br {}
            +"Follow us on "
            a(href = "https://twitter.com/KodeinKoders", target = "_blank") { +"Twitter" }
            +" & "
            a(href = "https://www.linkedin.com/company/kodein", target = "_blank") { +"Linkedin" }
            +"!"
        }
    }


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
            content = coverStrings
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
            content = wantMoreStrings
        }
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)

}
