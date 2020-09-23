package net.kodein.components

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RBuilder
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledSvg


private val layerHeight = 10.rem

val LayerSeparator by functionalComponent {

    styledDiv {
        css {
            width = 100.pct
            height = layerHeight
            position = Position.relative
        }

        layer(Color.white, 10) {
            moveTo(0, 0)
            verticalLineTo(63)
            lineTo(100, 5)
            verticalLineTo(0)
            closePath()
        }

        layer(Color.kodein.orange, 9) {
            moveTo(0, 2)
            verticalLineTo(66)
            lineTo(100, 25)
            verticalLineTo(2)
            closePath()
        }

        layer(Color.kodein.cute, 8) {
            moveTo(0, 4)
            verticalLineTo(70)
            lineTo(100, 50)
            verticalLineTo(4)
            closePath()
        }

        layer(Color.kodein.purple, 7) {
            moveTo(0, 6)
            verticalLineTo(90)
            lineTo(100, 35)
            verticalLineTo(6)
            closePath()
        }
    }

}

private fun RBuilder.layer(color: Color, z: Int, builder: SvgPathBuilder) {
    styledSvg {
        css {
            position = Position.absolute
            top = 0.pct
            left = 0.pct
            width = 100.pct
            height = layerHeight
            zIndex = z
            filter = "drop-shadow(0 .25rem 0.15rem ${Color.kodein.dark.withAlpha(0.2)})"
            clipPath = "polygon(0% 0%, 0% 200%, 100% 200%, 100% 0%)"
        }

        attrs {
            viewBox(0, 0, 100, 100)
            preserveAspectRatio = "none"
        }

        draw {
            path(path = builder, fill = color)
        }
    }
}
