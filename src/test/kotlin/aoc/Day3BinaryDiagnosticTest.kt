package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day3BinaryDiagnosticTest {

    @Test
    fun testPart1TestInput() = assertEquals(198, Day3BinaryDiagnostic.part1("input/day3test.txt"))

    @Test
    fun testPart1() = assertEquals(3633500, Day3BinaryDiagnostic.part1().also(::println))

    @Test
    fun testPart2TestInput() = assertEquals(230, Day3BinaryDiagnostic.part2("input/day3test.txt"))

    @Test
    fun testPart2() = assertEquals(4550283, Day3BinaryDiagnostic.part2().also(::println))
}
