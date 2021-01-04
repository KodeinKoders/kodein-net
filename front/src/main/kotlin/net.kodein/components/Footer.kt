package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.lh
import kotlinx.html.unsafe
import net.kodein.charter.kodein
import net.kodein.useText
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.maxSize
import net.kodein.utils.maxWidth
import net.kodein.withBasePath
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent
import styled.*


val Footer = functionalComponent<RProps>("Footer") {
    val strings = useText().footer
    val globalStrings = useText().global

    flexColumn {

        child(Separator) {
            attrs.height = 0.3.em
        }

        styledDiv {
            css {
                backgroundColor = Color.kodein.dark
                padding(3.rem, 0.rem)
            }

            styledDiv {
                css {
                    position = Position.relative
                    maxWidth = 65.rem
                    margin(LinearDimension.auto)
                    padding(0.rem, 1.rem)
                }
                child(KodeinLogo) {
                    attrs {
                        logo = "orange-fat"
                        logoAlt = globalStrings.kodeinLogo
                        logoHeight = 3.5.rem
                        bold = "KODEIN"
                        light = "Koders"
                        color = Color.kodein.orange
                        titleColor = Color.kodein.kaumon
                        subtitle = "PAINLESS TECHNOLOGY BY HUMANS"
                    }
                }

                styledDiv {
                    css {
                        width = 2.9.rem
                        height = 3.5.rem
                        background = "linear-gradient(180deg, transparent, ${Color.kodein.dark})"
                        position = Position.absolute
                        bottom = 0.rem
                        left = 1.rem
                    }
                }
            }

        }

        styledDiv {
            css {
                backgroundColor = Color.kodein.darker
                padding(4.rem, 1.rem, 2.rem, 1.rem)

                maxWidth(768) {
                    padding(1.rem)
                }
            }

            flexRow(JustifyContent.spaceBetween) {
                css {
                    backgroundColor = Color.kodein.darker
                    color = Color.kodein.orange
                    fontSize = 0.8.rem
                    letterSpacing = 0.05.em
                    lineHeight = 1.8.em.lh
                    maxWidth = 65.rem
                    flexWrap = FlexWrap.wrap
                    margin(LinearDimension.auto)

                    maxWidth(768) {
                        justifyContent = JustifyContent.spaceAround
                    }
                    maxWidth(480) {
                        flexDirection = FlexDirection.column
                        alignItems = Align.center
                    }

                    "div" {
                        width = 10.rem
                        maxWidth(768) {
                            width = 30.pct
                            paddingLeft = 5.pct
                            paddingBottom = 2.rem
                        }
                        maxWidth(480) {
                            width = 10.rem
                        }

                        "b" {
                            display = Display.block
                            paddingBottom = 2.rem

//                            maxWidth(768) {
//                                paddingTop = 1.rem
//                                paddingBottom = 1.rem
//                            }
                        }

                        "ul" {
                            listStyleType = ListStyleType.none

                            "li" {
                                color = Color.kodein.kuivre

                                "a" {
                                    hover {
                                        color = Color.kodein.purple
                                    }
                                }
                            }
                        }
                    }
                }

                div {
                    b { +strings.siteMap }
                    ul {
                        li { a(href = "index.html") { +strings.home } }
                        li { a(href = "services.html") { +strings.services } }
                        li { a(href = "training.html") { +strings.training } }
                        li { a(href = "oss.html") { +strings.openSource } }
                        li { a(href = "team.html") { +strings.team } }
                        li { a(href = "blog.html") { +strings.blog } }
                    }
                }

                div {
                    b { +"Contact us!" }
                    ul {
                        li { a(href = "contact.html") { +strings.contactForm } }
                        li { a(href = "contact@kodein.net", target = "_blank") { +"E-mail" } }
                        li { a(href = "https://twitter.com/KodeinKoders", target = "_blank") { +"Twitter" } }
                        li { a(href = "https://www.linkedin.com/company/kodein", target = "_blank") { +"LinkedIn" } }
                    }
                }

                div {
                    b { +strings.openSource }
                    ul {
                        li { a(href = "https://kodein.org", target = "_blank") { +"Kodein Framework" } }
                        li { a(href = "https://github.com/KodeinKoders", target = "_blank") { +"Github" } }
                        li { a(href = "https://medium.com/kodein-koders", target = "_blank") { +"Medium" } }
                    }
                }

                div {
                    b { +"Kodein SAS" }
                    ul {
                        li { +"2 Cour de la Badiane" }
                        li { +"94000, Créteil" }
                        li { +"FRANCE" }
                        li { attrs.unsafe { +"&nbsp;" } }
                        li { +"+33 6 83 54 55 96" }
                        li { +"+33 6 12 56 56 50" }
                    }
                }
            }

            flexRow(justifyContent = JustifyContent.spaceBetween, alignItems = Align.center) {
                css {
                    paddingTop = 4.rem
                    maxWidth = 65.rem
                    margin(LinearDimension.auto)
                    color = Color.kodein.kuivre

                    maxWidth(768) {
                        flexDirection = FlexDirection.column
                        paddingTop = 2.rem
                    }
                }
                flexRow(alignItems = Align.center) {
                    css {
                        maxWidth(768) {
                            flexDirection = FlexDirection.column
                        }
                    }

                    withBasePath { path ->
                        styledImg(src = "$path/imgs/logo-kaumon.svg", alt = globalStrings.kodeinLogo) {
                            attrs {
                                width = "24"
                                height = "24"
                            }
                            css {
                                width = 1.5.rem
                                height = 1.5.rem
                                padding(0.5.rem)
                                marginRight = 1.rem
                                border(0.05.rem, BorderStyle.solid, Color.kodein.korail, 0.15.rem)
                            }
                        }
                    }

                    styledP {
                        css {
                            maxSize(768) {
                                padding(1.rem, 0.rem)
                            }
                        }
                        strings.openSourced(this)
                    }
                }

                p {
                    +"© 2020 "
                    b { +"KODEIN" }
                    +"Koders ("
                    styledA(href = "credits.html") {
                        css.color = Color.kodein.kinzolin
                        +strings.credits
                    }
                    +"). "
                    +strings.allRightsReserved
                }
            }
        }

    }

}
