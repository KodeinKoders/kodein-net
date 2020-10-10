package net.kodein.components

import kotlinx.browser.window
import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.css.properties.lh
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import net.kodein.charter.KodeinColors
import net.kodein.charter.KodeinStyles
import net.kodein.charter.kodein
import net.kodein.utils.*
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.url.URLSearchParams
import org.w3c.fetch.RequestInit
import react.*
import react.dom.*
import styled.*
import kotlin.js.json


private val emailRegex = Regex(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
)

private sealed class FormStatus {
    object Ready : FormStatus()
    object Sending : FormStatus()
    data class Sent(val error: String?): FormStatus()
}

val ContactUs by functionalComponent {

    var status: FormStatus by useState(FormStatus.Ready)

    styledDiv {
        css {
            margin(5.rem, LinearDimension.auto)
            maxWidth = 50.rem
        }

        styledH1 {
            css {
                +kodein.display2
                textAlign = TextAlign.start
                color = Color.kodein.orange
                fontWeight = FontWeight.hairline
            }

            +"Contact us"
        }

        when (val s = status) {
            FormStatus.Ready -> child(ContactForm) {
                attrs.onSubmitFunction = { from, subject, message ->
                    status = FormStatus.Sending
                    sendContactForm(from, subject, message) {
                        status = FormStatus.Sent(it)
                    }
                }
            }
            FormStatus.Sending -> child(ContactMessage) {
                attrs.isError = false
                +"Sending..."
            }
            is FormStatus.Sent -> child(ContactMessage) {
                attrs.isError = s.error != null
                if (s.error != null) {
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
                    styledSpan {
                        css.fontSize = 1.rem
                        +s.error
                    }

                }
                else {
                    +"Thanks for reaching out!"
                    br {}
                    +"We'll be in touch soon."
                }
            }
        }

    }
}

interface ContactFormProps : RProps {
    var onSubmitFunction: (from: String, subject: String, message: String) -> Unit
}

private val ContactForm by functionalComponent<ContactFormProps> { props ->
    var from: String by useState("")
    var subject: String by useState("")
    var message: String by useState("")

    var bads: List<String> by useState(emptyList())

    styledDiv {
        css {
            height = 25.rem
            "p.input" {
                display = Display.flex
                flexDirection = FlexDirection.row
                fontSize = 1.2.rem
                fontWeight = FontWeight.regular
                borderBottom(0.1.rem, BorderStyle.solid, Color.kodein.cute)
                padding(3.rem, 0.1.rem, 0.4.rem, 0.1.rem)

                "span" {
                    fontFamily = KodeinStyles.piconExtended
                    color = KodeinColors.kyzantium
                    width = 7.rem
                }

                "input, textarea" {
                    flexGrow = 1.0
                    border = "none"
                    fontFamily = kodein.picon
                    fontSize = 1.2.rem

                    placeholder {
                        color = Color.kodein.kaumon
                        fontWeight = FontWeight.light
                    }

                    focus {
                        outline = Outline.none
                    }
                }

                "textarea" {
                    resize = Resize.none
                    height = 5.25.em // not rem!
                    lineHeight = 1.75.em.lh
                    marginTop = (-0.25).rem
                }
            }
        }

        p("input") {
            styledSpan {
                if ("from" in bads) css.specific(3) { color = Color.red }
                +"From:"
            }
            input(InputType.email, name = "email") {
                attrs.placeholder = "Your e-mail*"
                attrs.onChangeFunction = {
                    val newValue = (it.target as HTMLInputElement).value
                    if (newValue.isNotEmpty() && "from" in bads && emailRegex.matches(newValue)) bads -= "from"
                    from = newValue
                }
            }
        }

        p("input") {
            styledSpan {
                if ("subject" in bads) css.specific(3) { color = Color.red }
                +"Object:"
            }
            input(InputType.text, name = "object") {
                attrs.placeholder = "What's on your mind?*"
                attrs.onChangeFunction = {
                    val newValue = (it.target as HTMLInputElement).value
                    if (newValue.isNotEmpty() && "subject" in bads) bads -= "subject"
                    subject = newValue
                }
            }
        }

        p("input") {
            styledSpan {
                if ("message" in bads) css.specific(3) { color = Color.red }
                +"Message:"
            }
            textArea {
                attrs.placeholder = "Tell us a little more about your needs*"
                attrs.onChangeFunction = {
                    val newValue = (it.target as HTMLTextAreaElement).value
                    if (newValue.isNotEmpty() && "message" in bads) bads -= "message"
                    message = newValue
                }
            }
        }

        p {
            styledButton(type = ButtonType.button) {
                css {
                    display = Display.inlineFlex
                    border = "none"
                    +kodein.button
                    marginTop = 3.rem
                }
                styledImg(src = "imgs/send-cute.svg") {
                    css {
                        height = 1.em // not rem!
                        marginRight = 0.4.em // not rem!
                    }
                }
                attrs.onClickFunction = click@ {
                    val newBads = mutableListOf<String>()
                    if (from.isEmpty() || !emailRegex.matches(from)) newBads.add("from")
                    if (subject.isEmpty()) newBads.add("subject")
                    if (message.isEmpty()) newBads.add("message")
                    bads = newBads
                    println(newBads)
                    if (newBads.isNotEmpty()) return@click

                    props.onSubmitFunction(from, subject, message)
                }
                +"SEND"
            }

            if (bads.isNotEmpty()) {
                styledSpan {
                    css {
                        +KodeinStyles.body
                        color = Color.red
                        paddingLeft = 2.em
                    }
                    +"Invalid form"
                }
            }
        }
    }
}

private interface ContactMessageProps : RProps {
    var isError: Boolean
}

private val ContactMessage by functionalComponent<ContactMessageProps> { props ->
    flexColumn(JustifyContent.center, Align.center) {
        css {
            height = 25.rem
        }
        styledP {
            css {
                +kodein.display1
                textAlign = TextAlign.center
                lineHeight = 1.8.em.lh
                color = if (props.isError) Color.red else KodeinColors.kyzantium
            }
            props.children()
        }
    }
}

private fun sendContactForm(from: String, subject: String, message: String, callback: (error: String?) -> Unit) {
    val params = URLSearchParams().apply {
        append("from", from)
        append("subject", subject)
        append("message", message)
    }

    val server = if (window.location.hostname == "localhost") "http://localhost:8082" else "https://us-central1-kodein-net-website.cloudfunctions.net/kodein-net-back"

    window.fetch("$server/contactForm", RequestInit(
            method = "POST",
            headers = json("Content-Type" to "application/x-www-form-urlencoded"),
            body = params
    ))
            .then {
                if (it.ok) callback(null)
                else {
                    console.log(js("Array.from(it.headers.entries())"))
                    val id = it.headers.get("X-Kodein-Error-ID") ?: "no id"
                    callback("error ${it.status} ($id)")
                }
            }
            .catch {
                callback("exception \"${it.message}\"")
            }

//    URLSearchParams(jsO)
}
