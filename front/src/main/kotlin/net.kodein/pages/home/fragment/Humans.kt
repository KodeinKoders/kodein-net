package net.kodein.pages.home.fragment

import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.components.SwipeableDiv
import net.kodein.useText
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.hairline
import net.kodein.utils.maxWidth
import net.kodein.withBasePath
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import react.dom.a
import styled.*
import kotlin.math.ceil


val Humans = functionalComponent<RProps>("Humans") {
    val strings = useText().home

    styledDiv {
        css {
            margin(3.rem, 0.rem)
        }

        styledH1 {
            css {
                +kodein.display3
                specific {
                    fontWeight = FontWeight.hairline
                    textAlign = TextAlign.start
                }
                color = Color.kodein.orange
                paddingLeft = 5.rem
                maxWidth(440) { paddingLeft = 2.rem }
                maxWidth(360) { paddingLeft = 0.75.rem }
            }
            +strings.humansTitle
        }

        styledH3 {
            css {
                +kodein.display1
                specific { textAlign = TextAlign.start }
                color = Color.kodein.orange
                paddingLeft = 5.rem
                maxWidth(440) { paddingLeft = 2.rem }
                maxWidth(360) { paddingLeft = 0.75.rem }
            }
            +strings.humansSubtitle
        }

        child(HumanSlider)
        child(HumanStack)

    }
}

val humans = listOf(
        HumanProps(
            personName = "Fabrice Drouin",
            personPicture = "fabrice-drouin.jpeg",
            personJob = "Founder & CTO",
            personUrl = "https://www.linkedin.com/in/fabrice-drouin-95ab8012",
            companyName = "ACINQ",
            companyLogo = "acinq.svg",
            companyUrl = "https://acinq.co"
        ),
        HumanProps(
            personName = "Thalia Cruz Castañares",
            personPicture = "thalia-cruz.jpg",
            personJob = "Android team manager",
            personUrl = "https://www.linkedin.com/in/thal%C3%ADa-cruz-casta%C3%B1ares-a1257a3a",
            companyName = "Softbank Robotics",
            companyLogo = "softbank.svg",
            companyUrl = "https://www.softbankrobotics.com"
        ),
        HumanProps(
            personName = "Jochen Buhler",
            personPicture = "jochen-buhler.jpg",
            personJob = "Software Engineer",
            personUrl = "https://www.xing.com/profile/Jochen_Buehler8",
            companyName = "Bosch",
            companyLogo = "bosch.svg",
            companyUrl = "https://www.bosch.fr"
        ),
        HumanProps(
            personName = "Cedric Ravalec",
            personPicture = "cedric-ravalec.jpeg",
            personJob = "Embedded & IoT Business Line Manager",
            companyName = "Smile",
            companyLogo = "smile.png",
            personUrl = "https://www.linkedin.com/in/ravalec",
            companyUrl = "https://www.smile.eu"
        )
).shuffled()

@OptIn(ExperimentalStdlibApi::class)
val HumanSlider = functionalComponent<RProps>("HumanList") {
    val div = useRef<HTMLDivElement?>(null)

    var repeatCount by useState { 0 }

    useEffectWithCleanup(emptyList()) {
        val batchWidth = (20 * 16) * humans.size // 1rem = 16px
        val onResize: ((Event?) -> Unit) = {
            val count = (window.innerWidth.toDouble() / batchWidth.toDouble())
            repeatCount = ceil(count).toInt() + 1
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    styledDiv {
        css {
            overflow = Overflow.hidden
            width = 100.pct
            maxWidth(750) { display = Display.none }
            padding(top = 2.rem)
        }

        styledDiv {
            css {
                animation(
                    duration = (humans.size * 16).s,
                    timing = Timing.linear,
                    iterationCount = IterationCount.infinite
                ) {
                    from {
                        transform { translateX(0.rem) }
                    }
                    to {
                        transform { translateX((humans.size * -20).rem) }
                    }
                }
                put("animation-play-state", "running")
                hover {
                    put("animation-play-state", "paused")
                }
            }
            flexRow {
                ref = div
                css {
                    width = 20.rem * humans.size * repeatCount
                    animation(
                        duration = (humans.size * 16).s,
                        timing = Timing.linear,
                        iterationCount = IterationCount.infinite
                    ) {
                        from {
                            transform { translateX(0.rem) }
                        }
                        to {
                            transform { translateX((humans.size * -20).rem) }
                        }
                    }
                }

                repeat(repeatCount) {
                    humans.forEach { human ->
                        child(Human, human)
                    }
                }
            }
        }
    }
}

val HumanStack = functionalComponent<RProps>("HumanList") {

    var index by useState(Int.MAX_VALUE / 2)

    useEffectWithCleanup(listOf(index)) {
        var i = index
        val handle = window.setTimeout({ index = ++i }, 6000)
        ({ window.clearTimeout(handle) })
    }

    child(SwipeableDiv) {
        attrs {
            containerCss = {
                display = Display.none
                maxWidth(750) { display = Display.block }
                width = 100.pct
                height = 36.rem
                overflow = Overflow.hidden
                paddingBottom = 2.5.rem
            }
            onSwipe = {
                index += if (it) 1 else -1
            }
        }

        val count = 6

        for (i in (count - 1) downTo -1) {
            styledDiv {
                key = "human-${index + i}"
                css {
                    width = 20.rem
                    height = 32.rem
                    position = Position.absolute
                    left = 50.pct - 10.rem
                    top = 4.rem
                    put("transform-origin", "top center")
                    when {
                        i > 0 -> transform {
                            translateY((-(0.5 * i)).rem)
                            scale(1.0 - i * (0.2 / count))
                        }
                        i == 0 -> {}
                        i < 0 -> {
                            transform { translateX(8.rem) }
                            opacity = 0.0
                            pointerEvents = PointerEvents.none
                        }
                    }
                    transition(::transform, 0.5.s)
                    transition(::opacity, 0.5.s)
                }

                styledDiv {
                    css {
                        position = Position.absolute
                        left = 0.pct
                        top = 0.pct
                        width = 100.pct
                        height = 100.pct
                        backgroundColor = Color.white
                    }
                }

                styledDiv {
                    css {
                        backgroundColor = Color.white
                        if (i >= 0) opacity = 1.0 - (1.0 / (count - 1)) * i
                        transition(::opacity, 0.5.s)
                    }
                    child(Human, humans[(index + i) % humans.size])
                }
            }
        }

    }
}

interface HumanProps: RProps {
    var personName: String
    var personPicture: String
    var personJob: String
    var personUrl: String
    var companyName: String
    var companyLogo: String
    var companyUrl: String

    companion object {
        operator fun invoke(
            personName: String,
            personPicture: String,
            personJob: String,
            companyName: String,
            companyLogo: String,
            personUrl: String,
            companyUrl: String
        ) = jsObject<HumanProps> {
            this.personName = personName
            this.personPicture = personPicture
            this.personJob = personJob
            this.companyName = companyName
            this.companyLogo = companyLogo
            this.personUrl = personUrl
            this.companyUrl = companyUrl
        }
    }
}

private val Human = functionalComponent<HumanProps>("Human") { props ->
    flexColumn(alignItems = Align.center) {
        css {
            border(0.rem, BorderStyle.solid, Color.black, 0.2.rem)
            overflow = Overflow.hidden
            boxShadow(Color.kodein.cute.withAlpha(0.6), 0.rem, 0.4.rem, blurRadius = 2.rem)
            put("clip-path", "polygon(-100% -100%, -100% 0%, 0% 0%, 0% 100%, -100% 100%, -100% 200%, 200% 200%, 200% -100%)")
            firstChild {
                put("clip-path", "inherit")
            }
            width = 20.rem
            height = 32.rem
            position = Position.relative
            backgroundColor = Color.white

            hover {
                ".profile-pic" {
                    width = 11.rem
                    height = 11.rem
                    left = 50.pct - 5.5.rem
                    top = 10.rem - 5.5.rem
                    borderRadius = 4.rem
                }
                ".background-pic" {
                    width = 20.rem + 6.rem
                    height = 10.rem + 6.rem
                    marginLeft = (-3).rem
                    marginTop = (-3).rem
                }
                "img.logo" {
                    opacity = 0.75
                    filter = "grayscale(0%)"
                }
            }

            "* a" {
                hover {
                    color = Color.kodein.purple
                }
            }
        }

        withBasePath { path ->
            styledDiv {
                css {
                    backgroundColor = Color.kodein.dark
                    overflow = Overflow.hidden
                    width = 100.pct
                    height = 10.rem
                }
                styledImg(src = "$path/imgs/humans/${props.personPicture}", alt = props.personName) {
                    attrs.classes += "background-pic"
                    attrs {
                        width = "345"
                        height = "195"
                    }

                    css {
                        width = 20.rem + 3.rem
                        height = 10.rem + 3.rem
                        marginLeft = (-1.5).rem
                        marginTop = (-1.5).rem
                        objectFit = ObjectFit.cover
                        filter = "blur(1rem)"
                        opacity = 0.4
                        transition(::width, 0.6.s)
                        transition(::height, 0.6.s)
                        transition(::marginLeft, 0.6.s)
                        transition(::marginTop, 0.6.s)
                    }
                }
            }
            styledImg(src = "$path/imgs/humans/${props.personPicture}", alt = props.personName) {
                attrs.classes += "profile-pic"
                attrs {
                    width = "150"
                    height = "150"
                }
                css {
                    position = Position.absolute
                    left = 50.pct - 5.rem
                    top = 10.rem - 5.rem
                    width = 10.rem
                    height = 10.rem
                    objectFit = ObjectFit.cover
                    borderRadius = 5.rem
                    zIndex = 1
                    transition(::width, 0.6.s)
                    transition(::height, 0.6.s)
                    transition(::left, 0.6.s)
                    transition(::top, 0.6.s)
                    transition(::borderRadius, 0.6.s)
                }
            }
            styledH3 {
                css {
                    +kodein.intertitre
                    marginTop = 7.rem
                    width = 18.rem
                    textAlign = TextAlign.center
                }
                a(href = props.personUrl, target = "_blank") {
                    +props.personName
                }
            }
            styledP {
                css {
                    +kodein.body
                    width = 18.rem
                    marginTop = 0.5.rem
                    color = Color.kodein.orange
                    textAlign = TextAlign.center
                }
                +"${props.personJob} "
                styledSpan {
                    css.whiteSpace = WhiteSpace.nowrap
                    +"at ${props.companyName}"
                }
            }
            a(href = props.companyUrl, target = "_blank") {
                styledImg(src = "$path/imgs/logos/${props.companyLogo}", alt = props.companyName) {
                    attrs.classes += "logo"
                    attrs {
                        width = "120"
                        height = "60"
                    }
                    css {
                        width = 8.em
                        height = 4.em
                        objectFit = ObjectFit.contain
                        position = Position.absolute
                        bottom = 2.5.em
                        left  = 50.pct - 4.em
                        transition(::opacity, 0.6.s)
                        transition(::filter, 0.6.s)
                        filter = "grayscale(85%)"
                        opacity = 0.4
                    }
                }
            }
        }
    }
}
