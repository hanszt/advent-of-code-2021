package aoc

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day9Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day9.part1("input/day9test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day9.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day9.part2("input/day9test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day9.part2().also(::println))
}
