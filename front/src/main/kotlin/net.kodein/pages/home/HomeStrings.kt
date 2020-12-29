package net.kodein.pages.home

import net.kodein.TextHandler


interface HomeStrings {

    val title: TextHandler
    val subTitle: TextHandler
    val scroll: String

    data class TitledContent(val title: String, val content: TextHandler)

    val advisory: TitledContent
    val training: TitledContent
    val development: TitledContent

    val kodeinKoders: TitledContent
    val workForHumans: TitledContent
    val oss: TitledContent
    val trainings: TitledContent

    val readMore: String
}
