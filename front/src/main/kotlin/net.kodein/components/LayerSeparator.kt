package net.kodein.components

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.*
import styled.css
import styled.styledDiv
import styled.styledSvg
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random


interface LayerSeparatorProps : RProps {
    var i0Color: Color
    var i1Color: Color
    var i2Color: Color
    var i3Color: Color
}

fun RBuilder.layerSeparator(i0: Color, i1: Color, i2: Color, i3: Color) {
    child(LayerSeparator) {
        attrs.i0Color = i0
        attrs.i1Color = i1
        attrs.i2Color = i2
        attrs.i3Color = i3
    }
}

val LayerSeparator = functionalComponent<LayerSeparatorProps>("LayerSeparator") { props ->

    data class Params(
        val i0: Pair<Int, Int>,
        val i1: Pair<Int, Int>,
        val i2: Pair<Int, Int>,
        val i3: Pair<Int, Int>,
    )

    val params by useState {
        // Original parameters
//        Params(
//            i0 = 5 to 63,
//            i1 = 66 to 25,
//            i2 = 70 to 50,
//            bottomRight = 35
//        )

        fun nextLayer(base: Pair<Int, Int>): Pair<Int, Int> {
            val l1 = Random.nextInt(base.first + 10, base.first + 20 + 1)
            return if (l1 <= 90 && Random.nextBoolean()) {
                l1 to Random.nextInt(base.second - 20, base.second + 25 + 1)
            } else {
                val l2 = min(Random.nextInt(base.first - 25, base.first + 20 + 1), 90)
                l2 to Random.nextInt(base.second + 10, base.second + 20 + 1)
            }
        }

        val i0 = Random.nextInt(40, 70) to 5
        var base = i0
        val i1 = nextLayer(base)
        base = max(base.first, i1.first) to max(base.second, i1.second)
        val i2 = nextLayer(base)
        base = max(base.first, i2.first) to max(base.second, i2.second)
        val i3 = nextLayer(base)

        Params(i0, i1, i2, i3)
    }

    styledDiv {
        css {
            width = 100.pct
            height = 10.rem
            position = Position.relative
            maxWidth(1024) { height = 8.rem }
            maxWidth(516) { height = 6.rem }
        }

        layer(props.i0Color, 10) {
            moveTo(0, 0)
            verticalLineTo(params.i0.first)
            lineTo(100, params.i0.second)
            verticalLineTo(0)
            closePath()
        }

        layer(props.i1Color, 9) {
            moveTo(0, 2)
            verticalLineTo(params.i1.first)
            lineTo(100, params.i1.second)
            verticalLineTo(2)
            closePath()
        }

        layer(props.i2Color, 8) {
            moveTo(0, 4)
            verticalLineTo(params.i2.first)
            lineTo(100, params.i2.second)
            verticalLineTo(4)
            closePath()
        }

        layer(props.i3Color, 7) {
            moveTo(0, 6)
            verticalLineTo(params.i3.first)
            lineTo(100, params.i3.second)
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
            height = 100.pct
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
