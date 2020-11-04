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
    var position: Position
    var colors: List<Color>
}

fun RBuilder.layerSeparator(position: Position, vararg colors: Color) {
    child(LayerSeparator) {
        attrs {
            this.position = position
            this.colors = colors.toList()
        }
    }
}

val LayerSeparator = functionalComponent<LayerSeparatorProps>("LayerSeparator") { props ->

    data class LayerParams(
        val color: Color,
        val coords: Pair<Int, Int>
    )

    val params by useState {
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
        val layers = props.colors.drop(1).map {
            val i = nextLayer(base)
            base = max(base.first, i.first) to max(base.second, i.second)
            LayerParams(it, i)
        }

        listOf(LayerParams(props.colors.first(), i0)) + layers
    }

    styledDiv {
        css {
            width = 100.pct
            height = 10.rem
            marginTop = (-1).px
            position = props.position
            maxWidth(1024) { height = 8.rem }
            maxWidth(516) { height = 6.rem }
            pointerEvents = PointerEvents.none
        }

        params.forEachIndexed { index, layerParams ->
            layer(layerParams.color, 100 - index) {
                moveTo(0, index * 2)
                verticalLineTo(layerParams.coords.first)
                lineTo(100, layerParams.coords.second)
                verticalLineTo(index * 2)
                closePath()
            }
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
            clipPath = "polygon(0% 0%, 0% 200%, 100% 200%, 100% 0%)"
            +kodein.dropShadow
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
