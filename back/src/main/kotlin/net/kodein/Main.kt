package net.kodein

import com.google.cloud.functions.HttpFunction
import com.google.cloud.functions.HttpRequest
import com.google.cloud.functions.HttpResponse
import com.sendgrid.*
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import net.kodein.utils.value
import java.util.*
import java.util.logging.Logger

class Main : HttpFunction {
    private val log = Logger.getLogger(Main::class.qualifiedName)

    private val emailRegex = Regex(
            "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
    )

    private fun HttpResponse.error(code: Int, error: String) {
        val id = UUID.randomUUID().toString()
        log.severe("$id: $error")
        appendHeader("Access-Control-Expose-Headers", "X-Kodein-Error-ID")
        appendHeader("X-Kodein-Error-ID", id)
        setStatusCode(code)
    }

    override fun service(request: HttpRequest, response: HttpResponse) {
        response.appendHeader("Access-Control-Allow-Origin", "*")
        when ("${request.method} ${request.path}") {
            "POST /contactForm" -> postContactForm(request, response)
            else -> {
                response.error(404, "Unknown request ${request.method} ${request.path}")
            }
        }
    }

    private fun postContactForm(request: HttpRequest, response: HttpResponse) {

        if (request.contentType.value != "application/x-www-form-urlencoded") {
            return response.error(400, "Bad content type: ${request.contentType}")
        }

        val from = request.getFirstQueryParameter("from").value?.takeIf { it.isNotEmpty() }
                ?: return response.error(400, "Missing parameter 'from'")
        if (!emailRegex.matches(from)) return response.error(400, "Bad email '$from'")
        val subject = request.getFirstQueryParameter("subject").value?.takeIf { it.isNotEmpty() }
                ?: return response.error(400, "Missing parameter 'subject'")
        val message = request.getFirstQueryParameter("message").value?.takeIf { it.isNotEmpty() }
                ?: return response.error(400, "Missing parameter 'message'")

        val sendgridApiKey = System.getenv("SENDGRID_API_KEY")
                ?: return response.error(500, "Missing env SENDGRID_API_KEY")

        val sent = SendGrid(sendgridApiKey)
                .api(Request().apply {
                    method = Method.POST
                    endpoint = "mail/send"
                    body = Mail(
                            Email("contact+website@kodein.net"),
                            "CONTACT: $subject",
                            Email("contact@kodein.net"),
                            Content(
                                    "text/plain",
                                    "FROM: $from\nSUBJECT: $subject\n\n$message"
                            )
                    ).build()
                })

        if (sent.statusCode !in 200..299) {
            return response.error(500, "${sent.statusCode}\n${sent.body}")
        }

        response.setStatusCode(200)
    }
}
