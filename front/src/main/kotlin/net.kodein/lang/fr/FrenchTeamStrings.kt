package net.kodein.lang.fr

import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.team.JobDescription
import net.kodein.pages.team.MemberStrings
import net.kodein.pages.team.TeamStrings
import react.dom.br
import react.dom.p

object FrenchTeamStrings : TeamStrings {

    override val pageTitle: String = "L'équipe"

    override val pageDescription = "Notre super équipe !"

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"nous sommes humains" }
        override val title: TextHandler = { +"Animés par notre passion pour Kotlin." }
        override val chapo: TextHandler = {}
    }
    override val salomon = MemberStrings.salomon.copy(
        status = "Fondateur",
        bio = {
            p {
                +"""
                    Salomon programme depuis plus de 20 ans (il a commencé à l'âge de 12 ans), et travaille sur des applications mobiles depuis que les smartphones existent.
                    Profondément ancré dans la philosophie Open Source, il aime créer des outils et librairies, contribuant toujours à l'état de l'art de l'industrie.
                    Il est toujours à l'affut du futur de la programmation, et a contribué à Kotlin & Kotlin/Native dès leur création.
                """
            }
            br {}
            p {
                +"Il dance le rock à 6 temps, pilote des petits avions, et adooore les jeux de sociétés."
            }
        }
    )
    override val romain = MemberStrings.romain.copy(
        status = "Fondateur",
        bio = {
            p {
                +"""Romain code depuis presque 15 ans. 
                    |Il a d'abord travaillé sur des architectures serveur en Java, puis en Kotlin dès les premières versions. 
                    |Il a une nouvelle corde à son arc depuis la KotlinConf 2018, où il a rencontré Salomon et commencé à 
                    |contribuer a Kodein-DI, la toute première librairie communautaire Kotlin/Multiplatform.
                """.trimMargin()
            }
            br {}
            p {
                +"Il aime les grands espaces, la randonnée en montagne et l'escalade."
            }
        }
    )
    override val nerdOn: String = "Retrouvez moi sur "
    override val joinUs: String = "Rejoins-nous !"
    override val jobs: List<JobDescription> = listOf(
        JobDescription("Développeur Kotlin/Multiplatform") {
            p {
                +"Tu veux travailler avec Kotlin partout où il existe : Android, iOS, Serveur, peut-être même Web/JS."
                br {}
                +"Tu veux développer des outils & librairies qui font progresser l'état de l'art du développement mobile multiplateforme."
                br {}
                +"Tu veux travailler sur différents projets et aider à créer des applications sur mesure."
            }
            br {}
            p {
                +"Mais aussi..."
                br {}
                +"Tu aimes travailler à 100% en télé-travail, avec les horaires de ton choix."
                br {}
                +"Tu partages nos valeurs humanistes : bienveillance, ouverture, transparence financière."
                br {}
                +"Tu as un passeport ou un permis de travail européen."
            }
        },
        JobDescription("Évangéliste à mi-temps") {
            p {
                +"Tu comprends ce que Kotlin/Multiplatform apporte à l'industrie du développement mobile."
                br {}
                +"Tu veux attaquer le marché par le bas : parler aux dévelopeurs pour qu'ils convainquent eux-mêmes leur management."
                br {}
                +"Tu veux créer avec nous la renommée d'un centre d'excellence européen dans le développement d'applications mobiles."
            }
            br {}
            p {
                +"Mais aussi..."
                br {}
                +"Tu aimes travailler à 100% en télé-travail, avec les horaires de ton choix."
                br {}
                +"Tu partages nos valeurs humanistes : bienveillance, ouverture, transparence financière."
                br {}
                +"Tu as un passeport ou un permis de travail européen."
            }
        }
    )
}
