package net.kodein.pages.home.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.classes
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import react.dom.p
import styled.*


val Descriptions = functionalComponent<RProps>("Descriptions") {

    flexColumn {
        css {
            width = 100.pct
//            background = "linear-gradient(180deg, white 0%, ${Color.kodein.dark.withAlpha(0.05)} 100%)"
            backgroundColor = Color.kodein.cute.withAlpha(0.35)
        }

        child(Description) {
            attrs.even = true
            attrs.title = "Who are the Kodein Koders ?"
            attrs.illus = "services"
            attrs.first = true

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est." }
            p { +"Aliquam nunc urna, imperdiet non viverra eu, interdum ut dui. In hac habitasse platea dictumst." }
        }

        child(Description) {
            attrs.even = false
            attrs.title = "Work for humans"
            attrs.illus = "team"

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est." }
            p { +"Aliquam nunc urna, imperdiet non viverra eu, interdum ut dui. In hac habitasse platea dictumst." }
        }

        child(Description) {
            attrs.even = true
            attrs.title = "Kodein Framework: Open Source at our core"
            attrs.illus = "open-source"

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est." }
            p { +"Aliquam nunc urna, imperdiet non viverra eu, interdum ut dui. In hac habitasse platea dictumst." }
        }

        child(Description) {
            attrs.even = false
            attrs.title = "Training: Jetbrains Certified"
            attrs.illus = "training"
            attrs.last = true

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est." }
            p { +"Aliquam nunc urna, imperdiet non viverra eu, interdum ut dui. In hac habitasse platea dictumst." }
        }
    }

}

private interface DescriptionProps : RProps {
    var even: Boolean
    var title: String
    var illus: String
    var first: Boolean?
    var last: Boolean?
}

private val Description = functionalComponent<DescriptionProps>("Description") { props ->
    flexRow {
        val slant = 4
        css {
            minWidth(769) {
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
                    transition("clip-path", 0.3.s, Timing.easeOut)
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

            maxWidth(768) {
                flexDirection = FlexDirection.column

                ".illustration" {
                    width = 100.pct
                    height = 24.rem

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
        }

        styledDiv {
            css {
                flexGrow = 45.0
                flexBasis = FlexBasis.zero
            }

            flexColumn {
                css {
                    padding(4.rem)
                    maxWidth(768) {
                        padding(2.rem)
                    }
                    minWidth(769) {
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
                    }
                    +props.title
                }

                styledDiv {
                    css {
                        +kodein.body
                        flexGrow = 1.0

                        "p" {
                            paddingBottom = 0.6.rem
                        }
                    }

                    props.children()
                }

                styledA {
                    css {
                        +kodein.button
                        alignSelf = Align.flexStart
                        margin(1.rem, 0.rem)
                    }
                    +"READ MORE"
                }
            }
        }
    }
}

private val illustrationWidths = listOf(960, 1200, 1440, 1680, 1920, 2400, 2880, 3360, 3840)

interface IllustrationProps : RProps {
    var color: Color
    var image: String
    var slantCorrection: LinearDimension
}

private val Illustration = functionalComponent<IllustrationProps>("Illustration") { props ->
    val div = useRef<HTMLDivElement?>(null)

    var image: String? by useState(null)

    useEffectWithCleanup(emptyList()) {
        val onResize: ((Event?) -> Unit) = {
            val divWidth = div.current!!.clientWidth
            val imgWidth = illustrationWidths.firstOrNull { it >= (divWidth * 1.2) } ?: illustrationWidths.last()
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
            minHeight = 38.rem - props.slantCorrection
            minWidth(1950) {
                minHeight = 44.rem - props.slantCorrection
            }
            backgroundColor = props.color
            backgroundSize = "cover"
            backgroundPosition = "center"

            "body.webp &" {
                backgroundColor = Color.kodein.kaumon
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
