package net.kodein.pages.home.fragment

import kotlinx.css.*
import net.kodein.utils.Palette
import net.kodein.utils.maxWidthM
import net.kodein.utils.maxWidthXS
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p
import styled.css
import styled.styledDiv
import styled.styledSpan

class Slogan : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                height = 10.em
                backgroundColor = Palette.dark.color
                display = Display.flex
                alignItems = Align.center
                textAlign = TextAlign.center
                justifyContent = JustifyContent.center
                color = Palette.orange.color
                fontWeight = FontWeight.w200
                fontSize = 2.em

                maxWidthM {
                    fontSize = 1.7.em
                }
                maxWidthXS {
                    fontSize = 1.2.em
                }
            }

            styledSpan {
                p { +"Everywhere Kotlin goes;" }
                p { +"you should find the expert you need!" }
            }

        }
    }
}