package net.kodein.lang.fr

import kotlinx.css.height
import kotlinx.css.marginTop
import kotlinx.css.rem
import kotlinx.css.width
import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.training.TrainingStrings
import net.kodein.withBasePath
import react.dom.br
import styled.css
import styled.styledImg


object FrenchTrainingStrings : TrainingStrings {

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"Partageons le savoir!" }
        override val title: TextHandler = {
            +"Nous sommes"
            br {}
            +"Formateurs Certifiés JetBrains"
            br {}
            withBasePath { path ->
                styledImg(src = "$path/imgs/badge-orange.svg") {
                    css {
                        width = 10.rem
                        height = 10.rem
                        marginTop = 1.rem
                    }
                }
            }
        }
        override val chapo: TextHandler = {
            +"""|Notre expertise Kotlin s'étend au delà de son objectif premier : l'environement JVM.
                |Nous proposons des formations et workshops pour entreprises et évenements à travers le monde,
                |comme les conférences Kotlin/Everywhere Paris et KotlinConf'19.
                |Nous fournissons des formations sur les diffèrents niveaux & cibles de Kotlin.""".trimMargin()
        }
    }

}
