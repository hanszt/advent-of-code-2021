package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day6Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day6.part1("input/day6test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day6.part1("input/day6.txt").also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day6.part2("input/day6test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day6.part2("input/day6.txt").also(::println))
}
