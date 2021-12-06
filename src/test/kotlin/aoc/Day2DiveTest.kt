package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day2DiveTest {

    @Test
    fun testPart1TestInput() = assertEquals(150, Day2Dive.part1("input/day2test.txt"))

    @Test
    fun testPart1() = assertEquals(2019945, Day2Dive.part1().also(::println))

    @Test
    fun testPart2TestInput() = assertEquals(900, Day2Dive.part2("input/day2test.txt"))

    @Test
    fun testPart2() = assertEquals(1599311480, Day2Dive.part2().also(::println))
}
