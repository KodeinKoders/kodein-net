package net.kodein.pages.team.fragment

import kotlinx.css.*
import kotlinx.css.properties.*
import net.kodein.charter.kodein
import net.kodein.components.ContactUsProps
import net.kodein.utils.*
import react.RBuilder
import react.RProps
import react.child
import react.dom.a
import react.functionalComponent
import styled.*

val Members = functionalComponent<ContactUsProps>("Members") { props ->
    flexRow {
        css {
            "a" {
                +kodein.link
                color = Color.kodein.korail
                borderBottom = "none"
                put("text-shadow", "none")
                hover {
                    color = Color.kodein.kinzolin
                }
            }

            maxWidth(768) {
                flexDirection = FlexDirection.column
            }
        }
        flexColumn {
            css {
                width = 50.pct

                maxWidth(768) {
                width = 100.pct
                }
            }
            styledDiv {
                css {
                    width = 100.pct
                    height = 10.rem
                    backgroundColor = Color.kodein.kaumon
                }
            }

            child(Member) {
                attrs {
                    name = "Salomon Brys"
                    position = "Founder"
                    bio = "bio"
                    socialMediaList = listOf(
                        Twitter("salomonbrys"),
                        LinkedIn("salomonbrys"),
                        Github("SalomonBrys")
                    )
                }
            }
        }

        flexColumn {
            css {
                width = 50.pct
                maxWidth(768) {
                    width = 100.pct
                }
            }
            styledDiv {
                css {
                    width = 100.pct
                    height = 10.rem
                    backgroundColor = Color.kodein.kamethiste
                }
            }

            child(Member) {
                attrs {
                    name = "Romain Boisselle"
                    position = "Founder"
                    bio = "bio"
                    socialMediaList = listOf(
                        Twitter("romainbsl"),
                        LinkedIn("romainbsl"),
                        Github("romainbsl")
                    )
                }
            }
        }
    }
}

interface MemberProps : RProps {
    var name: String
    var position: String
    var bio: String
    var socialMediaList: List<SocialMedia>
}
private val Member = functionalComponent<MemberProps>("Member") { props ->
    flexColumn {
        css {
            +kodein.body
            padding(2.rem)
        }
        flexRow(justifyContent = JustifyContent.start, alignItems = Align.center) {
            styledP {
                css {
                    color = Color.kodein.dark
                    fontWeight = FontWeight.semiBold
                }
                +props.name
            }
            styledDiv {
                css {
                    width = 1.rem
                    backgroundColor = Color.kodein.dark
                    height = 0.15.rem
                    margin(horizontal = .5.rem)
                }
            }
            styledP {
                css { color = Color.kodein.korail }
                +props.position
            }
        }

        styledP {
            css {
                color = Color.kodein.dark
                textAlign = TextAlign.start
                margin(top = .5.rem, bottom = 2.rem)
            }
            +props.bio
        }

        flexRow {
            styledP {
                css {
                    +kodein.link
                    color = Color.kodein.dark
                    fontWeight = FontWeight.semiBold
                }
                if (props.socialMediaList.isNotEmpty()) {
                    +"Nerd on "
                    props.socialMediaList.forEachIndexed { index, media ->
                        a(href = "${media.url.removeSuffix("/")}/${media.handler}", target = "_blank") {
                                +media.service.capitalize()
                        }
                        if (index < props.socialMediaList.lastIndex) +", "
                    }
                }
            }
        }
    }
}
