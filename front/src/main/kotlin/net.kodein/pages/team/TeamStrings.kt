package net.kodein.pages.team

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.utils.Github
import net.kodein.utils.LinkedIn
import net.kodein.utils.SocialMedia
import net.kodein.utils.Twitter

interface TeamStrings {
    val cover: CoverStrings
    val salomon: MemberStrings
    val romain: MemberStrings
    val nerdOn: String
    val joinUs: String
    val jobs: List<JobDescription>
}

data class MemberStrings(
    val name: String,
    val photo: Pair<String, String>,
    val status: String = "",
    val bio: TextHandler = {},
    val socialMediaList: List<SocialMedia> = emptyList()
) {
    companion object {
        val salomon = MemberStrings(
            name = "Salomon Brys",
            photo = "salomon" to "top left",
            socialMediaList = listOf(
                Twitter("salomonbrys"),
                LinkedIn("salomonbrys"),
                Github("SalomonBrys")
            )
        )
        val romain = MemberStrings(
            name = "Romain Boisselle",
            photo = "romain" to "top left",
            socialMediaList = listOf(
                Twitter("romainbsl"),
                LinkedIn("romainbsl"),
                Github("romainbsl")
            )
        )
    }
}

data class JobDescription(val title: String, val content: TextHandler = {})