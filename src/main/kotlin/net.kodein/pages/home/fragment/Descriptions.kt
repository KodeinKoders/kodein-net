package net.kodein.pages.home.fragment

import kotlinx.css.*
import kotlinx.css.properties.Timing
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.getValue
import react.RProps
import react.child
import react.dom.img
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
            flexDirection = if (props.even) FlexDirection.row else FlexDirection.rowReverse
            put("clip-path",
                    when {
                        props.first == true -> "polygon(0% 0%,   100% 0%, 100% calc(100% - ${slant}rem),        0% 100%)"
                        props.last == true  -> "polygon(0% ${slant}rem, 100% 0%, 100% 100%,                     0% 100%)"
                        else                -> "polygon(0% ${slant}rem, 100% 0%, 100% calc(100% - ${slant}rem), 0% 100%)"
                    }
            )
            if (props.first != true) marginTop = -(slant.rem + 1.px)

            "img.illus" {
                width = 55.pct
//                put("clip-path", "polygon(0% 0%, 20% 50%, 0% 100%, 100% 100%, 100% 0%)")
                val l = if (props.even) 0 else 9
                val r = if (!props.even) 100 else 91
                put("clip-path", "polygon($l% 0%, $l% 50%, $l% 100%, $r% 100%, $r% 50%, $r% 0%)")
                transition("clip-path", 0.3.s, Timing.easeOut)
            }

            hover {
                "img.illus" {
                    val l = if (!props.even) 18 else 0
                    val r = if (props.even) 82 else 100
                    put("clip-path", "polygon(0% 0%, $l% 50%, 0% 100%, 100% 100%, $r% 50%, 100% 0%)")
                }
            }
        }

        img(src = "imgs/illus_${props.illus}.png", classes = "illus") {
        }

        flexColumn {
            css {
                padding(4.rem)
                if (props.first != true) {
                    if (props.even) marginTop = (slant / 2).rem + 1.px
                    else marginTop = slant.rem + 1.px
                }
            }

            styledH2 {
                css {
                    +KodeinStyles.intertitre
                    flexGrow = 1.0
                }
                +props.title
            }

            styledDiv {
                css {
                    +KodeinStyles.body
                    flexGrow = 1.0

                    "p" {
                        paddingBottom = 0.6.rem
                    }
                }

                props.children()
            }

            styledA {
                css {
                    +KodeinStyles.readMore
                    alignSelf = Align.flexStart
                    margin(1.rem, 0.rem)
                }
                +"READ MORE"
            }
        }
    }
}
