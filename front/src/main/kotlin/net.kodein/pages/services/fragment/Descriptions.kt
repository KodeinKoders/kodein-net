package net.kodein.pages.services.fragment

import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.html.id
import net.kodein.charter.kodein
import net.kodein.components.contentRow
import net.kodein.components.imageRow
import net.kodein.pages.services.ServiceDescription
import net.kodein.useText
import net.kodein.utils.*
import net.kodein.utils.Illus.Title
import react.*
import react.dom.div
import styled.css
import styled.styledP

val Descriptions = functionalComponent<RProps>("Descriptions") {

    val strings = useText().services

    div { attrs.id = "consultancy" }
    contentRow(
        backgroundColor = Color.kodein.kaumon, indexPosition = 1,
        illustration = Illus(Title.SERVICES, Illus.Position.RIGHT, Illus.Alignment.LEFT),
        bottomLayers = listOf(Color.kodein.korail, Color.kodein.kinzolin)
    ) {
        child(Description) {
            attrs.description = strings.consultancy
        }
    }

    imageRow(
        backgroundColor = Color.kodein.kaumon, indexPosition = 2,
        illustration = Illus(Title.SERVICES, Illus.Position.CENTER, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.kinzolin, Color.kodein.kaumon)
    ) {
        display = Display.none
        maxWidth(980) { display = Display.flex }
    }
    div { attrs.id = "development" }
    contentRow(
        backgroundColor = Color.kodein.cute, indexPosition = 3,
        illustration = Illus(Title.SERVICES, Illus.Position.LEFT, Illus.Alignment.RIGHT),
        bottomLayers = listOf(Color.kodein.orange, Color.kodein.kinzolin),
    ) {
        child(Description) {
            attrs.description = strings.projectDevelopment
        }
    }
}

private interface DescriptionProps : RProps {
    var description: ServiceDescription
}
private val Description = functionalComponent<DescriptionProps>("Description") { props ->
    flexColumn {
        styledP {
            css {
                +kodein.intertitre
                alignSelf = Align.center
                textAlign = TextAlign.center
                color = Color.kodein.orange
                marginBottom = 2.rem
                borderBottom(0.05.rem, BorderStyle.solid, Color.kodein.orange)
            }
            +props.description.title
        }

        styledP {
            css {
                +kodein.body
                specific {
                    textAlign = TextAlign.center
                }
                color = Color.kodein.kyzantium
                alignSelf = Align.center
                maxWidth = 60.rem
            }

            props.description.content(this)
        }
    }
}
