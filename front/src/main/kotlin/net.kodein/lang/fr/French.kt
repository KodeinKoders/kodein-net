package net.kodein.lang.fr

import net.kodein.components.strings.MenuStrings
import net.kodein.lang.Strings
import net.kodein.pages.contact.ContactStrings

object French : Strings {
    override val menu: MenuStrings = FrenchMenuStrings
    override val home = FrenchHomeStrings
    override val contact: ContactStrings = FrenchContactStrings
    override val training = FrenchTrainingStrings
}
