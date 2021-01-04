package net.kodein.pages.home.fragment

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.pages.home.HomeStrings
import net.kodein.useText
import net.kodein.utils.*
import net.kodein.withBasePath
import react.RProps
import react.child
import react.dom.a
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSvg

val Departments = functionalComponent<RProps>("Departments") {
    val strings = useText().home

    flexRow {
        css {
            maxWidth(889) {
                display = Display.block
                flexDirection = FlexDirection.column
            }
        }

        child(Department) {
            attrs.icon = "advisory"
            attrs.description = strings.advisoryDepartment
            attrs.readMoreLink = "services.html#consultancy"
        }

        child(Department) {
            attrs.icon = "training"
            attrs.description = strings.trainingDepartment
            attrs.readMoreLink = "training.html#description"
        }

        child(Department) {
            attrs.icon = "development"
            attrs.description = strings.developmentDepartment
            attrs.readMoreLink = "services.html#development"
            attrs.isLastItem = true
        }
    }
}

private interface DepartmentProps : RProps {
    var icon: String
    var description: HomeStrings.TitledContent
    var readMoreLink: String
    var isLastItem: Boolean?
}

private val Department = functionalComponent<DepartmentProps>("Department") { props ->
    val strings = useText().home

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
            "span.nowrap" {
                whiteSpace = WhiteSpace.nowrap
            }
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

        styledDiv {
            css {
                color = Color.kodein.purple
                +kodein.display1
                specific { textAlign = TextAlign.start }
                padding(0.5.rem, 1.rem)
            }
            +props.description.title.toUpperCase()
        }

        styledDiv {
            css {
                color = Color.kodein.orange
                +kodein.body
                padding(0.5.rem, 0.5.rem)
                minHeight = 60.pct
                maxWidth(889) { height = 5.rem }
            }
            props.description.content(this)
        }

        a(href = props.readMoreLink) { +strings.readMore }
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
