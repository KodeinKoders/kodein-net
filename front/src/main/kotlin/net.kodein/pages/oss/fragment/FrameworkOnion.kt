package net.kodein.pages.oss.fragment

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.DIV
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLParagraphElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import styled.*
import kotlin.math.PI
import kotlin.math.tan


private fun RBuilder.pie(diameter: LinearDimension, delta: LinearDimension, inside: StyledDOMBuilder<DIV>.() -> Unit) {
    styledDiv {
        css {
            position = Position.absolute
            width = diameter
            height = diameter
            left = delta
            top = delta
            borderRadius = diameter
            boxShadow(Color.black.withAlpha(0.2), blurRadius = 0.5.rem)
        }

        styledDiv {
            css {
                width = 100.pct
                height = 100.pct
                put("clip-path", "circle()")
            }

            inside()
        }
    }
}

private interface SliceProps : RProps {
    var ratio: Double
    var pos: Int
    var onEnter: () -> Unit
}

private val Slice = functionalComponent<SliceProps> { props ->
    val div = useRef<HTMLDivElement?>(null)

    useEffectWithCleanup(emptyList()) {
        val callback: (Event?) -> Unit = {
            props.onEnter()
        }

        div.current!!.addEventListener("mouseenter", callback)

        ({ div.current!!.removeEventListener("mouseenter", callback) })
    }

    styledDiv {
        ref = div
        css {
            position = Position.absolute
            width = 100.pct
            height = 100.pct
            left = 0.rem
            top = 0.rem
            textAlign = TextAlign.center
            cursor = Cursor.pointer
            transition(::backgroundColor, 1.s)
            hover { backgroundColor = Color.kodein.kyzantium }

            if (props.ratio < 1.0) {
                val angle = 2 * PI * props.ratio
                when (angle) {
                    in ((0 * PI / 2))..(1 * PI / 2) -> {
                        val hw = tan(angle / 2)
                        val l = 50.0 - hw * 50.0
                        val r = 50.0 + hw * 50.0
                        put("clip-path", "polygon(50% 50%, $l% 0%, $r% 0%)")
                    }
                    in (1 * PI / 2)..(2 * PI / 2) -> {
                        val vw = tan(PI / 2 - angle / 2)
                        val v = 50.0 - vw * 50.0
                        put("clip-path", "polygon(50% 50%, 0% $v%, 0% 0%, 100% 0%, 100% $v%)")
                    }
                    in (2 * PI / 2)..(3 * PI / 2) -> {
                        val vw = tan(angle / 2 - PI / 2)
                        val v = 50.0 + vw * 50.0
                        put("clip-path", "polygon(50% 50%, 0% $v%, 0% 0%, 100% 0%, 100% $v%)")
                    }
                    in (3 * PI / 2)..(4 * PI / 2) -> {
                        val vw = tan(PI - angle / 2)
                        val l = 50.0 - vw * 50.0
                        val r = 50.0 + vw * 50.0
                        put("clip-path", "polygon(50% 50%, $l% 100%, 0% 100%, 0% 0%, 100% 0%, 100% 100%, $r% 100%)")
                    }
                }
                put("transform-origin", "50% 50%")
                transform { rotate(((angle / 2) * (props.pos * 2 + 1 )).rad) }
            }
        }

        props.children()
    }
}

private fun RBuilder.simpleText(txt: String, reverse: Boolean = false) {
    styledSpan {
        css {
            display = Display.inlineBlock
            padding(top = 1.4.rem, bottom = 1.1.rem)
            if (reverse) transform { rotate(180.deg) }
        }
        +txt
    }

}

private fun RBuilder.slice(ratio: Double, pos: Int, onEnter: () -> Unit, inside: RBuilder.() -> Unit) {
    child(Slice) {
        attrs.ratio = ratio
        attrs.pos = pos
        attrs.onEnter = onEnter

        inside()
    }
}

private fun RBuilder.line(ratio: Double) {
    styledSpan {
        css {
            position = Position.absolute
            width = 2.px
            height = 50.pct
            left = 50.pct - 1.px
            top = 0.pct
            backgroundColor = Color.kodein.orange
            put("transform-origin", "50% 100%")
            transform { rotate((2 * PI * ratio).rad) }
        }
    }
}


private val texts = mapOf(
    "android" to (
            "Android" to
                    """
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Donec sed ex sit amet magna dictum consectetur sed sed ipsum.
                        Suspendisse ultrices ante imperdiet scelerisque mattis.
                        In eu nisl id velit imperdiet tincidunt at et ex.
                        Phasellus ut purus consequat, fringilla urna ac, fringilla justo.
                        Suspendisse blandit est ut accumsan pulvinar.
                        Sed suscipit leo non neque placerat, a rhoncus dolor maximus.
                        Aliquam ut tristique magna.
                        Suspendisse vehicula orci ut lorem condimentum pellentesque.     
                    """.trimIndent()
            ),

    "desktop" to (
            "Desktop" to
                    """
                        Sed dui tellus, eleifend et nulla quis, imperdiet varius velit.
                        Donec malesuada diam vitae tortor ornare malesuada.
                        Vivamus ligula justo, sagittis id arcu a, tempus convallis magna.
                        In ac eleifend lectus.
                        Aliquam finibus orci sit amet leo venenatis consectetur.
                        Aliquam nec turpis nibh.
                        Aliquam porttitor interdum hendrerit.
                        Donec tristique nisl at nunc suscipit, sed gravida magna sollicitudin.
                        Sed a ligula feugiat, mollis odio sed, vestibulum nisl.
                        Quisque a odio quis neque scelerisque sodales.
                        Duis semper et urna id maximus.
                        Donec nulla dui, maximus sed pharetra vel, sagittis sed tortor.
                        Nullam nec tellus sit amet purus semper pretium.
                        Vivamus sit amet sapien vel tortor vestibulum rutrum vel a nibh.
                        Proin sed nibh sed purus pretium molestie vel euismod nunc.
                    """.trimIndent()
            ),

    "ios" to (
            "iOS" to
                    """
                        Sed eget nisl a quam elementum consectetur.
                        Aenean sed porttitor nisl.
                        Etiam tempor suscipit nibh.
                        Ut ultrices eget odio ac eleifend.
                        Ut pharetra lobortis magna id ultricies.
                        Aliquam vitae nisi placerat, pulvinar dui non, congue metus.
                        Sed id magna vestibulum, posuere sapien euismod, tempus mi.
                    """.trimIndent()
            ),

    "kotlin-native" to (
            "Kotlin/Native" to
                    """
                        Donec nec lectus dui.
                        Nullam eu pharetra tellus, sit amet rhoncus odio.
                        Duis a felis rutrum, varius eros in, tincidunt magna.
                        Quisque eu varius est, eget varius ex.
                        Nulla placerat eu enim sed semper.
                        Praesent commodo rutrum erat, pretium dictum diam tempus vel.
                        Quisque sit amet porta libero.
                        Suspendisse placerat vitae tortor id tempor.
                        Mauris non interdum odio.
                        Cras a porta dolor.
                        Aliquam erat volutpat.
                        Proin at urna varius, tincidunt tortor ut, ornare turpis.
                        Proin id arcu nisl.
                        Maecenas elit urna, scelerisque eu neque vel, auctor varius risus.
                    """.trimIndent()
            ),

    "kotlin-js" to (
            "Kotlin/JS" to
                    """
                        Suspendisse pharetra lacus sed est feugiat dictum id condimentum quam.
                        Ut pulvinar ac enim ut ultricies.
                        Nam maximus lacinia condimentum.
                        Nunc tincidunt velit vitae justo ultricies tincidunt.
                        Aenean lacus nisi, commodo eu fermentum non, tincidunt sollicitudin mauris.
                        Praesent vel feugiat neque.
                        Morbi ornare aliquam dignissim.
                        Quisque ut metus arcu.
                        Pellentesque nec erat mollis, lacinia elit non, aliquam mi.
                        Nam egestas pharetra ante ullamcorper rutrum.
                        Sed ex lectus, feugiat eget nulla quis, consequat pretium nisi.
                    """.trimIndent()
            ),

    "kotlin-jvm" to (
            "Kotlin/JVM" to
                    """
                        Morbi a diam nec massa tempor pretium.
                        Suspendisse feugiat turpis ante, id maximus ex consectetur non.
                        Maecenas posuere sem odio.
                        Nulla facilisi.
                        Donec sit amet orci ac dui imperdiet aliquet.
                        Nullam lectus libero, sollicitudin nec felis id, tincidunt posuere nisl.
                        In bibendum ex nec pulvinar vestibulum.
                        In facilisis lorem aliquet purus porta, sed pulvinar massa varius.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce sit amet leo magna.
                        Sed lacinia lacus quis quam laoreet, quis egestas nisl suscipit.
                        In ac suscipit est.
                        Integer mattis, dui aliquet porttitor maximus, ipsum est faucibus ante, vitae imperdiet magna quam vel neque.
                        Vivamus bibendum augue a mi venenatis fermentum id id purus.
                        Vestibulum egestas elit ac dolor facilisis, vestibulum fermentum eros suscipit.
                    """.trimIndent()
            ),

    "kotlinx" to (
            "Kotlin & KotlinX" to
                    """
                        Ut libero magna, facilisis nec pulvinar eu, interdum a diam.
                        Donec aliquam leo sit amet purus posuere, nec accumsan felis efficitur.
                        Nunc bibendum convallis suscipit.
                        Sed libero massa, pellentesque sit amet erat quis, auctor bibendum dui.
                        Integer ultricies ultrices felis, laoreet posuere elit tempor at.
                        Morbi blandit cursus sapien ut efficitur.
                        Vivamus sollicitudin ultrices neque, egestas gravida nisi malesuada et.
                        Mauris id velit porta, semper enim ut, semper urna.
                        Ut rhoncus congue metus a malesuada.
                        Nam vitae leo lectus.
                        Nunc aliquam velit enim, non placerat dui gravida nec.
                    """.trimIndent()
            ),

    "kodein" to (
            "Kodein framework" to
                    """
                        Donec at ex tincidunt, iaculis nibh id, tincidunt nibh.
                        Mauris cursus ac arcu eu rutrum.
                        Morbi in dolor at augue vehicula aliquam eget id nunc.
                        Nunc tincidunt, urna ut porttitor commodo, ante elit volutpat metus, quis elementum mauris dui id est.
                        Proin at congue orci.
                        Mauris venenatis consectetur enim non dignissim.
                        Nunc sodales, dolor ultrices convallis fermentum, lorem dui consectetur ipsum, at lobortis neque orci at odio.
                        Donec vitae scelerisque orci.
                        Aliquam sed lorem elit.
                        Aliquam erat volutpat.
                        Duis nec arcu ac orci imperdiet dapibus et et eros.
                        Cras ut libero nibh.
                    """.trimIndent()
            ),

    "web" to (
            "Web" to
                    """
                        In id orci mollis, dignissim purus sit amet, placerat neque.
                        Fusce iaculis mi nibh, et posuere mauris elementum non.
                        Donec porta feugiat enim.
                        Curabitur eu libero sit amet diam tristique efficitur eget id lectus.
                        Nam libero nibh, fringilla sit amet orci non, tincidunt tempor mi.
                        Fusce iaculis neque quis magna tincidunt, nec sollicitudin nunc suscipit.
                        Suspendisse laoreet vel risus in facilisis.
                        Mauris faucibus massa a mauris congue, sit amet iaculis tortor dignissim.
                        Nunc molestie posuere rutrum.
                        Phasellus ut felis nec felis consequat venenatis.
                        Sed non porttitor turpis.
                        Integer sagittis sagittis justo.
                        Curabitur non velit tortor.
                        Integer tempor diam nisl, nec fringilla metus dictum rhoncus.
                        Nulla in viverra sem, in tincidunt odio.
                        Pellentesque sagittis augue erat, vitae faucibus mi pharetra ut.
                    """.trimIndent()
            ),
)


val FrameworkOnion = functionalComponent<RProps>("FrameworkLayers") {
    var selected by useState<String?>(null)

    flexColumn {
        css {
            paddingTop = 10.rem
            background = "linear-gradient(180deg, ${Color.kodein.korail} 75%, ${Color.kodein.dark} 100%)"
        }

        styledH2 {
            css {
                +kodein.intertitre
                specific {
                    fontWeight = FontWeight.ultraLight
                    textAlign = TextAlign.center

                    maxSize(480) {
                        fontSize = 1.8.rem
                    }
                    maxSize(400) {
                        fontSize = 1.6.rem
                    }
                }
                color = Color.kodein.cute
                margin(1.rem, 2.rem)
            }
            +"The "
            styledB {
                css.fontWeight = FontWeight.semiBold
                +"KODEIN"
            }
            +"Framework"
            br {}
            +"empowers multiplatform applications."
        }

        styledSpan {
            css {
                display = Display.block
                width = 0.05.rem
                height = 3.rem
                opacity = .7
                backgroundColor = Color.kodein.cute
                margin(1.rem, LinearDimension.auto)
                maxSize(768) {
                    margin(0.rem, LinearDimension.auto)
                }
                landscapeMobile {
                    margin(1.rem, LinearDimension.auto)
                }
            }
        }

        flexRow(JustifyContent.center, Align.flexStart) {
            css {
                margin(vertical = 5.rem)
            }

            styledDiv {
                css {
                    position = Position.relative
                    width = 34.rem
                    height = 34.rem
                }

                pie(34.rem, 0.rem) {
                    css {
                        backgroundColor = Color.kodein.cute
                        color = Color.kodein.orange
                    }

                    slice(1.0 / 3.0, 0, { selected = "web" }) { simpleText("Web") }
                    slice(1.0 / 3.0, 1, { selected = "ios" }) { simpleText("iOS", reverse = true) }
                    slice(1.0 / 6.0, 4, { selected = "desktop" }) { simpleText("Desktop") }
                    slice(1.0 / 6.0, 5, { selected = "android" }) { simpleText("Android") }

                    line(0.0)
                    line(1.0 / 3.0)
                    line(2.0 / 3.0)
                    line(5.0 / 6.0)
                }

                pie(27.rem, 3.5.rem) {
                    css {
                        backgroundColor = Color.kodein.kaumon
                        color = Color.kodein.orange
                    }

                    slice(1.0 / 3.0, 0, { selected = "kotlin-js" }) { simpleText("Kotlin/JS") }
                    slice(1.0 / 3.0, 1, { selected = "kotlin-native" }) { simpleText("Kotlin/Native", reverse = true) }
                    slice(1.0 / 3.0, 2, { selected = "kotlin-jvm" }) { simpleText("Kotlin/JVM") }

                    line(0.0)
                    line(1.0 / 3.0)
                    line(2.0 / 3.0)
                }

                pie(20.rem, 7.rem) {
                    css {
                        backgroundColor = Color.kodein.korail
                        color = Color.kodein.cute
                    }

                    slice(1.0, 0, { selected = "kotlinx" }) { simpleText("Kotlin[X]") }
                }

                pie(13.rem, 10.5.rem) {
                    css {
                        backgroundColor = Color.kodein.orange
                        color = Color.kodein.cute
                    }

                    slice(1.0, 0, { selected = "kodein" }) {
                        flexColumn(JustifyContent.center, Align.center) {
                            css {
                                width = 100.pct
                                height = 100.pct
                            }
                            styledSpan {
                                css {
                                    fontWeight = FontWeight.ultraLight
                                    fontFamily = KodeinStyles.piconExtended
                                }
                                styledB {
                                    css.fontWeight = FontWeight.semiBold
                                    +"KODEIN"
                                }
                                +"Framework"
                            }
                        }
                    }
                }
            }

            styledDiv {
                css {
                    width = 25.rem
                    paddingLeft = 4.rem
                    paddingTop = 6.rem
                }

                styledH3 {
                    css {
                        +KodeinStyles.intertitre
                        paddingBottom = 1.25.rem
                        color = Color.kodein.dark
                    }
                    if (selected != null) +texts[selected!!]!!.first
                }

                styledP {
                    css {
                        +KodeinStyles.body
                        color = Color.kodein.kinzolin
                    }

                    if (selected != null) +texts[selected!!]!!.second
                }
            }
        }
    }
}
