package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.services.ServiceDescription
import net.kodein.pages.services.ServicesStrings
import react.dom.b
import react.dom.br
import react.dom.p
import react.dom.span

object FrenchServicesStrings : ServicesStrings {
    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"on vous accompagne" }

        override val title: TextHandler = {
            +"Des applications natives au service"
            br {}
            +"d'une meilleure expérience utilisateur."
        }

        override val chapo: TextHandler = {
            p {
                +"""Nous pensons que les applications natives fournissent toujours la meilleure expérience utilisateur. 
                    |Nous utilisons Kotlin pour créer de belles applications multipateformes robustes et intégrées.
                """.trimMargin()
            }
            br{}
            p {
                +"Grâce à notre solide expérience, "
                span("nowrap") { +"nous saurons vous guider." }
            }
        }
    }

    override val consultancy: ServiceDescription = ServiceDescription("Conseil") {
        p {
            +"Quelle que soit la plateforme, donnez a vos équipes de développement la capacité de relever leurs défis "
            span("nowrap") { +"avec Kotlin." }
        }
        br{}
        p {
            +"""Que vous démarriez un nouveau projet ou que vous souhaitiez renforcer vos équipes et votre expertise, 
                |nous pouvons vous aider à valider et/ou améliorer votre process de développement. 
                |Nous pouvons mettre à votre disposition une équipe d'
            """.trimMargin()
            span("nowrap") { b { +"ingénieurs hautement qualifiés" } }
            +", ayant nos "
            b { +"valeurs humanistes" }
            +" au centre de leur travail."
        }
        br{}
        p {
            +"Que vous soyez à la recherche de guides pour vos "
            span("nowrap") { b { +"choix techniques" } }
            +", de conseils pour "
            span("nowrap") { b { +"votre architecture " } }
            +"serveur ou applicative, ou simplement un besoin de "
            span("nowrap") { b { +"support en développement" } }
            +", vous pouvez nous faire confiance pour vous accompagner "
            span("nowrap") { +"tout au long de votre projet." }
        }
    }

    override val projectDevelopment: ServiceDescription = ServiceDescription("Développement de projet") {
        p{
            +"""Que le développement d'applications ne soit pas au coeur de votre métier, 
                |ou que vous soyez en manque de ressources, nous pouvons vous aider à atteindre vos objectifs 
                |en prenant en charge vos challenges techniques. 
                |En utilisant la méthodologie 
           """.trimMargin()
            b { +"Scrum" }
            +""", nous vous proposons une manière simple et efficace de suivre votre projet, 
                |pendant que nous nous occupons de tout, 
           """.trimMargin()
            span("nowrap") { b { +"sprint après sprint" } }
            +"."
        }
        br{}
        p {
            +"""Kotlin est le premier langage issue de l'industrie à avoir été développé dans le but de créer des applications multiplatformes. 
                |Cela signifie que, plutôt que d'essayer de faire fonctionner un langage existant sur une plateforme qui n'est pas compatible,
                |le langage Kotlin a été pensé, dès le début de sa conception, pour sa portabilité et sa capacité à fonctionner dans de multiples environnements. 
            """.trimMargin()
        }
        br{}
        p {
            +"Focalisés sur la possiblité d'éxecuter du code Kotlin partout ou cela est possible, nos experts utilisent "
            span("nowrap") { b { +"Kotlin/Multiplatform" } }
            +" pour factoriser élégamment votre logique métier dans "
            span("nowrap") { b { +"un code commun" } }
            +", que nous pouvons écrire et tester une seule fois, tout en concervant un développement natif "
            span("nowrap") { b { +"pour chaque plateforme ciblée " } }
            +" (Serveur / Mobile / Web / Desktop)."
        }
    }
}