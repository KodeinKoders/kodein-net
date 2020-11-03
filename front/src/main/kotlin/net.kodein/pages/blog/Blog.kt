package net.kodein.pages.blog

import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.MenuTop
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv


val Blog = functionalComponent<RProps> {
    styledDiv {
        css {
            height = 1.5.rem
            backgroundColor = Color.kodein.dark
        }
    }

    child(MenuTop) {
        attrs.animated = true
        attrs.backgroundColor = Color.kodein.dark
    }

}
