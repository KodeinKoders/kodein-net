package net.kodein.charter

import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.lh
import net.kodein.utils.medium
import net.kodein.utils.regular
import styled.StyleSheet


object KodeinStyles : StyleSheet("KodeinStyles", isStatic = true) {

    private val modulor = .85.rem
//    private val modulor = 1.125.em
//    private val modulor = 18.px

    val picon = "Picon"
    val piconExtended = "Picon-Extended"

    val subHead by css {
        fontSize = modulor * 2
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center
    }

    val display2 by css {
        fontSize = modulor * 4
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center
    }

    val display1 by css {
        fontSize = modulor * 2
        lineHeight = 1.25.em.lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.regular
    }

    val chapo by css {
        fontSize = modulor * 2
        lineHeight = (modulor * 2.5).lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.regular
        textAlign = TextAlign.start
    }

    val separator by css {
        height = modulor * 7
        margin(modulor * 3, auto)
    }

    val intertitre by css {
        fontSize = modulor * 1.5
        letterSpacing = 0.05.em
        lineHeight = (modulor * 1.8).lh
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
    }

    val body by css {
        fontSize = modulor
        letterSpacing = 0.025.em
        lineHeight = (modulor * 1.5).lh
        fontFamily = picon
        fontWeight = FontWeight.regular
    }

    val link by css {
        fontSize = modulor * 0.825
        fontWeight = FontWeight.medium
        letterSpacing = 0.025.em
        lineHeight = (modulor * 1.5).lh
    }
}