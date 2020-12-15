package net.kodein.pages.home.fragment

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.js.onClickFunction
import net.kodein.charter.kodein
import net.kodein.text
import net.kodein.utils.*
import net.kodein.withBasePath
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.MouseEvent
import react.*
import react.dom.br
import react.dom.span
import styled.*
import kotlin.math.*


interface HeaderProps : RProps {
    var onScrollClick: () -> Unit
}

private data class Coords(val x: Int, val y: Int, val width: Int, val height: Int) {
    val xRight get() = x + width
    val yBottom get() = y + height
}

private data class Elements(
        val container: Coords,
        val bigText: Coords,
        val smallText: Coords,
        val separator: Coords,
        val scrollIndicator: Coords,
)

private fun Elements.rightCenterSpace() = Coords(
        x = bigText.xRight,
        y = 32,
        width = container.width - bigText.xRight - 32,
        height = container.height - 2 * 32
)

private fun Elements.rightBottomSpace() = Coords(
        x = smallText.xRight,
        y = smallText.y,
        width = container.width - smallText.xRight - 32,
        height = scrollIndicator.y - smallText.y - 16
)

private fun Elements.centerBottomSpace() = Coords(
        x = separator.xRight + 8,
        y = separator.y - 4,
        width = container.width - separator.xRight - 8 - 32,
        height = scrollIndicator.y - separator.y + 4 - 4
)

private val respirationAmplitude = 0.1

private fun Coords.sphereDiameter(): Int {
    if (width + (width * respirationAmplitude * 2) <= height) {
        return width
    } else {
        return (height / (1 + respirationAmplitude * 2)).toInt()
    }
}


val Header = functionalComponent<HeaderProps>("Header") { props ->

    val containerRef = useRef<HTMLDivElement?>(null)
    val bigTextRef = useRef<HTMLDivElement?>(null)
    val smallTextRef = useRef<HTMLDivElement?>(null)
    val separatorRef = useRef<HTMLDivElement?>(null)
    val scrollIndicatorRef = useRef<HTMLDivElement?>(null)

    var elements by useState<Elements?>(null)

    useEffectWithCleanup(emptyList()) {
        var previousElements = elements
        val onResize: (Event?) -> Unit = {
            val newElements = Elements(
                    container = Coords(
                            x = 0,
                            y = 0,
                            width = containerRef.current!!.clientWidth,
                            height = containerRef.current!!.clientHeight
                    ),
                    bigText = bigTextRef.current!!.recursiveOffset(upTo = containerRef.current!!).let { (x, y) ->
                        Coords(
                                x = x,
                                y = y,
                                width = bigTextRef.current!!.clientWidth,
                                height = bigTextRef.current!!.clientHeight
                        )
                    },
                    smallText = smallTextRef.current!!.recursiveOffset(upTo = containerRef.current!!).let { (x, y) ->
                        Coords(
                                x = x,
                                y = y,
                                width = smallTextRef.current!!.clientWidth,
                                height = smallTextRef.current!!.clientHeight
                        )
                    },
                    separator = separatorRef.current!!.recursiveOffset(upTo = containerRef.current!!).let { (x, y) ->
                        Coords(
                                x = x,
                                y = y,
                                width = separatorRef.current!!.clientWidth,
                                height = separatorRef.current!!.clientHeight
                        )
                    },
                    scrollIndicator = scrollIndicatorRef.current!!.recursiveOffset(upTo = containerRef.current!!).let { (x, y) ->
                        Coords(
                                x = x,
                                y = y,
                                width = scrollIndicatorRef.current!!.clientWidth,
                                height = scrollIndicatorRef.current!!.clientHeight
                        )
                    }
            )
            if (newElements != previousElements) {
                previousElements = newElements
                elements = newElements
            }
        }

        val intervalHandle = window.setInterval({ onResize(null) }, 250)
        window.addEventListener("resize", onResize)
        ({
            window.clearInterval(intervalHandle)
            window.removeEventListener("resize", onResize)
        })
    }

    flexColumn(justifyContent = JustifyContent.center, alignItems = Align.flexStart) {
        ref = containerRef
        css {
            width = 100.pct
            height = 100.pct
            backgroundColor = Color.kodein.dark
            position = Position.relative
        }

        child(HeaderText) {
            attrs.onScrollClick = props.onScrollClick
            attrs.bigTextRef = bigTextRef
            attrs.smallTextRef = smallTextRef
            attrs.separatorRef = separatorRef
        }

        // Uncomment this to see all possible sphere spaces
 /*
        elements?.let { e ->
            child(SphereContainer) {
                attrs.coords = e.rightCenterSpace()
                attrs.backgroundColor = Color.cornflowerBlue.withAlpha(0.5)
                attrs.showLargeSphere = true
            }
            child(SphereContainer) {
                attrs.coords = e.rightBottomSpace()
                attrs.backgroundColor = Color.forestGreen.withAlpha(0.5)
                attrs.showLargeSphere = true
            }
            child(SphereContainer) {
                attrs.coords = e.centerBottomSpace()
                attrs.backgroundColor = Color.indianRed.withAlpha(0.5)
                attrs.showLargeSphere = true
            }
        }
 */

        elements?.let { e ->
            val space = listOf(e.rightCenterSpace(), e.rightBottomSpace(), e.centerBottomSpace()).maxByOrNull { it.sphereDiameter() } !!
            child(SphereContainer) {
                attrs.coords = space
            }
        }

        flexRow(JustifyContent.center) {
            ref = scrollIndicatorRef
            css {
                position = Position.absolute
                width = 6.rem
                left = 50.pct - 3.rem
                bottom = 2.em
                +kodein.body
                portraitMobile {
                    specific { fontSize = 0.8.rem }
                    bottom = 1.em
                }
                landscapeMobile {
                    specific { fontSize = 0.8.rem }
                    bottom = 1.em
                }
                maxHeight(320) {
                    marginLeft = 3.rem
                }
            }

            child(ScrollIndicator, props)
        }
    }
}

private interface HeaderTextProps : HeaderProps {
    var bigTextRef: RMutableRef<HTMLDivElement?>
    var smallTextRef: RMutableRef<HTMLDivElement?>
    var separatorRef: RMutableRef<HTMLDivElement?>
}

private val HeaderText = functionalComponent<HeaderTextProps>("HeaderText") { props ->
    flexColumn(justifyContent = JustifyContent.center, alignItems = Align.flexStart) {
        css {
            paddingTop = 4.pct
            paddingBottom = 6.pct
            portrait {
                paddingTop = 8.pct
                paddingBottom = 12.pct
            }
            portraitMobile(800) {
                paddingTop = 0.pct
                paddingBottom = 0.pct
            }
            landscapeMobile {
                paddingTop = 0.pct
                paddingBottom = 0.pct
            }
            paddingLeft = 4.pct
            maxWidth(900) {
                paddingLeft = 0.pct
            }
            flexGrow = 1.0

            "span.nowrap" {
                whiteSpace = WhiteSpace.nowrap
            }
        }
        styledH1 {
            ref = props.bigTextRef
            css {
                +kodein.display3
                specific {
                    fontWeight = FontWeight.hairline
                    textAlign = TextAlign.start
                }
                color = Color.kodein.kaumon
                padding(1.rem, 2.rem)
                portraitMobile(800) { padding(1.rem, 1.rem, 0.5.rem, 1.rem) }
                landscapeMobile { padding(1.rem, 1.rem, 0.5.rem, 1.rem) }
            }

            text { home.everywhere }

//            +"Everywhere "
//            span("nowrap") { +"Kotlin goes," }
//            br {}
//            +"you should find"
//            br {}
//            +"the experts "
//            span("nowrap") { +"you need!" }
        }

        styledP {
            ref = props.smallTextRef
            css {
                +kodein.chapo
                textAlign = TextAlign.start
                color = Color.purple
                padding(1.rem, 2.rem)
                portraitMobile(800) { padding(0.5.rem, 1.rem) }
                landscapeMobile { padding(0.5.rem, 1.rem) }
                "br.mobile" {
                    display = Display.none
                    maxWidth(420) { display = Display.inherit }
                }
                "br.desktop" {
                    display = Display.inherit
                    maxWidth(420) { display = Display.none }
                }
            }
            +"We are Kodein Koders, "
            br("mobile") {}
            +"a tech company "
            br("desktop") {}
            +"driven by "
            br("mobile") {}
            +"our ideas for multiplatform "
            br {}
            +"and our passion "
            span("nowrap") { +"for craftsmanship." }
        }

        flexColumn(alignItems = Align.center) {
            ref = props.separatorRef
            css {
                flexGrow = 1.0
                width = 3.rem
                paddingLeft = 4.rem
                maxWidth(680) { paddingLeft = 2.rem }
            }

            styledSpan {
                css {
                    flexGrow = 1.0
                    display = Display.block
                    width = 0.05.rem
                    margin(3.rem, LinearDimension.auto)
                    backgroundColor = Color.kodein.korail
                    portraitMobile(800) { margin(1.rem, LinearDimension.auto) }
                    landscapeMobile { margin(1.rem, LinearDimension.auto) }
                    maxHeight(365) { display = Display.none }
                }
            }

            withBasePath { path ->
                styledImg(src = "$path/imgs/logo-kaumon.svg") {
                    css {
                        width = 1.5.rem
                        height = 1.5.rem
                        padding(0.5.rem)
                        margin(1.rem, 1.rem, 3.rem, 1.rem)
                        portraitMobile(800) { marginTop = 0.75.rem }
                        landscapeMobile { marginTop = 0.75.rem }
                        maxHeight(320) {
                            marginBottom = 1.rem
                        }
                        border(0.05.rem, BorderStyle.solid, Color.kodein.korail, 0.15.rem)
                        cursor = Cursor.pointer
                    }
                    attrs.onClickFunction = {
                        props.onScrollClick()
                    }
                }
            }
        }
    }

}

private fun Double.format(): String = ((this * 1000.0).roundToLong().toDouble() / 1000.0).toString()

private fun gradient(angle: Double): String = "linear-gradient(${angle.format()}rad, ${Color.kodein.kyzantium} 40%, ${Color.kodein.kuivre} 80%)"

private interface SphereContainerProps : RProps {
    var coords: Coords
    var backgroundColor: Color?
    var showLargeSphere: Boolean?
}

private val respirationInterval = 5000

private val SphereContainer = functionalComponent<SphereContainerProps>("SphereContainer") { props ->
    var respirationDelta by useState(respirationAmplitude)

    useEffectWithCleanup(emptyList()) {
        var r = respirationDelta
        val respiration = {
            r *= -1
            respirationDelta = r
        }
        val handle = window.setInterval({ respiration() }, respirationInterval)
        window.setTimeout({ respiration() }, 100)
        ({ window.clearInterval(handle) })
    }

    styledDiv {
        css {
            position = Position.absolute
            if (props.backgroundColor != null) backgroundColor = props.backgroundColor!!
            left = props.coords.x.px
            top = props.coords.y.px
            width = props.coords.width.px
            height = props.coords.height.px
        }

        val diameter = props.coords.sphereDiameter()

        styledDiv {
            css {
                position = Position.absolute
                left = 50.pct - (diameter / 2).px
                top = 50.pct - (diameter / 2).px + (diameter * respirationDelta).px
                width = diameter.px
                height = diameter.px
                transition(::top, respirationInterval.ms, Timing.easeInOut)
            }
            child(Sphere) {
                attrs.largeSphereDiameter = diameter
                attrs.smallSphereCoef = if (diameter < 220) 0.8 else 0.7
                attrs.showLargeSphere = props.showLargeSphere
            }
        }

    }
}

private interface SphereProps : RProps {
    var largeSphereDiameter: Int
    var smallSphereCoef: Double?
    var showLargeSphere: Boolean?
}

private val Sphere = functionalComponent<SphereProps>("Sphere") { props ->
    val largeSphereDiv = useRef<HTMLDivElement?>(null)
    val smallSphereDiv = useRef<HTMLDivElement?>(null)

    val largeSphereRadiusPx = props.largeSphereDiameter / 2
    val smallSphereRadiusCoef = props.smallSphereCoef ?: 0.7

    useEffectWithCleanup(emptyList()) {
        // Mathematics courtesy of William Grossman.
        // This is a small sphere, inside a large sphere.
        // Considering a geometric reference whose origin (0, 0) is the center of the large sphere,
        //   and whose positive values for X is right and for Y is down.
        // The large sphere radius is 1 (therefore its diameter is 2).
        // MouseVector is the vector between starting at the center and pointing to the mouse.
        // The small sphere center must be between 0 and (1 - smallSphereRadius) of the large sphere,
        //   since we don't want the small sphere to escape the large.
        // The exact distance between the center of the large sphere and the center of the small sphere
        //   is function of the distance between the large sphere center and the mouse
        //   (e.g. the length of MouseVector).
        // Since the small sphere center is on the vector of the mouse, we can assert:
        //   mouseVectorX / mouseVectorY = smallSphereX / smallSphereY
        // Also, since we know the distance between the 2 centers we can assert that:
        //   mouseVectorX^2 + mousevectorY^2 = distance^2
        // From those equation, we deduct:
        //   smallSphereY = distance / sqrt((mouseVectorX^2 / mouseVectorY^2) + 1) * sign(mouseVectorY)
        //   smallSphereX = sqrt(distance^2 - smallSphereY^2) * sign(mouseVectorX)
        // Finally, since the coordinates of the small sphere is the top left corner of its div
        //   relative to the top left corner of the large sphere div, we first need to transform
        //   the coordinate of the small sphere from its center to its top left corner (x - smallSphereRadius, y - smallSphereRadius),
        //   then we need to map these coordinates to the large sphere top left corner reference (1 + x, 1 + y)
        val mousemove = EventListener { event ->
            event as MouseEvent
            val (divX, divY) = largeSphereDiv.current!!.recursiveOffset()
            val mouseVectorX = (event.clientX + window.scrollX) - (divX + largeSphereDiv.current!!.clientWidth / 2)
            val mouseVectorY = (event.clientY + window.scrollY) - (divY + largeSphereDiv.current!!.clientHeight / 2)
            val mouseVectorLen = sqrt(mouseVectorX.pow(2) + mouseVectorY.pow(2))

            val ratio = mouseVectorLen / 512.0
            val distance = (1 - smallSphereRadiusCoef) * (1 - 1 / (exp(2 * ratio))) //ratio, 1.0)

            val smallSphereY = distance / sqrt((mouseVectorX.pow(2) / mouseVectorY.pow(2)) + 1) * sign(mouseVectorY)
            val smallSphereX = sqrt(distance.pow(2) - smallSphereY.pow(2)) * sign(mouseVectorX)

            smallSphereDiv.current!!.style.left = "${(1 + (smallSphereX - smallSphereRadiusCoef)) * largeSphereRadiusPx}px"
            smallSphereDiv.current!!.style.top = "${(1 + (smallSphereY - smallSphereRadiusCoef)) * largeSphereRadiusPx}px"

            val angle = acos((mouseVectorY / mouseVectorLen) * -1.0) * sign(mouseVectorX)
            smallSphereDiv.current!!.style.background = gradient(angle)
        }

        document.addEventListener("mousemove", mousemove)

        ({ document.removeEventListener("mousemove", mousemove) })
    }

    styledDiv {
        ref = largeSphereDiv
        css {
            position = Position.relative
            width = (2 * largeSphereRadiusPx).px
            height = (2 * largeSphereRadiusPx).px
            borderRadius = 100.pct
            if (props.showLargeSphere == true) backgroundColor = Color.aliceBlue.withAlpha(0.6)
        }
        styledSpan {
            ref = smallSphereDiv
            css {
                position = Position.absolute
                top = ((1 - smallSphereRadiusCoef) * largeSphereRadiusPx).px
                left = ((1 - smallSphereRadiusCoef) * largeSphereRadiusPx).px
                transition(::top, 2.s, timing = Timing.easeOut)
                transition(::left, 2.s, timing = Timing.easeOut)
                width = ((2 * smallSphereRadiusCoef) * largeSphereRadiusPx).px
                height = ((2 * smallSphereRadiusCoef) * largeSphereRadiusPx).px
                borderRadius = 100.pct
                background = gradient(-0.18)
            }
        }
    }
}

private val ScrollIndicator = functionalComponent<HeaderProps>("ScrollIndicator") { props ->
    var isTop by useState(true)
    var isVisible by useState(false)
    val img = useRef<HTMLImageElement?>(null)
    val isMobile = useIsMobile()

    useEffectWithCleanup(listOf(isVisible, isMobile)) {
        if (!isVisible) { return@useEffectWithCleanup ({}) }

        fun execute() {
            img.current?.style?.opacity = "1.0"
            window.setTimeout({
                img.current?.style?.paddingTop = if (isMobile) "0rem" else "1rem"
            }, 500)
            window.setTimeout({
                img.current?.style?.opacity = "0.0"
            }, 1500)
            window.setTimeout({
                img.current?.style?.paddingTop = if (isMobile) "1rem" else "0rem"
            }, 2500)
        }

        val handle = window.setInterval(::execute, 3500)
        execute()

        ({ window.clearInterval(handle) })
    }

    useEffectWithCleanup(listOf(isTop)) {
        if (isTop) {
            val handle = window.setTimeout({
                isVisible = true
            }, 4000)
            return@useEffectWithCleanup ({ window.clearTimeout(handle) })
        } else {
            isVisible = false
            return@useEffectWithCleanup ({})
        }
    }

    useEffectWithCleanup(emptyList()) {
        val scroll = EventListener {
            isTop = window.scrollY < 1.0
        }
        window.addEventListener("scroll", scroll)
        ({ window.removeEventListener("scroll", scroll) })
    }

    flexColumn(JustifyContent.center, Align.center) {
        css {
            opacity = if (isVisible) 1.0 else 0.0
            transition(::opacity, .5.s)
            cursor = Cursor.pointer
        }
        attrs.onClickFunction = {
            props.onScrollClick()
        }

        styledDiv {
            css {
                height = 3.em
                marginBottom = .5.em
            }
            withBasePath { path ->
                styledImg(src = "$path/imgs/pointer-${if (isMobile) "hand" else "mouse"}-korail.svg") {
                    ref = img
                    css {
                        height = 2.em
                        paddingTop = if (isMobile) 1.rem else 0.rem
                        opacity = 1.0
                        transition(::opacity, .5.s)
                        transition(::paddingTop, 1.s)
                    }
                }
            }
        }
        styledP {
            css {
                color = Color.kodein.korail
            }
            +"SCROLL"
        }
    }
}
