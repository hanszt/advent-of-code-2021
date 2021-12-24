package aoc

import aoc.Day05HydrothermalVenture.asGrid
import aoc.Day05HydrothermalVenture.countIntersections
import aoc.Day05HydrothermalVenture.toVentureLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.*
import java.io.File

internal class Day05HydrothermalVentureTest {

    @Test
    fun `part 1 test input`() = assertEquals(5, Day05HydrothermalVenture.part1("input/day5test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(7085, Day05HydrothermalVenture.part1().also(::println))

    @Test
    fun `part 2 test input`() = File("input/day5test.txt").toVentureLines().asGrid().also(::printGrid)
        .countIntersections().conclude { intersections -> assertEquals(12, intersections) }

    @Test
    fun `part 2 result`() = File("input/day5.txt").toVentureLines().asGrid()
        .countIntersections().also(::println).conclude { intersections -> assertEquals(20271, intersections) }

    private fun printGrid(grid: Array<IntArray>) = println(grid.gridAsString(alignment = 2) {
        if (it > 0) it.toString().withColors(BROWN_BG, YELLOW)
        else ".".withColor(BROWN_BG)
    })
}
