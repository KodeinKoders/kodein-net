package net.kodein.pages.oss.fragment

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.boxShadowInset
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RProps
import react.dom.*
import react.functionalComponent
import styled.*


val FrameworkLayers = functionalComponent<RProps>("FrameworkLayers") {
    flexColumn {
        css {
            paddingTop = 10.rem
            backgroundColor = Color.kodein.kaumon
        }

        styledH2 {
            css {
                +kodein.intertitre
                specific {
                    fontWeight = FontWeight.ultraLight
                    textAlign = TextAlign.center

                    maxSize(480) {
                        fontSize = 1.8.rem
                    }
                    maxSize(400) {
                        fontSize = 1.6.rem
                    }
                }
                color = Color.kodein.kinzolin
                margin(1.rem, 2.rem)
            }
            +"The "
            styledB {
                css.fontWeight = FontWeight.semiBold
                +"KODEIN"
            }
            +"Framework"
            br {}
            +"focuses on Application Business Code."
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 3.rem
                opacity = .7
                backgroundColor = Color.kodein.kinzolin
                margin(1.rem, LinearDimension.auto)
                maxSize(768) {
                    margin(0.rem, LinearDimension.auto)
                }
                landscapeMobile {
                    margin(1.rem, LinearDimension.auto)
                }
            }
        }

        flexRow(JustifyContent.center, Align.center) {
            css {
                +KodeinStyles.body
                backgroundColor = Color.kodein.purple
                color = Color.kodein.klycine
                padding(1.em, 2.5.em)
                boxShadowInset(Color.kodein.purple.darken(30), offsetY = 0.8.rem, blurRadius = 0.5.rem, spreadRadius = (-0.6).rem)
            }
            +"Platform specific UI"
        }

        flexColumn(JustifyContent.center, Align.center) {
            css {
                +KodeinStyles.body
                backgroundColor = Color.kodein.korail
                color = Color.kodein.kyzantium
                padding(1.em, 2.5.em)
                boxShadowInset(Color.kodein.korail.darken(30), offsetY = 0.8.rem, blurRadius = 0.5.rem, spreadRadius = (-0.6).rem)
            }
            flexRow {
                css {
                    "div" {
                        padding(0.5.rem)
                        width = 8.rem
                        display = Display.flex
                        justifyContent = JustifyContent.center
                        alignItems = Align.center
                        textAlign = TextAlign.center
                    }
                    "hr" {
                        width = 0.05.rem
                        border = "none"
                        backgroundColor = Color.kodein.kyzantium
                    }
                }
                div { +"Dependency Injection" }
                hr {}
                div { +"Embedded Database" }
                hr {}
                div { +"Logging & Reporting" }
                hr {}
                div { +"Presentation Behaviour" }
            }
            styledSpan {
                css {
                    +kodein.intertitre
                    specific { fontWeight = FontWeight.ultraLight }
                    marginTop = 1.rem
                }
                styledB {
                    css.fontWeight = FontWeight.semiBold
                    +"KODEIN"
                }
                +"Framework"
            }
        }

        flexColumn(JustifyContent.center, Align.center) {
            css {
                +KodeinStyles.body
                backgroundColor = Color.kodein.kinzolin
                color = Color.kodein.kamethiste
                padding(1.em, 2.5.em)
                boxShadowInset(Color.kodein.kinzolin.darken(30), offsetY = 0.8.rem, blurRadius = 0.5.rem, spreadRadius = (-0.6).rem)
            }
            styledSpan {
                css {
                    +kodein.intertitre
                    specific { fontWeight = FontWeight.normal }
                    marginBottom = 1.rem
                }
                +"Kotlin & KotlinX"
            }
            flexRow {
                css {
                    "div" {
                        padding(0.5.rem)
                        width = 8.rem
                        display = Display.flex
                        justifyContent = JustifyContent.center
                        alignItems = Align.center
                        textAlign = TextAlign.center
                    }
                    "hr" {
                        width = 0.05.rem
                        border = "none"
                        backgroundColor = Color.kodein.kamethiste
                    }
                }
                div { +"Coroutines" }
                hr {}
                div { +"Atomic operations" }
                hr {}
                div { +"Serialization" }
                hr {}
                div { +"Platform APIs" }
            }
        }

        flexRow(JustifyContent.center, Align.center) {
            css {
                +KodeinStyles.body
                backgroundColor = Color.kodein.kyzantium
                color = Color.kodein.purple
                padding(1.em, 2.5.em)
                boxShadowInset(Color.kodein.kyzantium.darken(30), offsetY = 0.8.rem, blurRadius = 0.5.rem, spreadRadius = (-0.6).rem)
            }
            +"Low-level Android, iOS & Web"
        }

    }
}
