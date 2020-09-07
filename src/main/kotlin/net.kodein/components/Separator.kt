package net.kodein.components

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.utils.getValue
import react.*
import styled.css
import styled.styledDiv


interface SeparatorProps : RProps {
    var height: LinearDimension?
}

val Separator by functionalComponent<SeparatorProps> { props ->
    styledDiv {
        css {
            background = "linear-gradient(90deg, ${Color.kodein.orange}, ${Color.kodein.purple})"
            height = props.height ?: 0.6.rem
        }
    }
}