package net.kodein

import net.kodein.lang.Strings
import react.RBuilder
import react.createContext


data class PageData(
    val pageId: String,
    val language: Language
)

val pageDataContext = createContext<PageData>()

inline fun RBuilder.text(crossinline method: Strings.() -> TextHandler) {
    pageDataContext.Consumer { data ->
        val content = data.language.strings.method()
        content()
    }
}

val Language.basePath get() = if (path != null) ".." else "."

inline fun RBuilder.withBasePath(crossinline block: RBuilder.(String) -> Unit) {
    pageDataContext.Consumer { data ->
        block(data.language.basePath)
    }
}

inline fun RBuilder.withPageId(crossinline block: RBuilder.(String) -> Unit) {
    pageDataContext.Consumer { data ->
        block(data.pageId)
    }
}
