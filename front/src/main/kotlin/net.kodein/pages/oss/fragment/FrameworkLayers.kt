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
            paddingTop = 8.rem
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
                padding(1.rem, 2.rem)
                maxWidth(360) { padding(1.rem, 1.rem) }
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
                margin(0.5.rem, LinearDimension.auto, 2.rem, LinearDimension.auto)
            }
        }

        flexRow(JustifyContent.center, Align.center) {
            css {
                +KodeinStyles.body
                backgroundColor = Color.kodein.purple
                color = Color.kodein.klycine
                padding(vertical = 1.rem)
                minWidth(1920) { padding(vertical = 1.75.rem) }
                minWidth(2500) { padding(vertical = 2.4.rem) }
                boxShadowInset(Color.kodein.purple.darken(30), offsetY = 0.8.rem, blurRadius = 0.5.rem, spreadRadius = (-0.6).rem)
            }
            +"Platform specific UI"
        }

        flexColumn(JustifyContent.center, Align.center) {
            css {
                +KodeinStyles.body
                backgroundColor = Color.kodein.korail
                color = Color.kodein.kyzantium
                padding(vertical = 1.em)
                minWidth(1920) { padding(vertical = 1.75.rem) }
                minWidth(2500) { padding(vertical = 2.4.rem) }
                boxShadowInset(Color.kodein.korail.darken(30), offsetY = 0.8.rem, blurRadius = 0.5.rem, spreadRadius = (-0.6).rem)
            }
            flexRow {
                css {
                    maxWidth(680) { flexDirection = FlexDirection.column }
                    "span" {
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
                        backgroundColor = Color.kodein.orange
                    }
                }
                flexRow {
                    css {
                        maxWidth(680) { paddingBottom = 1.rem }
                    }
                    span { +"Dependency Injection" }
                    hr {}
                    span { +"Embedded Database" }
                }
                styledHr {
                    css {
                        maxWidth(680) { display = Display.none }
                    }
                }
                flexRow {
                    span { +"Logging & Reporting" }
                    hr {}
                    span { +"Presentation Behaviour" }
                }
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
                padding(vertical = 1.rem)
                minWidth(1920) { padding(vertical = 1.75.rem) }
                minWidth(2500) { padding(vertical = 2.4.rem) }
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
                    maxWidth(680) { flexDirection = FlexDirection.column }
                    "span" {
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
                        backgroundColor = Color.kodein.purple
                    }
                }
                flexRow {
                    css {
                        maxWidth(680) { paddingBottom = 1.rem }
                    }
                    span { +"Coroutines" }
                    hr {}
                    span { +"Atomic operations" }
                }
                styledHr {
                    css {
                        maxWidth(680) { display = Display.none }
                    }
                }
                flexRow {
                    span { +"Serialization" }
                    hr {}
                    span { +"Platform APIs" }
                }
            }

        }

        flexRow(JustifyContent.center, Align.center) {
            css {
                +KodeinStyles.body
                backgroundColor = Color.kodein.kyzantium
                color = Color.kodein.purple
                padding(top = 1.em, bottom = 3.5.em)
                minWidth(1920) { padding(top = 1.75.em, bottom = 3.em) }
                minWidth(2500) { padding(top = 2.4.em, bottom = 2.5.em) }
                boxShadowInset(Color.kodein.kyzantium.darken(30), offsetY = 0.8.rem, blurRadius = 0.5.rem, spreadRadius = (-0.6).rem)
            }
            +"Low-level Android, iOS & Web"
        }

    }
}
