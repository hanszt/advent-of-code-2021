package aoc

import aoc.Day17TrickShot.Probe
import model.GridPoint2D
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import utils.*

internal class Day17TrickShotTest {

    @Test
    fun `part 1 test input`() = assertEquals(45, Day17TrickShot.part1(20..30, -10..-5))

    @Test
    fun `part 1 result`() = assertEquals(7381, Day17TrickShot.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(112, Day17TrickShot.part2(20..30, -10..-5))

    @Test
    fun `part 2 result`() = assertEquals(3019, Day17TrickShot.part2().also(::println))

    @Test
    fun `display traveled path`() {
        val probe = Probe(velocity = GridPoint2D(10, 3))
        val path = probe.getPath()
        val maxX = path.maxOf(GridPoint2D::x)
        val positions = path.stream().mapToInt(GridPoint2D::y).summaryStatistics()
        val grid = Array(positions.max - positions.min + 1) { IntArray(maxX + 1) }
        path.forEach { (x, y) -> grid[y - positions.min][x] = 1 }
        println(grid.mirroredHorizontally().gridAsStringOf(1,"") {
            if (it == 0) ".".withColors(BRIGHT_BLUE, ICY_BG) else "#".withColors(RED, ICY_BG) })
        assertTrue(path.isNotEmpty())
    }
}
