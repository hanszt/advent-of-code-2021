package aoc

import model.GridPoint3D
import utils.splitByBlankLine
import java.io.File

//
// I've not been able to solve this day myself. This solution is from the repo from Elizarov
//
// I did a little refactoring to understand what is going on.
//
// It is very educational to see such how such a solution can be solved
//
// https://github.com/elizarov/AdventOfCode2021
internal object Day19BeaconScanner : ChallengeDay {

    private fun String.toGrid3D(): List<Set<GridPoint3D>> =
        splitByBlankLine().map { s -> s.lines().filterNot { "scanner" in it }.map(::toPoint3D).toSet() }

    fun toPoint3D(p: String) = p.split(",").map(String::toInt).let { (x, y, z) -> GridPoint3D(x, y, z) }

    private fun Array<GridPoint3D?>.calculateLargestManhattanDistance(): Int =
        flatMap { point -> mapNotNull { other -> other?.let { point?.distance(it) } } }.maxOf { it }

    private fun List<Set<GridPoint3D>>.check(
        ui: Int,
        vi: Int,
        visited: Array<BooleanArray>,
        offsetPoints: Array<Array<T3F.Offset?>>
    ): T3F.Offset? {
        if (visited[ui][vi]) {
            return offsetPoints[ui][vi]
        }
        visited[ui][vi] = true
        val point3DS = this[ui]
        for (direction in 0 until 24) {
            val rotatedPoints = this[vi].map { it.rotate(direction) }
            val shiftedPoints = point3DS.flatMap { pu -> rotatedPoints.map { pv -> pu - pv } }.toSet()
            for (shifted in shiftedPoints) {
                val count = rotatedPoints.map { it + shifted }.count(point3DS::contains)
                if (count >= 12) {
                    return T3F.Offset(direction, shifted).also { offsetPoints[ui][vi] = it }
                }
            }
        }
        return null
    }

    private fun calculateBeaconCountAndMaxDistance(path: String): Pair<Int, Array<GridPoint3D?>> {
        val grid = File(path).readText().toGrid3D()
        val size = grid.size
        val visited = Array(size) { BooleanArray(size) }
        val offsetPoints = Array(size) { arrayOfNulls<T3F.Offset>(size) }

        val beacons = grid.first().toMutableSet()
        val arrayOfT3Fs = arrayOfNulls<T3F>(size).also { it[0] = T3F.Id }
        val arrayOfGridPoint3Ds = arrayOfNulls<GridPoint3D>(size).also { it[0] = GridPoint3D(0, 0, 0) }
        val found = hashSetOf(0)
        val remaining = (1 until grid.size).toHashSet()
        pair@ while (remaining.isNotEmpty()) {
            for (ui in found) {
                for (vi in remaining) {
                    val o = grid.check(ui, vi, visited, offsetPoints) ?: continue
                    val combo = T3F.Combo(o, arrayOfT3Fs[ui]!!)
                    arrayOfT3Fs[vi] = combo
                    arrayOfGridPoint3Ds[vi] = GridPoint3D(0, 0, 0).apply(combo)
                    beacons.addAll(grid[vi].map { it.apply(combo) })
                    found.add(vi)
                    remaining.remove(vi)
                    continue@pair
                }
            }
            error("Cannot find")
        }
        return beacons.size to arrayOfGridPoint3Ds
    }

    sealed class T3F {
        object Id : T3F()
        class Offset(val direction: Int, val shift: GridPoint3D) : T3F()
        class Combo(val leftPoint: T3F, val rightPoint: T3F) : T3F()
    }

    private fun GridPoint3D.apply(point: T3F): GridPoint3D = when (point) {
        is T3F.Id -> this
        is T3F.Offset -> rotate(point.direction) + point.shift
        is T3F.Combo -> apply(point.leftPoint).apply(point.rightPoint)
    }

    fun part1(path: String): Int = calculateBeaconCountAndMaxDistance(path).first
    fun part2(path: String): Int = calculateBeaconCountAndMaxDistance(path).second.calculateLargestManhattanDistance()

    override fun part1() = part1("input/day19.txt")
    override fun part2() = part2("input/day19.txt")
}
