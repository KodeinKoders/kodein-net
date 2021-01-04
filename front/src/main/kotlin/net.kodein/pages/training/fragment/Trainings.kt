package net.kodein.pages.training.fragment

import kotlinx.css.*
import net.kodein.charter.kodein
import net.kodein.components.AccordionElement
import net.kodein.components.contentRow
import net.kodein.pages.training.TrainingStrings
import net.kodein.useText
import net.kodein.utils.flexColumn
import net.kodein.withBasePath
import react.RBuilder
import react.RProps
import react.child
import react.dom.li
import react.dom.small
import react.dom.ul
import react.functionalComponent
import styled.css
import styled.styledImg
import styled.styledP


val Trainings = functionalComponent<RProps>("Trainings") {
    val strings = useText().training

    data class Course(val strings: TrainingStrings.CourseStrings, val notions: List<String>)

    val courses: List<Course> = listOf(
        Course(
            strings.courseCoroutines,
            listOf(
                "Suspend functions",
                "Coroutines context",
                "KotlinX Coroutines: Scopes",
                "KotlinX Coroutines: Cancellation",
                "KotlinX Coroutines: Channels",
                "KotlinX Coroutines: Context & Dispatchers",
                "KotlinX Coroutines: Flows",
                "Android specific coroutines"
            )
        ),
        Course(
            strings.courseAdvanced,
            listOf(
                "Lambdas",
                "Closures & inline functions",
                "Reified types",
                "Properties",
                "Delegated properties",
                "Generics",
                "Inline classes",
                "Operator overloading",
                "Enum, sealed classes & objects",
                "DSL: Domain Specific Languages",
                "Collections & sequences",
                "Virtual methods & extension functions",
                "Java compatibility",
                "Gradle: build & test for the JVM",
                "Gradle: deploy jars to a repository",
            )
        ),
        Course(
            strings.courseIos,
            listOf(
                "Kotlin Basics compared to swift",
                "Kotlin memory garbage collector",
                "Lambdas",
                "Nullability constraints",
                "Properties",
                "Generics",
                "Enum, sealed classes & objects",
                "Collections & sequences",
                "Virtual methods & extension functions",
                "Gradle: build tasks & configuration",
            )
        ),
        Course(
            strings.courseKmm,
            listOf(
                "Gradle: build tasks & configuration",
                "Gradle: KMM targets & source-sets",
                "Gradle: KMM tips & tricks",
                "Android Studio: KMM project architecture",
                "KMM: write & run tests",
                "Platform specific code: expect & actual",
                "KMM: the XCode compilation toolchain",
                "ObjC interop: basics & constraints",
                "ObjC interop: advanced language features",
                "K/N freeze & mutation errors",
                "KotlinX Serialization: no overhead efficient Serialization",
                "Ktor-clients: HTTP client setup",
                "Ktor-clients: Serialization & authentication",
                "Kodein-DI: Multiplatform Dependency Injection",
                "KotlinX DateTime: Multiplatform dates & durations",
                "Kodein-Log: Multiplatform Logging",
                "SQLDelight: Multiplatform SQLite ORM",
                "Kodein-DB: Multiplatform NoSQL DB",
                "The MVP & MVI patterns",
            )
        ),
        Course(
            strings.courseNative,
            listOf(
                "K/N native memory",
                "K/N pointers & arrays",
                "K/N POSIX & platform APIs",
                "K/N C-interop: definitions",
                "K/N C-interop: tips & tricks",
                "K/N freeze & mutation errors",
                "K/N worker API",
                "Coroutines in Kotlin/Native",
                "Kodein-Memory: Multiplatform low-level buffers & files"
            )
        ),
        Course(
            strings.courseKtor,
            listOf(
                "Ktor Server: setup",
                "Ktor Server: routing",
                "Ktor Server: authentication",
                "Ktor Server: sessions",
                "Ktor Server: content negociation",
                "Ktor Server: API calls",
                "Ktor Server: templates",
                "Ktor Server: testing",
                "Exposed: pure Kotlin ORM for SQL",
                "Kodein-DI: Dependency Injection in a Ktor server",
            )
        ),
        Course(
            strings.courseReact,
            listOf(
                "K/JS: Gradle config for webpack",
                "K/JS: dynamic type & js objects",
                "Kotlin Wrappers: React & Styled",
                "Kotlin React: HTML DSL",
                "Kotlin React: functional components & context",
                "Kotlin React: hooks for states & effects",
                "Kotlin Styled: embedded pure-kotlin CSS",
                "Kotlin React: Router",
                "Kotlin React: Server-Side rendering"
            )
        ),
    )

    contentRow(
        backgroundColor = Color.kodein.dark, indexPosition = 3, // see [Description] that stops at index 2
        bottomLayers = listOf(Color.kodein.orange, Color.kodein.kaumon),
    ) {
        flexColumn {
            css {
                width = 100.pct
                maxWidth = 60.rem
                margin(LinearDimension.auto)

                "ul" {
                    padding(0.5.em, 1.5.rem)
                    listStyleType = ListStyleType.disc
                    color = Color.kodein.korail
                }
                "small" {
                    display = Display.block
                    paddingTop = 0.5.em
                    color = Color.kodein.kamethiste
                }
            }

            styledP {
                css {
                    +kodein.chapo
                    specific {
                        textAlign = TextAlign.center
                    }
                    color = Color.kodein.kaumon
                    alignSelf = Align.center
                    marginBottom = 8.rem
                }

                strings.trainingTitle(this)
            }

            courses.forEach { course ->
                child(AccordionElement) {
                    attrs {
                        title = course.strings.title
                        sub = duration(course.strings.duration, strings.duration)
                    }

                    course.strings.description(this)

                    ul {
                        course.notions.forEach { notion ->
                            li { +notion }
                        }
                    }
                }
            }
        }
    }
}

private fun duration(duration: String, imageAlt: String): RBuilder.() -> Unit = {
    withBasePath { path ->
        styledImg(src = "$path/imgs/stopwatch-kaumon.svg", alt = imageAlt) {
            attrs {
                width = "16"
                height = "16"
            }
            css {
                width = 1.rem
                height = 1.rem
                paddingRight = .5.rem
            }
        }
    }
    +duration
}
