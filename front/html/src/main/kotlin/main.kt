import fs.MakeDirectoryOptions
import fs.existsSync
import fs.mkdirSync
import fs.writeFileSync
import kotlinext.js.jsObject
import kotlinx.css.CSSBuilder
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import net.kodein.*
import react.buildElement
import react.child
import react_dom.server.renderToString
import styled_components.ServerStyleSheet


enum class Mode {
    BARE,
    SSR
}

fun getHtml(page: Page, lang: Language, mode: Mode): String {
    val (html, css) = when (mode) {
        Mode.BARE -> "" to ""
        Mode.SSR -> {
            val sheet = ServerStyleSheet()
            val html = renderToString(sheet.collectStyles(buildElement {
                pageDataContext.Provider(PageData(page.id, lang)) {
                    child(page.component())
                }
            }))
            val css = "<style>${CSSBuilder().apply(appGlobalStyle)}</style>\n" + sheet.getStyleTags()
            html to css
        }
    }

    return createHTML().html {
        this.lang = lang.id
        head {
            meta("charset", "UTF-8")
            meta("viewport", "minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no")
            meta("description", "Kodein Koders: Kotlin/Multiplatform expertise")

            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/Hairline/LCTPicon-Hairline.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/Light/LCTPicon-Light.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/Regular/LCTPicon-Regular.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/Medium/LCTPicon-Medium.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/Semi-bold/LCTPicon-Semi-bold.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/Bold/LCTPicon-Bold.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/ExtendedHairline/LCTPicon-ExtendedHairline.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/ExtendedRegular/LCTPicon-ExtendedRegular.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }
            link(rel = "preload", href = "${lang.basePath}/picon/LCTPicon/ExtendedMedium/LCTPicon-ExtendedMedium.woff") { attributes["as"] = "font" ; attributes["crossorigin"] = "" }

            link(href = "${lang.basePath}/picon/LCTPicon.css", rel = "stylesheet")
            link(href = "${lang.basePath}/favicon.png", rel = "icon", type = "image/png")

            title("Kodein Koders")

            unsafe { raw(css) }
        }

        body {
            style = "margin: 0; padding: 0;"

            div {
                id = "page"
                attributes["data-rendering-mode"] = mode.name.toLowerCase()
                attributes["data-page"] = page.id
                attributes["data-lang"] = lang.id
                unsafe { raw(html) }
            }

            script(type = "text/javascript", src = "${lang.basePath}/kodein-net.js") {}
        }
    }
}

fun main() {
    if (process.argv.size != 4) error("Need 2 arguments: [bare|ssr] path")
    val basePath = process.argv[2]

    val mode = when (process.argv[3]) {
        "bare" -> Mode.BARE
        "ssr" -> Mode.SSR
        else -> error("Unknown mode ${process.argv[3]}")
    }

    appLanguages.forEach { lang ->

        val path = if (lang.path != null) "$basePath/${lang.path}" else basePath
        if (!existsSync(path))
            mkdirSync("$basePath/${lang.path}", jsObject<MakeDirectoryOptions> { this.recursive = true })

        appPages.forEach { page ->
            writeFileSync(
                    path = "$path/${page.id}.html",
                    data = getHtml(page, lang, mode),
                    options = null as String?
            )
        }
    }
}
