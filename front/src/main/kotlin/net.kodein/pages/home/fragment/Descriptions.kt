package net.kodein.pages.home.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.pages.home.HomeStrings
import net.kodein.useText
import net.kodein.utils.*
import net.kodein.withBasePath
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import styled.*


val Descriptions = functionalComponent<RProps>("Descriptions") {
    val strings = useText().home

    flexColumn {
        css {
            width = 100.pct
//            background = "linear-gradient(180deg, white 0%, ${Color.kodein.dark.withAlpha(0.05)} 100%)"
            backgroundColor = Color.kodein.cute.withAlpha(0.35)
        }

        child(Description) {
            attrs.even = true
            attrs.content = strings.kodeinKodersDescription
            attrs.illus = "services"
            attrs.readMoreLink = "services.html"
            attrs.first = true
        }

        child(Description) {
            attrs.even = false
            attrs.content = strings.humanistDescription
            attrs.illus = "team"
            attrs.readMoreLink = "team.html"
        }

        child(Description) {
            attrs.even = true
            attrs.content = strings.openSourceDescription
            attrs.illus = "open-source"
            attrs.readMoreLink = "oss.html"
        }

        child(Description) {
            attrs.even = false
            attrs.content = strings.trainingDescription
            attrs.illus = "training"
            attrs.readMoreLink = "training.html"
            attrs.last = true
        }
    }

}

private interface DescriptionProps : RProps {
    var even: Boolean
    var content: HomeStrings.TitledContent
    var illus: String
    var readMoreLink: String
    var first: Boolean?
    var last: Boolean?
}

private val Description = functionalComponent<DescriptionProps>("Description") { props ->
    flexRow {
        val slant = 4
        css {
            minWidth(1024) {
                flexDirection = if (props.even) FlexDirection.row else FlexDirection.rowReverse

                put("clip-path",
                    when {
                        props.first == true -> "polygon(0% 0%,   100% 0%, 100% calc(100% - ${slant}rem),        0% 100%)"
                        props.last == true  -> "polygon(0% ${slant}rem, 100% 0%, 100% 100%,                     0% 100%)"
                        else                -> "polygon(0% ${slant}rem, 100% 0%, 100% calc(100% - ${slant}rem), 0% 100%)"
                    }
                )

                ".illustration" {
                    val l = if (props.even) 0 else 9
                    val r = if (!props.even) 100 else 91
                    put("clip-path", "polygon($l% 0%, $l% 50%, $l% 100%, $r% 100%, $r% 50%, $r% 0%)")
                    transition("clip-path", 0.8.s, Timing.easeOut)
                }

                hover {
                    ".illustration" {
                        val l = if (!props.even) 18 else 0
                        val r = if (props.even) 82 else 100
                        put("clip-path", "polygon(0% 0%, $l% 50%, 0% 100%, 100% 100%, $r% 50%, 100% 0%)")
                    }
                }

                if (props.first != true) marginTop = -(slant.rem)
            }

            maxWidth(1024 - 1) {
                flexDirection = FlexDirection.column

                ".illustration" {
                    width = 100.pct
                    height = 1.rem

                    put("clip-path",
                        when {
                            props.first == true ->  "polygon(0% 0%, 100% 0%, 100% 90%, 0% 100%)"
                            props.even ->           "polygon(0% 0%, 100% 10%, 100% 90%, 0% 100%)"
                            else ->                 "polygon(0% 10%, 100% 0%, 100% 100%, 0% 90%)"
                        })

                    transition("clip-path", 0.3.s, Timing.easeOut)
                }
            }
        }

        child(Illustration) {
            attrs.color = if (props.even) Color.kodein.kyzantium else Color.kodein.kuivre
            attrs.slantCorrection = if (props.first == true || props.last  == true) (slant / 2).rem else 0.rem
            attrs.image = props.illus
            attrs.imageAlt = props.content.title
        }

        styledDiv {
            css {
                flexGrow = 45.0
                flexBasis = FlexBasis.zero
                alignSelf = Align.center
            }

            flexColumn {
                css {
                    padding(4.rem)
                    maxWidth(1024 - 1) {
                        padding(2.rem)
                    }
                    minWidth(1024) {
                        if (props.first != true) {
                            marginTop = if (props.even) (slant / 2).rem + 1.px
                            else slant.rem + 1.px
                        }
                    }
                }

                styledH2 {
                    css {
                        +kodein.intertitre
                        flexGrow = 1.0
                        margin(1.rem, 0.rem)
                    }
                    +props.content.title
                }

                styledDiv {
                    css {
                        +kodein.body
                        flexGrow = 1.0

                        "p" {
                            paddingBottom = 0.6.rem
                        }
                        "span.nowrap" {
                            whiteSpace = WhiteSpace.nowrap
                        }
                    }

                    props.content.content(this)
                }

                styledA(href = props.readMoreLink) {
                    css {
                        +kodein.button
                        alignSelf = Align.flexStart
                        margin(1.rem, 0.rem)
                    }
                    +props.content.readMore
                }
            }
        }
    }
}

interface IllustrationProps : RProps {
    var color: Color
    var image: String
    var imageAlt: String
    var slantCorrection: LinearDimension
}

private val Illustration = functionalComponent<IllustrationProps>("Illustration") { props ->
    val div = useRef<HTMLDivElement?>(null)

    var image: String? by useState(null)

    useEffectWithCleanup(emptyList()) {
        val onResize: ((Event?) -> Unit) = {
            val divWidth = div.current!!.clientWidth
            val imgWidth = illustrationWidths.firstOrNull { it >= divWidth } ?: illustrationWidths.last()
            image = "${props.image}_${imgWidth}"
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    styledDiv {
        ref = div
        attrs.classes += "illustration"
        css {
            flexGrow = 55.0
            flexBasis = FlexBasis.zero
            val minSize = 38.rem - props.slantCorrection
            minHeight = minSize
            maxWidth(1024) {
                minHeight = 24.rem
            }
            maxHeight = 60.vh
            backgroundColor = props.color
            backgroundSize = "cover"
            backgroundPosition = "center"

            "body.webp &" {
                backgroundColor = Color.kodein.kaumon
            }
        }

        if (image != null) {
            withBasePath { path ->
                picture {
                    source("image/webp", "$path/imgs/illus/$image.webp" to null)
                    source("image/jpeg", "$path/imgs/illus/$image.jpg" to null)

                    styledImg(src = "$path/imgs/illus/$image.jpg", alt = props.imageAlt) {
                        attrs {
                            width = "100%"
                            height = "100%"
                        }

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
}
