package net.kodein.pages.services.fragment

import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.TextDecorationLine
import kotlinx.css.properties.borderBottom
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RProps
import react.dom.b
import react.dom.br
import react.dom.p
import react.functionalComponent
import styled.*


val Cover = functionalComponent<RProps>("Cover") {
    flexColumn {
        css {
            backgroundColor = Color.kodein.dark
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
            +"Our services"
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
            +"Bringing your business"
            br {}
            +"to the next level,"
            br {}
            +"with strong and robust"
            br {}
            +"multi-platform applications"
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 5.rem
                opacity = .7
                backgroundColor = Color.kodein.korail
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
                color = Color.kodein.korail
                width = 65.pct
                padding(2.rem)
                alignSelf = Align.center

                maxSize(768) {
                    width = 85.pct
                    padding(1.rem)
                }
            }

            b { +"KODEIN" }
            +"""Koders is a tech company that is driven by
                |our passion for Kotlin. 
                |This technology allows us to develop applications 
                |and share code between systems; backend (based on Spring / Ktor) 
                |or frontend (iOS / Android / Web / Desktop). 
                |We are able to guide you to modernize your applications 
                |or reinforce your existing teams to help you reach your goals, 
                |with Kotlin, everywhere you need.""".trimMargin()
        }
    }
}
