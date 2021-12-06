package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day8Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day8.part1("input/day8test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day8.part1("input/day8.txt").also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day8.part2("input/day8test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day8.part2("input/day8.txt").also(::println))
}
