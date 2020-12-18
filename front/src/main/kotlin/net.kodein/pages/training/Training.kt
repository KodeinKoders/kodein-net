package net.kodein.pages.training

import kotlinx.css.Color
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.pages.training.fragment.Description
import net.kodein.pages.training.fragment.Trainings
import net.kodein.useText
import react.RProps
import react.child
import react.functionalComponent
import styled.styledDiv

val Training = functionalComponent<RProps>("Training") {
    val strings = useText().training

    child(MenuTop) {
            attrs{
                animated = true
                topMargin = 1.5.rem
                backgroundColor = Color.kodein.cute
            }
        }

        child(Cover) {
            attrs {
                colors = CoverPalette(
                    backgroundColor = Color.kodein.cute,
                    text = Color.kodein.korail,
                    title = Color.kodein.kyzantium,
                    layers = listOf(Color.kodein.korail, Color.kodein.kaumon)
                )
                content = strings.cover
            }

            strings.cover.chapo(this)
        }

        child(Description)

        child(Trainings)

        styledDiv { css.height = 10.rem }

        child(ContactUs)

        child(Footer)
}
