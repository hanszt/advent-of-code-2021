package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day14ExtendedPolymerizationTest {

    @Test
    fun `part 1 test input`() = assertEquals(1588, Day14ExtendedPolymerization.part1("input/day14test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(4244, Day14ExtendedPolymerization.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(2188189693529, Day14ExtendedPolymerization.part2("input/day14test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(4807056953866, Day14ExtendedPolymerization.part2().also(::println))
}
