package net.kodein

import net.kodein.lang.Strings
import react.RBuilder
import react.createContext
import react.useContext


data class PageData(
    val pageId: String,
    val language: Language
)

val pageDataContext = createContext<PageData>()

fun useText() : Strings {
    return useContext(pageDataContext).language.strings
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
