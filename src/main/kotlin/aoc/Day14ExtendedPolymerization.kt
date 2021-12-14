package aoc

import utils.splitByBlankLine
import java.io.File

internal object Day14ExtendedPolymerization : ChallengeDay {

    fun part1(path: String): Int {
        val (instructions, polymer) = parseInput(path)
        var list = polymer
        for (step in 1..10) {
            list = applyStep(list.toMutableList(), instructions)
        }
        val groupBy = list.groupBy { it }
        return groupBy.values.maxOf(List<Char>::size) - groupBy.values.minOf(List<Char>::size)
    }

    private fun parseInput(path: String): Pair<List<Pair<String, Char>>, List<Char>> {
        val (polymerTemplate, insertions) = File(path).readText().splitByBlankLine()
        val instructions = insertions.lines()
            .map { it.split(" -> ").let { adj -> adj.first() to adj.last().last() } }
        return Pair(instructions, polymerTemplate.toList())
    }

    private fun applyStep(polymer: MutableList<Char>, instructions: List<Pair<String, Char>>): MutableList<Char> {
        val newPolymer = polymer.toMutableList()
        while (newPolymer.size < polymer.size * 2 - 1) {
            for (index in 0 until polymer.size - 1) {
                val toCheck = polymer[index].toString() + polymer[index + 1]
                for ((toMatch, toInsert) in instructions) {
                    if (toCheck == toMatch) {
                        newPolymer.add(index + (newPolymer.size - polymer.size) + 1, toInsert)
                        break
                    }
                }
            }
        }
        return newPolymer
    }

    fun part2(path: String): Long = parseInput(path)
        .let { (instructions, initPolymer) ->
            countMostAndLeastCommon(initPolymer, instructions).let { (most, least) -> most - least }
        }

    private fun countMostAndLeastCommon(polymer: List<Char>, instructions: List<Pair<String, Char>>): Pair<Long, Long> {
        var pairToCount = mutableMapOf<String, Long>()
        for (index in 0 until polymer.size - 1) {
            val pair = polymer[index].toString() + polymer[index + 1].toString()
            pairToCount.putIfAbsent(pair, 0)
            pairToCount.computeIfPresent(pair) { _, count -> count + 1 }
        }
        for (step in 1..40) {
            pairToCount = applyStepPart2(instructions, pairToCount)
        }
        val charToCount = toCharCountMap(pairToCount, polymer.first())
        return charToCount.values.maxOf { it } to charToCount.values.minOf { it }
    }

    private fun applyStepPart2(
        instructions: List<Pair<String, Char>>,
        pairToCount: Map<String, Long>
    ): MutableMap<String, Long> {
        val newPairToCountMap = mutableMapOf<String, Long>()
        for ((pair, toInsert) in instructions) {
            val newPair1 = pair.first().plus(toInsert.toString())
            val newPair2 = toInsert.toString().plus(pair.last())
            pairToCount[pair]?.let { count ->
                newPairToCountMap.compute(newPair1) { _, newCount -> if (newCount == null) count else newCount + count }
                newPairToCountMap.compute(newPair2) { _, newCount -> if (newCount == null) count else newCount + count }
            }
        }
        return newPairToCountMap
    }

    private fun toCharCountMap(pairToCount: Map<String, Long>, first: Char): MutableMap<Char, Long> {
        val charToCount = mutableMapOf(first to 1L)
        for ((pair, pairCount) in pairToCount) {
            val chars = pair.toList()
            val last = chars.last()
            charToCount.compute(last) { _, charCount -> if (charCount == null) pairCount else charCount + pairCount }
        }
        return charToCount
    }

    override fun part1() = part1("input/day14.txt")
    override fun part2() = part2("input/day14.txt")
}
