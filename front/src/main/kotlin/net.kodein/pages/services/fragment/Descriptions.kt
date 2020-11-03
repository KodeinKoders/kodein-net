package net.kodein.pages.services.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.LinearDimension.Companion.none
import kotlinx.css.properties.borderBottom
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.components.layerSeparator
import net.kodein.components.layerSeparatorAbsolute
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP

val Descriptions = functionalComponent<RProps>("Descriptions") {
    flexColumn {
        styledDiv {
            css {
                zIndex = 20
                +kodein.dropShadow
            }

            layerSeparator(Position.absolute, Color.kodein.dark, Color.kodein.kyzantium, Color.kodein.orange, Color.kodein.cute)

            child(Description) {
                attrs.first = true
                attrs.title = "Consultancy"

                +"""That you are starting a new project,
                        |looking for expertise, or in the middle of one other
                        |considering taking some accompaniment to validate
                        |or boost your development process, we can guide you through,
                        |either it's back-end or mobile, using Kotlin.""".trimMargin()
            }

        }

        styledDiv {
            css {
                zIndex = 19
                display = Display.none
                marginTop = (-2).rem
                +kodein.dropShadow

                maxWidth(980) {
                    display = Display.flex
                }
            }

            child(Illustration)
            layerSeparator(Position.absolute, Color.kodein.orange, Color.kodein.kinzolin)
        }

        styledDiv {
            css {
                zIndex = 18
                marginTop = (-2).rem
                +kodein.dropShadow
            }

            layerSeparator(Position.absolute, Color.kodein.korail, Color.kodein.purple)

            child(Description) {
                attrs.title = "Project Development"

                +"""We can help you reach your goals by taking care
                            |of the technical challenges you have. Using Scrum methodology,
                            |we will provide you a nice and easy way to follow up on
                            |your project while we are taking care of everything sprint by sprint.
                            |Focused on running Kotlin code everywhere, our experts take advantage
                            |of Kotlin/Multiplatform to be able to write and test business logic
                            |efficiently once and use it on every targeted system (Backend / Mobile / Web / Desktop)""".trimMargin()

            }
        }

        styledDiv {
            css {
                zIndex = 17
                marginTop = (-2).rem
            }
            layerSeparator(Position.relative, Color.kodein.orange, Color.kodein.kyzantium)
        }
    }
}

private interface DescriptionProps : RProps {
    var title: String?
    var first: Boolean
}
private val Description = functionalComponent<DescriptionProps>("Description") { props ->
    flexRow {
        css {
            width = 100.pct
            backgroundColor = if (props.first) Color.kodein.kaumon else Color.kodein.cute
            put("clip-path", "polygon(0% 5%, 100% 0%, 100% calc(100% - 2rem), 0% 100%)")
        }

        if (!props.first) child(Illustration) { attrs.last = true }

        flexColumn {
            css {
                flexGrow = 70.0
                flexBasis = FlexBasis.zero
                margin(auto, 2.rem)

                if(props.first) padding(8.rem, 4.rem, 4.rem, 4.rem)
                else padding(4.rem)

                maxWidth(980) {
                    if(props.first) padding(8.rem, 0.rem, 4.rem, 0.rem)
                    else padding(4.rem, 0.rem)
                }
            }

            styledP {
                css {
                    +kodein.display1
                    specific {
                        fontWeight = FontWeight.light
                    }
                    alignSelf = Align.center
                    textAlign = TextAlign.center
                    color = Color.kodein.orange
                    marginBottom = 2.rem
                    borderBottom(0.05.rem, BorderStyle.solid, Color.kodein.orange)
                }
                props.title?.let { +it }
            }

            styledP {
                css {
                    +kodein.chapo
                    specific {
                        fontWeight = FontWeight.light
                        textAlign = TextAlign.center
                    }
                    color = Color.kodein.kyzantium
                    alignSelf = Align.center
                    maxWidth = 60.rem
                }

                props.children()
            }
        }

        if (props.first) child(Illustration) { attrs.first = true }
    }
}

interface ServiceIllustrationProps : RProps {
    var first: Boolean
    var last: Boolean
}

private val Illustration = functionalComponent<ServiceIllustrationProps>("Illustration") { props ->
    val div = useRef<HTMLDivElement?>(null)

    var image: String? by useState(null)

    useEffectWithCleanup(emptyList()) {
        val onResize: ((Event?) -> Unit) = {
            val divWidth = div.current!!.clientWidth
            val imgWidth = illustrationWidths.firstOrNull { it >= (divWidth * 1.2) } ?: illustrationWidths.last()
            image = "services_${imgWidth}"
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    styledDiv {
        ref = div
        attrs.classes += "illustration"
        css {
            flexGrow = 30.0
            flexBasis = FlexBasis.zero
            minHeight = 30.rem

            backgroundSize = "cover"
            backgroundPosition = "center"

            minWidth(1080) {
                minHeight = 35.rem
            }
            minWidth(1280) {
                minHeight = 40.rem
            }
            minWidth(1440) {
                minHeight = 45.rem
            }
            minWidth(1920) {
                minHeight = 50.rem
            }
            minWidth(2500) {
                minHeight = 55.rem
            }
            maxWidth(980) {
                if (props.first || props.last)
                    display = Display.none
                else {
                    flexGrow = 1.0
                    put("clip-path", "polygon(0% 0%, 100% 0%, 100% calc(100% - 2rem), 0% 100%)")
                }
            }

            "body.webp &" {
                backgroundColor = Color.kodein.kaumon
            }

            "img" {
                objectPosition = when {
                    props.first -> "left"
                    props.last -> "right"
                    else -> "center"
                }
            }
        }

        if (image != null) {
            picture {
                source("image/webp", "imgs/illus/$image.webp" to null)
                source("image/jpeg", "imgs/illus/$image.jpg" to null)

                styledImg(alt = image, src = "imgs/illus/$image.jpg") {
                    css {
                        width = 100.pct
                        height = 100.pct
                        objectFit = ObjectFit.cover
                    }
                }
            }
        }
    }
}
