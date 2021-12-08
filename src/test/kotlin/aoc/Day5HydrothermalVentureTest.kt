package aoc

import aoc.Day5HydrothermalVenture.asGrid
import aoc.Day5HydrothermalVenture.countIntersections
import aoc.Day5HydrothermalVenture.toVentureLines
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.printAsGrid
import java.io.File

internal class Day5HydrothermalVentureTest {

    @Test
    fun testPart1testInput() = assertEquals(5, Day5HydrothermalVenture.part1("input/day5test.txt"))

    @Test
    fun testPart1() = assertEquals(7085, Day5HydrothermalVenture.part1().also(::println))

    @Test
    fun testPart2testInput() {
        val lines = File("input/day5test.txt").toVentureLines()
        val grid = lines.asGrid()
        grid.printAsGrid(delimiter = ",", spacing = 2)
        val nrOfIntersections = grid.countIntersections()
        assertEquals(12, nrOfIntersections)
    }

    @Test
    fun testPart2() = assertEquals(20271, Day5HydrothermalVenture.part2().also(::println))
}
