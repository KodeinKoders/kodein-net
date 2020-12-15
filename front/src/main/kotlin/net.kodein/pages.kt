package net.kodein

import net.kodein.pages.blog.Blog
import net.kodein.pages.contact.Contact
import net.kodein.pages.home.Home
import net.kodein.pages.oss.Oss
import net.kodein.pages.services.Services
import net.kodein.pages.team.Team
import net.kodein.pages.training.Training
import react.FunctionalComponent
import react.RProps


data class Page(
    val id: String,
    val component: () -> FunctionalComponent<RProps>
)

val appPages = listOf(
    Page("index") { Home },
    Page("services") { Services },
    Page("training") { Training },
    Page("oss") { Oss },
    Page("team") { Team },
    Page("blog") { Blog },
    Page("contact") { Contact },
)
