package net.kodein

import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.charter.*
import net.kodein.utils.*
import react.dom.render
import styled.injectGlobal


fun main() {
    render(document.getElementById("app")) {
        injectGlobal {
            universal {
                margin(0.px)
                padding(0.px)
            }

            html {
                minSize(min = 360) { fontSize = 6.px }
                minSize(min = 480) { fontSize = 8.px }
                minSize(min = 680) { fontSize = 8.px }
                minSize(min = 768) { fontSize = 10.px }
                minSize(min = 880) { fontSize = 10.px }
                minSize(min = 980) { fontSize = 12.px }
                minSize(min = 1024) { fontSize = 14.px }
                minSize(min = 1920) { fontSize = 16.px }
                minSize(min = 2500) { fontSize = 18.px }
//                minSize(min = 3000) { fontSize = 22.px }
            }

            body {
                fontFamily = "Picon, sans-serif"
//                minWidth = 380.px
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
