package net.kodein.lang.en

import kotlinx.css.*
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.strings.ContactFormStrings
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.contact.ContactStrings
import net.kodein.pages.home.HomeStrings
import net.kodein.utils.landscapeMobile
import net.kodein.utils.maxSize
import react.dom.a
import react.dom.br
import react.dom.span
import styled.css
import styled.styledA
import styled.styledSpan

object EnglishContactStrings : ContactStrings {

    override val pageTitle: String = "Contact"

    override val pageDescription = "Contact us!"

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"let's keep in touch" }
        override val title: TextHandler = {
            +"Tell us about "
            span("nowrap") { +"your projects!" }
        }
        override val chapo: TextHandler = {
            +"...or just have a chat, "
            span("nowrap") { +"we are here to listen :-)" }

            br {}
            br {}

            +"Also, stay close through "
            span("nowrap") { +"our social media accounts." }
            br {}
            +"""Follow us on """
            span("nowrap") {
                a(href = "https://twitter.com/KodeinKoders") { +"Twitter" }
                +" & "
                a(href = "https://www.linkedin.com/company/kodein") { +"LinkedIn" }
                +"!"
            }
        }
    }

    override val form: ContactFormStrings = object : ContactFormStrings {
        override val title: String = "Contact us"
        override val from: Pair<String, String> = "From:" to "Your e-mail.*"
        override val subject: Pair<String, String> = "Object:" to "What's on your mind?*"
        override val message: Pair<String, String> = "Message:" to "Tell us a little more about your needs.*"
        override val send: String = "SEND"
        override val sending: String = "Sending..."
        override val messageSent: TextHandler = {
            +"Thanks for reaching out!"
            br {}
            +"We'll be in touch soon."
        }
        override val messageNotSent: TextHandler = {
            +"You're message could not be sent :-("
            br {}
            +"Reach us out at "
            styledA(href = "mailto:contact@kodein.net") {
                css.color = Color.kodein.purple
                +"contact@kodein.net"
            }
            +"!"
            br {}
            br {}
        }
        override val invalidForm: String = "Invalid form."
    }

}
