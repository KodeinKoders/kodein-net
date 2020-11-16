package net.kodein.charter

import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.lh
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import net.kodein.utils.*
import styled.StyleSheet


object KodeinStyles : StyleSheet("KodeinStyles", isStatic = true) {

    val picon = "Picon, Arial, sans-serif"
    val piconExtended = "Picon-Extended, Verdana, sans-serif"

    val subHead by css {
        fontSize = 2.rem
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center

        maxSize(980) { fontSize = 1.75.rem  }
        maxSize(775) { fontSize = 1.5.rem  }
        maxSize(570) { fontSize = 1.25.rem  }
    }

    val display3 by css {
        fontSize = 4.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center

        maxSize(980) { fontSize = 3.rem  }
        maxSize(775) { fontSize = 2.75.rem  }
        maxSize(570) { fontSize = 2.25.rem  }
    }

    val display2 by css {
        fontSize = 3.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.medium
        textAlign = TextAlign.center

        maxSize(980) { fontSize = 2.5.rem  }
        maxSize(775) { fontSize = 2.25.rem  }
        maxSize(570) { fontSize = 1.75.rem  }
    }

    val display1 by css {
        fontSize = 2.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.025.em
        fontFamily = piconExtended
        fontWeight = FontWeight.regular
        textAlign = TextAlign.center

        maxSize(980) { fontSize = 1.75.rem  }
        maxSize(775) { fontSize = 1.5.rem  }
        maxSize(570) { fontSize = 1.25.rem  }
    }

    val chapo by css {
        fontSize = 2.rem
        lineHeight = 1.25.em.lh
        letterSpacing = 0.05.em
        fontFamily = picon
        fontWeight = FontWeight.regular
        textAlign = TextAlign.start

        maxSize(980) { fontSize = 1.75.rem  }
        maxSize(775) { fontSize = 1.5.rem  }
        maxSize(570) { fontSize = 1.25.rem  }
    }

    val intertitre by css {
        fontSize = 1.5.rem
        letterSpacing = 0.025.em
        lineHeight = 1.8.rem.lh
        fontFamily = piconExtended
        fontWeight = FontWeight.medium

        maxSize(980) { fontSize = 1.4.rem  }
        maxSize(775) { fontSize = 1.3.rem  }
        maxSize(570) { fontSize = 1.2.rem  }
    }

    val body by css {
        fontSize = 1.25.rem
        letterSpacing = 0.025.em
        lineHeight = 1.5.rem.lh
        fontFamily = picon
        fontWeight = FontWeight.regular

        maxSize(980) { fontSize = 1.2.rem  }
        maxSize(775) { fontSize = 1.1.rem  }
        maxSize(570) { fontSize = 1.rem  }
    }

    val link by css {
        fontSize = 1.rem
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
            transition(::opacity, 0.5.s)
        }

        transition(::backgroundColor, 0.5.s)
        transition(::color, 0.5.s)
        transition(::boxShadow, 0.5.s)

        hover {
            backgroundColor = Color.kodein.kuivre
            color = Color.kodein.cute
            boxShadow(Color.kodein.dark.withAlpha(0.31), offsetY = 0.1.rem, blurRadius = 0.42.rem)

            img {
                opacity = 1
            }
        }
    }

    val dropShadow by css {
        filter = "drop-shadow(0 .25rem 0.15rem ${Color.kodein.dark.withAlpha(0.2)})"
    }
}

val CSSBuilder.kodein get() = KodeinStyles
