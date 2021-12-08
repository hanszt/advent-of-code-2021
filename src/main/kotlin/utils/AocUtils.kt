package utils

val oneOrMoreWhiteSpaces = "\\s+".toRegex()
val camelRegex = "(?<=[a-zA-Z0-9])[A-Z0-9]".toRegex()

fun Array<IntArray>.rotatedClockWise(): Array<IntArray> {
    val rotated = Array(size) { IntArray(size) }
    for (row in indices) {
        for (col in indices) {
            rotated[row][col] = this[size - col - 1][row]
        }
    }
    return rotated
}

fun CharSequence.isNaturalNumber() = "\\d+".toRegex().matches(this)

fun CharSequence.containsNoDigits() = "\\D+".toRegex().matches(this)

fun CharSequence.toIntGrid(): Array<IntArray> = split(Regex("\\n"))
    .map { it.trim().split(oneOrMoreWhiteSpaces).map(String::toInt).toIntArray() }.toTypedArray()

fun CharSequence.splitByBlankLine() = split(Regex("(?m)^\\s*$")).map(String::trim)

fun Array<IntArray>.printAsGrid(spacing: Int, delimiter: String = "") = forEach { row ->
    row.forEach { print("%${spacing}d$delimiter".format(it)) }
    println()
}

fun String.camelCaseToSentence(): String {
    return camelRegex.replace(this) {
        " ${it.value}"
    }.lowercase().replaceFirstChar(Char::uppercase)
}

fun Long.nanoTimeToFormattedDuration(decimalPlaces: Int = 3): String = when {
    this < 1e3 -> "$this ns"
    this < 1e6 -> "%.${decimalPlaces}f µs".format(this / 1e3)
    this < 1e9 -> "%.${decimalPlaces}f ms".format(this / 1e6)
    else -> "%.${decimalPlaces}f s".format(this / 1e9)
}
