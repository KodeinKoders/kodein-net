package net.kodein.lang.en

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.blog.BlogStrings
import react.dom.a
import react.dom.br

object EnglishBlogStrings : BlogStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"read & watch" }
        override val title: TextHandler = {
            +"We are technology lovers,"
            br {}
            +"passionates & experts."
        }
        override val chapo: TextHandler = {
            +"""Sharing is caring, which is why it is our mission to spread 
                    |our passion for multiplatform development. 
                    |When we acquire new experience, discover new cool stuff,
                    |create new piece of tech, or simply want to share our passion,
                    |we write an article or shoot a video.
                    |Have a look!""".trimMargin()
        }
    }

    override val wantMore: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Want more?" }
        override val title: TextHandler = {
            +"Check our "
            a(href = "https://medium.com/kodein-koders", target = "_blank") { +"Medium page" }
            +"."
        }
        override val chapo: TextHandler = {
            +"""Stay close through our social media accounts."""
            br {}
            +"""Follow us on """
            a(href = "https://twitter.com/KodeinKoders") { +"Twitter" }
            +" & "
            a(href = "https://www.linkedin.com/company/kodein") { +"LinkedIn" }
            +"!"
        }
    }

    override val errorLoadingFeed: String = "There was an error loading the feed :("


}