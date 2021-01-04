package net.kodein.pages.services

import net.kodein.PageStrings
import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.JobDescription
import net.kodein.pages.team.MemberStrings
import net.kodein.utils.Github
import net.kodein.utils.LinkedIn
import net.kodein.utils.SocialMedia
import net.kodein.utils.Twitter


interface ServicesStrings : PageStrings {
    val cover: CoverStrings
    val consultancy: ServiceDescription
    val projectDevelopment: ServiceDescription
}

data class ServiceDescription(val title: String, val content: TextHandler)
