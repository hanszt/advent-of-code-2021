package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day20Test {

    @Test
    fun testPart1testInput() = Assertions.assertEquals(0, Day20.part1("input/day20test.txt"))

    @Test
    fun testPart1() = Assertions.assertEquals(0, Day20.part1().also(::println))

    @Test
    fun testPart2testInput() = Assertions.assertEquals(0, Day20.part2("input/day20test.txt"))

    @Test
    fun testPart2() = Assertions.assertEquals(0, Day20.part2().also(::println))
}
