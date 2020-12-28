package net.kodein.pages.home

import net.kodein.TextHandler


interface HomeStrings {

    val title: TextHandler
    val subTitle: TextHandler
    val scroll: String

    val advisory: DepartmentStrings
    val training: DepartmentStrings
    val development: DepartmentStrings

//    val kodeinKoders: DescriptionStrings
//    val workForHumans: DescriptionStrings
//    val oss: DescriptionStrings
//    val trainings: DescriptionStrings

    val readMore: String
}

data class DepartmentStrings(val title: String, val icon: String, val content: TextHandler)
data class DescriptionStrings(val title: String, val illustration: String, val content: TextHandler)