package net.kodein.pages.home.fragment

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.borderRight
import kotlinx.css.properties.borderTop
import kotlinx.html.InputType
import net.kodein.charter.kodein
import net.kodein.components.layerSeparator
import net.kodein.pages.home.HomeStrings
import net.kodein.useText
import net.kodein.utils.*
import net.kodein.withBasePath
import react.RProps
import react.child
import react.dom.a
import react.functionalComponent
import styled.*

val Departments = functionalComponent<RProps>("Departments") {
    val strings = useText().home

    flexRow {
        css {
            backgroundColor = Color.kodein.cute
            maxWidth(889) {
                display = Display.block
                flexDirection = FlexDirection.column
            }
        }

        child(Department) {
            attrs.index = 1
            attrs.icon = "advisory"
            attrs.description = strings.advisoryDepartment
            attrs.readMoreLink = "services.html#consultancy"
        }

        child(Department) {
            attrs.index = 2
            attrs.icon = "training"
            attrs.description = strings.trainingDepartment
            attrs.readMoreLink = "training.html#description"
        }

        child(Department) {
            attrs.index = 3
            attrs.icon = "development"
            attrs.description = strings.developmentDepartment
            attrs.readMoreLink = "services.html#development"
            attrs.isLastItem = true
        }
    }
}

private interface DepartmentProps : RProps {
    var index: Int
    var icon: String
    var description: HomeStrings.TitledContent
    var readMoreLink: String
    var isLastItem: Boolean?
}

private val Department = functionalComponent<DepartmentProps>("Department") { props ->
    val dptHeight = 32.rem

    val index = props.index * 3

    flexColumn {
        css {
            backgroundColor = Color.kodein.cute
            flex(flexGrow = 1.0, flexBasis = FlexBasis.zero)
            zIndex = 100 - index
            padding(3.rem, 1.rem)
        }

        flexColumn {
            css {
                flexGrow = 1.0
            }

            withBasePath { path ->
                styledImg(alt="${props.icon} icon", src="$path/imgs/ic_${props.icon}.svg") {
                    attrs {
                        width = "48"
                        height = "38"
                    }
                    css {
                        width = 3.em
                        padding(0.rem, 0.rem, 1.rem, 3.rem)
                    }
                }
            }

            styledP {
                css {
                    color = Color.kodein.purple
                    +kodein.display1
                    specific { textAlign = TextAlign.start }
                    padding(0.5.rem, 1.rem)
                }
                +props.description.title.toUpperCase()
            }

            styledP {
                css {
                    flexGrow = 1.0
                    color = Color.kodein.orange
                    +kodein.body
                    padding(0.5.rem, 0.5.rem)

                    "span.nowrap" {
                        whiteSpace = WhiteSpace.nowrap
                    }
                }
                props.description.content(this)
            }
        }

        styledA(href = props.readMoreLink) {
            css {
                +kodein.button
                alignSelf = Align.flexStart
                margin(1.rem)
            }
            +props.description.readMore
        }
    }

    if(props.isLastItem != true) {
        styledDiv {
            css {
                flexGrow = 0.25
                zIndex = 99 - index
                backgroundColor = Color.kodein.cute
            }

            styledSvg {
                css {
                    maxWidth(889) { display = Display.none }

                    height = dptHeight
                    flexGrow = 0.25
                    filter = "drop-shadow(.8rem 0 1.5rem ${Color.kodein.kaumon.withAlpha(0.5)})"
                    put("clip-path", "polygon(0% 0%, 0% 100%, 500% 100%, 500% 0%)")
                }

                attrs {
                    viewBox(0, 0, 6, 100)
                    preserveAspectRatio = "none"
                }

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
                    minWidth(890) { display = Display.none }

                    height = dptHeight / 10
                    width = 100.pct
                    landscapeMobile { height = dptHeight / 8 }
                    filter = "drop-shadow(0 1rem 1.5rem ${Color.kodein.kaumon.withAlpha(0.5)})"
                    put("clip-path", "polygon(0 30%, 100% 30%, 100% 35%, 100% 500%, 0 500%)")
                }

                attrs {
                    viewBox(0, 0, 300, 12)
                    preserveAspectRatio = "none"
                }

                draw {
                    // M0,0 H300 V6 L100,4 H0 Z
                    path(fill = Color.kodein.cute) {
                        moveTo(0, 0)
                        horizontalLineTo(300)
                        verticalLineTo(6)
                        lineTo(100, 4)
                        horizontalLineTo(0)
                        closePath()
                    }
                }
            }
        }
    }
}
