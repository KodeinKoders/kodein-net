package net.kodein.pages.home

import kotlinx.css.*
import net.kodein.components.Header
import net.kodein.components.Separator
import net.kodein.pages.home.fragment.Slogan
import net.kodein.pages.home.fragment.Performance
import net.kodein.utils.Palette
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.style
import styled.styledDiv

class Home : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        div {
            child(Header::class) {}
            child(Slogan::class) {}
            child(Separator::class) {}
            child(Performance::class) {}
            child(Separator::class) {}
            // Fake div
            styledDiv {
                css.height = 1024.px
            }
            styledDiv {
                css.backgroundColor = Palette.dark.color
                css.color = Palette.purple.color
                css.textAlign = TextAlign.center
                +"fooooooooter!"
            }
        }
    }
}