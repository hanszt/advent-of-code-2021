package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day25SeaCucumberTest {

    @Test
    fun `part 1 test input`() = assertEquals(0, Day25SeaCucumber.part1("input/day25test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(0, Day25SeaCucumber.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(0, Day25SeaCucumber.part2("input/day25test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(0, Day25SeaCucumber.part2().also(::println))
}
