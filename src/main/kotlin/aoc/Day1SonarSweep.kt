package aoc

import java.io.File

object Day1SonarSweep {

    fun part1(filePath: String) = File(filePath).readLines().map(String::toInt).let(::calculateNrIncreases)

    private fun calculateNrIncreases(depths: List<Int>): Int =
        (0 until depths.size - 1).count { depths[it] < depths[it + 1] }

    fun part2(filePath: String) = File(filePath).readLines().map(String::toInt).let(::calculateNrSumIncreases)

    private fun calculateNrSumIncreases(depths: List<Int>): Int = (0 until depths.size - 3).count {
            val window1 = depths[it] + depths[it + 1] + depths[it + 2]
            val window2 = depths[it + 1] + depths[it + 2] + depths[it + 3]
            window1 < window2
        }
}
