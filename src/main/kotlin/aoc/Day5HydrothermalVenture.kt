package aoc

import utils.Line
import utils.Vector
import java.io.File
import kotlin.math.max
import kotlin.math.min

object Day5HydrothermalVenture : ChallengeDay {

    fun part1(path: String): Int = File(path).toVentureLines()
            .filter { it.isHorizontal().or(it.isVertical()) }.asGrid()
            .countIntersections()

    fun Array<IntArray>.countIntersections(): Int = asSequence()
        .flatMap { it.asSequence() }
        .filter { it > 1 }
        .count()

    fun File.toVentureLines(): Set<Line> = this.useLines { lines ->
        lines.map(::toBeginAndEndPoint)
            .map { (begin, end) -> Line(begin, end) }
            .toSet()
    }

    private fun toBeginAndEndPoint(line: String) = line.split("->").map(String::trim).map(::toVector)

    private fun toVector(it: String) = it.split(',').map(String::toInt).let { (x, y) -> Vector(x, y) }

    fun part2(path: String): Int = File(path).toVentureLines().asGrid().countIntersections()

    fun Iterable<Line>.asGrid(): Array<IntArray> {
        val maxX = maxOf { max(it.begin.x, it.end.x) }
        val maxY = maxOf { min(it.begin.y, it.end.y) }
        val sideLength = max(maxX, maxY) + 1
        val grid: Array<IntArray> = Array(sideLength) { IntArray(sideLength) }
        for (line in this) {
            for (coordinate in line.coordinates) {
                grid[coordinate.y][coordinate.x]++
            }
        }
        return grid
    }

    override fun part1() = part1("input/day5.txt")
    override fun part2() = part2("input/day5.txt")
}
