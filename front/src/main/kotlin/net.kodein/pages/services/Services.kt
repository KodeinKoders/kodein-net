package net.kodein.pages.services

import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.ContactUs
import net.kodein.components.Footer
import net.kodein.components.MenuTop
import net.kodein.pages.services.fragment.Cover
import net.kodein.pages.services.fragment.Descriptions
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv

val Services = functionalComponent<RProps>("Services") {
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

        child(Cover)

        child(Descriptions)

        child(ContactUs)

        child(Footer)
}
