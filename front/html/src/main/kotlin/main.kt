import kotlinx.css.CSSBuilder
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import net.kodein.App
import net.kodein.appGlobalStyle
import react.buildElement
import react.child
import react_dom.server.renderToString
import styled_components.ServerStyleSheet


private enum class Mode {
    BARE,
    SSR
}

fun main() {
    val argv = js("process.argv") as Array<String>
    val mode = when {
        "bare" in argv -> Mode.BARE
        "ssr" in argv -> Mode.SSR
        else -> error("Please specify a mode")
    }

    val (html, css) = when (mode) {
        Mode.BARE -> "" to ""
        Mode.SSR -> {
            val sheet = ServerStyleSheet()
            val html = renderToString(sheet.collectStyles(buildElement {
                child(App) { attrs.isStatic = true }
            }))
            val css = sheet.getStyleTags()
            html to css
        }
    }

    val htmlElement = createHTML().html {
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

            style {
                unsafe {
                    raw(
                            "\n" +
                            CSSBuilder()
                                    .apply(appGlobalStyle)
                                    .toString() +
                            "\n"
                    )
                }
            }

            unsafe { raw(css) }
        }

        body {
            style = "margin: 0; padding: 0;"

            div {
                id = "app"
                attributes["data-mode"] = mode.name.toLowerCase()
                unsafe { raw(html) }
            }

            script(type = "text/javascript", src = "kodein-net.js") {}
        }
    }

    println(htmlElement)

}
