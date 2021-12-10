package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day08SevenSegmentSearchTest {

    @Test
    fun testPart1testInput() = assertEquals(26, Day08SevenSegmentSearch.part1("input/day8test.txt"))

    @Test
    fun testPart1() = assertEquals(369, Day08SevenSegmentSearch.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(61229, Day08SevenSegmentSearch.part2("input/day8test.txt"))

    @Test
    fun testPart2() = assertEquals(1031553, Day08SevenSegmentSearch.part2().also(::println))
}
