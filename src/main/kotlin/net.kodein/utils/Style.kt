package net.kodein.utils

import kotlinx.css.CSSBuilder
import kotlinx.css.LinearDimension
import kotlinx.css.RuleSet
import kotlinx.css.px

fun CSSBuilder.maxWidth(maxWidth: LinearDimension, block: RuleSet) = media("(max-width: ${maxWidth.value})", block)
fun CSSBuilder.maxWidthXXL(block: RuleSet) = maxWidth(1100.px, block)
fun CSSBuilder.maxWidthXL(block: RuleSet) = maxWidth(1000.px, block)
fun CSSBuilder.maxWidthL(block: RuleSet) = maxWidth(900.px, block)
fun CSSBuilder.maxWidthM(block: RuleSet) = maxWidth(800.px, block)
fun CSSBuilder.maxWidthXM(block: RuleSet) = maxWidth(700.px, block)
fun CSSBuilder.maxWidthS(block: RuleSet) = maxWidth(500.px, block)
fun CSSBuilder.maxWidthXS(block: RuleSet) = maxWidth(400.px, block)
fun CSSBuilder.maxWidthXXS(block: RuleSet) = maxWidth(320.px, block)