package net.kodein

import net.kodein.lang.Strings
import net.kodein.pages.blog.Blog
import net.kodein.pages.contact.Contact
import net.kodein.pages.credits.Credits
import net.kodein.pages.home.Home
import net.kodein.pages.oss.Oss
import net.kodein.pages.services.Services
import net.kodein.pages.team.Team
import net.kodein.pages.training.Training
import react.FunctionalComponent
import react.RProps


interface PageStrings {
    val pageTitle: String
}

data class Page(
    val id: String,
    val title: Strings.() -> PageStrings,
    val component: () -> FunctionalComponent<RProps>
)

val appPages = listOf(
    Page("index", Strings::home) { Home },
    Page("services", Strings::services) { Services },
    Page("training", Strings::training) { Training },
    Page("oss", Strings::oss) { Oss },
    Page("team", Strings::team) { Team },
    Page("blog", Strings::blog) { Blog },
    Page("contact", Strings::contact) { Contact },
    Page("credits", Strings::credits) { Credits },
)
