package net.kodein.utils

import csstype.PropertiesBuilder
import web.cssom.*


// ---- Color utilities ----

fun Color.withAlpha(alpha: Double): Color {
    val str = unsafeCast<String>()
    if (str.startsWith("#") && str.length == 7) {
        val r = str.substring(1, 3).toInt(16)
        val g = str.substring(3, 5).toInt(16)
        val b = str.substring(5, 7).toInt(16)
        return Color("rgba($r, $g, $b, $alpha)")
    }
    return this
}


// ---- Padding / Margin shorthands ----

fun PropertiesBuilder.padding(all: Length) {
    paddingTop = all; paddingRight = all; paddingBottom = all; paddingLeft = all
}

fun PropertiesBuilder.padding(vertical: Length, horizontal: Length) {
    paddingTop = vertical; paddingBottom = vertical
    paddingLeft = horizontal; paddingRight = horizontal
}

fun PropertiesBuilder.padding(top: Length, right: Length, bottom: Length, left: Length) {
    paddingTop = top; paddingRight = right; paddingBottom = bottom; paddingLeft = left
}

fun PropertiesBuilder.padding(
    top: Length = 0.px, right: Length = 0.px, bottom: Length = 0.px, left: Length = 0.px,
    vertical: Length? = null, horizontal: Length? = null
) {
    paddingTop = vertical ?: top
    paddingBottom = vertical ?: bottom
    paddingLeft = horizontal ?: left
    paddingRight = horizontal ?: right
}

fun PropertiesBuilder.margin(all: Length) {
    marginTop = all; marginRight = all; marginBottom = all; marginLeft = all
}

fun PropertiesBuilder.margin(vertical: Length, horizontal: Length) {
    marginTop = vertical; marginBottom = vertical
    marginLeft = horizontal; marginRight = horizontal
}

fun PropertiesBuilder.margin(top: Length, right: Length, bottom: Length, left: Length) {
    marginTop = top; marginRight = right; marginBottom = bottom; marginLeft = left
}

fun PropertiesBuilder.margin(vertical: Length? = null, horizontal: Auto? = null) {
    vertical?.let { marginTop = it; marginBottom = it }
    horizontal?.let { marginLeft = Auto.auto; marginRight = Auto.auto }
}


// ---- Transition utility ----

fun PropertiesBuilder.transition(property: String, duration: String, timing: String? = null) {
    val value = listOfNotNull(property, duration, timing).joinToString(" ")
    val current = asDynamic().transition as? String
    asDynamic().transition = if (current.isNullOrEmpty()) value else "$current, $value"
}

fun PropertiesBuilder.transition(property: String, durationSeconds: Double, timing: String? = null) {
    transition(property, "${durationSeconds}s", timing)
}


// ---- Border utilities ----

fun PropertiesBuilder.borderBottom(width: Length, style: LineStyle, color: Color) {
    borderBottomWidth = width
    borderBottomStyle = style
    borderBottomColor = color
}

fun PropertiesBuilder.borderTop(width: Length, style: LineStyle, color: Color) {
    borderTopWidth = width
    borderTopStyle = style
    borderTopColor = color
}

fun PropertiesBuilder.border(width: Length, style: LineStyle, color: Color, radius: Length? = null) {
    borderWidth = width
    borderStyle = style
    borderColor = color
    radius?.let { borderRadius = it }
}


// ---- Box shadow ----

fun PropertiesBuilder.boxShadow(
    color: Color,
    offsetX: Length = 0.rem,
    offsetY: Length = 0.rem,
    blurRadius: Length = 0.rem,
    spreadRadius: Length? = null
) {
    val parts = mutableListOf("$offsetX", "$offsetY", "$blurRadius")
    spreadRadius?.let { parts.add("$it") }
    parts.add("$color")
    asDynamic().boxShadow = parts.joinToString(" ")
}


// ---- Specificity increase ----

fun PropertiesBuilder.specific(count: Int = 1, block: PropertiesBuilder.() -> Unit) {
    val selector = "&".repeat(count + 1)
    selector { block() }
}


// ---- Transform helpers ----

class TransformBuilder {
    private val transforms = mutableListOf<String>()

    fun translateX(value: Length) { transforms.add("translateX($value)") }
    fun translateY(value: Length) { transforms.add("translateY($value)") }
    fun translate(x: Length, y: Length) { transforms.add("translate($x, $y)") }
    fun rotate(angle: String) { transforms.add("rotate($angle)") }
    fun skew(x: String, y: String) { transforms.add("skew($x, $y)") }
    fun scale(value: Double) { transforms.add("scale($value)") }

    override fun toString(): String = transforms.joinToString(" ")
}

fun PropertiesBuilder.transform(block: TransformBuilder.() -> Unit) {
    val builder = TransformBuilder()
    builder.block()
    asDynamic().transform = builder.toString()
}


// ---- Font weight constants ----

val FontWeight.Companion.hairline get() = FontWeight(100)
val FontWeight.Companion.ultraLight get() = FontWeight(200)
val FontWeight.Companion.light get() = FontWeight(300)
val FontWeight.Companion.regular get() = FontWeight(400)
val FontWeight.Companion.medium get() = FontWeight(500)
val FontWeight.Companion.semiBold get() = FontWeight(600)
val FontWeight.Companion.extraBold get() = FontWeight(800)
val FontWeight.Companion.black get() = FontWeight(900)


// ---- Quoted string for content property ----

fun quoted(value: String): String = "\"$value\""
