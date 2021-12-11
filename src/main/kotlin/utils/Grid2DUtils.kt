package utils

fun <T> List<List<T>>.rotate(counterClockWise: Boolean = false): List<List<T>> =
    if (counterClockWise)
        this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] } }
    else this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] } }

fun List<String>.rotated(counterClockWise: Boolean = false): List<String> =
    if (counterClockWise)
        this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] }.joinToString("") }
    else this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] }.joinToString("") }

inline fun <reified T> Array<Array<T>>.rotated(counterClockWise: Boolean = false): Array<Array<T>> =
    (if (counterClockWise)
        this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] }.toTypedArray() }
    else this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] }.toTypedArray() })
        .toTypedArray()

inline fun <reified T> Array<Array<T>>.copyGrid() = map(Array<T>::copyOf).toTypedArray()

fun Array<IntArray>.rotated(counterClockWise: Boolean = false): Array<IntArray> =
    (if (counterClockWise)
        this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] }.toIntArray() }
    else this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] }.toIntArray() })
        .toTypedArray()

fun CharSequence.toIntGrid(splitter: Regex = oneOrMoreWhiteSpaces): Array<IntArray> = split(Regex("\\n"))
    .map { it.trim().split(splitter).map(String::toInt).toIntArray() }.toTypedArray()

fun List<String>.toIntGrid(): Array<IntArray> = map { it.map(Char::digitToInt).toIntArray() }.toTypedArray()

fun Array<IntArray>.printAsGrid(spacing: Int = 2, delimiter: String = "") = forEach { row ->
    row.forEach { col -> print("%${spacing}d$delimiter".format(col)) }
    println()
}

fun <T> Array<Array<T>>.printAsGrid(spacing: Int = 2, delimiter: String = "", select: (T) -> String = { it.toString() }) =
    forEach { row -> row.forEach { col -> print("%${spacing}s$delimiter".format(select(col))) }
    println()
}
