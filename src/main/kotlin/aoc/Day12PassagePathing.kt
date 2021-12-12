package aoc

import model.Node
import utils.allPathsByDfs
import utils.toBiDiGraph
import java.io.File
import java.util.*


object Day12PassagePathing : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines().toBiDiGraph('-').countDistinctPaths()

    private fun Map<String, Node<String>>.countDistinctPaths(): Int {
        val start = this["start"] ?: throw IllegalStateException()
        val end = this["end"] ?: throw IllegalStateException()
        val paths = allPathsByDfs(start, end) { it.value.all(Char::isLowerCase) }
        return paths.size
    }

    fun part2(path: String): Int {
        val graph = File(path).readLines().toBiDiGraph("-") { label -> Cave(label, false) }
        val start = graph["start"] ?: throw IllegalStateException()
        val end = graph["end"] ?: throw IllegalStateException()

        return graph.filter { (label) -> label != "start" && label != "end" && label.all(Char::isLowerCase) }
            .flatMap { (_, allowedToVisitTwice) -> findPaths(start, end, allowedToVisitTwice) }
            .distinct()
            .count()
    }

    private fun findPaths(src: Node<Cave>, goal: Node<Cave>, caveAllowedToVisitTwice: Node<Cave>): Set<String> {
        val uniquePaths = mutableSetOf<String>()

        val pathsQueue: Queue<MutableList<Node<Cave>>> = LinkedList()
        pathsQueue.offer(mutableListOf(src))
        while (pathsQueue.isNotEmpty()) {
            val currentPath = pathsQueue.poll()
            val currentCave = currentPath.last()

            if (currentCave == goal) {
                uniquePaths.add(currentPath.joinToString { it.value.label })
            }
            if (currentPath.count { it == caveAllowedToVisitTwice } < 2) {
                caveAllowedToVisitTwice.value.notVisitedTwice = true
            }
            for (neighbor in currentCave.neighbors) {
                val isBigCave = neighbor.value.label.all(Char::isUpperCase)
                val allowedToVisitTwice = neighbor.value.notVisitedTwice &&
                        currentPath.count { it == caveAllowedToVisitTwice } < 2
                if (neighbor !in currentPath || isBigCave || allowedToVisitTwice) {
                    val newPath = ArrayList(currentPath)
                    newPath.add(neighbor)
                    pathsQueue.offer(newPath)
                }
            }
            currentCave.value.notVisitedTwice = false
        }
        return uniquePaths
    }

    override fun part1() = part1("input/day12.txt")
    override fun part2() = part2("input/day12.txt")
}

data class Cave(val label: String, var notVisitedTwice: Boolean)
