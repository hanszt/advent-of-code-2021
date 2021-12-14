package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day15Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day15.part1("input/day15test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day15.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day15.part2("input/day15test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day15.part2().also(::println))
}
