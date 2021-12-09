package aoc

import utils.nanoTimeToFormattedDuration
import utils.readTextFromResource

private const val RESET = "\u001B[0m"
private const val RED = "\u001B[31m"
private const val GREEN = "\u001B[32m"

fun main() {
    println(RED + readTextFromResource("/title.txt") + RESET)
    listOf(
        Day1SonarSweep,
        Day2Dive,
        Day3BinaryDiagnostic,
        Day4GiantSquid,
        Day5HydrothermalVenture,
        Day6LanternFish,
        Day7TheTreacheryOfWales,
        Day8SevenSegmentSearch,
    ).flatMap(ChallengeDay::results)
        .sortedBy(Result::name)
        .forEach(Result::print)
    println(GREEN + readTextFromResource("/banner.txt") + RESET)
}

data class Result(val name: String, val result: Number, val solveTimeNanos: Long) {

    fun print() = println("%-40s Result: %-20s Elapsed time: %-7s"
        .format(name, result, solveTimeNanos.nanoTimeToFormattedDuration()))
}
