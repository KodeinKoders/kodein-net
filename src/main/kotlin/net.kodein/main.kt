package net.kodein

import kotlinx.browser.document
import kotlinx.css.*
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
        }

        child(App::class) {}
    }
}