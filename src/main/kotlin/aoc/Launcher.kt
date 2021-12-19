package aoc

import utils.*

fun main() {
    println(readTextFromResource("/title.txt").ofColor(RED))
    val totalSolveTime = sequenceOf(
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
        Day15Chiton,
        Day16PacketDecoder,
        Day17TrickShot,
        Day18SnailFish,
        Day19BeaconScanner,
        Day20,
        Day21,
        Day22,
        Day23,
        Day24,
        Day25,
    ).flatMap(ChallengeDay::runParts)
        .onEach(::println)
        .sumOf(AocResult::solveTimeNanos)
    println("%nTotal solve time: %2.3f seconds%n".format(totalSolveTime / 1e9))
    println(readTextFromResource("/banner.txt").ofColor(GREEN))
}

internal data class AocResult(val name: String, val result: Result<String>, val solveTimeNanos: Long) {

    private val color = name.slice(3..4).toInt().toColor(primaryColorList)

    private fun Int.toColor(colors: List<String>) = if (result.isSuccess) colors[this % colors.size] else RED

    override fun toString(): String = "%-40s Result: %-42s Solve time: %-7s".ofColor(color)
        .format(name, result.getOrElse { "Failure: ${it.message}" }, solveTimeNanos.nanoTimeToFormattedDuration())
}
