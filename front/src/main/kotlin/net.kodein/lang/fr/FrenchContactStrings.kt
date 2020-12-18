package net.kodein.lang.fr

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

object FrenchContactStrings : ContactStrings {

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"restons en contact" }
        override val title: TextHandler = {
            +"Parlez-nous de "
            span("nowrap") { +"vos projets !" }
        }
        override val chapo: TextHandler = {
            +"...ou lancez une discussion,"
            br {}
            +"nous sommes ici "
            span("nowrap") { +"pour vous écouter :-)" }

            br {}
            br {}

            +"Vous pouvez également suivre notre actualité "
            span("nowrap") { +"via les réseaux sociaux." }
            br {}
            +"""Retrouvez-nous sur """
            span("nowrap") {
                a(href = "https://twitter.com/KodeinKoders") { +"Twitter" }
                +" & "
                a(href = "https://www.linkedin.com/company/kodein") { +"LinkedIn" }
                +" !"
            }
        }
    }

    override val form: ContactFormStrings = object : ContactFormStrings {
        override val title: String = "Contactez-nous"
        override val from: Pair<String, String> = "De:" to "Votre adresse e-mail.*"
        override val subject: Pair<String, String> = "Objet:" to "De quoi voulez-vous nous parler ?*"
        override val message: Pair<String, String> = "Message:" to "Donnez-nous un peu plus de contexte sur vos besoins.*"
        override val send: String = "ENVOYER"
        override val sending: String = "En cours d'envoi..."
        override val messageSent: TextHandler = {
            +"Merci pour votre message !"
            br {}
            +"Nous reviendrons vers vous rapidement."
        }
        override val messageNotSent: TextHandler = {
            +"Votre message n'a pas pu être envoyé :-("
            br {}
            +"Contactez nous via "
            styledA(href = "mailto:contact@kodein.net") {
                css.color = Color.kodein.purple
                +"contact@kodein.net"
            }
            +" !"
            br {}
            br {}
        }
        override val invalidForm: String = "Formulaire incorrect."
    }

}
