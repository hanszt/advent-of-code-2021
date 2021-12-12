package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day13.part1("input/day13test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day13.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day13.part2("input/day13test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day13.part2().also(::println))
}
