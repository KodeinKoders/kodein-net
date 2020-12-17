package net.kodein.lang.fr

import kotlinx.css.Color
import kotlinx.css.color
import kotlinx.css.fontSize
import kotlinx.css.rem
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
import styled.styledSpan

object FrenchContactStrings : ContactStrings {

    override val cover: CoverStrings = FrenchContactCoverStrings
    override val form: ContactFormStrings = FrenchContactFormStrings

}

object FrenchContactCoverStrings : CoverStrings {
    override val overTitle: TextHandler = { +"restons en contact!" }
    override val title: TextHandler = {
        +"Besoin d'aide pour un projet / une formation?"
        br {}
        +"Envoyez-nous simplement un message :-)"
    }
    override val chapo: TextHandler = {
        +"""Suivez notre actualité via les réseaux sociaux."""
        br {}
        +"""Retrouvez-nous sur """
        a(href = "https://twitter.com/KodeinKoders") { +"Twitter" }
        +" & "
        a(href = "https://www.linkedin.com/company/kodein") { +"LinkedIn" }
    }
}

object FrenchContactFormStrings : ContactFormStrings {
    override val title: String = "Contactez-nous"
    override val from: Pair<String, String> = "De:" to "Votre adresse e-mail*"
    override val subject: Pair<String, String> = "Objet:" to "De quoi voulez-vous nous parler?*"
    override val message: Pair<String, String> = "Message:" to "Donnez-nous un peu plus de contexte sur vos besoins.*"
    override val send: String = "ENVOYER"
    override val sending: String = "En cours d'envoi..."
    override val messageSent: TextHandler = {
        +"Merci pour votre message!"
        br {}
        +"Nous reviendrons vers vous rapidement."
    }
    override val messageNotSent: TextHandler = {
        +"Votre message n'a pas pu être envoyé :("
        br {}
        +"Vous pouvez toujours nous contacter via "
        styledA(href = "mailto:contact@kodein.net") {
            css.color = Color.kodein.purple
            +"contact@kodein.net"
        }
        +"!"
        br {}
        br {}
    }
    override val invalidForm: String = "Formulaire incorrect"
}