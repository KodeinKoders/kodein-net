package net.kodein.utils

import kotlinx.css.*
import kotlinx.html.DIV
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledTag

//inline fun RBuilder.wrapper(block: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
//    css {
//        width = 1280.px
//        maxWidth = 1280.px
//        marginLeft = LinearDimension.auto
//        marginRight = LinearDimension.auto
//        display = Display.flex
//        flexDirection = FlexDirection.row
//    }
//
//    block()
//}