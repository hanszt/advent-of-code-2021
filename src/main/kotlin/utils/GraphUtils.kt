package utils

import model.Node
import java.util.*

fun List<String>.toBiDiGraph(delimiter: String): Map<String, Node<String>> = toBiDiGraph(delimiter) { it }

fun List<String>.toBiDiGraph(delimiter: Char): Map<String, Node<String>> = toBiDiGraph(delimiter.toString())

fun <T> List<String>.toBiDiGraph(delimiter: String, mapper: (String) -> T): Map<String, Node<T>> {
    val nodes = HashMap<String, Node<T>>()
    for (line in this) {
        val (value, other) = line.split(delimiter)
        val node = nodes.getOrDefault(value, Node(mapper(value)))
        val otherNode = nodes.getOrDefault(other, Node(mapper(other)))
        node.addNeighbor(otherNode)
        otherNode.addNeighbor(node)
        nodes[value] = node
        nodes[other] = otherNode
    }
    return nodes
}

fun <T> allPathsByDfs(start: Node<T>, goal: Node<T>, visitedCondition: (Node<T>) -> Boolean): List<List<Node<T>>> =
    ArrayList<List<Node<T>>>().apply { dfsRecursive(start, goal, visitedCondition = visitedCondition) }

private fun <T> MutableList<List<Node<T>>>.dfsRecursive(
    current: Node<T>,
    goal: Node<T>,
    visited: MutableSet<Node<T>> = mutableSetOf(),
    localPathList: MutableList<Node<T>> = mutableListOf(current),
    visitedCondition: (Node<T>) -> Boolean
) {
    if (current == goal) {
        this.add(ArrayList(localPathList))
        return
    }
    if (visitedCondition(current)) {
        visited.add(current)
    }
    for (neighbor in current.neighbors) {
        if (neighbor !in visited) {
            localPathList.add(neighbor)
            dfsRecursive(neighbor, goal, visited, localPathList, visitedCondition)
            localPathList.remove(neighbor)
            neighbor.parent = current
        }
    }
    if (visitedCondition(current)) {
        visited.remove(current)
    }
}
