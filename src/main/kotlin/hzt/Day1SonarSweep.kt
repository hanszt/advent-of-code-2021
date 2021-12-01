package hzt

import java.io.File

object Day1SonarSweep {

    fun calculatePart1(filePath: String): Int = calculateNrIncreases(File(filePath).readLines().map(String::toInt))

    private fun calculateNrIncreases(depths: List<Int>): Int {
        var amountOfIncreases = 0
        for (index in 0 until depths.size - 1) {
            val curDepth = depths[index]
            val nextDepth = depths[index + 1]
            if (nextDepth > curDepth) {
                amountOfIncreases++
            }
        }
        return amountOfIncreases
    }

    fun calculatePart2(filePath: String): Int = calculateNrSumIncreases(File(filePath).readLines().map(String::toInt))

    private fun calculateNrSumIncreases(depths: List<Int>): Int {
        var amountOfSumIncreases = 0
        for (index in 0 until depths.size - 3) {
            val first = depths[index]
            val second = depths[index + 1]
            val third = depths[index + 2]
            val fourth = depths[index + 3]
            if (first + second + third < second + third + fourth) {
                amountOfSumIncreases++
            }
        }
        return amountOfSumIncreases
    }
}
