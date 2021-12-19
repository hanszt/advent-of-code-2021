@file:Suppress("unused")

package utils

const val RESET = "\u001B[0m"
const val RED = "\u001B[31m"
const val GREEN = "\u001B[32m"
const val BRIGHT_GREEN = "\u001b[32;1m"
const val YELLOW = "\u001B[33m"
const val CYAN = "\u001B[36m"
const val BRIGHT_BLUE = "\u001B[94m"
const val BRIGHT_YELLOW = "\u001B[93m"
const val YELLOW_BG = "\u001B[43m"
const val BROWN_BG = "\u001B[48;5;88m"
const val ICY_BG = "\u001B[48;5;87m"
const val SIXTEEN_BIT = 16

val colorTable16Bit = to16bitAnsiColorTabel()

val bgColorTable16Bit = to16bitAnsiColorTabel(true)

val primaryColorList = listOf(GREEN, RESET, BRIGHT_BLUE, RESET, YELLOW, RESET, CYAN, RESET)

fun String.ofColor(ansiCode: String, size: Int = 1) = "${ansiCode}%${size}s${RESET}".format(this)

fun String.withColors(textColor: String, backgroundColor: String, size: Int = 1) =
    "${backgroundColor}${textColor}%${size}s${RESET}".format(this)

fun to16bitAnsiColorTabel(bgColors: Boolean = false): Map<Int, String> {
    return (0 until SIXTEEN_BIT).flatMap { i ->
        (0 until SIXTEEN_BIT).map { i * SIXTEEN_BIT + it }
    }.associateBy(::it) { "\u001b[${if (bgColors) 4 else 3}8;5;${it}m" }
}

fun random16BitColor() = colorTable16Bit.values.random()

fun randomPrimaryColor() = primaryColorList.random()

fun randomBgColor() = to16bitAnsiColorTabel().values.random()

fun randomColorList(size: Int, background: Boolean = false): List<String> =
    (0 until size).map { (if (background) bgColorTable16Bit else colorTable16Bit).values.random() }
