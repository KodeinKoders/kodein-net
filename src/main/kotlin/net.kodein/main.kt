package net.kodein

import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.kodein
import react.dom.render
import styled.injectGlobal


fun main() {
    render(document.getElementById("app")) {
        injectGlobal {
            universal {
                margin(0.px)
                padding(0.px)
            }

            body {
                fontFamily = "Picon, sans-serif"
//                fontSize = 1.25.em
//                minWidth = 380.px

//                maxWidthXL {
//                    fontSize = 1.1.em
//                }
//                maxWidthS {
//                    fontSize = 1.em
//                }
            }

            a {
                textDecoration = TextDecoration.none
                color = Color.inherit
                transition(::color, 0.2.s)

                hover {
                    color = Color.kodein.purple
                }

            }
        }

        child(App::class) {}
    }
}