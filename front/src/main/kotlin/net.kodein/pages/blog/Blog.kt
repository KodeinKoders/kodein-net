package net.kodein.pages.blog

import kotlinx.css.Color
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.Cover
import net.kodein.components.CoverPalette
import net.kodein.components.MenuTop
import net.kodein.pages.blog.fragment.ElementList
import react.RProps
import react.child
import react.dom.br
import react.functionalComponent


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
            colors = CoverPalette(backgroundColor = Color.kodein.kyzantium)
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

    child(ElementList)

}
