package net.kodein.pages.training.fragment

import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.borderBottom
import net.kodein.charter.kodein
import net.kodein.components.layerSeparator
import net.kodein.utils.*
import react.RProps
import react.dom.b
import react.dom.br
import react.functionalComponent
import styled.css
import styled.styledH1
import styled.styledP
import styled.styledSpan


val Cover = functionalComponent<RProps>("Cover") {
    flexColumn {
        css {
            backgroundColor = Color.kodein.cute
        }

        styledP {
            css {
                +kodein.display1
                alignSelf = Align.center
                textAlign = TextAlign.center
                color = Color.kodein.orange
                paddingTop = 6.rem
                marginBottom = 2.rem
                maxSize(768) {
                    paddingTop = 2.rem
                    marginBottom = 1.rem
                }
                borderBottom(0.05.rem, BorderStyle.solid, Color.kodein.orange)
            }
            +"Let's share knowledge!"
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
                color = Color.kodein.kyzantium
                margin(1.rem, 2.rem)
            }
            +"Yes we are"
            br {}
            +""" "JetBrains Certified Trainer" """
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 5.rem
                opacity = .7
                backgroundColor = Color.kodein.orange
                margin(1.rem, auto)
                maxSize(768) {
                    height = 3.rem
                    margin(0.rem, auto)
                }
                landscapeMobile {
                    margin(1.rem, auto)
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
                color = Color.kodein.orange
                maxWidth = 40.rem
                padding(2.rem)
                alignSelf = Align.center

                maxSize(1024) {
                    padding(1.rem)
                }
                maxSize(768) {
                    padding(1.rem)
                }
            }

            +"""Our Kotlin expertise goes way beyond its first objective, 
                |for the JVM world. We have the ability to give training and workshops 
                |for companies and world events, as we already did for  Kotlin/Everywhere Paris 
                |and KotlinConf'19. We can provide training upon the different level of Kotlin.""".trimMargin()
        }
    }

    layerSeparator(Position.absolute, Color.kodein.cute, Color.kodein.korail, Color.kodein.kaumon, Color.kodein.korail)
}
