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
            +"Consolidez votre audience, "
            br {}
            +"avec des applications natives."
        }

        override val chapo: TextHandler = {
            p {
                +"Nous croyons sincèrement que seules les applications natives peuvent combler le fossé qu'il y a entre vous et vos utilisateurs."
                br {}
                +"C'est pourquoi nous utilisons Kotlin pour créer des applications multipateformes stables et robustes."
            }
            br{}
            p {
                +"Grâce à notre solide expérience, "
                span("nowrap") { +"nous pouvons vous guider." }
            }
        }
    }

    override val consultancy: ServiceDescription = ServiceDescription("Accompagnement") {
        p {
            +"""Quelque soit la plateforme, donnez a vos équipes de développement 
            |la capacité de relever tous les défis, """.trimMargin()
            span("nowrap") { +"avec à Kotlin." }
        }
        br{}
        p {
            +"""Que vous démarriez un nouveau projet, que vous souhaitiez renforcer vos équipes et votre expertise, 
                |ou que vous cherchiez un accompagnement pour valider ou améliorer votre process de développement, 
                |nous pouvons mettre à votre disposition une équpe de    
            """.trimMargin()
            span("nowrap") { b { +"développeurs compétents" } }
            +", partageant des "
            span("nowrap") {
                b { +"valeurs humaines" }
                +"."
            }
        }
        br{}
        p {
            +"""À la recherche de guides pour vos choix techniques, de conseils pour votre architecture serveur ou mobile, 
                |ou simplement un besoin de support en développement, vous pouvez nous faire confiance pour vous accompagner  
            """.trimMargin()
            span("nowrap") { +"tout au long de votre projet." }
        }
    }

    override val projectDevelopment: ServiceDescription = ServiceDescription("Développement") {
        p{
            +"""Que le développement d'applications ne fasse pas parti du coeur de votre métier, 
                |ou que vous soyez en manque de ressources, nous pouvons vous aider à attendre vos objectifs 
                |en prenant en charge les challenges techniques que vous avez. En utilisant la méthodologie Scrum, 
                |nous vous proposons une manière simple et efficace de suivre votre projet, 
                |pendant que nous nous occupons de tout, sprint après sprint.
           """.trimMargin()
        }
        br{}
        p {
            +"""Kotlin est le premier langage issue de l'industrie à avoir été développé dans le but de créer des applications multiplatformes. 
                |Cela signifie, que plutôt que d'essayer de faire fonctionner un langage existant sur une plateforme qui n'est pas compatible,
                |le langage Kotlin a été pensé, dès le début de sa conception, pour sa portabilité et sa capacité à fonctionner dans de multiples environnements. 
            """.trimMargin()
        }
        br{}
        p {
            +"""Focalisés sur la possiblité d'éxecuter du code Kotlin partout ou cela est possible,
                |nos experts utilisent à leur avantage Kotlin/Multiplatform pour élégamment factoriser votre logoique métier 
                |dans un code common, que nous écrivons, testons et éxecutons une seule fois, 
                |tout en nous permettant de profiter des outils de développement natifs de chacun des systèmes 
                |que nous ciblons (Serveur / Mobile / Web / Desktop).
            """.trimMargin()
        }
    }
}