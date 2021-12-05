package utils

val ONE_OR_MORE_WHITE_SPACES = Regex("\\s+")

fun List<List<Int>>.rotatedClockWise(): Array<IntArray> {
    val rotated = Array(size) { IntArray(size) }
    for (row in indices) {
        for (col in indices) {
            rotated[row][col] = this[size - col - 1][row]
        }
    }
    return rotated
}

fun CharSequence.toIntGrid(): List<List<Int>> = split(Regex("\\n"))
    .map { it.trim().split(ONE_OR_MORE_WHITE_SPACES).map(String::toInt) }

fun CharSequence.splitByEmptyLine() = split(Regex("(?m)^\\s*$")).map(String::trim)

fun print2DGrid(grid: Array<IntArray>) = grid.forEach { row ->
    row.forEach { print("$it ") }
    println()
}
