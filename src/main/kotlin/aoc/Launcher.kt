package aoc

import utils.nanoTimeToFormattedDuration

fun main() = sequenceOf(
    Day1SonarSweep,
    Day2Dive,
    Day3BinaryDiagnostic,
    Day4GiantSquid,
    Day5HydrothermalVenture,
    Day6LanternFish,
    Day7TheTreacheryOfWales,
    Day8,
).flatMap(ChallengeDay::results)
    .sortedBy(Result::solveTimeNanos)
    .forEach(Result::print)

data class Result(val name: String, val result: Number, val solveTimeNanos: Long) {

    fun print() = println("%-40s Result: %-20s Elapsed Time: %-7s"
        .format(name, result, solveTimeNanos.nanoTimeToFormattedDuration()))
}
