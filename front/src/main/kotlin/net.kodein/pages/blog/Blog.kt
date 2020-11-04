package net.kodein.pages.blog

import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.pages.blog.fragment.EntryList
import react.RProps
import react.child
import react.dom.a
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
                layers = listOf(Color.kodein.orange, Color.kodein.kaumon, Color.kodein.kinzolin)
            )
            css = {
                "a" {
                    display = Display.inlineBlock

//                    textDecoration(TextDecorationLine.underline)
                    borderBottom(0.15.rem, BorderStyle.solid, Color.currentColor)
                    lineHeight = 0.85.em.lh
                    put("text-shadow", "0.03em 0.03em ${Color.kodein.dark}, 0.03em -0.03em ${Color.kodein.dark}, -0.03em 0.03em ${Color.kodein.dark}, -0.03em -0.03em ${Color.kodein.dark}")
                }
            }
            overTitle = "Want more?"
            title = {
                +"Check our "
                a(href = "https://medium.com/kodein-koders", target = "_blank") { +"Medium page" }
                +"."
            }
        }

        +"Keep us close through our social media accounts."
        br {}
        +"Follow us on "
        a(href = "https://twitter.com/KodeinKoders", target = "_blank") { +"Twitter" }
        +" & "
        a(href = "https://www.linkedin.com/company/kodein", target = "_blank") { +"Linkedin" }
        +"!"
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)

}
