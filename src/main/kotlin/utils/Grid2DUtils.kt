package utils

/**
 * from list of strings to grid converters
 */
fun List<String>.toIntGrid(regex: Regex): Array<IntArray> =
    map { it.trim().split(regex).map(String::toInt).toIntArray() }.toTypedArray()

fun List<String>.toIntGrid(): Array<IntArray> = map { it.map(Char::digitToInt).toIntArray() }.toTypedArray()

inline fun <reified T> List<String>.toGridOf(regex: Regex, mapper: (String) -> T): Array<Array<T>> =
    map { it.split(regex).map { char -> mapper(char) }.toTypedArray() }.toTypedArray()

inline fun <reified T> List<String>.toGridOf(mapper: (Char) -> T): Array<Array<T>> =
    map { it.map { char -> mapper(char) }.toTypedArray() }.toTypedArray()

inline fun <T, reified R> Array<Array<T>>.toGridOf(transform: (T) -> R): Array<Array<R>> =
    map { it.map { value -> transform(value) }.toTypedArray() }.toTypedArray()

inline fun <reified R> Array<IntArray>.toGridOf(transform: (Int) -> R): Array<Array<R>> =
    map { it.map { value -> transform(value) }.toTypedArray() }.toTypedArray()

inline fun Array<IntArray>.toIntGridOf(transform: (Int) -> Int): Array<IntArray> =
    map { it.map { value -> transform(value) }.toIntArray() }.toTypedArray()

inline fun <reified T> Array<Array<T>>.copyGrid() = map(Array<T>::copyOf).toTypedArray()

/**
 * mapping, filtering, actions and matching
 */
inline fun <T, reified R> Array<Array<T>>.mapByPoint(transform: (Int, Int) -> R): Array<Array<R>> =
    indices.map { y -> this[y].indices.map { x -> transform(x, y) }.toTypedArray() }.toTypedArray()

inline fun <T> Array<Array<T>>.anyInGrid(predicate: (T) -> Boolean) = any { it.any(predicate) }

inline fun Array<IntArray>.allInGrid(predicate: (Int) -> Boolean) = all { it.all(predicate) }

inline fun <T> Array<Array<T>>.forEachInGrid(action: (T) -> Unit) =
    forEach { row -> row.forEach { value -> action(value) } }

inline fun <T> Array<Array<T>>.forEachPointAndValue(action: (Int, Int, T) -> Unit) =
    withIndex().forEach { (y, row) -> row.withIndex().forEach { (x, value) -> action(x, y, value) } }

inline fun <T> Array<Array<T>>.forEachPoint(action: (Int, Int) -> Unit) =
    indices.forEach { y -> this[0].indices.forEach { x -> action(x, y) } }

inline fun Array<IntArray>.forEachPoint(action: (Int, Int) -> Unit) =
    indices.forEach { y -> this[0].indices.forEach { x -> action(x, y) } }

inline fun Array<IntArray>.forEachPointAndValue(action: (Int, Int, Int) -> Unit) =
    withIndex().forEach { (y, row) -> row.withIndex().forEach { (x, value) -> action(x, y, value) } }

/**
 * Rotation and mirroring
 *
 */
fun <T> List<List<T>>.rotate(): List<List<T>> =
    this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] } }

fun <T> List<List<T>>.rotateCc(): List<List<T>> =
    this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] } }

fun List<String>.rotated(): List<String> =
    this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] }.joinToString("") }

fun List<String>.rotatedCc(): List<String> =
    this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] }.joinToString("") }

inline fun <reified T> Array<Array<T>>.rotated(): Array<Array<T>> =
    this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] }.toTypedArray() }.toTypedArray()

inline fun <reified T> Array<Array<T>>.rotatedCc(): Array<Array<T>> =
    this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] }.toTypedArray() }.toTypedArray()

fun Array<IntArray>.rotated(): Array<IntArray> =
    this[0].indices.map { col -> indices.reversed().map { row -> this[row][col] }.toIntArray() }.toTypedArray()

fun Array<IntArray>.rotatedCc(): Array<IntArray> =
    this[0].indices.reversed().map { col -> indices.map { row -> this[row][col] }.toIntArray() }.toTypedArray()

fun Array<IntArray>.mirroredHorizontally(): Array<IntArray> =
    indices.reversed().map { row -> this[row] }.toTypedArray()

fun Array<IntArray>.mirroredVertically(): Array<IntArray> =
    map { row -> row.indices.reversed().map { col -> row[col] }.toIntArray() }.toTypedArray()

inline fun <reified T> Array<Array<T>>.mirroredHorizontally(): Array<Array<T>> =
    map { row -> row.indices.reversed().map { col -> row[col] }.toTypedArray() }.toTypedArray()

inline fun <reified T> Array<Array<T>>.mirroredVertically(): Array<Array<T>> =
    indices.reversed().map { row -> this[row] }.toTypedArray()

/**
 * Grid as string
 *
 * Functions to convert a grid to a string representation
 */
fun Array<IntArray>.gridAsString(alignment: Int = 2, separator: String = "") =
    map { row -> row.joinToString(separator) { col -> "%${alignment}d".format(col) } }.joinToString("\n") { it }

inline fun <T, R> Array<Array<T>>.gridAsString(
    alignment: Int = 2,
    separator: String = "",
    crossinline selector: (T) -> R
) =
    map { row -> row.joinToString(separator) { col -> "%${alignment}s".format(selector(col)) } }
        .joinToString("\n") { it }

fun <T> Array<Array<T>>.gridAsString(alignment: Int = 2, separator: String = "") =
    gridAsString(alignment, separator) { it }
