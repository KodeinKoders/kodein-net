package net.kodein.lang.en

import net.kodein.components.strings.MenuStrings
import net.kodein.lang.Strings
import net.kodein.pages.blog.BlogStrings
import net.kodein.pages.contact.ContactStrings

object English : Strings {
    override val menu: MenuStrings = EnglishMenuStrings
    override val home = EnglishHomeStrings
    override val training = EnglishTrainingStrings
    override val blog: BlogStrings = EnglishBlogStrings
    override val contact: ContactStrings = EnglishContactStrings
}
