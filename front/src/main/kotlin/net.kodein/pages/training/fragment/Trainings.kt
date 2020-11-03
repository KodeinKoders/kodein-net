package net.kodein.pages.training.fragment

import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import net.kodein.charter.kodein
import net.kodein.components.layerSeparator
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.light
import react.RProps
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledP

val Trainings = functionalComponent<RProps>("Trainings") {
    flexColumn {
        styledDiv {
            css {
                zIndex = 15
                +kodein.dropShadow
            }

            flexRow {
                css {
                    width = 100.pct
                    backgroundColor = Color.kodein.dark
                    put("clip-path", "polygon(0% 5%, 100% 0%, 100% calc(100% - 4rem), 0% 100%)")
                }

                flexColumn {
                    css {
                        margin(auto)
                        padding(8.rem, 4.rem)

//                        maxWidth(980) {
//                            padding(8.rem, 0.rem, 4.rem, 0.rem)
//                        }
                    }

                    styledP {
                        css {
                            +kodein.chapo
                            specific {
                                fontWeight = FontWeight.light
                                textAlign = TextAlign.center
                            }
                            color = Color.kodein.kaumon
                            alignSelf = Align.center
                            maxWidth = 60.rem
                        }

                        +"// TODO"
                    }
                }
            }
        }

        styledDiv {
            css {
                zIndex = 14
                marginTop = (-4).rem
            }

            layerSeparator(Position.absolute, Color.kodein.orange, Color.kodein.kaumon)
        }
    }
}
