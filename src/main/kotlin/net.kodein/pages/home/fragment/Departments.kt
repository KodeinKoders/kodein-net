package net.kodein.pages.home.fragment

import kotlinx.css.*
import kotlinx.html.unsafe
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.*
import react.dom.svg
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
            +"Empowering your engineering teams to take on challenges with Kotlin"
        }

        child(Department) {
            attrs.title = "TRAINING"
            +"Regardless that you do Mobile, Backend or Frontend we can help you increase your Kotlin expertise!"
        }

        child(Department) {
            attrs.title = "DEVELOPMENT"
            attrs.isLastItem = true
            +"We can help you reach your goals by taking care of the technical challenges you have!"
        }
    }
}

private interface DepartmentProps : RProps {
    var title: String
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

        styledDiv {
            css {
                color = Color.kodein.purple
                +KodeinStyles.display1
                padding(0.5.rem, 1.rem)
            }
            +props.title.toUpperCase()
        }
        styledDiv {
            css {
                color = Color.kodein.orange
                +KodeinStyles.body
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
                alignSelf = Align.flexStart
                +KodeinStyles.link
                margin(1.rem)
                padding(vertical = 0.3.rem, horizontal = 1.rem)
                backgroundColor = Color.kodein.orange
                color = Color.kodein.cute.withAlpha(0.8)
                borderRadius = 2.rem
                cursor = Cursor.pointer
                hover {
                    backgroundColor = Color.kodein.kuivre
                    color = Color.kodein.cute
                }
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

                attrs["viewBox"] = "0 0 6 100"
                attrs.unsafe {
                    +"""
                        <g>
                            <path id="svg_1"
                                d="M 0 0 V 100 H 2 V 60 L 6 0 Z"
                                fill="${Color.kodein.cute}"
                            />
                        </g>
                    """
                }
            }
        }
    }

}
