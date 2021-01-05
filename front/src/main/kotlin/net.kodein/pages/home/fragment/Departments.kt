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
            maxWidth(1023) {
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
            position = Position.relative
            zIndex = 100 - index
            flex(flexGrow = 1.0, flexBasis = FlexBasis.zero)
            padding(4.rem, 2.rem)

            if(props.isLastItem != true) {
                minWidth(1024) {
                    filter = "drop-shadow(1rem 0 0.5rem ${Color.kodein.kaumon.withAlpha(0.3)})"
                }
                maxWidth(1023) {
                    filter = "drop-shadow(0 1rem 0.5rem ${Color.kodein.kaumon.withAlpha(0.3)})"
                }

                "&:before" {
                    position = Position.absolute
                    zIndex = 99 - index
                    top = 0.px; bottom = 0.px; right = 0.px; left = 0.px
                    backgroundColor = Color.kodein.cute
                    content = QuotedString("")
                    minWidth(1024) {
                        clipPath = "polygon(0 0, 100% 0, 95% 70%, 95% 100%, 0 100%)"
                    }
                    maxWidth(1023) {
                        clipPath = "polygon(0 0, 100% 0, 100% 100%, 30% 98%, 0 98%)"
                    }
                }
            }
        }

        flexColumn {
            css {
                flexGrow = 1.0
                zIndex = 100
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
                zIndex = 100
                +kodein.button
                alignSelf = Align.flexStart
                margin(vertical = 1.rem)
            }
            +props.description.readMore
        }
    }
}
