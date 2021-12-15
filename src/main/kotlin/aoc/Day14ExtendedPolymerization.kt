package aoc

import utils.reduce
import utils.splitByBlankLine
import java.io.File

internal object Day14ExtendedPolymerization : ChallengeDay {

    fun part1(path: String) = solve(path, 10)

    private fun parseInput(path: String): Pair<List<Pair<String, Char>>, String> {
        val (polymerTemplate, insertions) = File(path).readText().splitByBlankLine()
        val instructions = insertions.lines()
            .map { it.split(" -> ").let { (pair, toBeInserted) -> pair to toBeInserted.first() } }
        return instructions to polymerTemplate
    }

    fun part2(path: String): Long = solve(path, 40)

    private fun solve(path: String, steps: Int) = parseInput(path)
        .let { (instructions, initPolymer) ->
            toPairToCountMap(instructions, initPolymer, steps)
                .toMaxAndMinCount(initPolymer.first())
                .reduce(Long::minus)
        }

    private fun toPairToCountMap(
        instructions: List<Pair<String, Char>>,
        polymer: String,
        steps: Int
    ): Map<String, Long> {
        var pairToCountMap = toInitPairToCountMap(polymer)
        for (step in 1..steps) {
            pairToCountMap = applyStep(instructions, pairToCountMap)
        }
        return pairToCountMap
    }

    private fun toInitPairToCountMap(polymer: String): MutableMap<String, Long> {
        val pairToCountMap = mutableMapOf<String, Long>()
        for (index in 0 until polymer.length - 1) {
            val pair = polymer[index].toString() + polymer[index + 1].toString()
            pairToCountMap.merge(pair, 1, Long::plus)
        }
        return pairToCountMap
    }

    private fun applyStep(
        instructions: List<Pair<String, Char>>,
        pairToCountMap: Map<String, Long>
    ): MutableMap<String, Long> {
        val newPairToCountMap = mutableMapOf<String, Long>()
        for ((instruction, toInsert) in instructions) {
            val newPair1 = instruction.first().plus(toInsert.toString())
            val newPair2 = toInsert.toString().plus(instruction.last())
            pairToCountMap[instruction]?.let { count ->
                newPairToCountMap.merge(newPair1, count, Long::plus)
                newPairToCountMap.merge(newPair2, count, Long::plus)
            }
        }
        return newPairToCountMap
    }

    private fun Map<String, Long>.toMaxAndMinCount(first: Char): Pair<Long, Long> {
        val charToCountMap = mutableMapOf(first to 1L)
        for ((pair, pairCount) in this) {
            charToCountMap.merge(pair.last(), pairCount, Long::plus)
        }
        return charToCountMap.values.run { maxOf { it } to minOf { it } }
    }

    override fun part1() = part1("input/day14.txt")
    override fun part2() = part2("input/day14.txt")
}
