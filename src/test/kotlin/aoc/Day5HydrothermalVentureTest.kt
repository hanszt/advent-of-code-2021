package aoc

import aoc.Day5HydrothermalVenture.asGrid
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.print2DGrid

internal class Day5HydrothermalVentureTest {

    @Test
    fun testPart1testInput() = assertEquals(5, Day5HydrothermalVenture.part1("input/day5test.txt"))

    @Test
    fun testPart1() = assertEquals(7085, Day5HydrothermalVenture.part1("input/day5.txt").also(::println))

    @Test
    fun testPart2testInput() {
        val lines = Day5HydrothermalVenture.extractLines("input/day5test.txt")
        val grid = lines.asGrid()
        print2DGrid(grid)
        val nrOfIntersections = Day5HydrothermalVenture.countIntersections(grid)
        assertEquals(12, nrOfIntersections)
    }

    @Test
    fun testPart2() = assertEquals(20271, Day5HydrothermalVenture.part2("input/day5.txt").also(::println))
}
