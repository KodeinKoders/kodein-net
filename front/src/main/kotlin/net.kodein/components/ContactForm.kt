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
import net.kodein.useText
import net.kodein.utils.*
import net.kodein.withBasePath
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

interface ContactUsProps : RProps {
    var big: Boolean
}

val ContactUs = functionalComponent<ContactUsProps>("ContactUs") { props ->
    val strings = useText().contact.form
    var status: FormStatus by useState(FormStatus.Ready)

    styledDiv {
        css {
            margin(5.rem, LinearDimension.auto)
            maxWidth = 50.rem

            maxWidth(768) {
                padding(0.rem, 1.rem)
            }
        }

        styledH1 {
            css {
                +kodein.display2
                specific {
                    fontWeight = FontWeight.hairline
                    textAlign = TextAlign.start
                }
                color = Color.kodein.orange
            }

            +strings.title
        }

        when (val s = status) {
            FormStatus.Ready -> child(ContactForm) {
                attrs.submit = { from, subject, message ->
                    status = FormStatus.Sending
                    sendContactForm(from, subject, message) {
                        status = FormStatus.Sent(it)
                    }
                }
                attrs.big = props.big
            }
            FormStatus.Sending -> child(ContactMessage) {
                attrs.isError = false
                +strings.sending
            }
            is FormStatus.Sent -> child(ContactMessage) {
                attrs.isError = s.error != null
                if (s.error != null) {
                    strings.messageNotSent(this)
                    styledSpan {
                        css.fontSize = 1.rem
                        +s.error
                    }
                }
                else {
                    strings.messageSent(this)
                }
            }
        }

    }
}

interface ContactFormProps : RProps {
    var submit: (from: String, subject: String, message: String) -> Unit
    var big: Boolean
}

private val ContactForm = functionalComponent<ContactFormProps>("ContactForm") { props ->
    var from: String by useState("")
    var subject: String by useState("")
    var message: String by useState("")
    var bads: List<String> by useState(emptyList())

    child(ContactFormView) {
        attrs.big = props.big
        attrs.from = from
        attrs.setFrom = {
            if (it.isNotEmpty() && "from" in bads && emailRegex.matches(it)) bads -= "from"
            from = it
        }
        attrs.subject = subject
        attrs.setSubject = {
            if (it.isNotEmpty() && "subject" in bads) bads -= "subject"
            subject = it
        }
        attrs.message = message
        attrs.setMessage = {
            if (it.isNotEmpty() && "message" in bads) bads -= "message"
            message = it
        }
        attrs.bads = bads
        attrs.submit = send@ {
            val newBads = mutableListOf<String>()
            if (from.isEmpty() || !emailRegex.matches(from)) newBads.add("from")
            if (subject.isEmpty()) newBads.add("subject")
            if (message.isEmpty()) newBads.add("message")
            bads = newBads
            if (newBads.isNotEmpty()) return@send

            props.submit(from, subject, message)
        }
    }

}

interface ContactFormViewProps : RProps {
    var big: Boolean
    var from: String
    var setFrom: (String) -> Unit
    var subject: String
    var setSubject: (String) -> Unit
    var message: String
    var setMessage: (String) -> Unit
    var bads: List<String>
    var submit: () -> Unit
}

private val ContactFormView = functionalComponent<ContactFormViewProps>("ContactFormView") { props ->
    val strings = useText().contact.form

    styledDiv {
        css {
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
                    height = (if (props.big) 15.75 else 5.25).em // not rem!
                    lineHeight = 1.75.em.lh
                    marginTop = (-0.25).rem
                }
            }
        }

        p("input") {
            styledSpan {
                if ("from" in props.bads) css.specific(3) { color = Color.red }
                +strings.from.first
            }
            input(InputType.email, name = "email") {
                attrs.placeholder = strings.from.second
                attrs.onChangeFunction = { props.setFrom((it.target as HTMLInputElement).value) }
            }
        }

        p("input") {
            styledSpan {
                if ("subject" in props.bads) css.specific(3) { color = Color.red }
                +strings.subject.first
            }
            input(InputType.text, name = "object") {
                attrs.placeholder = strings.subject.second
                attrs.onChangeFunction = {
                    attrs.onChangeFunction = { props.setSubject((it.target as HTMLInputElement).value) }
                }
            }
        }

        p("input") {
            styledSpan {
                if ("message" in props.bads) css.specific(3) { color = Color.red }
                +strings.message.first
            }
            textArea {
                attrs.placeholder = strings.message.second
                attrs.onChangeFunction = { props.setMessage((it.target as HTMLTextAreaElement).value) }
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
                withBasePath { path ->
                    styledImg(src = "$path/imgs/send-cute.svg", alt = strings.send) {
                        attrs {
                            width = "15"
                            height = "15"
                        }

                        css {
                            height = 1.em // not rem!
                            marginRight = 0.4.em // not rem!
                        }
                    }
                }
                attrs.onClickFunction = {
                    props.submit()
                }
                +strings.send
            }

            if (props.bads.isNotEmpty()) {
                styledSpan {
                    css {
                        +KodeinStyles.body
                        color = Color.red
                        paddingLeft = 2.em
                    }
                    +strings.invalidForm
                }
            }
        }
    }
}

private interface ContactMessageProps : RProps {
    var isError: Boolean
}

private val ContactMessage = functionalComponent<ContactMessageProps>("ContactMessage") { props ->
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
