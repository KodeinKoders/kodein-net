package net.kodein.pages.home.fragment

import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.classes
import kotlinx.html.id
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import react.*
import react.dom.a
import react.dom.br
import react.dom.key
import styled.*
import kotlin.time.seconds


val Humans = functionalComponent<RProps>("Humans") {

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
            }
            +"Humans trust us!"
        }

        styledH3 {
            css {
                +kodein.display1
                fontWeight = FontWeight.regular
                color = Color.kodein.orange
                textAlign = TextAlign.start
                paddingLeft = 5.rem
            }
            +"Are you next?"
        }

        child(HumanList)

    }
}

val humans = listOf(
        HumanProps(
                name = "Fabrice Drouin",
                picture = "fabrice-drouin.jpeg",
                job = "Founder & CTO",
                company = "ACINQ",
                url = "https://www.linkedin.com/in/fabrice-drouin-95ab8012",
                twitter = "acinq_co"
        ),
        HumanProps(
                name = "Thalia Cruz Castañares",
                picture = "thalia-cruz.jpg",
                job = "Android team manager",
                company = "Softbank Robotics",
                url = "https://www.linkedin.com/in/thal%C3%ADa-cruz-casta%C3%B1ares-a1257a3a",
                twitter = "sbreurope"
        ),
        HumanProps(
                name = "Jochen Buhler",
                picture = "jochen-buhler.jpg",
                job = "Software Engineer",
                company = "Bosch",
                url = "https://www.xing.com/profile/Jochen_Buehler8",
                twitter = "boschglobal"
        ),
        HumanProps(
                name = "Rémi B. Loizeau",
                picture = "remi-bouvet.jpg",
                job = "Crayola en chef",
                company = "Methodic.Design",
                url = "https://www.linkedin.com/in/r%C3%A9mi-bouvet-loizeau-914536155",
                twitter = "remi_b_loizeau"
        ),
        HumanProps(
                name = "Cedric Ravalec",
                picture = "cedric-ravalec.jpeg",
                job = "Embedded & IoT Business Line Manager",
                company = "Smile",
                url = "https://www.linkedin.com/in/ravalec",
                twitter = "groupesmile"
        )
).shuffled()

@OptIn(ExperimentalStdlibApi::class)
val HumanList = functionalComponent<RProps>("HumanList") {
    val div = useRef<HTMLDivElement?>(null)

    styledDiv {
        css {
            overflow = Overflow.hidden
            width = 100.pct
        }

        val count = 4

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
                    width = 20.rem * humans.size * 4
                    padding(vertical = 2.em)
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

                repeat(4) {
                    humans.forEach { human ->
                        child(Human, human)
                    }
                }
            }
        }
    }
}

interface HumanProps: RProps {
    var name: String
    var picture: String
    var job: String
    var company: String
    var url: String
    var twitter: String

    companion object {
        operator fun invoke(
                name: String,
                picture: String,
                job: String,
                company: String,
                url: String,
                twitter: String
        ) = jsObject<HumanProps> {
            this.name = name
            this.picture = picture
            this.job = job
            this.company = company
            this.url = url
            this.twitter = twitter
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
            }
        }

        styledDiv {
            css {
                backgroundColor = Color.kodein.dark
                overflow = Overflow.hidden
                width = 100.pct
                height = 10.rem
            }
            styledImg(src = "imgs/humans/${props.picture}") {
                attrs.classes += "background-pic"
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
        styledImg(src = "imgs/humans/${props.picture}", alt = props.name) {
            attrs.classes += "profile-pic"
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
                marginTop = 7.5.rem
                width = 18.rem
                textAlign = TextAlign.center
            }
            a(href = props.url, target="_blank") {
                +props.name
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
            +"${props.job} "
            styledSpan {
                css.whiteSpace = WhiteSpace.nowrap
                +"at ${props.company}"
            }
            br {}
            a(href = "https://twitter.com/${props.twitter}") {
                +"@${props.twitter}"
            }
        }
    }
}
