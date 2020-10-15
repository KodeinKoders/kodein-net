import fs.writeFileSync
import kotlinx.css.CSSBuilder
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import net.kodein.Page
import net.kodein.appGlobalStyle
import net.kodein.appPages
import react.RProps
import react.buildElement
import react.child
import react_dom.server.renderToString
import styled_components.ServerStyleSheet


enum class Mode {
    BARE,
    SSR
}

fun <P: RProps> Page<P>.getHtml(mode: Mode): String {
    val (html, css) = when (mode) {
        Mode.BARE -> "" to ""
        Mode.SSR -> {
            val sheet = ServerStyleSheet()
            val html = renderToString(sheet.collectStyles(buildElement {
                child(component()) {
                    props(attrs)
                    attrs.asDynamic().isSSR = true
                }
            }))
            val css = "<style>${CSSBuilder().apply(appGlobalStyle)}</style>\n" + sheet.getStyleTags()
            html to css
        }
    }

    return createHTML().html {
        head {
            meta("charset", "UTF-8")
            meta("viewport", "minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no")
            meta("description", "Kodein Koders: Kotlin/Multiplatform expertise")

            link(rel = "preload", href = "picon/LCTPicon/Hairline/LCTPicon-Hairline.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/Light/LCTPicon-Light.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/Regular/LCTPicon-Regular.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/Medium/LCTPicon-Medium.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/Semi-bold/LCTPicon-Semi-bold.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/Bold/LCTPicon-Bold.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/ExtendedHairline/LCTPicon-ExtendedHairline.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/ExtendedRegular/LCTPicon-ExtendedRegular.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "picon/LCTPicon/ExtendedMedium/LCTPicon-ExtendedMedium.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }

            link(href = "picon/LCTPicon.css", rel = "stylesheet")
            link(href = "favicon.png", rel = "icon", type = "image/png")

            title("Kodein Koders")

            unsafe { raw(css) }
        }

        body {
            style = "margin: 0; padding: 0;"

            div {
                id = "page"
                attributes["data-rendering-mode"] = mode.name.toLowerCase()
                attributes["data-page"] = this@getHtml.id
                unsafe { raw(html) }
            }

            script(type = "text/javascript", src = "kodein-net.js") {}
        }
    }
}

fun main() {
    if (process.argv.size != 4) error("Need 2 arguments: [bare|ssr] path")
    val path = process.argv[2]

    val mode = when (process.argv[3]) {
        "bare" -> Mode.BARE
        "ssr" -> Mode.SSR
        else -> error("Unknown mode ${process.argv[3]}")
    }

    appPages.forEach { page ->
        writeFileSync(
                path = "$path/${page.id}.html",
                data = page.getHtml(mode),
                options = null as String?
        )
    }

}
