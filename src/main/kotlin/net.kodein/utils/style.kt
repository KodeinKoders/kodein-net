package net.kodein.utils

import kotlinx.css.*

// CSS utils
fun CSSBuilder.rangeWidth(min: Int, max: Int, block: RuleSet): Rule = media("(min-width: ${min}px) and (max-width: ${max}px)", block)
fun CSSBuilder.rangeHeight(min: Int, max: Int, block: RuleSet): Rule = media("(min-height: ${min}px) and (max-height: ${max}px)", block)
fun CSSBuilder.maxWidth(max: Int, block: RuleSet): Rule = media("(max-width: ${max}px)", block)
fun CSSBuilder.minWidth(min: Int, block: RuleSet): Rule = media("(min-width: ${min}px)", block)
fun CSSBuilder.maxHeight(max: Int, block: RuleSet): Rule = media("(max-height: ${max}px)", block)
fun CSSBuilder.minHeight(min: Int, block: RuleSet): Rule = media("(min-height: ${min}px)", block)
fun CSSBuilder.maxSize(max: Int, block: RuleSet): Rule = media("(max-width: ${max}px), (max-height: ${max}px)", block)
fun CSSBuilder.minSize(min: Int, block: RuleSet): Rule = media("(min-height: ${min}px), (min-width: ${min}px)", block)

// CSS devices' sizes
fun CSSBuilder.mobileS(block: RuleSet) = maxSize(max = 280, block = block)
fun CSSBuilder.mobileM(block: RuleSet) = maxSize(max = 360, block = block)
fun CSSBuilder.mobileL(block: RuleSet) = maxSize(max = 480, block = block)
fun CSSBuilder.tablet(block: RuleSet) = maxSize(max = 768, block = block)
fun CSSBuilder.computerS(block: RuleSet) = rangeWidth(min = 769, max = 1024, block = block)
fun CSSBuilder.computerM(block: RuleSet) = rangeWidth(min = 1025, max = 1200, block = block)
fun CSSBuilder.computerL(block: RuleSet) = rangeWidth(min = 1201, max = 1440, block = block)
fun CSSBuilder.computerWide(block: RuleSet) = minWidth(min = 1441, block = block)

val FontWeight.Companion.hairline get() = w100
val FontWeight.Companion.ultraLight get() = w200
val FontWeight.Companion.light get() = w300
val FontWeight.Companion.regular get() = w400
val FontWeight.Companion.medium get() = w500
val FontWeight.Companion.semiBold get() = w600
//val FontWeight.Companion.bold get() = w700
val FontWeight.Companion.extraBold get() = w800
val FontWeight.Companion.black get() = w900
