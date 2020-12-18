package net.kodein.pages.oss

import kotlinx.css.*
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.*
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.oss.fragment.FrameworkLayers
import net.kodein.pages.oss.fragment.FrameworkOnion
import net.kodein.useText
import net.kodein.utils.Illus
import net.kodein.utils.maxWidth
import react.RProps
import react.child
import react.dom.a
import react.dom.b
import react.dom.br
import react.functionalComponent
import styled.styledDiv


val Oss = functionalComponent<RProps> {

    val strings = useText().oss

    child(MenuTop) {
        attrs {
            animated = true
            topMargin = 1.5.rem
            backgroundColor = Color.kodein.kyzantium
        }
    }

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.kyzantium,
                text = Color.kodein.kaumon,
                title = Color.kodein.orange,
                overTitle = Color.kodein.korail,
                layers = listOf(Color.kodein.krouille, Color.kodein.orange)
            )
            content = strings.cover
        }
    }

    contentRow(
        backgroundColor = Color.kodein.kyzantium,
        indexPosition = 1,
        illustration = Illus(Illus.Title.OSS, Illus.Position.RIGHT, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.kuivre),
        additionalStyle = {
          backgroundColor = Color.kodein.kyzantium
        },
        noPadding = true
    ) {
        child(FrameworkLayers)
    }

    imageRow(
        backgroundColor = Color.kodein.kyzantium,
        indexPosition = 2,
        illustration = Illus(Illus.Title.OSS, Illus.Position.CENTER, Illus.Alignment.CENTER),
        bottomLayers = listOf(Color.kodein.purple)
    ) {
        display = Display.none
        maxWidth(980) { display = Display.flex }
    }

    child(FrameworkOnion)

    child(Cover) {
        attrs {
            colors = CoverPalette(
                backgroundColor = Color.kodein.dark,
                title = Color.kodein.orange,
                layers = listOf(Color.kodein.orange, Color.kodein.kaumon, Color.kodein.kinzolin)
            )
            content = strings.wantMore
        }
    }

    styledDiv { css.height = 10.rem }

    child(ContactUs)

    child(Footer)


}