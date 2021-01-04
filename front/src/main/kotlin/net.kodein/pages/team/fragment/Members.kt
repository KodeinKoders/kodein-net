package net.kodein.pages.team.fragment

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.border
import net.kodein.charter.kodein
import net.kodein.components.ContactUsProps
import net.kodein.pages.team.MemberStrings
import net.kodein.useText
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
    val strings = useText().team

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
                attrs.member = strings.salomon
            }
        }

        flexColumn {
            css {
                width = 50.pct
                maxWidth(768) { width = 100.pct }
            }

            child(Member) {
                attrs.member = strings.romain
            }
        }
    }
}

interface MemberProps : RProps {
    var member: MemberStrings
}
private val Member = functionalComponent<MemberProps>("Member") { props ->
    val strings = useText().team

    val div = useRef<HTMLDivElement?>(null)
    var image: String? by useState(null)

    val teamWidths = listOf(960, 1200, 1440, 1680, 1920, 2400)

    useEffectWithCleanup(emptyList()) {
        val onResize: ((Event?) -> Unit) = {
            val divWidth = div.current!!.clientWidth
            val imgWidth = teamWidths.firstOrNull { it >= (divWidth * 1.2) } ?: teamWidths.last()
            image = "${props.member.photo.first}_${imgWidth}"
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    flexColumn {
        ref = div

        styledDiv {
            css {
                width = 100.pct
                height = 50.vw * 0.66
                minHeight = 20.rem
                maxHeight = 40.rem
                maxSizeStrict(980) { height = 20.rem }
            }
            withBasePath { path ->
                picture {
                    source("image/webp", "$path/imgs/team/$image.webp" to null)
                    source("image/jpeg", "$path/imgs/team/$image.jpg" to null)

                    styledImg(src = "$path/imgs/team/$image.jpg", alt = props.member.name) {
                        attrs {
                            width = "100%"
                            height = "100%"
                        }

                        css {
                            width = 100.pct
                            height = 100.pct
                            objectFit = ObjectFit.cover
                            objectPosition = props.member.photo.second
                        }
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
                    +props.member.name
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
                    +props.member.status
                }

            }

            styledP {
                css {
                    color = Color.kodein.dark
                    textAlign = TextAlign.start
                    margin(top = .5.rem, bottom = 2.rem)
                }
                props.member.bio(this)
            }

            flexRow {
                styledP {
                    css {
                        +kodein.link
                        color = Color.kodein.dark
                        fontWeight = FontWeight.semiBold
                    }
                    if (props.member.socialMediaList.isNotEmpty()) {
                        +strings.nerdOn
                        props.member.socialMediaList.forEachIndexed { index, media ->
                            a(href = "${media.url.removeSuffix("/")}/${media.handler}", target = "_blank") {
                                attrs.rel = "noopener"
                                +media.service.capitalize()
                            }
                            if (index < props.member.socialMediaList.lastIndex) +", "
                        }
                    }
                }
            }
        }
    }
}
