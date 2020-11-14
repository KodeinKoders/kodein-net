package net.kodein.components

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.dom.addClass
import kotlinx.dom.removeClass
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.*
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import react.*
import react.dom.a
import react.dom.span
import styled.*
import kotlin.math.max
import kotlin.math.min

interface MenuTopProps : RProps {
    var animated: Boolean
    var topMargin: LinearDimension?
    var backgroundColor : Color?
}

val MenuTop = functionalComponent<MenuTopProps>("MenuTop") { props ->
    val menuContainer = useRef<HTMLDivElement?>(null)
    val mobileMenuContainer = useRef<HTMLDivElement?>(null)
    val mobileMenuButton = useRef<HTMLDivElement?>(null)

    var isTop by useState(props.animated)

    var isMobileMenuOpen by useState(false)

    useEffectWithCleanup(listOf(isMobileMenuOpen)) {
        val openCloseMenu = EventListener {
            isMobileMenuOpen = !isMobileMenuOpen

            if (!isMobileMenuOpen) {
                document.body!!.style.setPropertyValue("overflow", "hidden")
            } else {
                document.body!!.style.removeProperty("overflow")
            }
        }
        val scrollToMenu = EventListener {
            val menuTop = menuContainer.current!!.getBoundingClientRect().top.toInt()
            if (menuTop > 0) {
                val (_, offsetTop) = menuContainer.current!!.recursiveOffset()
                window.scrollTo(ScrollToOptions(top = (offsetTop).toDouble(), behavior = ScrollBehavior.SMOOTH))
            }
        }

        mobileMenuContainer.current!!.addEventListener("mousedown", openCloseMenu)
        mobileMenuButton.current!!.addEventListener("mouseup", openCloseMenu)
        mobileMenuButton.current!!.addEventListener("mouseup", scrollToMenu)

        ({
            mobileMenuContainer.current!!.removeEventListener("mousedown", openCloseMenu)
            mobileMenuButton.current!!.removeEventListener("mouseup", openCloseMenu)
            mobileMenuButton.current!!.removeEventListener("mouseup", scrollToMenu)
        })
    }

//    useEffect(listOf(isMobileMenuOpen)) {
//        if (!isMobileMenuOpen) {
//            println("free scroll!")
//        } else {
//            println("block scroll!")
//        }
//    }

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

    props.topMargin?.let { _ ->
        styledDiv {
            css {
                height = 1.5.rem
                backgroundColor = if (isTop) props.backgroundColor ?: Color.transparent else Color.kodein.dark
                transition(::background, duration = .5.s)
            }
        }
    }

    flexColumn {
        ref = menuContainer
        css {
            if (isTop) {
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

        flexRow {
            css {
                position = Position.fixed
                left = 100.pct
                top = 0.px
                right = 0.px
                height = 100.pct
                width = 100.pct
                zIndex = 1000

                minWidth(1025) { display = Display.none }

                transition(::left, duration = .3.s, Timing.linear)
                left = if (isMobileMenuOpen) {
                    0.pct
                } else {
                    100.pct
                }
            }

            styledDiv {
                ref = mobileMenuContainer
                css {
                    left = 0.pct
                    height = 100.pct
                    width = 100.pct
                    backgroundColor = Color.kodein.dark
                    transition(::opacity, duration = .3.s, Timing.easeIn)
                    transition(::opacity, duration = .1.s, Timing.easeOut)
                    opacity = if (isMobileMenuOpen) 0.1 else 0
                }
            }

            flexRow(JustifyContent.center, Align.center) {
                css {
                    backgroundColor = Color.kodein.dark
                    position = Position.absolute
                    right = 0.pct
                    height = 100.pct
                    width = 100.pct

                    media("(min-height: 768px) and (min-width: 768px)") {
                        width = 50.pct
                    }

                    boxShadow(Color.black.withAlpha(0.25), 0.rem, 0.2.rem, blurRadius = 1.5.rem)
                }
                child(MenuNavigation) {
                    attrs.isMobile = true
                }
            }
        }
    }
}

interface MenuContentProps : RProps{
    var mobileMenuButton: RMutableRef<HTMLDivElement?>
    var isMobileMenuOpen: Boolean
}

val MenuContent = functionalComponent<MenuContentProps>("MenuContent") { props ->

    var isMobile by useState(false)

    useEffectWithCleanup {
        val onResize: (Event?) -> Unit = {
            isMobile  = document.body!!.clientWidth < 768 || document.body!!.clientHeight < 768
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

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
                    logo = if(props.isMobileMenuOpen && isMobile) "purple-fat" else  "orange-fat"
                    bold = "KODEIN"
                    light = "Koders"
                    color = if(props.isMobileMenuOpen && isMobile) Color.kodein.purple else  Color.kodein.orange
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
                        transition(::color, duration = .3.s, Timing.easeInOut)
                    }

                    "span.first" {
                        backgroundColor = Color.kodein.purple
                        transform {
                            translate(0.px, 7.px)
                            rotate((-45).deg)
                        }
                    }
                    "span.middle" { opacity = 0 }
                    "span.last" {
                        backgroundColor = Color.kodein.purple
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
    val foregroundColor = if (props.isMobile)  Color.kodein.kamethiste else  Color.kodein.orange
    val justify = if (props.isMobile) JustifyContent.flexStart else JustifyContent.flexEnd

    var currentPage by useState("home")

    useEffect {
        val element = document.getElementById("page")
        val page = element?.attributes?.get("data-page")?.value
        page?.let { currentPage = it }
    }

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

                "&.current" {
                    "span.underline" {
                        marginLeft = 0.pct
                        width = 100.pct
                        opacity = 1.0
                    }
                }
            }

            if (props.isMobile) {
                backgroundColor = Color.kodein.dark
                color = Color.kodein.kamethiste
                padding(horizontal = 2.rem)

                "a" {
                    fontSize = 1.5.rem
                    flexDirection = FlexDirection.column
                    padding(horizontal = 0.em)
                    "span.text" {
                        padding(horizontal = 0.rem)
                        padding(top = .6.rem, bottom = .4.rem)
                    }
                }
            }
        }

        fun String.asMenu() = if (props.isMobile) this.capitalize() else this.toUpperCase()

        a(href = "services.html", classes = if (currentPage == "services") "current" else null) {
            span("text") { +"services".asMenu() }
            span("underline") {}
        }
        if (props.isMobile) menuSeparator()
        a(href = "training.html", classes = if (currentPage == "training") "current" else null) {
            span("text") { +"training".asMenu() }
            span("underline") {}
        }
        if (props.isMobile) menuSeparator()
        a(href = "oss.html", classes = if (currentPage == "oss") "current" else null) {
            span("text") { +"OSS" }
            span("underline") {}
        }
        if (props.isMobile) menuSeparator()
        a(href = "", classes = if (currentPage == "team") "current" else null) {
            span("text") { +"team".asMenu() }
            span("underline") {}
        }
        if (props.isMobile) menuSeparator()
        a(href = "blog.html", classes = if (currentPage == "blog") "current" else null) {
            span("text") { +"blog".asMenu() }
            span("underline") {}
        }
        if (props.isMobile) menuSeparator()
        a(href = "contact.html", classes = if (currentPage == "contact") "current" else null) {
            span("text") { +"contact".asMenu() }
            span("underline") {}
        }
        if (props.isMobile) menuSeparator()
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
                    } else {
                        padding( vertical = .5.rem, horizontal = 0.rem)
                    }
                }
                +"we're hiring!".asMenu()
            }
        }
    }

//    if (props.isMobile) {
//        styledA(href = "index.html") {
//            css {
//                position = Position.absolute
//                left = 1.rem
//                bottom = 1.rem
//            }
//
//            styledImg(alt = "Kodein logo", src = "imgs/logo-purple.svg") {
//                css {
//                    display = Display.block
//                    height = 1.4.em
//                    padding(right = 0.5.em)
//                }
//            }
//            styledDiv {
//                styledH1 {
//                    css {
//                        fontSize = 1.2.em
//                        fontWeight = FontWeight.w700
//                        color = Color.kodein.purple
//                    }
//
//                    +"KODEIN"
//
//                    styledSpan {
//                        css {
//                            fontWeight = FontWeight.w300
//                        }
//                        +"Koders"
//                    }
//                }
//            }
//        }
//    }
}

private fun RBuilder.menuSeparator() {
    styledDiv {
        css {
            width = 100.pct
            backgroundColor = Color.kodein.kinzolin
            height = 0.05.rem
        }
    }
}
