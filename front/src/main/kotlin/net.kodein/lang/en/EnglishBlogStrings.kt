package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.blog.BlogStrings
import react.dom.a
import react.dom.br
import react.dom.span

object EnglishBlogStrings : BlogStrings {

    override val pageTitle = "Blog"

    override val pageDescription = "Articles & videos that we posted."

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"sharing is caring" }
        override val title: TextHandler = {
            +"We are technology lovers,"
            br {}
            +"passionates & experts."
        }
        override val chapo: TextHandler = {
            +"Our mission is to spread our passion for multiplatform development. "

            br {}
            br {}

            +"""When we acquire new experience, discover new cool stuff,
                |create new piece of tech, or simply want to share our passion,
                |we write an article """.trimMargin()
            span("nowrap") { +"or shoot a video." }

            br {}

            +"Have a look!"
        }
    }

    override val wantMore: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Want more?" }
        override val title: TextHandler = {
            +"Check our "
            a(href = "https://medium.com/kodein-koders", target = "_blank") { attrs.rel = "noopener" ; +"Medium page" }
            +"."
        }
        override val chapo: TextHandler = {
            +"Stay close through our "
            span("nowrap") { +"social media accounts." }
            br {}
            +"Follow us on "
            span("nowrap") {
                a(href = "https://twitter.com/KodeinKoders") { +"Twitter" }
                +" & "
                a(href = "https://www.linkedin.com/company/kodein") { +"LinkedIn" }
                +"!"
            }
        }
    }

    override val errorLoadingFeed: String = "There was an error loading the feed :-("


}