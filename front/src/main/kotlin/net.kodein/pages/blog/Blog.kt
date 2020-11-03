package net.kodein.pages.blog

import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.Cover
import net.kodein.components.CoverPalette
import net.kodein.components.MenuTop
import react.RProps
import react.child
import react.dom.br
import react.functionalComponent
import styled.css
import styled.styledDiv


val Blog = functionalComponent<RProps> {
    styledDiv {
        css {
            height = 1.5.rem
            backgroundColor = Color.kodein.kyzantium
        }
    }

    child(MenuTop) {
        attrs.animated = true
        attrs.backgroundColor = Color.kodein.kyzantium
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
                |we write an article or shoot a video.""".trimMargin()
    }

}
