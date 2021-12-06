package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day7Test {

    @Test
    fun testPart1testInput() = assertEquals(0, Day7.part1("input/day7test.txt"))

    @Test
    fun testPart1() = assertEquals(0, Day7.part1("input/day7.txt").also(::println))

    @Test
    fun testPart2testInput() = assertEquals(0, Day7.part2("input/day7test.txt"))

    @Test
    fun testPart2() = assertEquals(0, Day7.part2("input/day7.txt").also(::println))
}
