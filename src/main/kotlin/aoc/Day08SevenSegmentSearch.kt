package aoc

import model.SignalEntry
import utils.endsTo
import java.io.File

internal object Day08SevenSegmentSearch : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines()
        .flatMap { it.toSignalEntry().fourDigitPatterns }
        .count { it.hasUniqueLength() }

    private fun String.hasUniqueLength() = (length == 2) or (length == 3) or (length == 4) or (length == 7)

    fun String.toSignalEntry(): SignalEntry =
        split('|').map { it.trim().split(' ') }.endsTo(::SignalEntry)

    fun part2(path: String): Int = File(path).readLines()
        .map { line -> line.toSignalEntry() }
        .map(SignalEntry::decodeNumber)
        .sum()

    override fun part1() = part1("input/day8.txt")
    override fun part2() = part2("input/day8.txt")
}
