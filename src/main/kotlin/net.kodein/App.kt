package net.kodein

import net.kodein.pages.home.Home
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        child(Home::class){}
    }
}