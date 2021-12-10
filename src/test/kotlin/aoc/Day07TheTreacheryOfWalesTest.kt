package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day07TheTreacheryOfWalesTest {

    @Test
    fun testPart1testInput() = assertEquals(37, Day07TheTreacheryOfWales.part1("input/day7test.txt"))

    @Test
    fun testPart1() = assertEquals(340987, Day07TheTreacheryOfWales.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(168, Day07TheTreacheryOfWales.part2("input/day7test.txt"))

    @Test
    fun testPart2() = assertEquals(96987874, Day07TheTreacheryOfWales.part2().also(::println))
}
