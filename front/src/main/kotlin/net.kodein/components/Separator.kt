package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import react.*
import styled.css
import styled.styledDiv


interface SeparatorProps : RProps {
    var height: LinearDimension?
    var css: (CSSBuilder.() -> Unit)?
}

val Separator = functionalComponent<SeparatorProps>("Separator") { props ->
    styledDiv {
        css {
            background = "linear-gradient(90deg, ${Color.kodein.orange}, ${Color.kodein.purple})"
            height = props.height ?: 0.6.rem
            props.css?.invoke(this)
        }
    }
}
