package net.kodein.pages.home

import net.kodein.PageStrings
import net.kodein.TextHandler


interface HomeStrings : PageStrings {

    val title: TextHandler
    val subTitle: TextHandler
    val scroll: String

    data class TitledContent(val title: String, val readMore: String, val content: TextHandler)

    val advisoryDepartment: TitledContent
    val trainingDepartment: TitledContent
    val developmentDepartment: TitledContent

    val kodeinKodersDescription: TitledContent
    val humanistDescription: TitledContent
    val openSourceDescription: TitledContent
    val trainingDescription: TitledContent

    val humansTitle: String
    val humansSubtitle: String
}
