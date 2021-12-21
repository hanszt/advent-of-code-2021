package aoc

import aoc.Day09SmokeBasin.findBassinPoints
import aoc.Day09SmokeBasin.toLowPoints
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import model.GridPoint2D
import utils.CYAN
import utils.withColor
import utils.toIntGrid

internal class Day09SmokeBasinTest {

    @Test
    fun `part 1 test input`() = assertEquals(15, Day09SmokeBasin.part1("input/day9test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(530, Day09SmokeBasin.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(1134, Day09SmokeBasin.part2("input/day9test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(1019494, Day09SmokeBasin.part2().also(::println))

    @Test
    fun testFindBasinPoints() {
        val inputGrid = """
                    998789
                    985678
                    876789
                    989996
                """.trimIndent()
        val expectedBassin = """
                      878 
                     85678
                    87678 
                     8
        """.trimIndent()
        val expectedSize = expectedBassin.filter(Char::isLetterOrDigit).length

        val intGrid = inputGrid.lines().toIntGrid(Char::digitToInt)

        val (lowestPoint) = intGrid.toLowPoints().first()

        val basinPoints = LinkedHashSet<GridPoint2D>().apply { add(lowestPoint) }
        intGrid.findBassinPoints(lowestPoint.x, lowestPoint.y, basinPoints)

        basinPoints.forEach { (x, y) -> println("x=$x, y=$y, value=${intGrid[y][x]}".withColor(CYAN)) }

        assertEquals(5, intGrid[lowestPoint.y][lowestPoint.x])
        assertEquals(expectedSize, basinPoints.size)
    }
}
