package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.pages.home.HomeStrings
import react.dom.br
import react.dom.span

object EnglishHomeStrings : HomeStrings {

    override val everywhere: TextHandler = {
        +"Everywhere "
        span("nowrap") { +"Kotlin goes," }
        br {}
        +"you will find"
        br {}
        +"the experts "
        span("nowrap") { +"you need!" }
    }

}
