package net.kodein.pages.oss.fragment

import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import kotlinx.html.classes
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.components.SwipeableDiv
import net.kodein.useText
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import styled.*
import kotlin.js.Date
import kotlin.math.PI
import kotlin.math.tan


private fun RBuilder.pie(delta: Int, inside: StyledDOMBuilder<DIV>.() -> Unit) {
    styledDiv {
        css {
            position = Position.absolute

            val sw = 21.0
            width = (100 - delta * sw).pct
            height = (100 - delta * sw).pct
            left = (delta * (sw / 2)).pct
            top = (delta * (sw / 2)).pct

            maxWidth(410) {
                fontSize = 0.9.rem
            }

            borderRadius = 100.pct
            boxShadow(Color.black.withAlpha(0.2), blurRadius = 0.5.rem)

            "span.simple-text" {
                padding(vertical = (4.0 * (100.0 / (100 - delta * sw))).pct)
            }
        }

        styledDiv {
            css {
                width = 100.pct
                height = 100.pct
                put("clip-path", "circle()")
            }

            inside()
        }
    }
}

private interface SliceProps : RProps {
    var ratio: Double
    var pos: Int
    var selected: Boolean
    var onEnter: (Boolean) -> Unit
}

private val Slice = functionalComponent<SliceProps>("Slice") { props ->
    val div = useRef<HTMLDivElement?>(null)

    useEffectWithCleanup(emptyList()) {
        var touchTime = 0.0

        val onMouseEnter: (Event) -> Unit = {
            props.onEnter(Date().getTime() - touchTime <= 1000)
        }
        val onTouchStart: (Event) -> Unit = {
            touchTime = Date().getTime()
        }

        div.current!!.addEventListener("mouseenter", onMouseEnter)
        div.current!!.addEventListener("touchstart", onTouchStart, jsObject { passive = true })

        ({
            div.current!!.removeEventListener("mouseenter", onMouseEnter)
            div.current!!.removeEventListener("touchstart", onTouchStart)
        })
    }

    styledDiv {
        attrs.classes += "slice"
        ref = div
        css {
            position = Position.absolute
            width = 100.pct
            height = 100.pct
            left = 0.rem
            top = 0.rem
            textAlign = TextAlign.center
            cursor = Cursor.pointer
            userSelect = UserSelect.none
            put("-webkit-tap-highlight-color", "transparent")
            transition(::backgroundColor, 1.s)
            if (props.selected) specific(3) { backgroundColor = Color.kodein.kyzantium }

            if (props.ratio < 1.0) {
                val angle = 2 * PI * props.ratio
                when (angle) {
                    in ((0 * PI / 2))..(1 * PI / 2) -> {
                        val hw = tan(angle / 2)
                        val l = 50.0 - hw * 50.0
                        val r = 50.0 + hw * 50.0
                        put("clip-path", "polygon(50% 50%, $l% 0%, $r% 0%)")
                    }
                    in (1 * PI / 2)..(2 * PI / 2) -> {
                        val vw = tan(PI / 2 - angle / 2)
                        val v = 50.0 - vw * 50.0
                        put("clip-path", "polygon(50% 50%, 0% $v%, 0% 0%, 100% 0%, 100% $v%)")
                    }
                    in (2 * PI / 2)..(3 * PI / 2) -> {
                        val vw = tan(angle / 2 - PI / 2)
                        val v = 50.0 + vw * 50.0
                        put("clip-path", "polygon(50% 50%, 0% $v%, 0% 0%, 100% 0%, 100% $v%)")
                    }
                    in (3 * PI / 2)..(4 * PI / 2) -> {
                        val vw = tan(PI - angle / 2)
                        val l = 50.0 - vw * 50.0
                        val r = 50.0 + vw * 50.0
                        put("clip-path", "polygon(50% 50%, $l% 100%, 0% 100%, 0% 0%, 100% 0%, 100% 100%, $r% 100%)")
                    }
                }
                put("transform-origin", "50% 50%")
                transform { rotate(((angle / 2) * (props.pos * 2 + 1 )).rad) }
            }
        }

        props.children()
    }
}

private fun RBuilder.slice(ratio: Double, pos: Int, selected: Boolean, onEnter: (Boolean) -> Unit, inside: RBuilder.() -> Unit) {
    child(Slice) {
        attrs.ratio = ratio
        attrs.pos = pos
        attrs.selected = selected
        attrs.onEnter = onEnter

        inside()
    }
}

private fun RBuilder.simpleText(txt: String, reverse: Boolean = false) {
    styledSpan {
        attrs.classes += "simple-text"
        css {
            display = Display.inlineBlock
//            padding(top = 1.4.rem, bottom = 1.1.rem)
            if (reverse) transform { rotate(180.deg) }
        }
        +txt
    }

}

private fun RBuilder.line(ratio: Double) {
    styledSpan {
        css {
            position = Position.absolute
            width = 2.px
            height = 50.pct
            left = 50.pct - 1.px
            top = 0.pct
            backgroundColor = Color.kodein.orange
            put("transform-origin", "50% 100%")
            transform { rotate((2 * PI * ratio).rad) }
        }
    }
}


val FrameworkOnion = functionalComponent<RProps>("FrameworkLayers") {
    val strings = useText().oss

    val texts = mapOf(
        "android" to ("Android" to strings.onionLayerAndroid),
        "desktop" to ("Desktop" to strings.onionLayerDesktop),
        "ios" to ("iOS" to strings.onionLayerIos),
        "js" to ("Kotlin/JS" to strings.onionLayerJs),
        "jvm" to ("Kotlin/JVM" to strings.onionLayerJvm),
        "native" to ("Kotlin/Native" to strings.onionLayerNative),
        "kodein" to ("Kodein framework" to strings.onionLayerKodein),
        "kotlin" to ("Kotlin & KotlinX" to strings.onionLayerKotlin),
        "web" to ("Web" to strings.onionLayerWeb)
    )

    val order by useState { texts.keys.shuffled() }

    var selected by useState(order[0])
    val bigPie = useRef<HTMLDivElement?>(null)
    var tick by useState(0)
    var running by useState(true)
    var isRight: Boolean by useState(true)

    useEffectWithCleanup(listOf(running)) effect@ {
        if (!running) return@effect ({})

        var handle: Int? = null

        var t = 0
        val onInterval = {
            t += 1
            tick = t
        }

        val onEnter: (Event?) -> Unit = {
            handle?.let { window.clearInterval(it) }
            handle = null
        }

        val onLeave: (Event?) -> Unit = {
            handle?.let { window.clearInterval(it) }
            handle = window.setInterval(onInterval, 4200)
        }

        bigPie.current!!.addEventListener("mouseenter", onEnter)
        bigPie.current!!.addEventListener("mouseleave", onLeave)

        onLeave(null)

        ({
            handle?.let { window.clearInterval(it) }
            bigPie.current!!.removeEventListener("mouseenter", onEnter)
            bigPie.current!!.removeEventListener("mouseleave", onLeave)
        })
    }

    useEffect(listOf(tick)) {
        if (tick == 0) return@useEffect
        val index = order.indexOf(selected)
        selected = order[(index + 1) % order.size]
    }

    fun selectedSetter(id: String): (Boolean) -> Unit = { isTouch ->
        selected = id
        isRight = true
        running = !isTouch
    }

    flexColumn {
        css {
            padding(top = 10.rem, bottom = 5.rem)
            maxWidth(980) { paddingTop = 7.rem }
            background = "linear-gradient(180deg, ${Color.kodein.korail} 70%, ${Color.kodein.dark} 100%)"
            maxWidth(560) {
                background = "linear-gradient(180deg, ${Color.kodein.korail} 85%, ${Color.kodein.dark} 100%)"
            }
        }

        styledH2 {
            css {
                +kodein.intertitre
                specific {
                    fontWeight = FontWeight.ultraLight
                    textAlign = TextAlign.center

                    maxSize(480) {
                        fontSize = 1.8.rem
                    }
                    maxSize(400) {
                        fontSize = 1.6.rem
                    }
                }
                color = Color.kodein.cute
                margin(1.rem, 2.rem)
            }

            strings.onionTitle(this)
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 3.rem
                opacity = .7
                backgroundColor = Color.kodein.cute
                margin(1.rem, LinearDimension.auto)
                maxSize(768) {
                    margin(0.rem, LinearDimension.auto)
                }
                landscapeMobile {
                    margin(1.rem, LinearDimension.auto)
                }
            }
        }

        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                justifyContent = JustifyContent.center
                alignItems = Align.flexStart
                margin(vertical = 5.rem)
                overflow = Overflow.hidden
                maxWidth(560) {
                    flexDirection = FlexDirection.column
                    alignItems = Align.center
                    marginTop = 2.5.rem
                }
            }

            styledDiv {
                css {
                    flexGrow = 1.0
                    maxWidth = 34.rem
                    margin(horizontal = 1.5.rem)
                    maxWidth(560) {
                        width = 90.vw
                    }

                }
                styledDiv {
                    ref = bigPie
                    css {
                        position = Position.relative
                        width = 100.pct
                        paddingTop = 100.pct
                        maxHeight = 34.rem
                        put("clip-path", "circle()")
                        overflow = Overflow.hidden
                    }

                    pie(0) {
                        css {
                            "div.slice" { backgroundColor = Color.kodein.cute }
                            color = Color.kodein.orange
                        }

                        slice(1.0 / 3.0, 0, selected == "web", selectedSetter("web")) { simpleText("Web") }
                        slice(1.0 / 3.0, 1, selected == "ios", selectedSetter("ios")) { simpleText("iOS", reverse = true) }
                        slice(1.0 / 6.0, 4, selected == "desktop", selectedSetter("desktop")) { simpleText("Desktop") }
                        slice(1.0 / 6.0, 5, selected == "android", selectedSetter("android")) { simpleText("Android") }

                        line(0.0)
                        line(1.0 / 3.0)
                        line(2.0 / 3.0)
                        line(5.0 / 6.0)
                    }

                    pie(1) {
                        css {
                            "div.slice" { backgroundColor = Color.kodein.kaumon }
                            color = Color.kodein.orange
                        }

                        slice(1.0 / 3.0, 0, selected == "js", selectedSetter("js")) { simpleText("Kotlin/JS") }
                        slice(1.0 / 3.0, 1, selected == "native", selectedSetter("native")) { simpleText("Kotlin/Native", reverse = true) }
                        slice(1.0 / 3.0, 2, selected == "jvm", selectedSetter("jvm")) { simpleText("Kotlin/JVM") }

                        line(0.0)
                        line(1.0 / 3.0)
                        line(2.0 / 3.0)
                    }

                    pie(2) {
                        css {
                            "div.slice" { backgroundColor = Color.kodein.korail }
                            color = Color.kodein.cute
                        }

                        slice(1.0, 0, selected == "kotlin", selectedSetter("kotlin")) { simpleText("Kotlin[X]") }
                    }

                    pie(3) {
                        css {
                            "div.slice" { backgroundColor = Color.kodein.orange }
                            color = Color.kodein.cute
                        }

                        slice(1.0, 0, selected == "kodein", selectedSetter("kodein")) {
                            flexColumn(JustifyContent.center, Align.center) {
                                css {
                                    width = 100.pct
                                    height = 100.pct
                                }
                                styledSpan {
                                    css {
                                        fontWeight = FontWeight.ultraLight
                                        fontFamily = KodeinStyles.piconExtended
                                        padding(0.25.rem)
                                        maxWidth = 100.pct
                                    }
                                    styledB {
                                        css {
                                            fontWeight = FontWeight.semiBold
                                            display = Display.inlineBlock
                                        }
                                        +"KODEIN"
                                    }
                                    styledSpan {
                                        css {
                                            display = Display.inlineBlock
                                        }
                                        +"Framework"
                                    }
                                }
                            }
                        }
                    }
                }
            }

            childFunction<String, SwipeableTextProps>(
                SwipeableText,
                {
                    attrs {
                        contentId = selected
                        onSwipe = { isNext ->
                            running = false
                            val index = order.indexOf(selected)
                            isRight = isNext
                            selected = order[(index + (if (isNext) 1 else -1 + order.size)) % order.size]
                        }
                        this.isRight = isRight
                        css = {
                            height = 20.rem
                            flexGrow = 1.0
                            maxWidth = 25.rem
                            margin(top = 3.rem, left = 2.5.rem, right = 1.5.rem)
                            maxWidth(800) { margin(top = 2.rem, left = 1.rem, right = 1.rem) }
                            maxWidth(560) {
                                width = 100.pct - 2.rem
                            }
                        }
                    }
                }
            ) { contentId ->
                styledDiv {
                    styledH3 {
                        css {
                            +KodeinStyles.intertitre
                            paddingBottom = 1.25.rem
                            color = Color.kodein.dark
                        }
                        +texts[contentId]!!.first
                    }

                    styledP {
                        css {
                            +KodeinStyles.body
                            color = Color.kodein.kinzolin
                        }

                        texts[contentId]!!.second(this)
                    }
                }
            }
        }
    }
}

private interface SwipeableTextProps : RProps {
    var css: RuleSet?
    var contentId: String
    var onSwipe: (Boolean) -> Unit
    var isRight: Boolean
}

private val SwipeableText = functionalComponent<SwipeableTextProps>("SwipeableText") { props ->

    var oldContentId: String? by useState(null)
    var contentId: String? by useState(null)

    var showing: Boolean by useState(false)
    var tick: Int by useState(0)

    useEffect(listOf(props.contentId)) {
        oldContentId = contentId
        contentId = props.contentId
        showing = false
        tick += 1
    }

    useEffect(listOf(contentId)) effect@ {
        if (contentId == null) return@effect
        window.setTimeout({ showing = true }, 50)
    }

    child(SwipeableDiv) {
        attrs {
            containerCss = props.css
            indicatorCss = {
                display = Display.none
                maxWidth(560) { display = Display.unset }
            }
            onSwipe = props.onSwipe
        }

        contentId?.let { id ->
            styledDiv {
                attrs.key = "$id-$tick"
                css {
                    zIndex = 2
                    position = Position.absolute
                    left = 0.rem
                    top = 0.rem
                    width = 100.pct
                    height = 100.pct
                    transition(::transform, 0.5.s)
                    transition(::opacity, 0.5.s)
                    if (!showing) {
                        transform { translateX(if (props.isRight) (-2).rem else 2.rem) }
                        opacity = 0.0
                    }
                }
                props.children(id)
            }
        }

        oldContentId?.let { id ->
            styledDiv {
                attrs.key = "$id-${tick - 1}"
                css {
                    zIndex = 1
                    pointerEvents = PointerEvents.none
                    position = Position.absolute
                    left = 0.rem
                    top = 0.rem
                    width = 100.pct
                    height = 100.pct
                    transition(::transform, 0.5.s)
                    transition(::opacity, 0.5.s)
                    if (showing) {
                        transform { translateX(if (props.isRight) 2.rem else (-2).rem) }
                        opacity = 0.0
                    }
                }
                props.children(id)
            }
        }
    }

}
