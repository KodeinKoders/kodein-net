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
                maxWidth(768) { width = 100.pct }
            }
            child(Member) {
                attrs {
                    name = "Salomon Brys"
                    photo = "salomon"
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
                maxWidth(768) { width = 100.pct }
            }

            child(Member) {
                attrs {
                    name = "Romain Boisselle"
                    photo = "romain"
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
    var photo: String
    var position: String
    var bio: String
    var socialMediaList: List<SocialMedia>
}
private val Member = functionalComponent<MemberProps>("Member") { props ->
    styledImg(src = "imgs/team/${props.photo}.jpg") {
        css {
            width = 100.pct
            objectFit = ObjectFit.cover
            objectPosition = "top"
            minHeight = 20.rem
            maxHeight = 40.rem

            maxSizeStrict(980) { height = 20.rem }
        }
    }

    flexColumn {
        css {
            +kodein.body
            padding(vertical = 2.rem)
            maxSize(980) { padding(horizontal = 1.rem) }
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
