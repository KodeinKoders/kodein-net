package net.kodein.pages.training.fragment

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.components.contentRow
import net.kodein.components.imageRow
import net.kodein.utils.Illus
import net.kodein.utils.flexColumn
import net.kodein.utils.light
import net.kodein.utils.maxWidth
import react.RProps
import react.functionalComponent
import styled.css
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
        backgroundColor = Color.kodein.kyzantium, indexPosition = 2,
        illustration = Illus(Illus.Title.TRAINING, Illus.Position.RIGHT, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.purple)
    ) {
        flexColumn {
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

        }
    }
}
