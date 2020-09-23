package net.kodein.pages.home.fragment

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RProps
import react.child
import react.dom.a
import react.dom.br
import react.functionalComponent
import styled.*


val Humans by functionalComponent {

    styledDiv {
        css {
            marginTop = 3.rem
        }
    }

    styledH1 {
        css {
            +kodein.display2
            fontWeight = FontWeight.hairline
            color = Color.kodein.orange
            textAlign = TextAlign.start
            paddingLeft = 5.rem
        }
        +"Humans trust us!"
    }

    styledH3 {
        css {
            +kodein.display1
            fontWeight = FontWeight.regular
            color = Color.kodein.orange
            textAlign = TextAlign.start
            paddingLeft = 5.rem
        }
        +"Are you next?"
    }

    flexRow {
        css {
            padding(2.em)
            flexWrap = FlexWrap.wrap
        }

        child(Human) {
            attrs.name = "Fabrice Drouin"
            attrs.picture = "fabrice-drouin.jpeg"
            attrs.job = "Founder & CTO at ACINQ"
            attrs.url = "https://www.linkedin.com/in/fabrice-drouin-95ab8012"
            attrs.twitter = "acinq_co"
        }
        child(Human) {
            attrs.name = "Thalia Cruz Castañares"
            attrs.picture = "thalia-cruz.jpg"
            attrs.job = "Android team manager at Softbank Robotics"
            attrs.url = "https://www.linkedin.com/in/thal%C3%ADa-cruz-casta%C3%B1ares-a1257a3a"
            attrs.twitter = "sbreurope"
        }
        child(Human) {
            attrs.name = "Jochen Buhler"
            attrs.picture = "jochen-buhler.jpg"
            attrs.job = "Software Engineer at Bosch"
            attrs.url = "https://www.xing.com/profile/Jochen_Buehler8"
            attrs.twitter = "boschglobal"
        }
        child(Human) {
            attrs.name = "Rémi B. Loizeau"
            attrs.picture = "remi-bouvet.jpg"
            attrs.job = "Crayola quand il y pense"
            attrs.url = "https://www.linkedin.com/in/r%C3%A9mi-bouvet-loizeau-914536155"
            attrs.twitter = "remi_b_loizeau"
        }
        child(Human) {
            attrs.name = "Cedric Ravalec"
            attrs.picture = "cedric-ravalec.jpeg"
            attrs.job = "Embedded & IoT Business Line Manager at Smile"
            attrs.url = "https://www.linkedin.com/in/ravalec"
            attrs.twitter = "groupesmile"
        }
    }
}

interface HumanProps: RProps {
    var name: String
    var picture: String
    var job: String
    var url: String
    var twitter: String
}

private val Human by functionalComponent<HumanProps> { props ->
    flexColumn(alignItems = Align.center) {
        css {
            border(0.rem, BorderStyle.solid, Color.black, 0.2.rem)
            overflow = Overflow.hidden
            boxShadow(Color.kodein.cute.withAlpha(0.6), 0.rem, 0.4.rem, blurRadius = 2.rem)
            put("clip-path", "polygon(-100% -100%, -100% 0%, 0% 0%, 0% 100%, -100% 100%, -100% 200%, 200% 200%, 200% -100%)")
            firstChild {
                put("clip-path", "inherit")
            }
            paddingBottom = 8.rem
        }

        styledDiv {
            css {
                backgroundColor = Color.kodein.dark
                overflow = Overflow.hidden
                width = 20.rem
                height = 10.rem
            }
            styledImg(src = "imgs/humans/${props.picture}") {
                css {
                    width = 20.rem + 3.rem
                    height = 10.rem + 3.rem
                    marginTop = (-1.5).rem
                    marginLeft = (-1.5).rem
                    objectFit = ObjectFit.cover
                    filter = "blur(1rem)"
                    opacity = 0.4
                }
            }
        }
        styledImg(src = "imgs/humans/${props.picture}") {
            css {
                width = 10.rem
                height = 10.rem
                objectFit = ObjectFit.cover
                borderRadius = 12.rem
                marginTop = (-5).rem
                zIndex = 1
            }
        }
        styledH3 {
            css {
                +kodein.intertitre
                marginTop = 2.5.rem
                width = 18.rem
                textAlign = TextAlign.center
            }
            a(href = props.url, target="_blank") {
                +props.name
            }
        }
        styledP {
            css {
                +kodein.body
                width = 18.rem
                marginTop = 0.5.rem
                color = Color.kodein.orange
                textAlign = TextAlign.center
            }
            +props.job
            br {}
            a(href = "https://twitter.com/${props.twitter}") {
                +"@${props.twitter}"
            }
        }
    }
}
