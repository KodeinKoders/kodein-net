package net.kodein.components

import kotlinx.css.*
import net.kodein.utils.Palette
import net.kodein.utils.maxWidthM
import net.kodein.utils.maxWidthXM
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.*

class PerfDescription : RComponent<PerfDescription.Props, RState>() {

    interface Props : RProps {
        var title: String
        var description: String
        var isLastItem: Boolean
    }

    override fun RBuilder.render() {
        val isLastItem = props.isLastItem ?: false

        styledDiv {
            css {
                width = 28.pct
                height = 12.em
                maxWidthXM {
                    width = 100.pct
                }
            }

            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row }
                styledDiv {
                    css{
                        backgroundColor = Palette.orange.color
                        marginLeft = 1.em
                        marginTop = 1.em
                        height = 1.em
                        width = 1.em
                        put("border-radius", "50px")
                        display = Display.block
                    }
                }
                styledSpan {
                    css{
                        color = Palette.orange.color
                        fontStyle = FontStyle.italic
                        marginLeft = 0.3.em
                        marginTop = 1.em
                        height = 1.em
                        display = Display.block
                    }
                    +"icon here!" }
            }
            styledDiv {
                css{
                    color = Palette.purple.color
                    fontWeight = FontWeight.w700
                    padding(top = 0.3.em, left = 1.em)
                    height = 2.em
                }
                +props.title.toUpperCase()
            }
            styledDiv {
                css {
                    color = Palette.orange.color
                    textAlign = TextAlign.justify
                    height = 5.em
                    padding(top = 1.em, left = 1.em)
                    maxWidthXM {
                        width = 75.pct
                    }
                }
                +props.description
            }

            styledButton {
                css {
                    margin(1.em)
                    height = 2.em
                    padding(vertical = 0.3.em, horizontal = 1.em)
                    color = Color.white
                    fontSize = 0.7.em
                    fontWeight = FontWeight.w500
                    backgroundColor = Palette.orange.color
                    put("border-radius", "50px")
                }
                +"READ MORE"
            }
        }

        if(!isLastItem) {
            styledDiv {
                val backgroundGradient: (String) -> String = { deg: String ->
                    """linear-gradient(${deg}deg, 
                                rgba(247,225,222,0) 40%, 
                                rgba(36,8,33,0.10) 50%, 
                                rgba(247,225,222,0) 50%)""".trimIndent()
                }

                css {
                    background = backgroundGradient("277")
                    height = 14.em
                    width = 5.333.pct
                    display = Display.block
                    padding(0.5.em)
                    maxWidthXM {
                        background = backgroundGradient("-3")
                        height = 2.5.em
                        padding(0.em)
                        width = 100.pct
                    }
                }
            }
        }


    }
}