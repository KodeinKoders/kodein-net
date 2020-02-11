package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.utils.Palette
import net.kodein.utils.maxWidthM
import net.kodein.utils.maxWidthXM
import org.w3c.dom.events.Event
import react.*
import react.dom.a
import styled.css
import styled.styledDiv
import kotlin.browser.document
import kotlin.browser.window

class Header : RComponent<RProps, Header.State>() {
    interface State : RState {
        var isPageUp: Boolean
        var hasTransition: Boolean
    }

    override fun State.init() {
        isPageUp = true
        hasTransition = false
    }

    override fun RBuilder.render() {
        val isPageUp = state.isPageUp
        val hasTransition = state.hasTransition

        styledDiv {
            css {
                backgroundColor = if (isPageUp) Palette.dark.color else Color.white
                position = Position.sticky
                left = 0.px
                top = 0.px
                right = 0.px
                display = Display.flex
                flexDirection = FlexDirection.row
                alignItems = Align.center
                if (hasTransition) transition(duration = 0.5.s)
            }
            // Title Bar
            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    alignItems = Align.center
                    alignContent = Align.center
                    padding(1.em)
                    maxWidth = 1150.px
                    margin(LinearDimension.auto)
                }

                // Logo
                child(Logo::class) {
                    attrs {
                        bold = "KODEIN"
                        light = "Koders"
                        palette = Palette.orange
                    }
                }

                // Menu
                styledDiv {
                    css {
                        color = Palette.orange.color
                        fontWeight = FontWeight.w700
                        display = Display.flex
                        justifyContent = JustifyContent.flexEnd
                        alignItems = Align.center
                        fontSize = 0.8.em

                        kotlinx.css.a {
                            display = Display.block
                            fontWeight = FontWeight.w700
                            padding(left = 2.em)
                            textDecoration = TextDecoration.none
                            color = Palette.orange.color
                            cursor = Cursor.pointer
                            transition("fontWeight", duration = 0.15.s)

                            hover {
                                fontWeight = FontWeight.w400
                            }
                        }

                        maxWidthXM {
                            display = Display.none
                        }
                    }

                    a(href = "") { +"SERVICE" }
                    a(href = "") { +"TRAINING" }
                    a(href = "") { +"OSS" }
                    a(href = "") { +"TEAM" }
                    a(href = "") { +"BLOG" }
                    a(href = "") { +"CONTACT" }
                }
            }

        }
    }

    private val scrollCallback: (Event) -> Unit = { setHeaderBackgroundColor(false) }

    private fun setHeaderBackgroundColor(firstCall: Boolean) {
        if (!firstCall) {
            val isPageUp = window.scrollY.toInt() < 50

            if (state.isPageUp != isPageUp || state.hasTransition != !firstCall)
                setState {
                    this.isPageUp = isPageUp
                    this.hasTransition = !firstCall
                }
        }
    }

    override fun componentDidMount() {
        setHeaderBackgroundColor(true)
        document.addEventListener("scroll", scrollCallback)
    }

    override fun componentWillUnmount() {
        document.removeEventListener("scroll", scrollCallback)
    }

}