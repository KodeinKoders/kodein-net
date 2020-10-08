package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.EventListener
import react.*
import react.dom.a
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledSpan


val MenuTop by functionalComponent {
    val menuContainer = useRef<HTMLDivElement?>(null)
    val menuButton = useRef<HTMLDivElement?>(null)

    var isMobileMenuOpen by useState(false)
    var menuContainerHeight by useState(0.0)

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

    useEffectWithCleanup {
        val openCloseMenu = EventListener {
            console.log("CLICKED")
        }
        menuButton.current!!.addEventListener("mouseup", openCloseMenu)
        ({ menuButton.current!!.removeEventListener("mouseup", openCloseMenu) })
    }

    flexColumn {
        ref = menuContainer
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
                    minWidth(1025) { display = Display.none }
                    flexGrow = 1.0
                }

                flexColumn(justifyContent = JustifyContent.center) {
                    ref = menuButton
                    css {
                        "span" {
                            display = Display.block
                            width = 30.px
                            height = 3.px
                            margin(2.px)
                            backgroundColor = Color.kodein.orange
                            borderRadius = 3.px
                            transition(::transform, duration = .3.s, Timing.materialAcceleration)
                            transition(::background, duration = .3.s, Timing.easeInOut)
                            transition(::opacity, duration = .3.s, Timing.easeInOut)
                        }

                        "span.first" {
                            transform {
                                rotate(45.deg)
                                translate(4.px, 5.px)
                            }
                        }
                        "span.middle" { opacity = 0 }
                        "span.last" {
                            transform {
                                rotate((-45).deg)
                                translate(6.px, (-5).px)
                            }
                        }
                    }

//                    attrs.onClickFunction = {
//                        menuButton.current!!.children[0]?.classList?.toggle("first")
//                        menuButton.current!!.children[1]?.classList?.toggle("middle")
//                        menuButton.current!!.children[2]?.classList?.toggle("last")
//                    }

                    styledSpan {
                        css { if (isMobileMenuOpen) +"first" }
                    }
                    styledSpan {
                        css { if (isMobileMenuOpen) +"middle" }
                    }
                    styledSpan {
                        css { if (isMobileMenuOpen) +"last" }
                    }
                }
            }

            // Menu > 1024
            menuNavigation() { maxWidth(1024) { display = Display.none } }
        }

        child(Separator) {
            attrs.height = 0.3.em
        }
    }

    flexRow(JustifyContent.center, Align.center) {
        css {
            minWidth(1025) { display = Display.none }

            position = Position.sticky
            left = 0.px
            top = 0.px
            right = 0.px
            zIndex = 999
            boxShadow(Color.black.withAlpha(0.25), 0.rem, 0.2.rem, blurRadius = 1.5.rem)

            transition(::height, duration = .5.s, Timing.linear)
            transition(::visibility, duration = 0.s)
            transition(::opacity, duration = .5.s, Timing.easeInOut)

            if (isMobileMenuOpen) {
                display = Display.flex
                opacity = 1
                visibility = Visibility.visible
            } else {
                display = Display.none
                opacity = 0
                visibility = Visibility.hidden
            }
        }

        menuNavigation(isMobile = true) {
            backgroundColor = Color.kodein.kaumon
            "a" { margin(.5.em) }
        }
    }
}

private fun RBuilder.menuNavigation(
        isMobile: Boolean = false,
        additionalStyle : RuleSet = {}
) {
    val foregroundColor = if (isMobile)  Color.kodein.kinzolin else  Color.kodein.orange
    val justify = if (isMobile) JustifyContent.center else JustifyContent.flexEnd

    flexRow {
        css {
            color = foregroundColor
            fontWeight = FontWeight.w700
            justifyContent = justify
            alignItems = Align.center
            flexGrow = 1.0
            if (isMobile) flexDirection = FlexDirection.column

            "a" {
                display = Display.block
                fontWeight = FontWeight.w700
                marginLeft = 2.em
                textDecoration = TextDecoration.none
                color = foregroundColor
                cursor = Cursor.pointer
                transition("fontWeight", duration = 0.15.s)
            }
            +additionalStyle
        }

        a(href = "") { +"SERVICES" }
        if (isMobile) menuSeparator()
        a(href = "") { +"KOTLIN" }
        if (isMobile) menuSeparator()
        a(href = "") { +"TRAINING" }
        if (isMobile) menuSeparator()
        a(href = "") { +"OSS" }
        if (isMobile) menuSeparator()
        a(href = "") { +"TEAM" }
        if (isMobile) menuSeparator()
        a(href = "") { +"BLOG" }
        if (isMobile) menuSeparator()
        a(href = "") { +"CONTACT" }
        if (isMobile) menuSeparator()
        styledA(href = "") {
                css {
                    if (!isMobile) {
                        border(.1.rem, BorderStyle.solid, foregroundColor)
                        borderRadius = 1.rem
                        padding(.3.rem, .6.rem)
                    }
                }
                +"GUYS: WE'RE HIRING!"
            }
    }
}
private fun RBuilder.menuSeparator() {
    styledDiv {
        css {
            width = 95.pct
            opacity = .5
            backgroundColor = Color.kodein.kamethiste
            height = 0.05.rem
        }
    }
}
