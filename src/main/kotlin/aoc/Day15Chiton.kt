package aoc

import model.WeightedNode
import utils.*
import java.io.File
import java.util.*

internal object Day15Chiton : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines().toIntGrid().calculateTotalRisk()

    private fun Array<IntArray>.calculateTotalRisk(): Int {
        val graph = toWeightedGraph<Any>(listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1))
        val upperLeft = 0 to 0
        val lowerRight = this[0].size - 1 to size - 1
        val start = graph[lowerRight] ?: throw IllegalStateException()
        val goal = graph[upperLeft] ?: throw IllegalStateException()
        return start.dijkstra(goal).shortestPath.sumOf(WeightedNode<Any>::weight)
    }

    fun part2(path: String) = File(path).readLines().toIntGrid().enlarge(times = 5).calculateTotalRisk()

    private fun Array<IntArray>.enlarge(times: Int): Array<IntArray> {
        val oriWidth = this[0].size
        val oriHeight = this.size
        val result = Array(oriHeight * times) { IntArray(oriWidth * times) }
        for (stepX in 0 until times) {
            for (stepY in 0 until times) {
                toIntGridOf { oneToNine(it + stepX + stepY) }
                    .forEachPointAndValue { x, y, value -> result[y + oriHeight * stepY][x + oriWidth * stepX] = value }
            }
        }
        return result
    }

    private fun oneToNine(i: Int): Int = (i - 1) % 9 + 1

    override fun part1() = part1("input/day15.txt")
    override fun part2() = part2("input/day15.txt")
}
