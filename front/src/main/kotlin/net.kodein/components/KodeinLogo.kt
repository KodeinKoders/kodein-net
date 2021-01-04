package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.lh
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.utils.maxSize
import net.kodein.utils.maxWidth
import net.kodein.withBasePath
import react.RProps
import react.functionalComponent
import styled.*


interface KodeinLogoProps : RProps {
    var logo: String
    var logoAlt: String
    var logoHeight: LinearDimension?
    var bold: String
    var light: String
    var color: Color
    var titleColor: Color?
    var subtitle: String?
}

val KodeinLogo = functionalComponent<KodeinLogoProps>("KodeinLogo") { props ->

    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignItems = Align.center
            justifyContent = JustifyContent.flexStart
            color = props.color
            fontSize = if (props.subtitle == null) 1.75.em else 1.4.em
            transition("marginRight", duration = 0.15.s)
            zIndex = 1001
        }
        withBasePath { path ->
            styledImg(src = "$path/imgs/logo-${props.logo}.svg", alt = props.logoAlt) {
                attrs {
                    width = "30"
                    height = "38"
                }
                css {
                    display = Display.block
                    padding(right = 1.em)
                    maxWidth(350) { padding(right = 0.5.em) }
                    height = props.logoHeight ?: 1.8.em
                    width = (props.logoHeight ?: 1.8.em) * 0.8
                    maxSize(480) {
                        height = props.logoHeight ?: 1.4.em
                        width = (props.logoHeight ?: 1.4.em) * 0.8
                    }
                }
            }
        }
        styledDiv {
            styledH1 {
                css {
                    fontSize = 1.4.em
                    maxSize(480) { fontSize = 1.25.em }
                    fontWeight = FontWeight.w700
                    if (props.subtitle == null) {
                        lineHeight = 1.em.lh
                        paddingTop = 0.25.em
                    }
                    lineHeight = 1.em.lh
                    props.titleColor?.let {
                        color = it
                    }
                }

                +props.bold

            styledSpan {
                css {
                    opacity = 0.7
                    fontWeight = FontWeight.w300
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

                        maxSize(440) { fontSize = 0.6.em }
                        maxSize(350) { fontSize = 0.54.em }
                    }
                    +subtitle
                }
            }
        }
    }

}
