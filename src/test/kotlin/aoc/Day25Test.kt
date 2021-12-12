package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day25Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day25.part1("input/day25test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day25.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day25.part2("input/day25test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day25.part2().also(::println))
}
