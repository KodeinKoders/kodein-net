package net.kodein.components.strings

import net.kodein.TextHandler
import org.w3c.dom.Text

interface ContactFormStrings {
    val title: String
    val from: Pair<String, String>
    val subject: Pair<String, String>
    val message: Pair<String, String>
    val send: String
    val sending: String
    val messageSent: TextHandler
    val messageNotSent: TextHandler
    val invalidForm: String
}