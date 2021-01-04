package net.kodein.lang

import net.kodein.components.FooterStrings
import net.kodein.components.strings.GlobalStrings
import net.kodein.components.strings.MenuStrings
import net.kodein.pages.blog.BlogStrings
import net.kodein.pages.contact.ContactStrings
import net.kodein.pages.credits.CreditsStrings
import net.kodein.pages.home.HomeStrings
import net.kodein.pages.oss.OssStrings
import net.kodein.pages.services.ServicesStrings
import net.kodein.pages.team.TeamStrings
import net.kodein.pages.training.TrainingStrings


interface Strings {
    val blog: BlogStrings
    val contact: ContactStrings
    val credits: CreditsStrings
    val footer: FooterStrings
    val global: GlobalStrings
    val home: HomeStrings
    val menu: MenuStrings
    val oss: OssStrings
    val services: ServicesStrings
    val team: TeamStrings
    val training: TrainingStrings
}
