package aoc

fun main() = sequenceOf(
    Day1SonarSweep,
    Day2Dive,
    Day3BinaryDiagnostic,
    Day4GiantSquid,
    Day5HydrothermalVenture,
    Day6LanternFish,
    Day7,
).flatMap(ChallengeDay::results)
    .sortedBy(Result::name)
    .forEach(Result::print)

data class Result(val name: String, val result: Number, val solveTimeMillis: Long) {

    fun print() = println("%-40s Result: %-20s Elapsed Time: %-7s ms".format(name, result, solveTimeMillis))
}
