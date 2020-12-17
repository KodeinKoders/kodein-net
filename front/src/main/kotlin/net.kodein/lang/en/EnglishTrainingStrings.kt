package net.kodein.lang.en

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


object EnglishTrainingStrings : TrainingStrings {

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
            +"""|Our Kotlin expertise goes way beyond its first objective: the JVM environment.
                |We offer training and workshops for companies and world events,
                |such as the Kotlin/Everywhere Paris and KotlinConf'19 conferences.
                |We provide training addressing the different levels & targets of Kotlin.""".trimMargin()
        }
    }

}
