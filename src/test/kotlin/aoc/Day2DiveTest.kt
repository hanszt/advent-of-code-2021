package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day2DiveTest {

    @Test
    fun testPart1Test() = assertEquals(150, Day2Dive.part1("input/day2test.txt"))

    @Test
    fun testPart1() {
        val part1 = Day2Dive.part1("input/day2.txt")
        println(part1)
        assertEquals(2019945, part1)
    }

    @Test
    fun testPart2Test() = assertEquals(900, Day2Dive.part2("input/day2test.txt"))

    @Test
    fun testPart2() {
        val result = Day2Dive.part2("input/day2.txt")
        println(result)
        assertEquals(1599311480, result)
    }
}
