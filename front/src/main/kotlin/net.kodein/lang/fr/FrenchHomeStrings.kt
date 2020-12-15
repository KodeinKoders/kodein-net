package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.pages.home.HomeStrings
import react.dom.br
import react.dom.span

object FrenchHomeStrings : HomeStrings {

    override val everywhere: TextHandler = {
        +"Où que "
        span("nowrap") { +"Kotlin aille," }
        br {}
        +"vous trouverez"
        br {}
        +"les experts "
        span("nowrap") { +"qu'il vous faut!" }
    }

}
