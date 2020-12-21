package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.pages.home.DepartmentStrings
import net.kodein.pages.home.DescriptionStrings
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

    override val advisory: DepartmentStrings = DepartmentStrings("ACCOMPAGNEMENT", "advisory") {
        +"..." // TODO
    }
    override val training: DepartmentStrings = DepartmentStrings("FORMATION", "training") {
        +"..." // TODO
    }
    override val development: DepartmentStrings = DepartmentStrings("DÉVELOPPEMENT", "development") {
        +"..." // TODO
    }

    override val readMore: String = "EN SAVOIR PLUS"
}
