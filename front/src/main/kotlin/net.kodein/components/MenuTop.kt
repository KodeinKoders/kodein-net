package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.charter.kodein
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.getValue
import org.w3c.dom.HTMLDivElement
import react.*
import react.dom.a
import styled.css
import styled.styledA
import styled.styledDiv


val MenuTop by functionalComponent {
    val isOpen by useState(false)
    val menuButton = useRef<HTMLDivElement?>(null)

//    var isDark by useState(false)
//    val div = useRef<HTMLDivElement?>(null)

//    useEffectWithCleanup {
//        val scroll = EventListener {
//            val top = div.current!!.getBoundingClientRect().top
//            isDark = top != 0.0
//        }
//        window.addEventListener("scroll", scroll)
//        ({ window.removeEventListener("scroll", scroll) })
//    }

    flexColumn {
        css {
            position = Position.sticky
            left = 0.px
            top = 0.px
            right = 0.px
            zIndex = 1000
            boxShadow(Color.black.withAlpha(0.25), 0.rem, 0.2.rem, blurRadius = 1.5.rem)
        }

        flexRow {
//            ref = div
            css {
//                backgroundColor = if (isDark) Color.kodein.dark else Color.kodein.cute
//                transition(::backgroundColor, duration = .5.s)
//                if (!isDark) boxShadow(Color.black, offsetY = 0.2.rem)

                backgroundColor = Color.kodein.dark
                padding(0.75.rem, 3.rem)
                fontSize = .8.rem
            }

            // Logo
            child(KodeinLogo) {
                attrs {
                    logo = "orange-fat"
                    bold = "KODEIN"
                    light = "Koders"
                    color = Color.kodein.orange
                }
            }

            flexRow(JustifyContent.flexEnd, Align.center) {
                css {
                    color = Color.kodein.orange
                    fontWeight = FontWeight.w700
                    flexGrow = 1.0

                    "a" {
                        display = Display.block
                        fontWeight = FontWeight.w700
                        marginLeft = 2.em
                        textDecoration = TextDecoration.none
                        color = Color.kodein.orange
                        cursor = Cursor.pointer
                        transition("fontWeight", duration = 0.15.s)
                    }
                }

                a(href = "") { +"SERVICES" }
                a(href = "") { +"KOTLIN" }
                a(href = "") { +"TRAINING" }
                a(href = "") { +"OSS" }
                a(href = "") { +"TEAM" }
                a(href = "") { +"BLOG" }
                a(href = "") { +"CONTACT" }
                styledA(href = "") {
                    css {
                        border(.1.rem, BorderStyle.solid, Color.kodein.orange)
                        borderRadius = 1.rem
                        padding(.3.rem, .6.rem)
                    }
                    +"GUYS: WE'RE HIRING!"
                }
            }
        }

        child(Separator) {
            attrs.height = 0.3.em
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
