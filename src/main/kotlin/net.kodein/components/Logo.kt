package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.utils.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.*

class Logo : RComponent<Logo.Props, RState>() {

    interface Props : RProps {
        var bold: String
        var light: String
        var palette: Palette
    }

    override fun RBuilder.render() {

        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                alignItems = Align.center
                justifyContent = JustifyContent.flexStart
                color = props.palette.color
                marginRight = 10.em
                transition("marginRight", duration = 0.15.s)

                maxWidthXXL { marginRight = 8.em }
                maxWidth(1050.px) { marginRight = 6.em }
                maxWidthXL { marginRight = 3.em }
                maxWidth(950.px) { marginRight = 2.em }
                maxWidth(800.px) {
                    fontSize = 0.9.em
                    marginRight = 0.em
                }
                maxWidth(600.px) { fontSize = 0.8.em }
                maxWidthXS { fontSize = 0.75.em }
            }
            styledImg(alt = "Kodein logo", src = "imgs/logo-${props.palette.name}.svg") {
                css {
                    display = Display.block
                    padding(right = 1.em)
                    width = 3.em
                    height = 3.em
                }
            }
            styledH1 {
                css {
                    fontWeight = FontWeight.w700
                }

                +props.bold

                styledSpan {
                    css.opacity = 0.8
                    css.fontWeight = FontWeight.w300
                    +props.light
                }
            }
        }

    }


}
