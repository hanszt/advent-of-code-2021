package aoc

import utils.GridPoint
import java.io.File

private val directions = listOf(
    GridPoint(0, 1), GridPoint(1, 0),
    GridPoint(0, -1), GridPoint(-1, 0)
)

object Day9SmokeBasin : ChallengeDay {

    fun part1(path: String): Int =
        toIntGrid(File(path).readLines()).lowestPoints().sumOf { (_, value) -> value + 1 }

    fun part2(path: String): Int = toIntGrid(File(path).readLines()).findBassinSizes().sortedDescending()
        .slice(0..2).reduce { acc, cur -> acc * cur }

    fun toIntGrid(strings: List<String>) = strings.map { it.toList().map(Char::digitToInt) }

    private fun List<List<Int>>.findBassinSizes(): List<Int> {
        return lowestPoints().map { (point, _) ->
            val basinPoints = HashSet<GridPoint>()
            findBassinPoints(point.x, point.y, basinPoints)
            basinPoints.add(point)
            basinPoints.size
        }
    }

    override fun part1() = part1("input/day9.txt")
    override fun part2() = part2("input/day9.txt")
}

fun List<List<Int>>.lowestPoints() = curToNeighborVals()
    .filter { (cur, neighborVals) -> neighborVals.all { it > cur.second } }.map { it.first }

private fun List<List<Int>>.curToNeighborVals() =
    indices.flatMap { y ->
        this[0].indices.map { x ->
            val curPointAndVal = Pair(GridPoint(x, y), this[y][x])
            val neighborVals = directions.mapNotNull { getOrNull(y + it.y)?.getOrNull(x + it.x) }
            curPointAndVal to neighborVals
        }
    }

fun List<List<Int>>.findBassinPoints(x: Int, y: Int, basinPoints: MutableSet<GridPoint>) {
    val basinSize = basinPoints.size
    for (dir in directions) {
        val xNew = x + dir.x
        val yNew = y + dir.y
        val newVal = getOrNull(yNew)?.getOrNull(xNew) ?: continue
        if (newVal != 9 && newVal > this[y][x]) {
            basinPoints.add(GridPoint(xNew, yNew))
            findBassinPoints(xNew, yNew, basinPoints)
        }
    }
    if (basinSize == basinPoints.size) return
}
