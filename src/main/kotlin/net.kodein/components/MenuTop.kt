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

    useEffectWithCleanup {
        val openCloseMenu = EventListener {
            isMobileMenuOpen = !isMobileMenuOpen
        }
        menuButton.current!!.addEventListener("mouseup", openCloseMenu)
        ({ menuButton.current!!.removeEventListener("mouseup", openCloseMenu) })
    }

    useEffect { menuContainerHeight = menuContainer.current!!.getBoundingClientRect().height }

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
            css {
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
                            transition(::transform, duration = .1.s, Timing.materialAcceleration)
                            transition(::background, duration = .1.s, Timing.easeInOut)
                            transition(::opacity, duration = .1.s, Timing.easeInOut)
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

        child(Separator) {
            attrs.height = 0.3.em
        }
    }

    flexRow(JustifyContent.center, Align.center) {
        css {
            minWidth(1025) { display = Display.none }

            position = Position.sticky
            left = 0.px
            top = menuContainerHeight.px
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

        child(MenuNavigation) {
            attrs.isMobile = true
        }
    }
}

//


interface MenuProps : RProps {
    var isMobile: Boolean
    var additionalStyle: RuleSet?
}

val MenuNavigation by functionalComponent<MenuProps> { props ->
    val foregroundColor = if (props.isMobile)  Color.kodein.kinzolin else  Color.kodein.orange
    val justify = if (props.isMobile) JustifyContent.center else JustifyContent.flexEnd

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
                    fontSize = 1.5.rem
                    flexDirection = FlexDirection.column
                    margin(.5.em)
                }
            }

            props.additionalStyle?.invoke(this)
        }

        a(href = "") { +"SERVICES" }
        if (props.isMobile) menuSeparator()
        a(href = "") { +"KOTLIN" }
        if (props.isMobile) menuSeparator()
        a(href = "") { +"TRAINING" }
        if (props.isMobile) menuSeparator()
        a(href = "") { +"OSS" }
        if (props.isMobile) menuSeparator()
        a(href = "") { +"TEAM" }
        if (props.isMobile) menuSeparator()
        a(href = "") { +"BLOG" }
        if (props.isMobile) menuSeparator()
        a(href = "") { +"CONTACT" }
        if (props.isMobile) menuSeparator()
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
