package aoc

import utils.Line
import utils.Vector
import java.io.File
import kotlin.math.max
import kotlin.math.min

object Day5HydrothermalVenture {

    fun part1(path: String): Int {
        val lines = extractLines(path).filter { it.isHorizontal().or(it.isVertical()) }
        return countIntersections(lines.toSet())
    }

    fun countIntersections(lines: Set<Line>): Int {
        val intersections: MutableSet<Vector> = HashSet()
        for (line in lines) {
            for (other in lines) {
                if (line != other) intersections.addAll(line.intersections(other))
            }
        }
        return intersections.count()
    }

    fun extractLines(path: String): Set<Line> = File(path).useLines { lines ->
        lines.map(::toVectorPair)
            .map { (begin, end) -> Line(begin, end) }
            .toSet()
    }

    private fun toVectorPair(line: String) = line.split("->").map(String::trim).map(::toVector)

    private fun toVector(it: String) = it.split(',').map(String::toInt).let { (x, y) -> Vector(x, y) }

    fun part2(path: String): Int {
        val lines = extractLines(path)
        val grid = lines.asGrid()
        return countIntersections(lines.toSet())
    }

    fun Set<Line>.asGrid(): Array<IntArray> {
        val maxX = maxOf { max(it.begin.x, it.end.x) }
        val maxY = maxOf { min(it.begin.y, it.end.y) }
        val side = max(maxX, maxY) + 1
        val grid: Array<IntArray> = Array(side) { IntArray(side) }
        for (line in this) {
            for (coordinate in line.coordinates) {
                grid[coordinate.y][coordinate.x]++
            }
        }
        return grid
    }

}
