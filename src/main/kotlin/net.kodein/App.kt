package net.kodein

import net.kodein.pages.home.Home
import react.*

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        child(Home)
    }
}