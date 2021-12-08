package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day7TheTreacheryOfWalesTest {

    @Test
    fun testPart1testInput() = assertEquals(37, Day7TheTreacheryOfWales.part1("input/day7test.txt"))

    @Test
    fun testPart1() = assertEquals(340987, Day7TheTreacheryOfWales.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(168, Day7TheTreacheryOfWales.part2("input/day7test.txt"))

    @Test
    fun testPart2() = assertEquals(96987874, Day7TheTreacheryOfWales.part2().also(::println))
}
