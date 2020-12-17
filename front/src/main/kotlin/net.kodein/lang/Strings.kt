package net.kodein.lang

import net.kodein.components.strings.MenuStrings
import net.kodein.pages.blog.BlogStrings
import net.kodein.pages.contact.ContactStrings
import net.kodein.pages.home.HomeStrings
import net.kodein.pages.training.TrainingStrings


interface Strings {
    val menu: MenuStrings
    val home: HomeStrings
    val training: TrainingStrings
    val blog: BlogStrings
    val contact: ContactStrings
}
