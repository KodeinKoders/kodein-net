package net.kodein.charter

import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.lh
import net.kodein.utils.*
import styled.StyleSheet


object KodeinStyles : StyleSheet("KodeinStyles", isStatic = true) {

    val picon = "Picon"
    val piconExtended = "Picon-Extended"

    val subHead by css {
        fontSize = 2.rem
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center
    }

    val display3 by css {
        fontSize = 4.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center
    }

    val display2 by css {
        fontSize = 3.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center
    }

    val display1 by css {
        fontSize = 2.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.regular
    }

    val chapo by css {
        fontSize = 1.8.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.05.em
        fontFamily = picon
        fontWeight = FontWeight.regular
        textAlign = TextAlign.start
    }

    val separator by css {
        height = 7.rem
        margin(3.rem, auto)
    }

    val intertitre by css {
        fontSize = 1.5.rem
        letterSpacing = 0.025.em
        lineHeight = 1.8.rem.lh
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
    }

    val body by css {
        fontSize = 1.rem
        letterSpacing = 0.025.em
        lineHeight = 1.5.rem.lh
        fontFamily = picon
        fontWeight = FontWeight.regular
    }

    val link by css {
        fontSize = 0.825.rem
        fontWeight = FontWeight.medium
        letterSpacing = 0.025.em
        lineHeight = 1.5.rem.lh
    }

    val button by css {
        +link
        display = Display.flex
        flexDirection = FlexDirection.row
        alignItems = Align.center
        padding(vertical = 0.3.rem, horizontal = 1.rem)
        backgroundColor = Color.kodein.orange
        color = Color.kodein.cute.withAlpha(0.8)
        borderRadius = 2.rem
        cursor = Cursor.pointer
        boxShadow(Color.kodein.dark.withAlpha(0.2), offsetY = 0.1.rem, blurRadius = 0.2.rem)

        focus {
            outline = Outline.none
        }

        img {
            opacity = 0.8
        }

        hover {
            backgroundColor = Color.kodein.kuivre
            color = Color.kodein.cute

            img {
                opacity = 1
            }
        }
    }
}

val CSSBuilder.kodein get() = KodeinStyles
