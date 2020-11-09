package net.kodein.pages.oss

import kotlinx.css.Color
import kotlinx.css.Position
import kotlinx.css.height
import kotlinx.css.rem
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.pages.oss.fragment.FrameworkLayers
import net.kodein.pages.oss.fragment.FrameworkOnion
import react.RProps
import react.child
import react.dom.a
import react.dom.b
import react.dom.br
import react.functionalComponent
import styled.styledDiv


val Oss = functionalComponent<RProps> {
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
                text = Color.kodein.kaumon,
                title = Color.kodein.orange,
                overTitle = Color.kodein.korail,
                layers = listOf(Color.kodein.krouille, Color.kodein.orange)
            )
            overTitle = "Kodein Framework"
            title = {
                +"Discover our powerful"
                br {}
                +"Open Source Software."
            }
        }

        +"""Since the dawn of Kotlin/Multiplatform, we have been contributing many tools to the ecosystem.
            |In fact, Kodein Koders released the very first Open Source Kotlin/Multiplatform community library.
            |We are always looking for new ways to contribute to the multiplatform narrative!""".trimMargin()
    }

    child(FrameworkLayers)

    layerSeparator(Position.absolute, Color.kodein.kyzantium, Color.kodein.purple)

    child(FrameworkOnion)

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.dark,
                title = Color.kodein.orange,
                layers = listOf(Color.kodein.orange, Color.kodein.kaumon, Color.kodein.kinzolin)
            )
            overTitle = "Want more?"
            title = {
                +"Check our "
                a(href = "https://github.com/Kodein-Framework", target = "_blank") { +"Github" }
                +"."
            }
        }

        +"Ask us anything about the Open Source "
        b { +"KODEIN" }
        +"Framework on "
        a(href = "https://stackoverflow.com/tags/kodein", target = "_blank") { +"Stack Overflow" }
        +", "
        a(href = "https://kotlinlang.slack.com/archives/C0BLU9K96", target = "_blank") { +"Slack" }
        +" or "
        a(href = "https://twitter.com/KodeinKoders", target = "_blank") { +"Twitter" }
        +"!"
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)


}