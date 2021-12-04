package utils

fun List<List<Int>>.rotatedClockWise(): Array<IntArray> {
    val rotated = Array(size) { IntArray(size) }
    for (row in indices) {
        for (col in indices) {
            rotated[row][col] = this[size - col - 1][row]
        }
    }
    return rotated
}

fun String.toIntGrid(): List<List<Int>> = split(Regex("\\n"))
    .map { it.trim().split(Regex("\\s+")).map(String::toInt) }
