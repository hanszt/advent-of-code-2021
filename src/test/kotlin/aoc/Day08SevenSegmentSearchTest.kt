package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day08SevenSegmentSearchTest {

    @Test
    fun `part 1 test input`() = assertEquals(26, Day08SevenSegmentSearch.part1("input/day8test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(369, Day08SevenSegmentSearch.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(61229, Day08SevenSegmentSearch.part2("input/day8test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(1031553, Day08SevenSegmentSearch.part2().also(::println))
}
