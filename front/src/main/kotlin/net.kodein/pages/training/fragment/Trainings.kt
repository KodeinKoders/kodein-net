package net.kodein.pages.training.fragment

import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.charter.kodein
import net.kodein.components.contentRow
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.light
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.EventListener
import org.w3c.dom.get
import react.*
import react.dom.br
import react.dom.span
import styled.css
import styled.styledDiv
import styled.styledP

val Trainings = functionalComponent<RProps>("Trainings") {

    val traningListContainer = useRef<HTMLDivElement?>(null)
    var selectedTraining by useState(-1)

    useEffectWithCleanup {
        val trainingListElement = traningListContainer.current!!

        val events = mutableListOf<() -> Unit>()

        for (trainingId in (0..trainingListElement.childElementCount)) {
            val trainingItem = trainingListElement.children[trainingId]
            if (trainingItem != null) {
                val openTraining = EventListener {
                    selectedTraining = if (selectedTraining == trainingId) -1 else trainingId
                }
                trainingItem.addEventListener("mouseup", openTraining)
                events.add { trainingItem.removeEventListener("mouseup", openTraining) }
            }
        }

        ({ events.forEach { it() } })
    }

    contentRow(
        backgroundColor = Color.kodein.dark, indexPosition = 3, // see [Description] that stops at index 2
        bottomLayers = listOf(Color.kodein.orange, Color.kodein.kaumon),
        additionalStyle = { marginBottom = 8.rem }
    ) {
        flexColumn {
            css {
                width = 100.pct
                maxWidth = 60.rem
                margin(LinearDimension.auto)
            }

            styledP {
                css {
                    +kodein.chapo
                    specific {
                        textAlign = TextAlign.center
                    }
                    color = Color.kodein.kaumon
                    alignSelf = Align.center
                    marginBottom = 8.rem
                }

                +"We are here to help you to keep your Kotlin knowledge up to date"
            }

            flexColumn {
                ref = traningListContainer

                child(Training) {
                    attrs {
                        title = "Kotlin for Android"
                        duration = 1
                        isOpen = selectedTraining == 0
                    }
                }

                child(Training) {
                    attrs {
                        title = "Working with coroutines"
                        duration = 2
                        isOpen = selectedTraining == 1
                    }
                }

                child(Training) {
                    attrs {
                        title = "Kotlin on the backend"
                        duration = 3
                        isOpen = selectedTraining == 2
                        last = true
                    }
                }
            }
        }
    }
}


interface TrainingProps : RProps{
    var title: String
    var duration: Int
    var last: Boolean
    var isOpen: Boolean
}

val Training = functionalComponent<TrainingProps>("MenuContent") { props ->
    flexColumn {
        css {
            if (props.last) borderBottom(1.px, BorderStyle.solid, Color.kodein.klycine.withAlpha(0.5))
        }

        flexRow {
            css {
                cursor = Cursor.pointer
                width = 100.pct
                height = 5.rem
                borderTop(1.px, BorderStyle.solid, Color.kodein.klycine.withAlpha(0.5))
            }

            styledP {
                css {
                    flexGrow = 80.0
                    flexBasis = FlexBasis.zero
                    +kodein.intertitre
                    color = Color.kodein.kaumon
                    alignSelf = Align.center
                }

                +props.title
            }

            styledP {
                css {
                    flexGrow = 10.0
                    flexBasis = FlexBasis.zero
                    color = Color.kodein.kaumon
                    alignSelf = Align.center
                    justifyContent = JustifyContent.end

                    transition(::opacity, duration = .2.s, Timing.linear)
                    opacity = if (props.isOpen) 1 else 0
                }

                +"${props.duration} days"
            }

            styledDiv {
                css {
                    flexGrow = 10.0
                    flexBasis = FlexBasis.zero
                    alignSelf = Align.center
                    color = Color.kodein.kaumon
                }

                flexRow(justifyContent = JustifyContent.center) {
                    css {
                        "span.chevron" {
                            position = Position.relative
                            textAlign = TextAlign.center
                            height = 3.px
                            width = 30.px
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
                        }

                        "span.chevron.up:before" {
                            transform { skew(0.deg, (-30).deg); }
                        }

                        "span.chevron.up:after" {
                            transform { skew(0.deg, (30).deg); }
                        }
                    }

                    span(if (props.isOpen) "chevron up" else "chevron") {}
                }
            }
        }

        styledDiv {
            css {
                overflow = Overflow.hidden

                transition(::opacity, duration = .3.s, Timing.linear)

                if (props.isOpen) {
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
                    color = Color.kodein.kaumon
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

data class Trai(val title: String, val description: String)
val trainingList = listOf(
    Trai("Kotlin for Android", ""),
    Trai("Working with coroutines", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    Trai("Succeeding your next KMM project", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
)
