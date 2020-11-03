package net.kodein.pages.blog.fragment

import kotlinx.browser.window
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.height
import kotlinx.css.pct
import net.kodein.charter.kodein
import react.*
import styled.css
import styled.styledDiv

val ElementList = functionalComponent<RProps>("ElementList") {

    data class Entry(val title: String, val image: String, val url: String)

    var entries: List<Entry>? by useState(null)

    useEffect(emptyList()) {
        window.fetch("blog.json")
            .then { println(it) }
            .catch { println("OUCH") }
    }

    styledDiv {
        css {
            backgroundColor = Color.kodein.dark
            height = 100.pct
        }
    }

}