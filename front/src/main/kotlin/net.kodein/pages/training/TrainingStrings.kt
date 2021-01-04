package net.kodein.pages.training

import net.kodein.PageStrings
import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings


interface TrainingStrings : PageStrings {

    data class CourseStrings(val title: String, val duration: String, val description: TextHandler)

    val cover: CoverStrings

    val descriptionTitle: TextHandler
    val descriptionText: TextHandler

    val trainingTitle: TextHandler
    val courseCoroutines: CourseStrings
    val courseAdvanced: CourseStrings
    val courseIos: CourseStrings
    val courseKmm: CourseStrings
    val courseNative: CourseStrings
    val courseKtor: CourseStrings
    val courseReact: CourseStrings

    val duration: String
}
