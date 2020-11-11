package net.kodein.pages.home.fragment

import kotlinx.css.*
import kotlinx.html.SVG
import net.kodein.charter.kodein
import net.kodein.utils.*
import react.RProps
import react.child
import react.dom.a
import react.functionalComponent
import styled.*

val Departments = functionalComponent<RProps>("Departments") {
    flexRow {
        css {
            maxWidth(889) {
                display = Display.block
                flexDirection = FlexDirection.column
            }
        }

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

private val Department = functionalComponent<DepartmentProps>("Department") { props ->

    val dptHeight = 32.rem

    flexColumn {
        css {
            backgroundColor = Color.kodein.cute
            flex(flexGrow = 1.0, flexBasis = FlexBasis.zero)
            padding(5.rem, 3.rem)

            minWidth(890) { height = dptHeight - 5.em * 2 }
            maxWidth(889) {
                padding(2.rem)
            }

            "a" {
                +kodein.button
                alignSelf = Align.flexStart
                margin(1.rem)
            }
        }

            styledImg(alt="${props.title} icon", src="imgs/ic_${props.icon}.svg") {
            attrs {
                width = "48"
                height = "38"
            }
                css {
                    width = 3.em
                    padding(0.rem, 0.rem, 1.rem, 3.rem)
                }
            }

            styledDiv {
                css {
                    color = Color.kodein.purple
                    +kodein.display1
                    specific { textAlign = TextAlign.start }
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
                    maxWidth(889) { height = 5.rem }
                }
                props.children()
            }

            a { +"READ MORE" }
    }

    if(props.isLastItem != true) {
        styledDiv {
            css {
                backgroundColor = Color.kodein.cute
            }

            styledSvg {
                css {
                    maxWidth(889) { display = Display.none }

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

            styledSvg {
                css {
                    height = dptHeight / 10
                    width = 100.pct

                    minWidth(890) { display = Display.none }
                    landscapeMobile { height = dptHeight / 8 }
                    filter = "drop-shadow(0 0.8rem 1rem  ${Color.kodein.kaumon.withAlpha(0.5)})"
                    put("clip-path", "polygon(0 30%, 100% 30%, 100% 35%, 100% 500%, 0 500%)")
                }

                val s: SVG = attrs

                attrs.viewBox(0, 0, 300, 12)

                draw {
                    // M 0 0, H 300, V 6, L 100 2, H 0, Z
                    path(fill = Color.kodein.cute) {
                        moveTo(0, 0)
                        horizontalLineTo(300)
                        verticalLineTo(10)
                        lineTo(100, 4)
                        horizontalLineTo(0)
                        closePath()
                    }
                }
            }
        }
    }

}
