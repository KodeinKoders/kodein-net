package net.kodein.pages.training.fragment

import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import net.kodein.charter.kodein
import net.kodein.components.contentRow
import net.kodein.components.layerSeparator
import net.kodein.utils.Illus
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.light
import react.RProps
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledP

val Trainings = functionalComponent<RProps>("Trainings") {

    contentRow(
        backgroundColor = Color.kodein.dark, indexPosition = 3, // see [Description] that stops at index 2
        bottomLayers = listOf(Color.kodein.orange, Color.kodein.kaumon),
        additionalStyle = { marginBottom = 8.rem }
    ) {
        flexColumn {
            styledP {
                css {
                    +kodein.chapo
                    specific {
                        fontWeight = FontWeight.light
                        textAlign = TextAlign.center
                    }
                    color = Color.kodein.korail
                    alignSelf = Align.center
                    maxWidth = 60.rem
                }

                +"// TODO"
            }

            styledP {
                css {
                    +kodein.intertitre
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

            styledP {
                css {
                    +kodein.body
                    specific {
                        fontWeight = FontWeight.light
                        textAlign = TextAlign.center
                    }
                    color = Color.kodein.korail
                    alignSelf = Align.center
                    maxWidth = 60.rem
                }

                +"// TODO"
            }
        }
    }
}
