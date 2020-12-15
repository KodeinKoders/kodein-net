package net.kodein.components

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.withBasePath
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.TouchEvent
import org.w3c.dom.events.Event
import react.*
import styled.css
import styled.styledDiv
import styled.styledImg
import kotlin.js.Date
import kotlin.math.abs


interface SwipeableDivProps : RProps {
    var containerCss: RuleSet?
    var indicatorCss: RuleSet?
    var onSwipe: (Boolean) -> Unit
}

val SwipeableDiv = functionalComponent<SwipeableDivProps>("SwipeableDiv") { props ->
    val div = useRef<HTMLDivElement?>(null)
    val img = useRef<HTMLImageElement?>(null)

    var swipeIndicatorAnim: Int by useState(-1)
    var swipeIndicatorVisible by useState(true)
    var swipeIndicatorInView by useState(false)

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
                it.preventDefault()
            }
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
            props.containerCss?.invoke(this)
            position = Position.relative
        }

        withBasePath { path ->
            styledImg(src = "$path/imgs/swipe.svg") {
                ref = img
                css {
                    props.indicatorCss?.invoke(this)
                    position = Position.absolute
                    left = 50.pct - 3.rem
                    top = 1.7.rem
                    width = 3.rem
                    zIndex = 100
                    pointerEvents = PointerEvents.none
                    transform { translateX( (swipeIndicatorAnim * 3).rem ) }
                    transition(::transform, 1.5.s, Timing.easeInOut)
                    opacity = if (swipeIndicatorVisible) 1.0 else 0.0
                    transition(::opacity, 1.s, Timing.linear)
                }
            }
        }

        props.children()
    }

}