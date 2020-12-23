package net.kodein.lang.fr

import kotlinx.css.Color
import kotlinx.css.color
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.FooterStrings
import styled.styledA

object FrenchFooterStrings : FooterStrings {
    override val siteMap: String = "Carte du Site"
    override val home: String = "Accueil"
    override val services: String = "Services"
    override val training: String = "Formation"
    override val openSource: String = "Open Source"
    override val team: String = "Équipe"
    override val blog: String = "Blog"

    override val contactForm: String = "Formulaire de Contact"

    override val powered: TextHandler = {
        +"Propulsé avec fierté par l'"
        styledA(href = "https://github.com/KodeinKoders/kodein-net") {
            css.color = Color.kodein.kinzolin
            +"Open Source"
        }
        +"."
    }

    override val credits: String = "crédits"
    override val allRightsReserved: String = "Tous droits réservés."
}
