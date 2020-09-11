package net.kodein.pages.home.fragment

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.js.onClickFunction
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.MouseEvent
import react.*
import react.dom.br
import styled.*
import kotlin.math.*


interface HeaderProps : RProps {
    var onScrollClick: () -> Unit
}

val Header by functionalComponent<HeaderProps> { props ->
    flexColumn {
        css {
            width = 100.pct
            height = 100.pct
            backgroundColor = Color.kodein.dark
        }

        flexRow {
            css {
                flexGrow = 1.0
            }

            flexColumn(justifyContent = JustifyContent.center) {

                styledH1 {
                    css {
                        +KodeinStyles.display2
                        fontWeight = FontWeight.hairline
                        color = Color.kodein.kaumon
                        textAlign = TextAlign.start
                        margin(1.rem, 2.rem)
                    }
                    +"Everywhere Kotlin goes,"
                    br {}
                    +"you should find"
                    br {}
                    +"the experts you need!"
                }

                styledP {
                    css {
                        +KodeinStyles.chapo
                        textAlign = TextAlign.start
                        color = Color.purple
                        margin(1.rem, 2.rem)
                    }
                    +"We are Kodein Koders, a tech company"
                    br {}
                    +"that is driven by our ideas for multiplatform"
                    br {}
                    +"and our passion for craftsmanship."
                }

                flexColumn(alignItems = Align.center) {
                    css {
                        width = 3.rem
                        marginLeft = 4.rem
                    }

                    styledSpan {
                        css {
                            +KodeinStyles.separator
                            display = Display.block
                            width = 0.05.rem
                            backgroundColor = Color.kodein.korail
                        }
                    }

                    styledImg(src = "imgs/logo-kaumon.svg") {
                        css {
                            width = 1.5.rem
                            height = 1.5.rem
                            padding(0.5.rem)
                            margin(1.rem)
                            border(0.05.rem, BorderStyle.solid, Color.kodein.korail, 0.15.rem)
                            cursor = Cursor.pointer
                        }
                        attrs.onClickFunction = {
                            props.onScrollClick()
                        }
                    }
                }
            }

            flexColumn(justifyContent = JustifyContent.center, alignItems = Align.center) {
                css {
                    flexGrow = 1.0
                }

                child(Sphere)
            }
        }
        flexRow(JustifyContent.center) {
            css {
                paddingBottom = 2.rem
            }

            child(ScrollIndicator, props)
        }
    }
}

private fun Double.format(): String = ((this * 1000.0).roundToLong().toDouble() / 1000.0).toString()

private fun gradient(angle: Double): String = "linear-gradient(${angle.format()}rad, ${Color.kodein.kyzantium} 40%, ${Color.kodein.kuivre} 80%)"

private val Sphere by functionalComponent {
    val largeSphereDiv = useRef<HTMLDivElement?>(null)
    val smallSphereDiv = useRef<HTMLDivElement?>(null)

    val amplitude = 16.0
    val smallSphereRadius = .8

    useEffectWithCleanup {
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
            val distance = (1 - smallSphereRadius) * (1 - 1 / (exp(2 * ratio))) //ratio, 1.0)

            val smallSphereY = distance / sqrt((mouseVectorX.pow(2) / mouseVectorY.pow(2)) + 1) * sign(mouseVectorY)
            val smallSphereX = sqrt(distance.pow(2) - smallSphereY.pow(2)) * sign(mouseVectorX)

            smallSphereDiv.current!!.style.left = "${(1 + (smallSphereX - smallSphereRadius)) * amplitude}rem"
            smallSphereDiv.current!!.style.top = "${(1 + (smallSphereY - smallSphereRadius)) * amplitude}rem"

            val angle = acos((mouseVectorY / mouseVectorLen) * -1.0) * sign(mouseVectorX)
            smallSphereDiv.current!!.style.background = gradient(angle)
        }

        document.addEventListener("mousemove", mousemove)

        ({ document.removeEventListener("mousemove", mousemove) })
    }

    val respirationInterval = 5000
    useEffectWithCleanup {
        var margin = -4
        fun invert() {
            margin *= -1
            largeSphereDiv.current!!.style.marginTop = "${margin}rem"
        }
        val handle = window.setInterval(::invert, respirationInterval)
        window.setTimeout(::invert, 1)
        ({ window.clearInterval(handle) })
    }

    styledDiv {
        ref = largeSphereDiv
        css {
            position = Position.relative
            width = (2 * amplitude).rem
            height = (2 * amplitude).rem
            borderRadius = 100.pct
            marginTop = (-5).rem
            transition(::marginTop, respirationInterval.ms, Timing.easeInOut)
        }
        styledSpan {
            ref = smallSphereDiv
            css {
                position = Position.absolute
                top = ((1 - smallSphereRadius) * amplitude).rem
                left = ((1 - smallSphereRadius) * amplitude).rem
                transition(::top, 2.s, timing = Timing.easeOut)
                transition(::left, 2.s, timing = Timing.easeOut)
                width = ((2 * smallSphereRadius) * amplitude).rem
                height = ((2 * smallSphereRadius) * amplitude).rem
                borderRadius = 100.pct
                background = gradient(-0.18)
            }
        }
    }
}

private val ScrollIndicator by functionalComponent<HeaderProps> { props ->
    var isTop by useState(true)
    var isVisible by useState(false)
    val img = useRef<HTMLImageElement?>(null)

    useEffectWithCleanup(listOf(isVisible)) {
        if (!isVisible) { return@useEffectWithCleanup ({}) }

        fun execute() {
            img.current?.style?.opacity = "1.0"
            window.setTimeout({
                img.current?.style?.paddingTop = "1rem"
            }, 500)
            window.setTimeout({
                img.current?.style?.opacity = "0.0"
            }, 1500)
            window.setTimeout({
                img.current?.style?.paddingTop = "0rem"
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

    useEffectWithCleanup {
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
                height = 3.rem
                marginBottom = .5.rem
            }
            styledImg(src = "imgs/mouse-korail.svg") {
                ref = img
                css {
                    height = 2.rem
                    opacity = 1.0
                    transition(::opacity, .5.s)
                    transition(::paddingTop, 1.s)
                }
            }
        }
        styledP {
            css {
                +KodeinStyles.body
                color = Color.kodein.korail
            }
            +"SCROLL"
        }
    }
}
