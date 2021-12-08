package aoc

import utils.SignalEntry
import java.io.File

object Day8SevenSegmentSearch : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines()
        .flatMap { line -> line.toSignalEntry().fourDigitOutput }
        .count { it.hasUniqueLength() }

    private fun String.hasUniqueLength() = (length == 2) or (length == 3) or (length == 4) or (length == 7)

    fun String.toSignalEntry(): SignalEntry {
        return split('|').map { it.trim().split(' ') }.run { SignalEntry(first(), last()) }
    }

    fun part2(path: String): Int {
        return File(path).readLines()
            .map { line -> line.toSignalEntry() }
            .map(SignalEntry::decodeFourDigitOutPut)
            .sum()
    }

    override fun part1() = part1("input/day8.txt")
    override fun part2() = part2("input/day8.txt")


}


