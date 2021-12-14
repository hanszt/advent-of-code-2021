package aoc

import utils.nanoTimeToFormattedDuration
import utils.readTextFromResource

fun main() {
    println(RED + readTextFromResource("/title.txt") + RESET)
    sequenceOf(
        Day01SonarSweep,
        Day02Dive,
        Day03BinaryDiagnostic,
        Day04GiantSquid,
        Day05HydrothermalVenture,
        Day06LanternFish,
        Day07TheTreacheryOfWales,
        Day08SevenSegmentSearch,
        Day09SmokeBasin,
        Day10SyntaxScoring,
        Day11DumboOctopus,
        Day12PassagePathing,
        Day13TransparentOrigami,
        Day14ExtendedPolymerization,
        Day15,
        Day16,
        Day17,
        Day18,
        Day19,
        Day20,
        Day21,
        Day22,
        Day23,
        Day24,
        Day25,
    ).flatMap(ChallengeDay::runParts)
        .forEach(::println)
    println(GREEN + readTextFromResource("/banner.txt") + RESET)
}

private const val RESET = "\u001B[0m"
private const val RED = "\u001B[31m"
private const val GREEN = "\u001B[32m"
private const val YELLOW = "\u001B[33m"
private const val CYAN = "\u001B[36m"
private const val BRIGHT_BLUE = "\u001B[94m"

private val colors = listOf(YELLOW, RESET, GREEN, RESET, CYAN, RESET, BRIGHT_BLUE, RESET)

internal data class AocResult(val name: String, val result: Result<String>, val solveTimeNanos: Long) {

    private val color = name.slice(3..4).toInt().toColor(colors)

    private fun Int.toColor(colors: List<String>) = if (result.isSuccess) colors[this % colors.size] else RED

    override fun toString(): String = "$color%-40s Result: %-40s Elapsed time: %-7s"
        .format(name, result.getOrElse { "Failure: ${it.message}" }, solveTimeNanos.nanoTimeToFormattedDuration())
}
