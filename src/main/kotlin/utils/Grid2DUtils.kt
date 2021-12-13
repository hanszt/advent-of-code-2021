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

fun Array<IntArray>.mirrored(horizontally: Boolean = false): Array<IntArray> =
    if (horizontally) map { row -> row.indices.reversed().map { col -> row[col] }.toIntArray() }.toTypedArray()
    else indices.reversed().map { row -> this[row] }.toTypedArray()

inline fun <reified T> Array<Array<T>>.mirrored(horizontally: Boolean = false): Array<Array<T>> =
    if (horizontally) map { row -> row.indices.reversed().map { col -> row[col] }.toTypedArray() }.toTypedArray()
    else indices.reversed().map { row -> this[row] }.toTypedArray()

fun CharSequence.toIntGrid(splitter: Regex): Array<IntArray> = lines()
    .map { it.trim().split(splitter).map(String::toInt).toIntArray() }.toTypedArray()

fun CharSequence.toIntGrid(): Array<IntArray> = lines().toIntGrid()

fun List<String>.toIntGrid(): Array<IntArray> = map { it.map(Char::digitToInt).toIntArray() }.toTypedArray()

inline fun <reified T> CharSequence.toGridOf(mapper: (Char) -> T): Array<Array<T>> = lines().toGridOf(mapper)

inline fun <reified T> CharSequence.toGridOf(delimiter: Regex, mapper: (String) -> T): Array<Array<T>> =
    lines().toGridOf(delimiter, mapper)

inline fun <reified T> List<String>.toGridOf(delimiter: Regex, mapper: (String) -> T): Array<Array<T>> =
    map { it.split(delimiter).map { char -> mapper(char) }.toTypedArray() }.toTypedArray()

inline fun <reified T> List<String>.toGridOf(mapper: (Char) -> T): Array<Array<T>> =
    map { it.map { char -> mapper(char) }.toTypedArray() }.toTypedArray()

inline fun <T, reified R> Array<Array<T>>.toGridOf(mapper: (T) -> R): Array<Array<R>> =
    map { it.map { value -> mapper(value) }.toTypedArray() }.toTypedArray()

inline fun <T, reified R> Array<Array<T>>.mapByPoint(transform: (Int, Int) -> R): Array<Array<R>> =
    indices.map { y -> this[y].indices.map { x -> transform(x, y) }.toTypedArray() }.toTypedArray()

inline fun <reified R> Array<IntArray>.toGridOf(mapper: (Int) -> R): Array<Array<R>> =
    map { it.map { value -> mapper(value) }.toTypedArray() }.toTypedArray()

inline fun <T> Array<Array<T>>.anyInGrid(predicate: (T) -> Boolean) = any { it.any(predicate) }

inline fun Array<IntArray>.allInGrid(predicate: (Int) -> Boolean) = all { it.all(predicate) }

inline fun <T> Array<Array<T>>.forEachInGrid(consume: (T) -> Unit) =
    forEach { row -> row.forEach { value -> consume(value) } }

inline fun <T> Array<Array<T>>.forEachPointAndValue(consumeValue: (Int, Int, T) -> Unit) =
    withIndex().forEach { (y, row) -> row.withIndex().forEach { (x, value) -> consumeValue(x, y, value) } }

inline fun <T> Array<Array<T>>.forEachPoint(consumeValue: (Int, Int) -> Unit) =
    indices.forEach { y -> this[0].indices.forEach { x -> consumeValue(x, y) } }

fun Array<IntArray>.gridAsString(alignment: Int = 2, separator: String = "") =
    map { row -> row.joinToString(separator) { col -> "%${alignment}d".format(col) } }.joinToString("\n") { it }

fun <T, R> Array<Array<T>>.gridAsString(alignment: Int = 2, separator: String = "", selector: (T) -> R) =
    map { row -> row.joinToString(separator) { col -> "%${alignment}s".format(selector(col)) } }
        .joinToString("\n") { it }

fun <T> Array<Array<T>>.gridAsString(alignment: Int = 2, separator: String = "") =
    gridAsString(alignment, separator) { it }
