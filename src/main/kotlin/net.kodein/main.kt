package net.kodein

import kotlinx.css.*
import net.kodein.utils.Palette
import net.kodein.utils.maxWidthS
import net.kodein.utils.maxWidthXL
import react.dom.render
import styled.StyledComponents
import styled.injectGlobal
import kotlin.browser.document


fun main() {
    render(document.getElementById("app")) {
        StyledComponents.injectGlobal {
            universal {
                margin(0.px)
                padding(0.px)
            }

            body {
                backgroundColor = Palette.cute.color
                fontFamily = "Picon, sans-serif"
                fontSize = 1.25.em
                minWidth = 380.px

                maxWidthXL {
                    fontSize = 1.1.em
                }
                maxWidthS {
                    fontSize = 1.em
                }
            }

            "#app" {
                margin(LinearDimension.auto)
            }
        }

        child(App::class) {}
    }
}