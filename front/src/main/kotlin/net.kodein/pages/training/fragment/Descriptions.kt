package net.kodein.pages.training.fragment

import kotlinx.css.*
import kotlinx.html.id
import net.kodein.charter.kodein
import net.kodein.components.contentRow
import net.kodein.components.imageRow
import net.kodein.useText
import net.kodein.utils.Illus
import net.kodein.utils.flexColumn
import net.kodein.utils.light
import net.kodein.utils.maxWidth
import react.RProps
import react.dom.div
import react.functionalComponent
import styled.css
import styled.styledH3
import styled.styledP

val Description = functionalComponent<RProps>("Description") {
    val strings = useText().training

    imageRow(
        backgroundColor = Color.kodein.kaumon, indexPosition = 1,
        illustration = Illus(Illus.Title.TRAINING, Illus.Position.CENTER, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.kaumon)
    ) {
        display = Display.none
        maxWidth(980) { display = Display.flex }
    }

    div { attrs.id = "description" }

    contentRow(
        backgroundColor = Color.kodein.kyzantium, indexPosition = 2,
        illustration = Illus(Illus.Title.TRAINING, Illus.Position.RIGHT, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.purple)
    ) {
        flexColumn {
            css {
                "p" {
                    +kodein.body
                    color = Color.kodein.korail
                    paddingBottom = 0.5.em
                }
            }
            styledH3 {
                css {
                    +kodein.chapo
                    color = Color.kodein.kaumon
                    paddingBottom = 0.5.em
                }

                strings.descriptionTitle(this)
            }

            strings.descriptionText(this)
        }
    }
}
