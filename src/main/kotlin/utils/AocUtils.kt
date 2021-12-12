package utils

val oneOrMoreWhiteSpaces = "\\s+".toRegex()
val camelRegex = "(?<=[a-zA-Z0-9])[A-Z]".toRegex()

fun <A, B, R> Pair<A, B>.mapFirst(mapToR: (A) -> R): Pair<R, B> = Pair(mapToR(first), second)

fun <A, B, R> Pair<A, B>.mapSecond(mapToR: (B) -> R): Pair<A, R> = Pair(first, mapToR(second))

fun <A, R> Pair<A, A>.mapBoth(mapToR: (A) -> R): Pair<R, R> = Pair(mapToR(first), mapToR(second))

fun <T> Iterable<T>.toEnds() = first() to last()

fun <T, R> Iterable<T>.endsTo(mapToR: (T, T) -> R) = mapToR(first(), last())

fun CharSequence.containsAllCharsOf(other: CharSequence) = toSet().containsAll(other.toSet())

fun CharSequence.isNaturalNumber() = "\\d+".toRegex().matches(this)

fun CharSequence.containsNoDigits() = "\\D+".toRegex().matches(this)

fun CharSequence.splitByBlankLine(): List<String> = split(Regex("(?m)^\\s*$")).map(String::trim)

fun String.camelCaseToSentence(): String = camelRegex.replace(this) { " ${it.value}" }
    .lowercase().replaceFirstChar(Char::uppercase)

fun Long.nanoTimeToFormattedDuration(spacer: Int = 7, decimalPlaces: Int = 3): String {
    val timeSpacer: Int = spacer + decimalPlaces
    return when {
        this < 1e3 -> "$timeSpacer$this ns"
        this < 1e6 -> "%$timeSpacer.${decimalPlaces}f µs".format(this / 1e3)
        this < 1e9 -> "%$timeSpacer.${decimalPlaces}f ms".format(this / 1e6)
        else -> "%$timeSpacer.${decimalPlaces}f s".format(this / 1e9)
    }
}

fun <T, R> Iterable<T>.printAsList(delimiter: String = "", selector: (T) -> R) =
    forEach { print("${selector(it)}$delimiter") }

fun <T, R> Iterable<Iterable<T>>.printAsListOfLists(delimiter: String = "", selector: (T) -> R) =
    forEach { row ->
        row.printAsList(delimiter, selector)
        println()
    }
