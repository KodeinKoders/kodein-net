package net.kodein.utils

import kotlinx.css.Color
import kotlinx.html.SVG
import kotlinx.html.unsafe
import net.kodein.utils.SvgPath.Companion.build
import styled.StyledDOMBuilder


var SVG.viewBox : String?
    get() = attributes["viewBox"]
    set(newValue) { if (newValue != null) attributes["viewBox"] = newValue else attributes.remove("viewBox") }

fun SVG.viewBox(x: Int, y: Int, width: Int, height: Int) { viewBox = "$x $y $width $height" }

var SVG.preserveAspectRatio : String?
    get() = attributes["preserveAspectRatio"]
    set(newValue) { if (newValue != null) attributes["preserveAspectRatio"] = newValue else attributes.remove("preserveAspectRatio") }


typealias SvgPathBuilder = SvgPath.() -> Unit

class SvgPath {

    private val commands = ArrayList<String>()

    fun moveTo(x: Int, y: Int) { commands.add("M$x,$y") }
    fun moveOf(dx: Int, dy: Int) { commands.add("m$dx,$dy") }

    fun lineTo(x: Int, y: Int) { commands.add("L$x,$y") }
    fun lineOf(dx: Int, dy: Int) { commands.add("l$dx,$dy") }

    fun horizontalLineTo(x: Int) { commands.add("H$x") }
    fun horizontalLineOf(dx: Int) { commands.add("h$dx") }

    fun verticalLineTo(y: Int) { commands.add("V$y") }
    fun verticalLineOf(dy: Int) { commands.add("V$dy") }

    fun closePath() { commands.add("Z") }

    companion object {
        fun build(block: SvgPath.() -> Unit): String =
                SvgPath().apply(block).commands.joinToString(" ")
    }

}


class SvgDrawer(private val str: StringBuilder) {
    fun path(fill: Color? = null, path: SvgPathBuilder) {
        str.append("<path d=\"${SvgPath.build(path)}\" ")
        if (fill != null) str.append("fill=\"$fill\" ")
        str.append("/>")
    }
}

fun StyledDOMBuilder<SVG>.draw(builder: SvgDrawer.() -> Unit) {
    val str = StringBuilder()
    SvgDrawer(str).apply(builder)
    attrs.unsafe {
        +str.toString()
    }
}
