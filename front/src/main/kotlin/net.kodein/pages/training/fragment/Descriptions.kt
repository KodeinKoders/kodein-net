package net.kodein.pages.training.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.components.layerSeparator
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP

val Description = functionalComponent<RProps>("Description") {
    flexColumn {

        styledDiv {
            css {
                zIndex = 20
                +kodein.dropShadow
            }

            flexRow {
                css {
                    width = 100.pct
                    backgroundColor = Color.kodein.kyzantium
                    put("clip-path", "polygon(0% 5%, 100% 0%, 100% calc(100% - 4rem), 0% 100%)")
                }

                flexColumn {
                    css {
                        flexGrow = 70.0
                        flexBasis = FlexBasis.zero
                        margin(auto, 2.rem)

                        padding(8.rem, 4.rem, 4.rem, 4.rem)

                        maxWidth(980) {
                            padding(8.rem, 0.rem, 4.rem, 0.rem)
                        }
                    }

                    styledP {
                        css {
                            +kodein.chapo
                            specific {
                                fontWeight = FontWeight.light
                                textAlign = TextAlign.center
                            }
                            color = Color.kodein.korail
                            alignSelf = Align.center
                            maxWidth = 60.rem
                        }

                        +"// TODO"
                    }

                    styledP {
                        css {
                            +kodein.intertitre
                            specific {
                                fontWeight = FontWeight.light
                                textAlign = TextAlign.center
                            }
                            color = Color.kodein.kaumon
                            alignSelf = Align.center
                            maxWidth = 60.rem
                        }

                        +"// TODO"
                    }

                    styledP {
                        css {
                            +kodein.body
                            specific {
                                fontWeight = FontWeight.light
                                textAlign = TextAlign.center
                            }
                            color = Color.kodein.korail
                            alignSelf = Align.center
                            maxWidth = 60.rem
                        }

                        +"// TODO"
                    }
                }

                child(Illustration) { attrs.leftAligned = true }
            }
        }

        styledDiv {
            css {
                zIndex = 19
                display = Display.none
                marginTop = (-4).rem
                +kodein.dropShadow

                maxWidth(980) {
                    display = Display.flex
                }
            }

            child(Illustration)
            layerSeparator(Position.absolute, Color.kodein.orange, Color.kodein.purple)
        }

        styledDiv {
            css {
                zIndex = 18
                marginTop = (-4).rem
            }
            layerSeparator(Position.absolute, Color.kodein.purple)
        }
    }
}

interface TrainingIllustrationProps : RProps {
    var leftAligned: Boolean
}

private val Illustration = functionalComponent<TrainingIllustrationProps>("Illustration") { props ->
    val div = useRef<HTMLDivElement?>(null)

    var image: String? by useState(null)

    useEffectWithCleanup(emptyList()) {
        val onResize: ((Event?) -> Unit) = {
            val divWidth = div.current!!.clientWidth
            val imgWidth = illustrationWidths.firstOrNull { it >= (divWidth * 1.2) } ?: illustrationWidths.last()
            image = "training_${imgWidth}"
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
                if (props.leftAligned)
                    display = Display.none
                else {
                    flexGrow = 1.0
                    put("clip-path", "polygon(0% 0%, 100% 0%, 100% calc(100% - 4rem), 0% 100%)")
                }
            }

            "body.webp &" {
                backgroundColor = Color.kodein.kaumon
            }

            "img" {
                objectPosition = "center"
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
