package net.kodein

import net.kodein.lang.Strings
import net.kodein.lang.en.English
import net.kodein.lang.fr.French
import react.RBuilder
import react.createContext

data class Language(
    val id: String,
    val path: String?,
    val strings: Strings
)

val appLanguages = listOf(
    Language("en", null, English),
    Language("fr", "fr", French)
)

typealias TextHandler = RBuilder.() -> Unit

