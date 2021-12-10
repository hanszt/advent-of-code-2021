package aoc

import java.io.File

object Day01SonarSweep : ChallengeDay {

    fun part1(filePath: String) = File(filePath).readLines().map(String::toInt).toDepthIncreaseCount()

    private fun List<Int>.toDepthIncreaseCount(): Int =
        (0 until size - 1).count { this[it] < this[it + 1] }

    fun part2(filePath: String) = File(filePath).readLines().map(String::toInt).toWindowIncreaseCount()

    private fun List<Int>.toWindowIncreaseCount(): Int = (0 until size - 3).count {
        val window1 = this[it] + this[it + 1] + this[it + 2]
        val window2 = this[it + 1] + this[it + 2] + this[it + 3]
        window1 < window2
    }

    override fun part1() = part1("input/day1.txt")
    override fun part2() = part2( "input/day1.txt")
}
