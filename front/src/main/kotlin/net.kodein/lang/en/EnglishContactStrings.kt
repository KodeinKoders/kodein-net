package net.kodein.lang.en

import kotlinx.css.Color
import kotlinx.css.color
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.strings.ContactFormStrings
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.contact.ContactStrings
import net.kodein.pages.home.HomeStrings
import react.dom.a
import react.dom.br
import react.dom.span
import styled.styledA

object EnglishContactStrings : ContactStrings {

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"let's keep in touch!" }
        override val title: TextHandler = {
            +"Need some help on a project / a training?"
            br {}
            +"Just send us a message :-)"
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

    override val form: ContactFormStrings = object : ContactFormStrings {
        override val title: String = "Contact us"
        override val from: Pair<String, String> = "From:" to "Your e-mail*"
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
            +"You're message could not be sent :("
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
        override val invalidForm: String = "Invalid form"
    }

}