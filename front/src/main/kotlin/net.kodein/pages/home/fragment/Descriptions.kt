package net.kodein.pages.home.fragment

import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RProps
import react.child
import react.dom.p
import react.functionalComponent
import styled.*


val Descriptions by functionalComponent {

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

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est. Donec ut aliquet turpis. Integer eros augue, faucibus at cursus a, imperdiet vitae massa. Duis consequat lectus dolor, quis sollicitudin massa aliquam eget. Proin tincidunt cursus lacus eu elementum. Fusce ac auctor ante, quis congue ante." }
            p { +"Aliquam nunc urna, imperdiet non viverra eu, interdum ut dui. In hac habitasse platea dictumst." }
        }

        child(Description) {
            attrs.even = false
            attrs.title = "Work for humans"
            attrs.illus = "team"

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est. Donec ut aliquet turpis. Integer eros augue, faucibus at cursus a, imperdiet vitae massa. Duis consequat lectus dolor, quis sollicitudin massa aliquam eget. Proin tincidunt cursus lacus eu elementum. Fusce ac auctor ante, quis congue ante." }
            p { +"Aliquam nunc urna, imperdiet non viverra eu, interdum ut dui. In hac habitasse platea dictumst." }
        }

        child(Description) {
            attrs.even = true
            attrs.title = "Kodein Framework: Open Source at our core"
            attrs.illus = "open-source"

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est. Donec ut aliquet turpis. Integer eros augue, faucibus at cursus a, imperdiet vitae massa. Duis consequat lectus dolor, quis sollicitudin massa aliquam eget. Proin tincidunt cursus lacus eu elementum. Fusce ac auctor ante, quis congue ante." }
            p { +"Aliquam nunc urna, imperdiet non viverra eu, interdum ut dui. In hac habitasse platea dictumst." }
        }

        child(Description) {
            attrs.even = false
            attrs.title = "Training: Jetbrains Certified"
            attrs.illus = "training"
            attrs.last = true

            p { +"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent maximus euismod ullamcorper. Fusce nibh nisi, imperdiet id libero id, iaculis consectetur libero. Morbi turpis dui, eleifend ut bibendum et, lobortis sit amet velit. Vivamus malesuada viverra tellus eget finibus. Praesent eget turpis ut neque egestas varius. Cras ac facilisis odio. Pellentesque posuere, ante at elementum vestibulum, ante odio semper dui, ac accumsan felis nisl ac est. Donec ut aliquet turpis. Integer eros augue, faucibus at cursus a, imperdiet vitae massa. Duis consequat lectus dolor, quis sollicitudin massa aliquam eget. Proin tincidunt cursus lacus eu elementum. Fusce ac auctor ante, quis congue ante." }
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

private val Description by functionalComponent<DescriptionProps> { props ->
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

                "img" {
                    val l = if (props.even) 0 else 9
                    val r = if (!props.even) 100 else 91
                    put("clip-path", "polygon($l% 0%, $l% 50%, $l% 100%, $r% 100%, $r% 50%, $r% 0%)")
                    transition("clip-path", 0.3.s, Timing.easeOut)
                }

                hover {
                    "img" {
                        val l = if (!props.even) 18 else 0
                        val r = if (props.even) 82 else 100
                        put("clip-path", "polygon(0% 0%, $l% 50%, 0% 100%, 100% 100%, $r% 50%, 100% 0%)")
                    }
                }

                if (props.first != true) marginTop = -(slant.rem + 1.px)
            }

            maxWidth(768) {
                flexDirection = FlexDirection.column

                "img" {
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

        styledDiv {
            css {
                flexGrow = 55.0
                flexBasis = FlexBasis.zero
            }

            picture {
                source("image/webp",
                        "imgs/illus/${props.illus}_960.webp" to "960w",
                        "imgs/illus/${props.illus}_1200.webp" to "1200w",
                        "imgs/illus/${props.illus}_1440.webp" to "1440w",
                        "imgs/illus/${props.illus}_1680.webp" to "1680w",
                        "imgs/illus/${props.illus}_1920.webp" to "1920w",
                        "imgs/illus/${props.illus}_2400.webp" to "2400w",
                        "imgs/illus/${props.illus}_2880.webp" to "2880w",
                        "imgs/illus/${props.illus}_3360.webp" to "3360w",
                        "imgs/illus/${props.illus}_3840.webp" to "3840w"
                )
                source("image/jpeg",
                        "imgs/illus/${props.illus}_960.jpg" to "960w",
                        "imgs/illus/${props.illus}_1200.jpg" to "1200w",
                        "imgs/illus/${props.illus}_1440.jpg" to "1440w",
                        "imgs/illus/${props.illus}_1680.jpg" to "1680w",
                        "imgs/illus/${props.illus}_1920.jpg" to "1920w",
                        "imgs/illus/${props.illus}_2400.jpg" to "2400w",
                        "imgs/illus/${props.illus}_2880.jpg" to "2880w",
                        "imgs/illus/${props.illus}_3360.jpg" to "3360w",
                        "imgs/illus/${props.illus}_3840.jpg" to "3840w"
                )

                styledImg(src = "imgs/illus/${props.illus}_1920.jpg") {
                    css {
                        width = 100.pct
                        maxHeight = if (props.first == true || props.last  == true) (48 - slant / 2).rem
                        else 48.rem
                        objectFit = ObjectFit.cover
                    }
                }
            }
        }

        styledDiv {
            css {
                flexGrow = 45.0
                flexBasis = FlexBasis.zero
            }

            flexColumn {
                css {
                    padding(4.rem)

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
