package net.kodein.utils

val illustrationWidths = listOf(960, 1200, 1440, 1680, 1920, 2400, 2880, 3360, 3840)

data class Illus(
    val title: Title,
    val position: Position = Position.NONE,
    val alignment: Alignment = Alignment.CENTER
) {

    enum class Title(private val str: String) {
        SERVICES("services"),
        TRAINING("training"),
        OSS("open-source"),
        TEAM("team");

        override fun toString(): String = str
    }
    enum class Position { LEFT, CENTER, RIGHT, NONE }
    enum class Alignment { LEFT, CENTER, RIGHT }
}
