package net.kodein.utils

import kotlinx.css.*
import kotlinx.html.DIV
import net.kodein.charter.kodein
import org.w3c.dom.HTMLElement
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledTag


fun HTMLElement.recursiveOffset(): Pair<Int, Int> {
    val parent = offsetParent as? HTMLElement ?: return offsetLeft to offsetTop
    return parent.recursiveOffset().let { (it.first + offsetLeft) to (it.second + offsetTop) }
}

inline fun RBuilder.flexColumn(justifyContent: JustifyContent? = null, alignItems: Align? = null, block: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
    css {
        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent?.let { this.justifyContent = it }
        alignItems?.let { this.alignItems = it }
    }

    block()
}

inline fun RBuilder.flexRow(justifyContent: JustifyContent? = null, alignItems: Align? = null, block: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
    css {
        display = Display.flex
        flexDirection = FlexDirection.row
        justifyContent?.let { this.justifyContent = it }
        alignItems?.let { this.alignItems = it }
    }

    block()
}
