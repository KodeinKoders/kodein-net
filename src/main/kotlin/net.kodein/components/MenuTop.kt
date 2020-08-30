package net.kodein.components

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import net.kodein.utils.flexRow
import net.kodein.utils.getValue
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.EventListener
import react.*
import react.dom.a
import styled.css


val MenuTop by functionalComponent {
    var isDark by useState(false)
    val div = useRef<HTMLDivElement?>(null)

    useEffectWithCleanup {
        val scroll = EventListener {
            val top = div.current!!.getBoundingClientRect().top
            isDark = top != 0.0
        }
        window.addEventListener("scroll", scroll)
        ({ window.removeEventListener("scroll", scroll) })
    }

    flexRow {
        ref = div
        css {
            backgroundColor = if (isDark) Color.kodein.dark else Color.kodein.cute
            position = Position.sticky
            left = 0.px
            top = 0.px
            right = 0.px
            transition(::backgroundColor, duration = .5.s)
            padding(.75.em, 3.em)
        }

        // Logo
        child(KodeinLogo) {
            attrs {
                bold = "KODEIN"
                light = "Koders"
                colorName = Color.kodein.nameOf { orange }
            }
        }

        // Menu
        flexRow(JustifyContent.flexEnd, Align.center) {
            css {
                color = Color.kodein.orange
                fontWeight = FontWeight.w700
                flexGrow = 1.0

                "a" {
                    display = Display.block
                    fontWeight = FontWeight.w700
                    padding(left = 2.em)
                    textDecoration = TextDecoration.none
                    color = Color.kodein.orange
                    cursor = Cursor.pointer
                    transition("fontWeight", duration = 0.15.s)
                }

//                    maxWidthXM {
//                        display = Display.none
//                    }
            }

            a(href = "") { +"SERVICE" }
            a(href = "") { +"TRAINING" }
            a(href = "") { +"OSS" }
            a(href = "") { +"TEAM" }
            a(href = "") { +"BLOG" }
            a(href = "") { +"CONTACT" }
        }
    }

}

//class Header : RComponent<RProps, Header.State>() {
//    interface State : RState {
//        var isPageUp: Boolean
//        var hasTransition: Boolean
//    }
//
//    override fun State.init() {
//        isPageUp = true
//        hasTransition = false
//    }
//
//
//    private val scrollCallback: (Event) -> Unit = { setHeaderBackgroundColor(false) }
//
//    private fun setHeaderBackgroundColor(firstCall: Boolean) {
//        if (!firstCall) {
//            val isPageUp = window.scrollY.toInt() < 50
//
//            if (state.isPageUp != isPageUp || state.hasTransition != !firstCall)
//                setState {
//                    this.isPageUp = isPageUp
//                    this.hasTransition = !firstCall
//                }
//        }
//    }
//
//    override fun componentDidMount() {
//        setHeaderBackgroundColor(true)
//        document.addEventListener("scroll", scrollCallback)
//    }
//
//    override fun componentWillUnmount() {
//        document.removeEventListener("scroll", scrollCallback)
//    }
//
//}