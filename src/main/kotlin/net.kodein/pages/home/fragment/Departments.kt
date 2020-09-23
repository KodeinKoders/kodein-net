package net.kodein.pages.home.fragment

import kotlinx.css.*
import kotlinx.html.SVG
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RProps
import react.child
import react.functionalComponent
import styled.*

val Departments by functionalComponent {
    flexRow {
//        css {
//            maxWidthXM {
//                flexDirection = FlexDirection.column
//            }
//        }

        child(Department) {
            attrs.title = "ADVISORY"
            attrs.icon = "advisory"
            +"Empowering your engineering teams to take on challenges with Kotlin"
        }

        child(Department) {
            attrs.title = "TRAINING"
            attrs.icon = "training"
            +"Regardless that you do Mobile, Backend or Frontend we can help you increase your Kotlin expertise!"
        }

        child(Department) {
            attrs.title = "DEVELOPMENT"
            attrs.icon = "development"
            attrs.isLastItem = true
            +"We can help you reach your goals by taking care of the technical challenges you have!"
        }
    }
}

private interface DepartmentProps : RProps {
    var title: String
    var icon: String
    var isLastItem: Boolean?
}

private val Department by functionalComponent<DepartmentProps> { props ->

    val dptHeight = 32.rem

    flexColumn {
        css {
            height = dptHeight - 5.em * 2
            backgroundColor = Color.kodein.cute
            flex(flexGrow = 1.0, flexBasis = FlexBasis.zero)
            padding(5.rem, 3.rem)
//            maxWidthXM {
//                width = 100.pct
//            }
        }

        styledImg(src="imgs/ic_${props.icon}.svg") {
            css {
                width = 3.em
                padding(0.rem, 0.rem, 1.rem, 3.rem)
            }
        }

        styledDiv {
            css {
                color = Color.kodein.purple
                +kodein.display1
                padding(0.5.rem, 1.rem)
            }
            +props.title.toUpperCase()
        }
        styledDiv {
            css {
                color = Color.kodein.orange
                +kodein.body
                padding(0.5.rem, 1.rem)
                height = 15.rem
//                maxWidthXM {
//                    width = 75.pct
//                }
            }
            props.children()
        }

        styledA {
            css {
                +kodein.readMore
                alignSelf = Align.flexStart
                margin(1.rem)
            }
            +"READ MORE"
        }
    }

    if(props.isLastItem != true) {
        styledDiv {
            css {
                backgroundColor = Color.kodein.cute
            }

            styledSvg {
                css {
                    height = dptHeight
                    filter = "drop-shadow(.8rem 0 1.5rem ${Color.kodein.kaumon.withAlpha(0.5)})"
                    put("clip-path", "polygon(0% 0%, 0% 100%, 500% 100%, 500% 0%)")
                }

                val s: SVG = attrs

                attrs.viewBox(0, 0, 6, 100)

                draw {
                    path(fill = Color.kodein.cute) {
                        moveTo(0, 0)
                        verticalLineTo(100)
                        horizontalLineTo(2)
                        verticalLineTo(60)
                        lineTo(6, 0)
                        closePath()
                    }
                }
            }
        }
    }

}
