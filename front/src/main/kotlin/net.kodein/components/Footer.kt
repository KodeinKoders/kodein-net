package net.kodein.components

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.lh
import kotlinx.html.js.onClickFunction
import kotlinx.html.unsafe
import net.kodein.charter.kodein
import net.kodein.utils.flexColumn
import net.kodein.utils.flexRow
import net.kodein.utils.maxWidth
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledImg


val Footer = functionalComponent<RProps>("Footer") {

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
                }
                child(KodeinLogo) {
                    attrs {
                        logo = "orange-fat"
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
                        top = 0.rem
                        left = 0.rem
                    }
                }
            }

        }

        styledDiv {
            css {
                backgroundColor = Color.kodein.darker
                padding(4.rem, 0.rem, 2.rem, 0.rem)
            }

            flexRow(JustifyContent.spaceBetween) {
                css {
                    backgroundColor = Color.kodein.darker
                    color = Color.kodein.orange
                    fontSize = 0.8.rem
                    letterSpacing = 0.05.em
                    lineHeight = 1.8.em.lh
                    maxWidth = 65.rem
                    margin(LinearDimension.auto)

                    maxWidth(768) {
                        flexDirection = FlexDirection.column
                    }

                    "p" {
                        width = 10.rem

                        "b" {
                            display = Display.block
                            paddingBottom = 2.rem
                        }

                        "ul" {
                            listStyleType = ListStyleType.none

                            "li" {
                                color = Color.kodein.kuivre
                            }
                        }
                    }
                }

                p {
                    b { +"Un gros footer" }
                    ul {
                        li { a(href = "nowhere") { +"Un gros footer" } }
                        li { a(href = "nowhere") { +"On est sérieux" } }
                        li { a(href = "nowhere") { +"KKoders = big boss" } }
                        li { a(href = "nowhere") { +"Du boulot. Vite !" } }
                    }
                }

                p {
                    b { +"Keep contact" }
                    ul {
                        li { a(href = "nowhere") { +"E-mail" } }
                        li { a(href = "nowhere") { +"Twitter" } }
                        li { a(href = "nowhere") { +"Linkedin" } }
                    }
                }

                p {
                    b { +"Lisez ça" }
                    ul {
                        li { a(href = "nowhere") { +"Un gros footer" } }
                        li { a(href = "nowhere") { +"On est sérieux" } }
                        li { a(href = "nowhere") { +"KKoders = big boss" } }
                        li { a(href = "nowhere") { +"Du boulot. Vite !" } }
                    }
                }

                p {
                    b { +"Des trucs" }
                    ul {
                        li { a(href = "nowhere") { +"Sans déconner ?" } }
                        li { a(href = "nowhere") { +"Faut pas lire ça !" } }
                        li { a(href = "nowhere") { +"Arrête de lire !" } }
                        li { a(href = "nowhere") { +"Fait un truc de ta vie" } }
                        li { a(href = "nowhere") { +"Un truc bien." } }
                        li { a(href = "nowhere") { +"Faut pas lire ça." } }
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
                    }
                }
                flexRow(alignItems = Align.center) {
                    styledImg(src = "imgs/logo-kaumon.svg") {
                        css {
                            width = 1.5.rem
                            height = 1.5.rem
                            padding(0.5.rem)
                            marginRight = 1.rem
                            border(0.05.rem, BorderStyle.solid, Color.kodein.korail, 0.15.rem)
                            cursor = Cursor.pointer
                        }
                    }

                    +"Proudly powered by the"
                    span { attrs.unsafe { +"&nbsp;" } }
                    styledA(href = "https://kodein.org") {
                        css {
                            color = Color.kodein.kinzolin
                        }
                        b { +"KODEIN" }
                        +"Framework"
                    }
                    +"."
                }

                p {
                    +"© 2020 "
                    b { +"KODEIN" }
                    +"Koders. All rights reserved."
                }
            }
        }

    }

}
