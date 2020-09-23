package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.*
import react.dom.br
import styled.*


interface KodeinLogoProps : RProps {
    var logo: String
    var logoHeight: LinearDimension?
    var bold: String
    var light: String
    var color: Color
    var titleColor: Color?
    var subtitle: String?
}

val KodeinLogo by functionalComponent<KodeinLogoProps> { props ->

    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignItems = Align.center
            justifyContent = JustifyContent.flexStart
            color = props.color
            marginRight = 10.em
            transition("marginRight", duration = 0.15.s)
        }
        styledImg(alt = "Kodein logo", src = "imgs/logo-${props.logo}.svg") {
            css {
                display = Display.block
                padding(right = 1.em)
                height = props.logoHeight ?: 3.em
                mobileL {
                    width = 4.em
                    height = 4.em
                }
            }
        }
        styledDiv {
            styledH1 {
                css {
                    fontWeight = FontWeight.w700
                    fontSize = if (props.subtitle == null) 2.em else 1.4.em
                    props.titleColor?.let {
                        color = it
                    }
                    mobileL { fontSize = 3.rem }
                }

                +props.bold

            styledSpan {
                css {
                    opacity = 0.8
                    fontWeight = FontWeight.w300
                    mobileL { fontSize = 3.rem }
                }
                    +props.light
                }
            }
            props.subtitle?.let { subtitle ->
                styledP {
                    css {
                        fontWeight = FontWeight.w600
                        letterSpacing = 0.075.em
                        fontSize = 0.8.em
                        marginTop = (-0.25).em
                    }
                    +subtitle
                }
            }
        }
    }

}
