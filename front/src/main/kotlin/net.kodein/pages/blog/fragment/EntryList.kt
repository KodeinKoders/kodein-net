package net.kodein.pages.blog.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.scale
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import net.kodein.utils.flexRow
import net.kodein.utils.maxWidth
import net.kodein.utils.minWidth
import react.*
import react.dom.var_
import styled.css
import styled.styledDiv
import styled.styledImg

private external class Entry {
    val title: String
    val image: String
    val imageScale: Double?
    val url: String
}

val EntryList = functionalComponent<RProps>("ElementList") {

    var entries: List<Entry>? by useState(null)

//    var size by useState(0)

    useEffect(emptyList()) {
        window.fetch("blog.json")
            .then { response ->
                if (response.ok) {
                    response.json().then {
                        entries = (it.unsafeCast<Array<Entry>>()).toList()
                    }
                }
            }
            .catch { println("OUCH") }
    }

//    useEffectWithCleanup(emptyList()) {
//        val onResize: (Event?) -> Unit = {
//            val count = window.innerWidth / (24 * 16)
//        }
//        window.addEventListener("resize", onResize)
//        onResize(null)
//        ({ window.removeEventListener("resize", onResize) })
//    }

//    styledDiv {
//        css {
//            backgroundColor = Color.kodein.dark
//            height = 4.rem
//        }
//    }

    if (entries != null) {
        flexRow {
            css {
                flexWrap = FlexWrap.wrap
                backgroundColor = Color.kodein.dark
                position = Position.relative
            }

            fun w(count: Int) = 100.pct / count
            fun h(count: Int) = (100.vw / count - 1.rem) / 4 * 3

            entries!!.forEach { entry ->
                styledDiv {
                    css {
                        width = w(1) ; height = h(1)
                        minWidth((25 * 2).rem) { width = w(2) ; height = h(2) }
                        minWidth((25 * 3).rem) { width = w(3) ; height = h(3) }
                        minWidth((25 * 4).rem) { width = w(4) ; height = h(4) }
                        minWidth((25 * 5).rem) { width = w(5) ; height = h(5) }

                        overflow = Overflow.hidden
                        backgroundColor = Color.kodein.cute

                        cursor = Cursor.pointer
                        opacity = 0.5
                        transition(::opacity, 1.s)
                        hover {
                            opacity = 1.0
                        }
                    }
                    styledImg(src = entry.image) {
                        css {
                            width = 100.pct
                            height = 100.pct
                            objectFit = ObjectFit.cover
                            entry.imageScale?.let { transform { scale(it) } }
                        }
                    }
                }
            }

            styledDiv {
                css {
                    position = Position.absolute
                    background = "linear-gradient(180deg, transparent, ${Color.kodein.dark})"
                    pointerEvents = PointerEvents.none
                    width = 100.pct
                    bottom = 0.rem
                    height = h(1) * 2.5
                    minWidth((25 * 2).rem) { height = h(2) * 2.5 }
                    minWidth((25 * 3).rem) { height = h(3) * 2.5 }
                    minWidth((25 * 4).rem) { height = h(4) * 2.5 }
                    minWidth((25 * 5).rem) { height = h(5) * 2.5 }
                }
            }
        }
    }

}