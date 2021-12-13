package aoc

import aoc.Day05HydrothermalVenture.asGrid
import aoc.Day05HydrothermalVenture.countIntersections
import aoc.Day05HydrothermalVenture.toVentureLines
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.gridAsString
import java.io.File

internal class Day05HydrothermalVentureTest {

    @Test
    fun testPart1testInput() = assertEquals(5, Day05HydrothermalVenture.part1("input/day5test.txt"))

    @Test
    fun testPart1() = assertEquals(7085, Day05HydrothermalVenture.part1().also(::println))

    @Test
    fun testPart2testInput() {
        val lines = File("input/day5test.txt").toVentureLines()
        val grid = lines.asGrid()
        println(grid.gridAsString(alignment = 2))
        val nrOfIntersections = grid.countIntersections()
        assertEquals(12, nrOfIntersections)
    }

    @Test
    fun testPart2() = assertEquals(20271, Day05HydrothermalVenture.part2().also(::println))
}
