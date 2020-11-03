package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RBuilder
import react.RProps
import react.functionalComponent
import styled.css
import styled.styledH1
import styled.styledP
import styled.styledSpan


interface CoverProps : RProps {
    var backgroundColor: Color
    var overTitle: String
    var title: RBuilder.() -> Unit
}

val Cover = functionalComponent<CoverProps>("Cover") { props ->
    flexColumn {
        css {
            backgroundColor = props.backgroundColor
        }

        styledP {
            css {
                +kodein.display1
                alignSelf = Align.center
                textAlign = TextAlign.center
                color = Color.kodein.korail
                paddingTop = 6.rem
                marginBottom = 2.rem
                maxSize(768) {
                    paddingTop = 2.rem
                    marginBottom = 1.rem
                }
                borderBottom(0.05.rem, BorderStyle.solid, Color.kodein.korail)
            }
            +props.overTitle
        }

        styledH1 {
            css {
                +kodein.display3
                specific {
                    fontWeight = FontWeight.hairline
                    textAlign = TextAlign.center

                    maxSize(480) {
                        fontSize = 1.8.rem
                    }
                    maxSize(400) {
                        fontSize = 1.6.rem
                    }
                }
                color = Color.kodein.kaumon
                margin(1.rem, 2.rem)
            }
            props.title(this)
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 5.rem
                opacity = .7
                backgroundColor = Color.kodein.korail
                margin(1.rem, LinearDimension.auto)
                maxSize(768) {
                    height = 3.rem
                    margin(0.rem, LinearDimension.auto)
                }
                landscapeMobile {
                    margin(1.rem, LinearDimension.auto)
                }
            }
        }

        styledP {
            css {
                +kodein.chapo
                specific {
                    fontWeight = FontWeight.light
                    textAlign = TextAlign.center
                }
                color = Color.kodein.korail
                width = 65.pct
                padding(2.rem)
                alignSelf = Align.center

                maxSize(768) {
                    width = 85.pct
                    padding(1.rem)
                }
            }

            props.children()
        }
    }
}
