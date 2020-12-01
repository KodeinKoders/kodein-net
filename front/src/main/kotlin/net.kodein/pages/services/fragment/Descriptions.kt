package net.kodein.pages.services.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.borderBottom
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.components.ContentRow
import net.kodein.components.contentRow
import net.kodein.components.imageRow
import net.kodein.components.layerSeparator
import net.kodein.utils.*
import net.kodein.utils.Illus.Title
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP

val Descriptions = functionalComponent<RProps>("Descriptions") {

    contentRow(
        backgroundColor = Color.kodein.kaumon, indexPosition = 1,
        illustration = Illus(Title.SERVICES, Illus.Position.RIGHT, Illus.Alignment.LEFT),
        bottomLayers = listOf(Color.kodein.korail, Color.kodein.kinzolin)
    ) {
        child(Description) {
            attrs.title = "Consultancy"

            +"""That you are starting a new project,
        |looking for expertise, or in the middle of one other
        |considering taking some accompaniment to validate
        |or boost your development process, we can guide you through,
        |either it's back-end or mobile, using Kotlin.""".trimMargin()
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

    contentRow(
        backgroundColor = Color.kodein.cute, indexPosition = 3,
        illustration = Illus(Title.SERVICES, Illus.Position.LEFT, Illus.Alignment.RIGHT),
        bottomLayers = listOf(Color.kodein.orange, Color.kodein.kinzolin),
    ) {
        child(Description) {
            attrs.title = "Project Development"

            +"""We can help you reach your goals by taking care
            |of the technical challenges you have. Using Scrum methodology,
            |we will provide you a nice and easy way to follow up on
            |your project while we are taking care of everything sprint by sprint.
            |Focused on running Kotlin code everywhere, our experts take advantage
            |of Kotlin/Multiplatform to be able to write and test business logic
            |efficiently once and use it on every targeted system (Backend / Mobile / Web / Desktop)""".trimMargin()
        }
    }
}

private interface DescriptionProps : RProps {
    var title: String
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
            +props.title
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

            props.children()
        }
    }
}
