package net.kodein.pages.training

import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.ContactUs
import net.kodein.components.Footer
import net.kodein.components.MenuTop
import net.kodein.pages.training.fragment.Cover
import net.kodein.pages.training.fragment.Description
import net.kodein.pages.training.fragment.Trainings
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv

val Training = functionalComponent<RProps>("Training") {
        styledDiv {
            css {
                height = 1.5.rem
                backgroundColor = Color.kodein.cute
            }
        }

        child(MenuTop) {
            attrs.animated = true
            attrs.backgroundColor = Color.kodein.cute
        }

        child(Cover)

        child(Description)

        child(Trainings)

        child(ContactUs)

        child(Footer)
}
