package aoc

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import utils.GridPoint

internal class Day09SmokeBasinTest {

    @Test
    fun testPart1testInput() = assertEquals(15, Day09SmokeBasin.part1("input/day9test.txt"))

    @Test
    fun testPart1() = assertEquals(530, Day09SmokeBasin.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(1134, Day09SmokeBasin.part2("input/day9test.txt"))

    @Test
    fun testPart2() = assertEquals(1019494, Day09SmokeBasin.part2().also(::println))

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
        val expectedSize = expectedBassin.toList().filter(Char::isLetterOrDigit).size

        val intGrid = Day09SmokeBasin.toIntGrid(inputGrid.lines())

        val (lowestPoint) = intGrid.toLowPoints().first()

        val basinPoints = LinkedHashSet<GridPoint>()
        basinPoints.add(lowestPoint)
        intGrid.findBassinPoints(lowestPoint.x, lowestPoint.y, basinPoints)

        basinPoints.map { Pair(it, intGrid[it.y][it.x]) }.forEach(::println)

        assertEquals(5, intGrid[lowestPoint.y][lowestPoint.x])
        assertEquals(expectedSize, basinPoints.size)
    }
}
