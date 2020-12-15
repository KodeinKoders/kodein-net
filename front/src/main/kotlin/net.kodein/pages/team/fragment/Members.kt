package net.kodein.pages.team.fragment

import kotlinx.browser.window
import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.components.ContactUsProps
import net.kodein.utils.*
import net.kodein.withBasePath
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import react.dom.a
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP

val Members = functionalComponent<ContactUsProps>("Members") {
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
    val div = useRef<HTMLDivElement?>(null)

    var image: String? by useState(null)

    useEffectWithCleanup(emptyList()) {
        val onResize: ((Event?) -> Unit) = {
            val divWidth = div.current!!.clientWidth
            val imgWidth = illustrationWidths.firstOrNull { it >= (divWidth * 1.2) } ?: illustrationWidths.last()
            image = "${props.photo}_${imgWidth}"
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    flexColumn {
        ref = div

        withBasePath { path ->
            picture {
                source("image/webp", "$path/imgs/team/$image.webp" to null)
                source("image/jpeg", "$path/imgs/team/$image.jpg" to null)

                styledImg(alt = image, src = "$path/imgs/team/$image.jpg") {
                    css {
                        width = 100.pct
                        objectFit = ObjectFit.cover
                        objectPosition = "top"
                        minHeight = 20.rem
                        maxHeight = 40.rem

                        maxSizeStrict(980) { height = 20.rem }
                    }
                }
            }
        }

        flexColumn {
            css {
                +kodein.body
                padding(vertical = 2.rem, horizontal = 1.rem)
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
}
