package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.*
import java.io.File
import kotlin.test.assertTrue

internal class Day15ChitonTest {

    @Test
    fun `part 1 test input`() = assertEquals(40, Day15Chiton.part1("input/day15test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(592, Day15Chiton.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(315, Day15Chiton.part2("input/day15test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(2897, Day15Chiton.part2().also(::println))

    @Test
    fun `part 2 result as grid`() {
        val pathInGridAsString = File("input/day15-2.txt").readLines().toIntGrid(Char::digitToInt).pathInGridAsString()
        println(pathInGridAsString)
        assertTrue(pathInGridAsString.isNotBlank())
    }

    private fun Array<IntArray>.pathInGridAsString(): String {
        val graph = toWeightedGraph<Any>(listOf(-1 to 1, 0 to 1, 1 to 0))
        val startPoint = 0 to 0
        val endPoint = first().lastIndex to lastIndex
        val start = graph[startPoint] ?: throw IllegalStateException()
        val goal = graph[endPoint] ?: throw IllegalStateException()
        val shortestPath = start.dijkstra(goal).shortestPath.toMutableList { add(goal) }
        val nodesToCoordinates = graph.inverseMap()
        val grid = toGridOf(Int::toString)
        shortestPath.mapNotNull(nodesToCoordinates::get)
            .forEach { (x, y) -> grid[y][x] = grid[y][x].withColors(BRIGHT_YELLOW, YELLOW_BG, 2) }
        return grid.gridAsString(-1) {
            if (it.length == 1) it.withColors(BROWN_BG, random16BitColor(), 2) else it
        }
    }

}
