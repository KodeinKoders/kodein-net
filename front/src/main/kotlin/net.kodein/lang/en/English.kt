package net.kodein.lang.en

import net.kodein.components.FooterStrings
import net.kodein.lang.Strings
import net.kodein.pages.services.ServicesStrings
import net.kodein.pages.team.TeamStrings


object English : Strings {
    override val blog = EnglishBlogStrings
    override val contact = EnglishContactStrings
    override val home = EnglishHomeStrings
    override val menu = EnglishMenuStrings
    override val oss = EnglishOssStrings
    override val services: ServicesStrings = EnglishServicesStrings
    override val team: TeamStrings = EnglishTeamStrings
    override val training = EnglishTrainingStrings
    override val footer = EnglishFooterStrings
}
