package net.kodein.pages.home.fragment

import kotlinx.css.*
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.getValue
import net.kodein.utils.hairline
import net.kodein.utils.regular
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledH1
import styled.styledH3


val Humans by functionalComponent {

    styledDiv {
        css {
            marginTop = 3.rem
        }
    }

    styledH1 {
        css {
            +KodeinStyles.display2
            fontWeight = FontWeight.hairline
            color = Color.kodein.orange
            textAlign = TextAlign.start
            paddingLeft = 5.rem
        }
        +"Humans trust us!"
    }

    styledH3 {
        css {
            +KodeinStyles.display1
            fontWeight = FontWeight.regular
            color = Color.kodein.orange
            textAlign = TextAlign.start
            paddingLeft = 5.rem
        }
        +"Are you next?"
    }

}
