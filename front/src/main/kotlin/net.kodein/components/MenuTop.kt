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
import react.dom.span
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledSpan

interface MenuTopProps : RProps {
    var animated: Boolean
    var topMargin: LinearDimension?
    var backgroundColor : Color?
}

val MenuTop = functionalComponent<MenuTopProps>("MenuTop") { props ->
    val menuContainer = useRef<HTMLDivElement?>(null)
    val mobileMenuButton = useRef<HTMLDivElement?>(null)

    var isTop by useState(props.animated)

    var isMobileMenuOpen by useState(false)

    useEffectWithCleanup {
        val openCloseMenu = EventListener {
            isMobileMenuOpen = !isMobileMenuOpen
        }
        mobileMenuButton.current!!.addEventListener("mouseup", openCloseMenu)
        ({ mobileMenuButton.current!!.removeEventListener("mouseup", openCloseMenu) })
    }

    if (props.animated) {
        useEffectWithCleanup {
            val scroll = EventListener {
                val top = menuContainer.current!!.getBoundingClientRect().top.toInt()
                isTop = top != 0
            }
            window.addEventListener("scroll", scroll)
            ({ window.removeEventListener("scroll", scroll) })
        }
    }

    props.topMargin?.let { size ->
        styledDiv {
            css {
                height = 1.5.rem
                backgroundColor = if (isTop || isMobileMenuOpen) props.backgroundColor ?: Color.transparent else Color.kodein.dark
                transition(::background, duration = .5.s)
            }
        }
    }

    flexColumn {
        ref = menuContainer
        css {
            if (isTop || isMobileMenuOpen) {
                backgroundColor = props.backgroundColor ?: Color.transparent
            } else {
                backgroundColor = Color.kodein.dark
                boxShadow(Color.black.withAlpha(0.25), 0.rem, 0.2.rem, blurRadius = 1.5.rem)
            }

            transition(::background, duration = .5.s)
            position = Position.sticky
            left = 0.px
            top = 0.px
            right = 0.px
            zIndex = 999
        }

        child(MenuContent) {
            attrs.mobileMenuButton = mobileMenuButton
            attrs.isMobileMenuOpen = isMobileMenuOpen
        }

        child(Separator) {
            attrs.height = 0.3.em
            attrs.css = {
                opacity = if (isTop) 0.0 else 1.0
                transition(::opacity, duration = .3.s, Timing.linear)
            }
        }

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

interface MenuContentProps : RProps{
    var mobileMenuButton: RMutableRef<HTMLDivElement?>
    var isMobileMenuOpen: Boolean
}

val MenuContent = functionalComponent<MenuContentProps>("MenuContent") { props ->
    flexRow {
        css {
            padding(0.75.rem, 2.5.rem, 0.75.rem, 3.rem)
            fontSize = .8.rem

            maxWidth(768) { padding(0.75.rem, 2.rem) }
            maxWidth(480) { padding(0.75.rem, 1.rem) }
            maxWidth(350) { padding(0.75.rem, 0.75.rem) }
        }

        // Logo
        styledA(href = "index.html") {
            css {
                display = Display.flex
                flexDirection = FlexDirection.column
                "span.underline" {
                    maxWidth(1024) { display = Display.none }
                    display = Display.block
                    width = (100.pct - 3.5.rem + 0.4.rem) * 0.2
                    marginRight = (100.pct - 3.5.rem + 0.4.rem) * 0.4
                    alignSelf = Align.flexEnd
                    opacity = 0.0
                    height = 0.1.rem
                    backgroundColor = Color.kodein.orange
                    transition(::width, 0.5.s)
                    transition(::marginRight, 0.5.s)
                    transition(::opacity, 0.5.s)
                }
                hover {
                    "span.underline" {
                        width = 100.pct - 3.5.rem + 0.4.rem
                        marginRight = (-0.2).rem
                        opacity = 1.0
                    }
                }
            }
            child(KodeinLogo) {
                attrs {
                    logo = "orange-fat"
                    bold = "KODEIN"
                    light = "Koders"
                    color = Color.kodein.orange
                }
            }
            span("underline") {}
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
                ref = props.mobileMenuButton
                css {
                    cursor = Cursor.pointer
                    padding(0.55.rem)

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

                span(if (props.isMobileMenuOpen) "first" else null) {}
                span(if (props.isMobileMenuOpen) "middle" else null) {}
                span(if (props.isMobileMenuOpen) "last" else null) {}
            }
        }
    }}

//


interface MenuProps : RProps {
    var isMobile: Boolean
}

val MenuNavigation = functionalComponent<MenuProps>("MenuNavigation") { props ->
    val foregroundColor = if (props.isMobile)  Color.kodein.kinzolin else  Color.kodein.orange
    val justify = if (props.isMobile) JustifyContent.flexStart else JustifyContent.flexEnd

    flexRow(alignItems = Align.stretch) {
        css {
            color = foregroundColor
            fontWeight = FontWeight.w700
            justifyContent = justify
            flexGrow = 1.0
            if (props.isMobile) flexDirection = FlexDirection.column

            "a" {
                display = Display.flex
                flexDirection = FlexDirection.column
                justifyContent = JustifyContent.center
                fontWeight = FontWeight.w700
                padding(horizontal = 0.8.em)
                textDecoration = TextDecoration.none
                color = foregroundColor
                cursor = Cursor.pointer

                "span.text" {
                    padding(horizontal = 0.2.rem)
                }

                "span.underline" {
                    maxWidth(1024) { display = Display.none }
                    width = 20.pct
                    marginLeft = 40.pct
                    opacity = 0.0
                    height = 0.1.rem
                    backgroundColor = foregroundColor
                    transition(::width, 0.5.s)
                    transition(::marginLeft, 0.5.s)
                    transition(::opacity, 0.5.s)
                }

                hover {
                    "span.underline" {
                        marginLeft = 0.pct
                        width = 100.pct
                        opacity = 1.0
                    }
                }
            }

            if (props.isMobile) {
                backgroundColor = Color.kodein.kaumon
                "a" {
                    flexDirection = FlexDirection.column
                    margin(horizontal = 0.75.em)
                    padding(top = 1.6.rem, bottom = 0.4.rem)
                    borderBottom(0.1.rem, BorderStyle.solid, Color.kodein.kinzolin)
                }
            }
        }

        a(href = "services.html") {
            span("text") { +"SERVICES" }
            span("underline") {}
        }
        a(href = "training.html") {
            span("text") { +"TRAINING" }
            span("underline") {}
        }
        a(href = "") {
            span("text") { +"OSS" }
            span("underline") {}
        }
        a(href = "") {
            span("text") { +"TEAM" }
            span("underline") {}
        }
        a(href = "blog.html") {
            span("text") { +"BLOG" }
            span("underline") {}
        }
        a(href = "") {
            span("text") { +"CONTACT" }
            span("underline") {}
        }
        styledA(href = "") {
            css {
                if (!props.isMobile) {
                    hover {
                        "span" {
                            backgroundColor = foregroundColor
                            color = Color.kodein.dark
                        }
                    }
                }
            }
            styledSpan {
                css {
                    if (!props.isMobile) {
                        marginTop = (-0.3).rem
                        border(.1.rem, BorderStyle.solid, foregroundColor)
                        borderRadius = 1.rem
                        padding(.3.rem, .6.rem)
                        transition(::backgroundColor, 0.5.s)
                        transition(::color, 0.5.s)
                    }
                }
                +"GUYS: WE'RE HIRING!"
            }
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
