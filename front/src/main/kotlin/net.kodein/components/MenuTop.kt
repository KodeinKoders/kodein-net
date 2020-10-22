package net.kodein.components

import kotlinx.browser.window
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

interface MenuTopProps : RProps {
    var animated: Boolean
    var backgroundColor : Color?
}

val MenuTop = functionalComponent<MenuTopProps>("MenuTop") { props ->
    val menuContainer = useRef<HTMLDivElement?>(null)
    val menuButton = useRef<HTMLDivElement?>(null)

    var isTop by useState(props.animated)

    var isMobileMenuOpen by useState(false)
    var menuContainerHeight by useState(0.0)

    useEffectWithCleanup {
        val openCloseMenu = EventListener {
            isMobileMenuOpen = !isMobileMenuOpen
        }
        menuButton.current!!.addEventListener("mouseup", openCloseMenu)
        ({ menuButton.current!!.removeEventListener("mouseup", openCloseMenu) })
    }

    if (props.animated) {
        useEffectWithCleanup {
            val scroll = EventListener {
                val top = menuContainer.current!!.parentElement?.getBoundingClientRect()?.top
                isTop = top == 0.0
            }
            window.addEventListener("scroll", scroll)
            ({ window.removeEventListener("scroll", scroll) })
        }
    }
    useEffect { menuContainerHeight = menuContainer.current!!.getBoundingClientRect().height }

    flexColumn {
        ref = menuContainer
        css {
            if (isTop) {
                backgroundColor = props.backgroundColor ?: Color.transparent
                paddingTop = 1.5.rem
            } else {
                backgroundColor = Color.kodein.dark
                paddingTop = 0.rem
                boxShadow(Color.black.withAlpha(0.25), 0.rem, 0.2.rem, blurRadius = 1.5.rem)
            }

            transition(::padding, duration = .5.s, timing = Timing.linear)
            transition(::background, duration = .5.s)
            position = Position.sticky
            left = 0.px
            top = 0.px
            right = 0.px
            zIndex = 999
        }

        flexRow {
            css {
                padding(0.75.rem, 3.rem)
                fontSize = .8.rem

                maxWidth(768) { padding(0.75.rem, 2.rem) }
                maxWidth(480) { padding(0.75.rem, 1.rem) }
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

            // Menu > 1024
            flexRow {
                css {
                    flexGrow = 1.0
                    maxWidth(1024) { display = Display.none }
                }
                child(MenuNavigation)
            }

            // Menu < 1024
            flexRow(JustifyContent.flexEnd, Align.center) {
                css {
                    flexGrow = 1.0
                    zIndex = 1001
                    minWidth(1025) { display = Display.none }
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
                            transition(::opacity, duration = .3.s, Timing.easeInOut)
                        }

                        "span.first" {
                            transform {
                                translate(0.px, 7.px)
                                rotate((-45).deg)
                            }
                        }
                        "span.middle" { opacity = 0 }
                        "span.last" {
                            transform {
                                translate(0.px, (-7).px)
                                rotate(45.deg)
                            }
                        }
                    }

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
        }

        child(Separator) { attrs.height = if (isTop) 0.rem else 0.3.em }

        flexRow(JustifyContent.center, Align.center) {
            css {
                backgroundColor = Color.kodein.kaumon
                position = Position.fixed
                left = 100.pct
                top = 0.px
                right = 0.px
                height = 100.pct
                width = 100.pct
                zIndex = 1000

                minWidth(1025) { display = Display.none }

                boxShadow(Color.black.withAlpha(0.25), 0.rem, 0.2.rem, blurRadius = 1.5.rem)

                transition(::left, duration = .3.s, Timing.linear)

                if (isMobileMenuOpen) {
                    left = 0.pct
                } else {
                    left = 100.pct
                }
            }

            child(MenuNavigation) {
                attrs.isMobile = true
            }
        }
    }
}

//


interface MenuProps : RProps {
    var isMobile: Boolean
    var additionalStyle: RuleSet?
}

val MenuNavigation = functionalComponent<MenuProps>("MenuNavigation") { props ->
    val foregroundColor = if (props.isMobile)  Color.kodein.kinzolin else  Color.kodein.orange
    val justify = if (props.isMobile) JustifyContent.flexStart else JustifyContent.flexEnd

    flexRow {
        css {
            color = foregroundColor
            fontWeight = FontWeight.w700
            justifyContent = justify
            alignItems = Align.center
            flexGrow = 1.0
            if (props.isMobile) flexDirection = FlexDirection.column

            "a" {
                display = Display.block
                fontWeight = FontWeight.w700
                marginLeft = 2.em
                textDecoration = TextDecoration.none
                color = foregroundColor
                cursor = Cursor.pointer
                transition("fontWeight", duration = 0.15.s)
            }

            if (props.isMobile) {
                backgroundColor = Color.kodein.kaumon
                "a" {
                    flexDirection = FlexDirection.column
                    margin(.75.em)
                    borderBottom(0.1.rem, BorderStyle.solid, Color.kodein.kinzolin)
                }
            }

            props.additionalStyle?.invoke(this)
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
                if (!props.isMobile) {
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
