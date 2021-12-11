package aoc

import utils.GridPoint
import utils.toIntGrid
import java.io.File

private val directions = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

object Day09SmokeBasin : ChallengeDay {

    fun part1(path: String) = File(path).readLines().toIntGrid().toLowPoints().sumOf { (_, height) -> height + 1 }

    fun part2(path: String) = File(path).readLines().toIntGrid().findBassinSizes().sortedDescending()
        .slice(0 until 3).reduce { thisSize, otherSize -> thisSize * otherSize }

    private fun Array<IntArray>.findBassinSizes() =
        toLowPoints().map { (lowPoint) ->
            val basinPoints = HashSet<GridPoint>(setOf(lowPoint))
            findBassinPoints(lowPoint.x, lowPoint.y, basinPoints)
            return@map basinPoints.size
        }

    override fun part1() = part1("input/day9.txt")
    override fun part2() = part2("input/day9.txt")
}

fun Array<IntArray>.toLowPoints() = heightToNeighborHeights()
    .filter { (_, height, neighborHeights) -> neighborHeights.all { it > height } }

private fun Array<IntArray>.heightToNeighborHeights() =
    indices.flatMap { y ->
        this[0].indices.map { x ->
            val neighborHeights = directions.mapNotNull { (dx, dy) -> getOrNull(y + dy)?.getOrNull(x + dx) }
            return@map Triple(GridPoint(x, y), this[y][x], neighborHeights)
        }
    }

fun Array<IntArray>.findBassinPoints(x: Int, y: Int, bassinPoints: MutableSet<GridPoint>) {
    val basinSize = bassinPoints.size
    for ((dx, dy) in directions) {
        val xNew = x + dx
        val yNew = y + dy
        val neighborHeight = getOrNull(yNew)?.getOrNull(xNew) ?: continue
        if (neighborHeight != 9 && neighborHeight > this[y][x]) {
            bassinPoints.add(GridPoint(xNew, yNew))
            findBassinPoints(xNew, yNew, bassinPoints)
        }
    }
    if (basinSize == bassinPoints.size) return
}