package net.kodein.pages.blog.fragment

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.classes
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import react.*
import styled.*
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

private external class Entry : RProps {
    val title: String
    val image: String
    val imageScale: Double?
    val url: String
}

const val revealDurationByRow = 600

val EntryList = functionalComponent<RProps>("ElementList") {

    val div = useRef<HTMLDivElement?>(null)

    var entries: List<Entry>? by useState(null)

    var entriesByRow by useState(0)
    var entryWidth by useState(0.0)
    var entryHeight by useState(0.0)

    var animHeight by useState(true)
    var heightTransitionTime: Time? by useState(null)

    var centerText by useState("Loading...")

    useEffect(emptyList()) {
        window.fetch("blog.json")
            .then { response ->
                if (response.ok) {
                    response.json().then {
                        val list = (it.unsafeCast<Array<Entry>>()).toList()
                        entries = list
                    }
                } else {
                    console.log(response)
                    centerText = "There was an error loading the feed :("
                }
            }
            .catch {
                it.printStackTrace()
                centerText = "There was an error loading the feed :("
            }
    }

    useEffectWithCleanup(emptyList()) {
        val onResize: (Event?) -> Unit = {
            val ebr = max(min((document.body!!.clientWidth / 400), 5), 1)
            val w = document.body!!.clientWidth.toDouble() / ebr
            entriesByRow = ebr
            entryWidth = w
            entryHeight = w / 4 * 3
        }
        window.addEventListener("resize", onResize)
        onResize(null)
        ({ window.removeEventListener("resize", onResize) })
    }

    useEffect(listOf(entries, entryHeight, animHeight)) effect@ {
        val list = entries ?: return@effect

        val rows = ceil(list.size.toDouble() / entriesByRow).toInt()
        if (animHeight) {
            val duration = revealDurationByRow * rows
            div.current!!.style.transition = "height ${duration}ms ease"
            window.setTimeout({ animHeight = false}, duration)
        } else {
            div.current!!.style.transition = ""
        }
        div.current!!.style.height = "${rows * entryHeight}px"
    }

    styledDiv {
        ref = div
        css {
            position = Position.relative
            backgroundColor = Color.kodein.dark
            height = 20.rem
            overflow = Overflow.hidden
        }

        styledH2 {
            css {
                position = Position.absolute
                width = 100.pct
                left = 0.rem
                top = 12.rem
                zIndex = 20
                color = Color.kodein.kuivre
                fontWeight = FontWeight.ultraLight
                fontSize = 1.8.rem
                textAlign = TextAlign.center
                if (entries != null) {
                    opacity = 0
                    pointerEvents = PointerEvents.none
                }
                transition(::opacity, 0.5.s)
            }
            +centerText
        }
        if (entries != null) {
            flexRow {
                css {
                    flexWrap = FlexWrap.wrap
                    alignContent = Align.baseline
                    minHeight = (entryHeight * 4).px
                }

                entries!!.forEachIndexed { i, e ->
                    child(EntryBlock) {
                        attrs {
                            index = i
                            entry = e
                            width = entryWidth
                            height = entryHeight
                            countByLine = entriesByRow
                        }
                    }
                }

            }
        }

        styledDiv {
            css {
                position = Position.absolute
                background = "linear-gradient(180deg, transparent 0%, ${Color.kodein.dark} 95%)"
                pointerEvents = PointerEvents.none
                width = 100.pct
                bottom = 0.rem
                height = (entryHeight * 2.5).px
                zIndex = 10
            }
        }
    }
}

private interface EntryBlockProps : RProps {
    var index: Int
    var entry: Entry
    var width: Double
    var height: Double
    var countByLine: Int
}

private val EntryBlock = functionalComponent<EntryBlockProps> { props ->

    var visible by useState(false)
    val box = useRef<HTMLElement?>(null)

    useEffect(emptyList()) {
        window.setTimeout({ visible = true }, props.index * (revealDurationByRow / props.countByLine))
    }

    useEffect(listOf(props.width, props.height)) {
        box.current!!.style.width = "${props.width}px"
        box.current!!.style.height = "${props.height}px"
    }

    styledA(href = props.entry.url, target = "_blank") {
        ref = box
        css {
            display = Display.block
            position = Position.relative

            overflow = Overflow.hidden
            backgroundColor = Color.kodein.cute
            cursor = Cursor.pointer

            media("(hover: hover)") {
                opacity = if (visible) 0.5 else 0.0
                transition(::opacity, 1.s)
                ".title" {
                    opacity = 0
                    transition(::opacity, 0.5.s)
                }
                hover {
                    opacity = 1.0
                    ".title" {
                        opacity = 1.0
                    }
                }
            }
        }
        styledImg(src = props.entry.image) {
            css {
                width = 100.pct
                height = 100.pct
                objectFit = ObjectFit.cover
                props.entry.imageScale?.let { transform { scale(it) } }
            }
        }
        flexColumn(JustifyContent.flexEnd, Align.center) {
            attrs.classes += "title"
            css {
                position = Position.absolute
                left = 0.pct
                right = 0.pct
                top = 0.pct
                bottom = 0.pct
                background = "linear-gradient(0deg, ${Color.kodein.dark.withAlpha(0.8)} 8%, transparent 48%)"
            }

            styledH2 {
                css {
                    margin(1.5.rem, 3.rem)
                    maxWidth(380) {
                        margin(1.rem)
                    }
                    fontFamily = KodeinStyles.piconExtended
                    fontWeight = FontWeight.normal
                    fontSize = 1.2.rem
                    color = Color.kodein.cute
                    textAlign = TextAlign.center
                }
                +props.entry.title
            }
        }
    }
}