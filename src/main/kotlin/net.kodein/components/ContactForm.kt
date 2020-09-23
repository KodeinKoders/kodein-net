package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.lh
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import net.kodein.charter.KodeinColors
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.dom.input
import react.dom.p
import react.dom.span
import react.dom.textArea
import react.functionalComponent
import styled.*


val ContactForm by functionalComponent {

    styledDiv {
        css {
            margin(5.rem, LinearDimension.auto)
            maxWidth = 50.rem
        }

        styledH1 {
            css {
                +kodein.display2
                textAlign = TextAlign.start
                color = Color.kodein.orange
                fontWeight = FontWeight.hairline
            }

            +"Contact us"
        }

        styledForm {
            css {
                "p" {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    fontSize = 1.2.rem
                    fontWeight = FontWeight.regular
                    borderBottom(0.1.rem, BorderStyle.solid, Color.kodein.cute)
                    padding(3.rem, 0.1.rem, 0.4.rem, 0.1.rem)

                    "span" {
                        fontFamily = KodeinStyles.piconExtended
                        color = KodeinColors.kyzantium
                        width = 7.rem
                    }

                    "input, textarea" {
                        flexGrow = 1.0
                        border = "none"
                        fontFamily = kodein.picon
                        fontSize = 1.2.rem

                        placeholder {
                            color = Color.kodein.kaumon
                            fontWeight = FontWeight.light
                        }

                        focus {
                            outline = Outline.none
                        }
                    }

                    "textarea" {
                        resize = Resize.none
                        height = 5.25.em // not rem!
                        lineHeight = 1.75.em.lh
                        marginTop = (-0.25).rem
                    }
                }
            }

            p {
                span {
                    +"From:"
                }
                input(InputType.email, name = "email") {
                    attrs.placeholder = "Your e-mail*"
                }
            }

            p {
                span {
                    +"Object:"
                }
                input(InputType.text, name = "object") {
                    attrs.placeholder = "What's on your mind?*"
                }
            }

            p {
                span {
                    +"Message:"
                }
                textArea {
                    attrs.placeholder = "Tell us a little more about your needs*"
                }
            }

            styledButton(type = ButtonType.button) {
                css {
                    border = "none"
                    +kodein.button
                    marginTop = 3.rem
                }
                styledImg(src = "imgs/send-cute.svg") {
                    css {
                        height = 1.em // not rem!
                        marginRight = 0.4.em // not rem!
                    }
                }
                +"SEND"
            }
        }
    }

}
