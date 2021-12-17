package aoc

import model.WeightedNode
import utils.*
import java.io.File
import java.util.*

internal object Day15Chiton : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines().toIntGrid().calculateTotalRisk<Any>()

    private fun <T> Array<IntArray>.calculateTotalRisk(): Int {
        val graph = toWeightedGraph<T>(listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1))
        val startPoint = first().lastIndex to lastIndex
        val endPoint = 0 to 0
        val start = graph[startPoint] ?: throw IllegalStateException()
        val goal = graph[endPoint] ?: throw IllegalStateException()
        return start.dijkstra(goal).shortestPath.sumOf(WeightedNode<T>::weight)
    }

    fun part2(path: String) = File(path).readLines().toIntGrid().enlarge(times = 5).calculateTotalRisk<Any>()

    fun Array<IntArray>.enlarge(times: Int = 2): Array<IntArray> {
        val result = Array(size * times) { IntArray(first().size * times) }
        for (stepX in 0 until times) {
            for (stepY in 0 until times) {
                toIntGridOf { oneToNine(it + stepX + stepY) }
                    .forEachPointAndValue { x, y, value -> result[y + size * stepY][x + first().size * stepX] = value }
            }
        }
        return result
    }

    private fun oneToNine(value: Int): Int = (value - 1) % 9 + 1

    override fun part1() = part1("input/day15.txt")
    override fun part2() = part2("input/day15.txt")
}
