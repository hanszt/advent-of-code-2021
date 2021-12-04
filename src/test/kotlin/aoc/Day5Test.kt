package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day5.part1("input/day5test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day5.part1("input/day5.txt").also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day5.part2("input/day5test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day5.part2("input/day5.txt").also(::println))
}
