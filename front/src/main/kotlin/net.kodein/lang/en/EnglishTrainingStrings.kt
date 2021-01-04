package net.kodein.lang.en

import kotlinx.css.height
import kotlinx.css.marginTop
import kotlinx.css.rem
import kotlinx.css.width
import net.kodein.TextHandler
import net.kodein.components.strings.CoverStrings
import net.kodein.pages.training.TrainingStrings
import net.kodein.withBasePath
import react.dom.b
import react.dom.br
import react.dom.p
import react.dom.small
import styled.css
import styled.styledImg


object EnglishTrainingStrings : TrainingStrings {

    override val cover: CoverStrings = object : CoverStrings {
        override val overTitle: TextHandler = { +"let's share knowledge" }
        override val title: TextHandler = {
            +"We are"
            br {}
            +"JetBrains Certified Trainers."
            br {}
            withBasePath { path ->
                styledImg(src = "$path/imgs/badge-orange.svg", alt = "Certification logo") {
                    attrs {
                        width = "150"
                        height = "150"
                    }
                    css {
                        width = 10.rem
                        height = 10.rem
                        marginTop = 1.rem
                    }
                }
            }
        }
        override val chapo: TextHandler = {
            +"""
                We offer training and workshops for companies and world events,
                such as the Kotlin/Everywhere Paris and KotlinConf'19 conferences.
                We provide training addressing the different levels & targets of Kotlin.
            """
        }
    }

    override val descriptionTitle: TextHandler = {
        +"Update your team to a higher level of quality, productivity & consistency."
    }

    override val descriptionText: TextHandler = {
        p {
            +"Because all teams are unique in their compositions, backgrounds, and goals, we "
            b { +"tailor each workshop" }
            +" to the specific needs of team we are addressing, making each training "
            b { +"unique to the client" }
            +"."
        }
        p {
            +"""
                Thanks to our experience in pedagogy as well as in architecture & development,
                we have developed a set of training courses & exercises that focuses on providing technical teams
            """
            b { +"the most practical knowledge in mere days" }
            +"."
            br {}
            +"""
                Each notion set is always put in perspective with a corresponding coding exercise.
                We focus on productivity and quality, not only addressing technical notions, but more importantly outlining their best practices.
            """
        }
    }

    override val trainingTitle: TextHandler = { +"Have a look at some training programs." }

    override val courseCoroutines = TrainingStrings.CourseStrings(
        "Structured concurency with Kotlin coroutines",
        "1 day",
    ) {
        +"""
            Kotlin coroutines allow modeling complex concurrency scenarios in a structural and expressive way.
            Learn how to model a concurrent task, and harness the power of the KotlinX Coroutines library (scoping, cancellation, etc).
        """
    }

    override val courseAdvanced = TrainingStrings.CourseStrings(
        "Advanced Kotlin for library & SDK development",
        "2 days",
    ) {
        +"""
            Kotlin offers many advanced language features that are typically used by library developers, allowing them to provide application developers great tools.
            Discover these advanced features, and master them to create elegant yet powerful Kotlin APIs.
        """
    }

    override val courseIos = TrainingStrings.CourseStrings(
        "The Kotlin language for iOS developers",
        "1 day",
    ) {
        +"""
            Kotlin's syntax is very similar to Swift's, yet their philosophy may sometimes diverge.
            This training is focused at iOS Swift developers that want the tools to work on a shared Kotlin multiplatform project.
        """
    }

    override val courseKmm = TrainingStrings.CourseStrings(
        "KMM: Koltin Multiplatform Mobile for Android & iOS",
        "2 days",
    ) {
        +"""
            Share meaningful business & behaviour code between your Android & iOS apps, keeping their views native & specific. That's the dream!
            Learn how to implement that dream, how to architecture your application accordingly, and how to communicate cleanly between a shared core and a native UI.
        """
        small { +"Requires: \"Structured concurency with Kotlin coroutines\" and possibly \"The Kotlin language for iOS developers\"." }
    }

    override val courseNative = TrainingStrings.CourseStrings(
        "Advanced low-level Koltin/Native",
        "1 day",
    ) {
        +"""
            Kotlin/Native is the Kotlin compiler that produces native executable for multiple targets (iOS being just one of them).
            It exposes Kotlin APIs allowing native code to access C libraries, manage low-level memories, and access platform system APIs (such as POSIX).
        """
        small { +"Requires: \"KMM: Koltin Multiplatform Mobile for Android & iOS\"." }
    }

    override val courseKtor = TrainingStrings.CourseStrings(
        "Efficient pure Kotlin backend",
        "1 day",
    ) {
        +"""
            Learn how to create a pure Kotlin, lightweight, concurrent HTTP server using Jetbrains dedicated framework: Ktor.
        """
        small { +"Requires: \"Structured concurency with Kotlin coroutines\"." }
    }

    override val courseReact = TrainingStrings.CourseStrings(
        "Kotlin/React for the web",
        "1 day",
    ) {
        +"""
            React is the framework of the web: it allows creating very effective confined web components & applications.
            This training is for those who want to create web UIs while still using the structured & safe language they know and love: Kotlin!
        """
    }

    override val duration = "Duration"

}
