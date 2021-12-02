package aoc

import java.io.File

object Day1SonarSweep {

    fun calculatePart1(filePath: String) = File(filePath).readLines().map(String::toInt).let(::calculateNrIncreases)

    private fun calculateNrIncreases(depths: List<Int>): Int {
        var depthIncreaseCount = 0
        for (index in 0 until depths.size - 1) {
            if (depths[index] < depths[index + 1]) {
                depthIncreaseCount++
            }
        }
        return depthIncreaseCount
    }

    fun calculatePart2(filePath: String) = File(filePath).readLines().map(String::toInt).let(::calculateNrSumIncreases)

    private fun calculateNrSumIncreases(depths: List<Int>): Int {
        var depthIncreaseCount = 0
        for (index in 0 until depths.size - 3) {
            val first = depths[index]
            val second = depths[index + 1]
            val third = depths[index + 2]
            val fourth = depths[index + 3]
            if (first + second + third < second + third + fourth) {
                depthIncreaseCount++
            }
        }
        return depthIncreaseCount
    }
}
