package net.kodein.pages.training.fragment

import kotlinx.css.*
import kotlinx.css.properties.border
import net.kodein.charter.kodein
import net.kodein.components.AccordionElement
import net.kodein.components.contentRow
import net.kodein.utils.flexColumn
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styledImg
import styled.styledP

val Trainings = functionalComponent<RProps>("Trainings") {

    contentRow(
        backgroundColor = Color.kodein.dark, indexPosition = 3, // see [Description] that stops at index 2
        bottomLayers = listOf(Color.kodein.orange, Color.kodein.kaumon),
    ) {
        flexColumn {
            css {
                width = 100.pct
                maxWidth = 60.rem
                margin(LinearDimension.auto)
            }

            styledP {
                css {
                    +kodein.chapo
                    specific {
                        textAlign = TextAlign.center
                    }
                    color = Color.kodein.kaumon
                    alignSelf = Align.center
                    marginBottom = 8.rem
                }

                +"We are here to help you to keep your Kotlin knowledge up to date"
            }

            child(AccordionElement) {
                attrs {
                    title = "Kotlin for Android"
                    sub = duration("1 days")
                }

                styledP {
                    css {
                        +kodein.chapo
                        color = Color.kodein.korail
                    }

                    +"// TODO"
                }
                styledP {
                    css {
                        +kodein.intertitre
                        color = Color.kodein.kaumon
                    }

                    +"// TODO"
                }

                styledP {
                    css {
                        +kodein.body
                        color = Color.kodein.korail
                    }

                    +"// TODO"
                }
                styledP {
                    css {
                        +kodein.body
                        color = Color.kodein.korail
                        maxWidth = 60.rem
                    }

                    +"// TODO"
                }
            }

            child(AccordionElement) {
                attrs {
                    title = "Mastering coroutines"
                    sub = duration("1 days")
                }
            }

            child(AccordionElement) {
                attrs {
                    title = "Kotlin on the backend"
                    sub = duration("1 days")
                    last = true
                }
            }
        }
    }
}

private fun duration(duration: String): RBuilder.() -> Unit = {
    styledImg(src = "imgs/stopwatch-kaumon.svg") {
        css {
            width = 1.rem
            height = 1.rem
            paddingRight = .5.rem
        }
    }
    +duration
}
