package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.pages.home.DepartmentStrings
import net.kodein.pages.home.DescriptionStrings
import net.kodein.pages.home.HomeStrings
import react.dom.br
import react.dom.span

object FrenchHomeStrings : HomeStrings {

    override val title: TextHandler = {
        +"Où que "
        span("nowrap") { +"Kotlin aille," }
        br {}
        +"vous trouverez"
        br {}
        +"les experts "
        span("nowrap") { +"qu'il vous faut!" }
    }

    override val subTitle: TextHandler = {
        +"Nous sommes Kodein Koders, "
        br("mobile") {}
        +"une startup tech "
        br("desktop") {}
        +"motivée par "
        br("mobile") {}
        +"nos idées pour le multi-platformes "
        br("desktop") {}
        +"et notre passion "
        span("nowrap") { +"pour l'état de l'art." }
    }

    override val scroll: String = "DÉFILER"

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
