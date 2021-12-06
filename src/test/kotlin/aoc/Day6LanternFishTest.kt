package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day6LanternFishTest {

    @Test
    fun testPart1testInput() = assertEquals(5934.toBigInteger(), Day6LanternFish.part1("input/day6test.txt"))

    @Test
    fun testPart1() = assertEquals(386640.toBigInteger(), Day6LanternFish.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(26984457539.toBigInteger(), Day6LanternFish.part2("input/day6test.txt"))

    @Test
    fun testPart2() = assertEquals(1733403626279.toBigInteger(), Day6LanternFish.part2().also(::println))
}
