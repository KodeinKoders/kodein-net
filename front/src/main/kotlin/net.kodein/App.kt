package net.kodein

import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import net.kodein.pages.home.Home
import net.kodein.pages.services.Services
import net.kodein.utils.minSize
import org.w3c.dom.get
import react.*
import react.dom.render


val appGlobalStyle: CSSBuilder.() -> Unit = {
    universal {
        margin(0.px)
        padding(0.px)
    }

    html {
        minSize(min = 360) { fontSize = 6.px }
        minSize(min = 480) { fontSize = 8.px }
        minSize(min = 680) { fontSize = 8.px }
        minSize(min = 768) { fontSize = 9.px }
        minSize(min = 880) { fontSize = 10.px }
        minSize(min = 980) { fontSize = 12.px }
        minSize(min = 1024) { fontSize = 14.px }
        minSize(min = 1920) { fontSize = 16.px }
        minSize(min = 2500) { fontSize = 18.px }
//                minSize(min = 3000) { fontSize = 22.px }
    }

    body {
        fontFamily = "Picon, Arial, sans-serif"
    }

    a {
        textDecoration = TextDecoration.none
        color = Color.inherit
        transition(::color, 0.2.s)

        hover {
            color = Color.kodein.purple
        }

    }
}

data class Page<P : RProps>(
        val id: String,
        val component: () -> FunctionalComponent<P>,
        val props: P.() -> Unit = {}
)

val appPages = listOf(
        Page("index", { Home }),
        Page("services", { Services} ),
)

fun renderApp() {
    val element = document.getElementById("page") ?: error("Could not find page element")
    val id = element.attributes["data-page"]?.value ?: error("Page element has no data-page attribute")
    val page = appPages.find { it.id == id } ?: error("Could not find page $id")

    console.log(page)

    render(element) {
        child(page.component()) { page.props(attrs) }
    }

    console.log("SUCCESS")
}
