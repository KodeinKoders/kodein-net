package net.kodein.components

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.*
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.*
import org.w3c.dom.ScrollBehavior
import org.w3c.dom.events.Event
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
    val mobileMenuContainer = useRef<HTMLDivElement?>(null)
    val mobileMenuButton = useRef<HTMLDivElement?>(null)

    var isTop by useState(props.animated)

    var isMobileMenuOpen by useState(false)

    useEffectWithCleanup {
        val openCloseMenu = EventListener { isMobileMenuOpen = !isMobileMenuOpen }

        val scrollToMenu = EventListener {
            val menuTop = menuContainer.current!!.getBoundingClientRect().top.toInt()
            if (menuTop > 0) {
                val (_, offsetTop) = menuContainer.current!!.recursiveOffset()
                window.scrollTo(ScrollToOptions(top = (offsetTop).toDouble(), behavior = ScrollBehavior.SMOOTH))
            }
        }

        val shadowCloseMenu = EventListener {
            if (it.target == mobileMenuContainer.current) isMobileMenuOpen = false
        }

        mobileMenuContainer.current!!.addEventListener("mousedown", shadowCloseMenu)
        mobileMenuButton.current!!.addEventListener("mouseup", openCloseMenu)
        mobileMenuButton.current!!.addEventListener("mouseup", scrollToMenu)

        ({
            mobileMenuContainer.current!!.removeEventListener("mousedown", shadowCloseMenu)
            mobileMenuButton.current!!.removeEventListener("mouseup", openCloseMenu)
            mobileMenuButton.current!!.removeEventListener("mouseup", scrollToMenu)
        })
    }

    useEffect(listOf(isMobileMenuOpen)) {
        document.body!!.style.overflowY = if (!isMobileMenuOpen) { "auto" } else { "hidden" }
    }

    useEffectWithCleanup {
        val onTouchmove = EventListener { it.preventDefault() }
        menuContainer.current!!.addEventListener("touchmove", onTouchmove)
        ({ menuContainer.current!!.removeEventListener("touchmove", onTouchmove) })
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

        styledDiv {
            ref = mobileMenuContainer
            css {
                minWidth(1025) { display = Display.none }
                position = Position.fixed
                top = 0.px
                right = 0.px
                height = 100.pct
                width = 100.pct
                zIndex = 1000
                backgroundColor = Color.kodein.dark.withAlpha(0.25)
                if (!isMobileMenuOpen) {
                    opacity = 0.0
                    pointerEvents = PointerEvents.none
                }
                transition(::opacity, .5.s)
            }

            flexRow(JustifyContent.center, Align.center) {
                css {
                    backgroundColor = Color.kodein.dark
                    position = Position.absolute
                    right = 0.pct
                    height = 100.pct
                    width = 50.pct
                    minWidth = 28.rem

                    if (!isMobileMenuOpen) transform { translateX(20.rem) }
                    transition(::transform, 0.5.s)

                    boxShadow(Color.black.withAlpha(0.25), 0.rem, 0.2.rem, blurRadius = 1.5.rem)

                    maxWidth(580) {
                        width = 100.pct
                        minWidth = LinearDimension.auto
                    }
                    maxHeight(370) { alignItems = Align.flexEnd }
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
            isMobile  = document.body!!.clientWidth <= 1024
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
                    put("-webkit-tap-highlight-color", "transparent")
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
    }
}


interface MenuProps : RProps {
    var isMobile: Boolean
}

val MenuNavigation = functionalComponent<MenuProps>("MenuNavigation") { props ->
    val foregroundColor = if (props.isMobile)  Color.kodein.kamethiste else  Color.kodein.orange
    val justify = if (props.isMobile) JustifyContent.flexStart else JustifyContent.flexEnd
    val strings = useText().menu

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
                        padding(top = .9.rem, bottom = .6.rem)
                        maxHeight(679) {
                            padding(top = .6.rem, bottom = .4.rem)
                        }
                    }
                    maxHeight(480) { fontSize = 1.2.rem }
                }
            }
        }

        fun String.asMenu() = if (props.isMobile) this.capitalize() else this.toUpperCase()

        withPageId { pageId ->
            a(href = "services.html", classes = if (pageId == "services") "current" else null) {
                span("text") { +strings.services.asMenu() }
                span("underline") {}
            }
            if (props.isMobile) menuSeparator()
            a(href = "training.html", classes = if (pageId == "training") "current" else null) {
                span("text") { +strings.training.asMenu() }
                span("underline") {}
            }
            if (props.isMobile) menuSeparator()
            a(href = "oss.html", classes = if (pageId == "oss") "current" else null) {
                span("text") { +strings.oss }
                span("underline") {}
            }
            if (props.isMobile) menuSeparator()
            a(href = "team.html", classes = if (pageId == "team") "current" else null) {
                span("text") { +strings.team.asMenu() }
                span("underline") {}
            }
            if (props.isMobile) menuSeparator()
            a(href = "blog.html", classes = if (pageId == "blog") "current" else null) {
                span("text") { +strings.blog.asMenu() }
                span("underline") {}
            }
            if (props.isMobile) menuSeparator()
            a(href = "contact.html", classes = if (pageId == "contact") "current" else null) {
                span("text") { +strings.contact.asMenu() }
                span("underline") {}
            }
        }
        if (props.isMobile) menuSeparator()
        styledA(href = "team.html#jobs") {
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
                +strings.hiring.asMenu()
            }
        }

        pageDataContext.Consumer { data ->
            flexRow(alignItems = Align.center) {
                css {
                    if (props.isMobile) {
                        position = Position.absolute
                        left = 1.rem
                        bottom = 1.rem
                    } else {
                        marginBottom = 0.3.rem
                        marginLeft = 2.rem
                        marginRight = 0.5.rem
                    }
                }
                appLanguages.forEachIndexed { index, language ->
                    if (index != 0) {
                        styledA {
                            css {
                                specific {
                                    cursor = Cursor.text
                                    textDecoration = TextDecoration.none
                                    if (props.isMobile) margin(horizontal = 0.3.rem)
                                }
                            }
                            +"/"
                        }
                    }

                    if (language.id == data.language.id) {
                        styledA {
                            css {
                                opacity = 0.5
                                specific {
                                    padding(0.em)
                                    cursor = Cursor.text
                                    textDecoration = TextDecoration.none
                                }
                            }
                            +language.id.toUpperCase()
                        }
                    } else {
                        val url = if (language.path != null) "${language.path}/${data.pageId}.html" else "${data.pageId}.html"
                        styledA("${data.language.basePath}/$url") {
                            css {
                                specific { padding(0.em) }
                            }
                            +language.id.toUpperCase()
                        }
                    }
                }
            }
        }
    }
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
