package net.kodein.pages.oss.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import kotlinx.html.classes
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.TouchEvent
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import styled.*
import kotlin.js.Date
import kotlin.math.PI
import kotlin.math.abs
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
        div.current!!.addEventListener("touchstart", onTouchStart)

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


private val texts = mapOf(
    "android" to (
            "Android" to
                    """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Donec sed ex sit amet magna dictum consectetur sed sed ipsum.
                        Suspendisse ultrices ante imperdiet scelerisque mattis.
                        In eu nisl id velit imperdiet tincidunt at et ex.
                        Phasellus ut purus consequat, fringilla urna ac, fringilla justo.
                        Suspendisse blandit est ut accumsan pulvinar.
                        Sed suscipit leo non neque placerat, a rhoncus dolor maximus.
                        Aliquam ut tristique magna.
                        Suspendisse vehicula orci ut lorem condimentum pellentesque.     
                    """.trimIndent()
            ),

    "desktop" to (
            "Desktop" to
                    """
                        Sed dui tellus, eleifend et nulla quis, imperdiet varius velit.
                        Donec malesuada diam vitae tortor ornare malesuada.
                        Vivamus ligula justo, sagittis id arcu a, tempus convallis magna.
                        In ac eleifend lectus.
                        Aliquam finibus orci sit amet leo venenatis consectetur.
                        Aliquam nec turpis nibh.
                        Aliquam porttitor interdum hendrerit.
                        Donec tristique nisl at nunc suscipit, sed gravida magna sollicitudin.
                        Sed a ligula feugiat, mollis odio sed, vestibulum nisl.
                        Quisque a odio quis neque scelerisque sodales.
                        Duis semper et urna id maximus.
                        Donec nulla dui, maximus sed pharetra vel, sagittis sed tortor.
                        Nullam nec tellus sit amet purus semper pretium.
                        Vivamus sit amet sapien vel tortor vestibulum rutrum vel a nibh.
                        Proin sed nibh sed purus pretium molestie vel euismod nunc.
                    """.trimIndent()
            ),

    "ios" to (
            "iOS" to
                    """
                        Sed eget nisl a quam elementum consectetur.
                        Aenean sed porttitor nisl.
                        Etiam tempor suscipit nibh.
                        Ut ultrices eget odio ac eleifend.
                        Ut pharetra lobortis magna id ultricies.
                        Aliquam vitae nisi placerat, pulvinar dui non, congue metus.
                        Sed id magna vestibulum, posuere sapien euismod, tempus mi.
                    """.trimIndent()
            ),

    "kotlin-native" to (
            "Kotlin/Native" to
                    """
                        Donec nec lectus dui.
                        Nullam eu pharetra tellus, sit amet rhoncus odio.
                        Duis a felis rutrum, varius eros in, tincidunt magna.
                        Quisque eu varius est, eget varius ex.
                        Nulla placerat eu enim sed semper.
                        Praesent commodo rutrum erat, pretium dictum diam tempus vel.
                        Quisque sit amet porta libero.
                        Suspendisse placerat vitae tortor id tempor.
                        Mauris non interdum odio.
                        Cras a porta dolor.
                        Aliquam erat volutpat.
                        Proin at urna varius, tincidunt tortor ut, ornare turpis.
                        Proin id arcu nisl.
                        Maecenas elit urna, scelerisque eu neque vel, auctor varius risus.
                    """.trimIndent()
            ),

    "kotlin-js" to (
            "Kotlin/JS" to
                    """
                        Suspendisse pharetra lacus sed est feugiat dictum id condimentum quam.
                        Ut pulvinar ac enim ut ultricies.
                        Nam maximus lacinia condimentum.
                        Nunc tincidunt velit vitae justo ultricies tincidunt.
                        Aenean lacus nisi, commodo eu fermentum non, tincidunt sollicitudin mauris.
                        Praesent vel feugiat neque.
                        Morbi ornare aliquam dignissim.
                        Quisque ut metus arcu.
                        Pellentesque nec erat mollis, lacinia elit non, aliquam mi.
                        Nam egestas pharetra ante ullamcorper rutrum.
                        Sed ex lectus, feugiat eget nulla quis, consequat pretium nisi.
                    """.trimIndent()
            ),

    "kotlin-jvm" to (
            "Kotlin/JVM" to
                    """
                        Morbi a diam nec massa tempor pretium.
                        Suspendisse feugiat turpis ante, id maximus ex consectetur non.
                        Maecenas posuere sem odio.
                        Nulla facilisi.
                        Donec sit amet orci ac dui imperdiet aliquet.
                        Nullam lectus libero, sollicitudin nec felis id, tincidunt posuere nisl.
                        In bibendum ex nec pulvinar vestibulum.
                        In facilisis lorem aliquet purus porta, sed pulvinar massa varius.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce sit amet leo magna.
                        Sed lacinia lacus quis quam laoreet, quis egestas nisl suscipit.
                        In ac suscipit est.
                        Integer mattis, dui aliquet porttitor maximus, ipsum est faucibus ante, vitae imperdiet magna quam vel neque.
                        Vivamus bibendum augue a mi venenatis fermentum id id purus.
                        Vestibulum egestas elit ac dolor facilisis, vestibulum fermentum eros suscipit.
                    """.trimIndent()
            ),

    "kotlinx" to (
            "Kotlin & KotlinX" to
                    """
                        Ut libero magna, facilisis nec pulvinar eu, interdum a diam.
                        Donec aliquam leo sit amet purus posuere, nec accumsan felis efficitur.
                        Nunc bibendum convallis suscipit.
                        Sed libero massa, pellentesque sit amet erat quis, auctor bibendum dui.
                        Integer ultricies ultrices felis, laoreet posuere elit tempor at.
                        Morbi blandit cursus sapien ut efficitur.
                        Vivamus sollicitudin ultrices neque, egestas gravida nisi malesuada et.
                        Mauris id velit porta, semper enim ut, semper urna.
                        Ut rhoncus congue metus a malesuada.
                        Nam vitae leo lectus.
                        Nunc aliquam velit enim, non placerat dui gravida nec.
                    """.trimIndent()
            ),

    "kodein" to (
            "Kodein framework" to
                    """
                        Donec at ex tincidunt, iaculis nibh id, tincidunt nibh.
                        Mauris cursus ac arcu eu rutrum.
                        Morbi in dolor at augue vehicula aliquam eget id nunc.
                        Nunc tincidunt, urna ut porttitor commodo, ante elit volutpat metus, quis elementum mauris dui id est.
                        Proin at congue orci.
                        Mauris venenatis consectetur enim non dignissim.
                        Nunc sodales, dolor ultrices convallis fermentum, lorem dui consectetur ipsum, at lobortis neque orci at odio.
                        Donec vitae scelerisque orci.
                        Aliquam sed lorem elit.
                        Aliquam erat volutpat.
                        Duis nec arcu ac orci imperdiet dapibus et et eros.
                        Cras ut libero nibh.
                    """.trimIndent()
            ),

    "web" to (
            "Web" to
                    """
                        In id orci mollis, dignissim purus sit amet, placerat neque.
                        Fusce iaculis mi nibh, et posuere mauris elementum non.
                        Donec porta feugiat enim.
                        Curabitur eu libero sit amet diam tristique efficitur eget id lectus.
                        Nam libero nibh, fringilla sit amet orci non, tincidunt tempor mi.
                        Fusce iaculis neque quis magna tincidunt, nec sollicitudin nunc suscipit.
                        Suspendisse laoreet vel risus in facilisis.
                        Mauris faucibus massa a mauris congue, sit amet iaculis tortor dignissim.
                        Nunc molestie posuere rutrum.
                        Phasellus ut felis nec felis consequat venenatis.
                        Sed non porttitor turpis.
                        Integer sagittis sagittis justo.
                        Curabitur non velit tortor.
                        Integer tempor diam nisl, nec fringilla metus dictum rhoncus.
                        Nulla in viverra sem, in tincidunt odio.
                        Pellentesque sagittis augue erat, vitae faucibus mi pharetra ut.
                    """.trimIndent()
            ),
)


val FrameworkOnion = functionalComponent<RProps>("FrameworkLayers") {

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
            +"The "
            styledB {
                css.fontWeight = FontWeight.semiBold
                +"KODEIN"
            }
            +"Framework"
            br {}
            +"empowers multiplatform applications."
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

                        slice(1.0 / 3.0, 0, selected == "kotlin-js", selectedSetter("kotlin-js")) { simpleText("Kotlin/JS") }
                        slice(1.0 / 3.0, 1, selected == "kotlin-native", selectedSetter("kotlin-native")) { simpleText("Kotlin/Native", reverse = true) }
                        slice(1.0 / 3.0, 2, selected == "kotlin-jvm", selectedSetter("kotlin-jvm")) { simpleText("Kotlin/JVM") }

                        line(0.0)
                        line(1.0 / 3.0)
                        line(2.0 / 3.0)
                    }

                    pie(2) {
                        css {
                            "div.slice" { backgroundColor = Color.kodein.korail }
                            color = Color.kodein.cute
                        }

                        slice(1.0, 0, selected == "kotlinx", selectedSetter("kotlinx")) { simpleText("Kotlin[X]") }
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
                            height = 32.rem
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

                        +texts[contentId]!!.second
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

    var swipeIndicatorAnim: Int by useState(-1)
    var swipeIndicatorVisible by useState(true)

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

    val div = useRef<HTMLDivElement?>(null)

    useEffectWithCleanup {
        var start: Triple<Int, Int, Double>? = null
        val onTouchStart: (Event) -> Unit = {
            val touch = (it as TouchEvent).changedTouches.item(0)!!
            start = Triple(touch.pageX, touch.pageY, Date().getTime())
        }
        val onTouchMove: (Event) -> Unit = listener@ {
            val (startX, startY, _) = start ?: return@listener
            val touch = (it as TouchEvent).changedTouches.item(0)!!
            val distX = abs(touch.pageX - startX)
            val distY = abs(touch.pageY - startY)
            if ((distX <= 10 && distY <= 10) || distX >= distY) it.preventDefault()
            else start = null
        }
        val onTouchEnd: (Event) -> Unit = listener@ {
            val (startX, startY, startTime) = start ?: return@listener
            val touch = (it as TouchEvent).changedTouches.item(0)!!
            val dist = touch.pageX - startX
            val elapsed = Date().getTime() - startTime
            if (elapsed <= 200 && abs(dist) >= 100 && abs(touch.pageY - startY) <= 100) {
                swipeIndicatorVisible = false
                if (dist > 0) props.onSwipe(true)
                else props.onSwipe(false)
            }
            it.preventDefault()
        }

        div.current!!.addEventListener("touchstart", onTouchStart)
        div.current!!.addEventListener("touchmove", onTouchMove)
        div.current!!.addEventListener("touchend", onTouchEnd)

        ({
            div.current!!.removeEventListener("touchstart", onTouchStart)
            div.current!!.removeEventListener("touchmove", onTouchMove)
            div.current!!.removeEventListener("touchend", onTouchEnd)
        })
    }

    useEffectWithCleanup(emptyList()) {
        var i = swipeIndicatorAnim
        val block = {
            i *= -1
            swipeIndicatorAnim = i
        }
        val handle = window.setInterval(block, 1800)
        window.setTimeout(block, 20)
        ({ window.clearInterval(handle) })
    }

    val img = useRef<HTMLImageElement?>(null)
    var swipeIndicatorInView by useState(false)
    useEffectWithCleanup(listOf(swipeIndicatorInView)) effect@ {
        if (swipeIndicatorInView) return@effect ({})
        val onScroll: (Event) -> Unit = {
            if (img.current!!.getBoundingClientRect().top.toInt() in 150..(window.innerHeight - 150)) {
                swipeIndicatorInView = true
            }
        }
        window.addEventListener("scroll", onScroll)
        ({ window.removeEventListener("scroll", onScroll) })
    }
    useEffectWithCleanup(listOf(swipeIndicatorInView)) effect@ {
        if (!swipeIndicatorInView) return@effect ({})
        val handle = window.setTimeout({ swipeIndicatorVisible = false }, 4000)
        ({ window.clearTimeout(handle) })
    }

    styledDiv {
        ref = div
        css {
            props.css?.invoke(this)
            position = Position.relative
        }
        styledImg(src = "imgs/swipe.svg") {
            ref = img
            css {
                position = Position.absolute
                left = 50.pct - 3.rem
                top = 1.7.rem
                width = 3.rem
                zIndex = 3
                pointerEvents = PointerEvents.none
                transform { translateX( (swipeIndicatorAnim * 3).rem ) }
                transition(::transform, 1.5.s, Timing.easeInOut)
                opacity = if (swipeIndicatorVisible) 1.0 else 0.0
                transition(::opacity, 1.s, Timing.linear)
                display = Display.none
                maxWidth(560) { display = Display.unset }
            }
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
