import fs.MakeDirectoryOptions
import fs.existsSync
import fs.mkdirSync
import fs.writeFileSync
import kotlinext.js.jsObject
import kotlinx.css.CSSBuilder
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import net.kodein.*
import net.kodein.charter.KodeinColors
import react.buildElement
import react.child
import react_dom.server.renderToString
import styled_components.ServerStyleSheet
import kotlin.js.Date


enum class Mode {
    BARE,
    SSR
}

private val PageStrings.fullTitle get() = if (pageTitle.isNotEmpty()) "Kodein Koders - $pageTitle" else "Kodein Koders"

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

    val strings = page.strings(lang.strings)

    return "<!DOCTYPE html>\n" + createHTML().html {
        this.lang = lang.id
        head {
            meta { charset = "UTF-8" }
            meta("viewport", "minimum-scale=1, initial-scale=1, width=device-width, shrink-to-fit=no")
            meta("description", strings.pageDescription)
            meta("author", "Kodein Koders")
            meta("copyright", "Kodein Koders")
            meta("language", lang.id)
            meta("robots", "index, follow")
            meta("category", "IT consulting services")
            meta("date-creation-ddmmyyyy",
                Date().let {
                    it.getDate().toString().padStart(2, '0') +
                    (it.getMonth() + 1).toString().padStart(2, '0') +
                    it.getFullYear().toString()
                }
            )
            meta("theme-color", KodeinColors.dark.toString())

            meta("og:url", "https://kodein.net/" + (lang.path?.let { "$it/" } ?: "") + "${page.id}.html")
            meta("og:site_name", "Kodein Koders")
            meta("og:title", strings.fullTitle)
            meta("og:description", strings.pageDescription)
            meta("og:image", "https://kodein.net/imgs/illus/${page.illus}_1200.jpg")

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

            title(strings.fullTitle)


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
