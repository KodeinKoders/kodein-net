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
        override val overTitle: TextHandler = { +"Let's share knowledge!" }
        override val title: TextHandler = {
            +"We are"
            br {}
            +"JetBrains Certified Trainers"
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
            +"""|Our Kotlin expertise goes way beyond its first objective, 
                |for the JVM world. We have the ability to give training and workshops 
                |for companies and world events, as we already did for  Kotlin/Everywhere Paris 
                |and KotlinConf'19. We can provide training upon the different level of Kotlin.""".trimMargin()
        }
    }

}
