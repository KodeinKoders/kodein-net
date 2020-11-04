package net.kodein.pages.training.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.components.contentRow
import net.kodein.components.imageRow
import net.kodein.components.layerSeparator
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP

val Description = functionalComponent<RProps>("Description") {

    imageRow(
        backgroundColor = Color.kodein.kaumon, indexPosition = 1,
        illustration = Illus(Illus.Title.TRAINING, Illus.Position.CENTER, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.kaumon)
    ) {
        display = Display.none
        maxWidth(980) { display = Display.flex }
    }

    contentRow(
        backgroundColor = Color.kodein.kinzolin, indexPosition = 2,
        illustration = Illus(Illus.Title.TRAINING, Illus.Position.RIGHT, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.purple)
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
