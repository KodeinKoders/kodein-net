package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.lh
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RBuilder
import react.RProps
import react.functionalComponent
import styled.*

data class CoverPalette(
    val backgroundColor: Color,
    val text: Color = Color.kodein.korail,
    val title: Color = Color.kodein.kaumon,
    val overTitle: Color = text,
    val layers: List<Color> = listOf(Color.kodein.kyzantium, Color.kodein.orange)
)

interface CoverProps : RProps {
    var colors  : CoverPalette
    var overTitle: String
    var title: RBuilder.() -> Unit
    var overrideContentRuleSet: RuleSet?
}

val Cover = functionalComponent<CoverProps>("Cover") { props ->
    flexColumn {
        css {
            backgroundColor = props.colors.backgroundColor
            paddingBottom = 2.5.em

            "a" {
                display = Display.inlineBlock

                borderBottom(0.15.rem, BorderStyle.solid, Color.currentColor)
                lineHeight = 0.85.em.lh
                put("text-shadow", "0.03em 0.03em ${Color.kodein.dark}, 0.03em -0.03em ${Color.kodein.dark}, -0.03em 0.03em ${Color.kodein.dark}, -0.03em -0.03em ${Color.kodein.dark}")

                hover {
                    color = Color.kodein.purple
                }
            }
        }

        styledP {
            css {
                +kodein.subHead
                alignSelf = Align.center
                textAlign = TextAlign.center
                color = props.colors.overTitle
                paddingTop = 6.rem
                marginBottom = 2.rem
                maxSize(768) {
                    paddingTop = 2.rem
                    marginBottom = 1.rem
                }
                borderBottom(0.05.rem, BorderStyle.solid, props.colors.overTitle)
            }
            +props.overTitle
        }

        styledP {
            css {
                +kodein.display3
                specific {
                    fontWeight = FontWeight.hairline
                    textAlign = TextAlign.center
                }
                color = props.colors.title
                margin(1.rem, 1.rem)

                maxSize(980, 570) { +kodein.display2  }
            }
            props.title(this)
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 5.rem
                opacity = .7
                backgroundColor = props.colors.text
                margin(1.rem, LinearDimension.auto)
                maxSize(768) {
                    height = 3.rem
                    margin(0.rem, LinearDimension.auto)
                }
                landscapeMobile {
                    margin(1.rem, LinearDimension.auto)
                }
            }
        }

        styledDiv {
            css {
                +kodein.display1
                color = props.colors.text
                width = 75.pct
                padding(2.rem)
                alignSelf = Align.center

                maxSize(768) {
                    width = 85.pct
                    padding(1.rem, 0.rem)
                }

                props.overrideContentRuleSet?.invoke(this)
            }

            props.children()
        }
    }
    layerSeparator(Position.absolute, props.colors.backgroundColor, *props.colors.layers.toTypedArray())
}
