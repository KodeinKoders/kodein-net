//package net.kodein.pages.home.fragment
//
//import kotlinx.css.*
//import net.kodein.components.PerfDescription
//import net.kodein.utils.maxWidthM
//import net.kodein.utils.maxWidthXM
//import react.RBuilder
//import react.RComponent
//import react.RProps
//import react.RState
//import styled.css
//import styled.styledDiv
//
//class Performance : RComponent<RProps, RState>() {
//    override fun RBuilder.render() {
//        styledDiv {
//            css {
//                display = Display.flex
//                flexDirection = FlexDirection.row
//                maxWidth = 1150.px
//                margin(LinearDimension.auto)
//
//                maxWidthXM {
//                    flexDirection = FlexDirection.column
//                }
//            }
//
//            child(PerfDescription::class) {
//                attrs {
//                    title = "ADVISORY"
//                    description = """
//                        Empowering your engineering teams to take on challenges with Kotlin
//                    """.trimIndent()
//                }
//            }
//
//            child(PerfDescription::class) {
//                attrs {
//                    title = "TRAINING ON KOTLIN"
//                    description = """
//                        Regardless that you do Mobile, Backend or Frontend we can help you increase your Kotlin expertise!
//                    """.trimIndent()
//                }
//            }
//
//            child(PerfDescription::class) {
//                attrs {
//                    title = "PROJECT DEVELOPMENT"
//                    description = """
//                        We can help you reach your goals by taking care of the technical challenges you have!
//                    """.trimIndent()
//                    isLastItem = true
//                }
//            }
//
//        }
//    }
//
//}