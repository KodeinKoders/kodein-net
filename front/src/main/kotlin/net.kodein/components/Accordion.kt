package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.charter.kodein
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.EventListener
import react.*
import react.dom.br
import react.dom.span
import styled.css
import styled.styledDiv
import styled.styledP


interface AccordionElementProps : RProps{
    var title: String
    var last: Boolean
    var sub: (RBuilder.() -> Unit)?
    var borderColor: Color?
    var fontColor: Color?
}
val AccordionElement = functionalComponent<AccordionElementProps>("AccordionElement") { props ->
    val accordionHeader = useRef<HTMLDivElement?>(null)
    var isOpen by useState(false)

    val borderColor = props.borderColor ?: Color.kodein.klycine
    val fontColor = props.fontColor ?: Color.kodein.kaumon

    useEffectWithCleanup {
        val openTraining = EventListener {
            isOpen = !isOpen
        }
        accordionHeader.current!!.addEventListener("mouseup", openTraining)
        ({ accordionHeader.current!!.removeEventListener("mouseup", openTraining) })
    }

    flexColumn {
        css {
            if (props.last) borderBottom(1.px, BorderStyle.solid, borderColor.withAlpha(0.5))
        }

        // Header
        flexRow {
            ref = accordionHeader
            css {
                width = 100.pct
                height = 5.rem
                alignItems = Align.center
                cursor = Cursor.pointer
                borderTop(1.px, BorderStyle.solid, borderColor.withAlpha(0.5))
            }

            styledP {
                css {
                    flexGrow = 75.0
                    flexBasis = FlexBasis.zero
                    +kodein.intertitre
                    color = Color.kodein.kaumon
                }

                +props.title
            }

            styledDiv {
                css {
                    flexGrow = 20.0
                    flexBasis = FlexBasis.zero
                    color = fontColor
                    textAlign = TextAlign.end
                    minWidth = LinearDimension.fitContent
                    paddingRight = 1.rem

                    transition(::opacity, duration = .5.s, Timing.linear)
                    opacity = if (isOpen) 1 else 0
                }

                props.sub?.invoke(this)
            }

            child(Chevron) { attrs.isUp = isOpen }
        }

        // Content
        styledDiv {
            css {
                overflow = Overflow.hidden

                transition(::opacity, duration = .5.s, Timing.linear)

                if (isOpen) {
                    maxHeight = 50.rem
                    opacity = 1
                } else {
                    maxHeight = 0.rem
                    opacity = 0
                }
            }

            styledP {
                css {
                    +kodein.body
                    color = fontColor
                    padding(2.rem)
                }

                props.children()
                +"Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
                br {  }
                +"- Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
                br {  }
                +"- when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
            }
        }
    }
}

private interface ChevronProps : RProps {
    var isUp: Boolean
}
private val Chevron = functionalComponent<ChevronProps>("Chevron") { props ->
    styledDiv {
        css {
            display = Display.flex
            flexGrow = 5.0
            flexBasis = FlexBasis.zero
            minWidth = 2.rem
            paddingRight = .5.rem

            "span.chevron" {
                display= Display.inlineBlock
                position = Position.absolute
                textAlign = TextAlign.center
                height = 0.2.rem
                width = 2.rem
            }
            "span.chevron:before" {
                content = QuotedString("")
                position = Position.absolute
                top = 0.px
                left = 0.px
                height = 100.pct
                width = 51.pct
                backgroundColor = Color.kodein.klycine.withAlpha(0.75)
                transform { skew(0.deg, (30).deg); }
                transition("transform", duration = 0.5.s)
            }
            "span.chevron:after" {
                content = QuotedString("")
                position = Position.absolute
                top = 0.px
                right = 0.px
                height = 100.pct
                width = 50.pct
                backgroundColor = Color.kodein.klycine.withAlpha(0.75)
                transform { skew(0.deg, (-30).deg); }
                transition("transform", 0.5.s)
            }
            "span.chevron.up:before" {
                transform { skew(0.deg, (-30).deg); }
            }
            "span.chevron.up:after" {
                transform { skew(0.deg, (30).deg); }
            }
        }

        span(if (props.isUp) "chevron up" else "chevron") {}
    }
}
