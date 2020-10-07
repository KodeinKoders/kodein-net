package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.get
import react.*
import react.dom.*
import styled.*


val MenuTop by functionalComponent {
    var isOpen by useState(false)
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

            // Menu < 1024
            flexRow(JustifyContent.flexEnd, Align.center) {
                css {
                    flexGrow = 1.0
                    minWidth(1024) { display = Display.none }
                }

                flexColumn(justifyContent = JustifyContent.center) {
                    ref = menuButton
                    css {
                        "span" {
                            display = Display.block
                            width = 30.px
                            height = 2.px
                            margin(2.px)
                            backgroundColor = Color.kodein.orange
                            borderRadius = 3.px
                            zIndex= 1
                            put("transition",
                                    """transform 0.5s cubic-bezier(0.77,0.2,0.05,1.0), 
                                    |background 0.5s cubic-bezier(0.77,0.2,0.05,1.0), 
                                    |opacity 0.55s ease;""".trimMargin())
                        }

                        "span.first" {
                            backgroundColor = Color.kodein.kaumon
                            put("transform", "rotate(45deg) translate(4px, 4px);")
                        }
                        "span.middle" { opacity = 0 }
                        "span.last" {
                            backgroundColor = Color.kodein.kaumon
                            put("transform", "rotate(-45deg) translate(5px, -4px);")
                        }
                    }
                    attrs.onClickFunction = {
                        menuButton.current!!.children[0]?.classList?.toggle("first")
                        menuButton.current!!.children[1]?.classList?.toggle("middle")
                        menuButton.current!!.children[2]?.classList?.toggle("last")
                    }

                    span { }
                    span { }
                    span { }
                }
            }

            // Menu > 1024
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
                    maxWidth(1025) { display = Display.none }
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
