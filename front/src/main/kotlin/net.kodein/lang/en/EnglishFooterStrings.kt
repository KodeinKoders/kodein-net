package net.kodein.lang.en

import kotlinx.css.Color
import kotlinx.css.color
import net.kodein.TextHandler
import net.kodein.charter.kodein
import net.kodein.components.FooterStrings
import styled.styledA

object EnglishFooterStrings : FooterStrings {
    override val siteMap: String = "Site Map"
    override val home: String = "Home"
    override val services: String = "Services"
    override val training: String = "Training"
    override val openSource: String = "Open Source"
    override val team: String = "Team"
    override val blog: String = "Blog"

    override val contactForm: String = "Contact Form"

    override val openSourced: TextHandler = {
        +"Proudly "
        styledA(href = "https://github.com/KodeinKoders/kodein-net") {
            css.color = Color.kodein.kinzolin
            +"Open Sourced"
        }
        +"."
    }

    override val credits: String = "credits"
    override val allRightsReserved: String = "All rights reserved."
}