package aoc

import utils.Vector
import java.io.File

object Day2Dive {

    fun part1(filePath: String) = File(filePath).useLines { lines ->
        lines.asPairs()
            .map { (dir, size) -> toVector(dir, size) }
            .reduce(Vector::plus)
            .let { it.x * it.y }
    }

    private fun toVector(dir: String, size: Int) = when (dir) {
        "up" -> Vector(0, -size)
        "down" -> Vector(0, size)
        "forward" -> Vector(size, 0)
        else -> Vector(0,0)
    }

    fun part2(filePath: String): Int {
        val pairs = File(filePath).useLines { it.asPairs().toList() }
        var result = Vector(0, 0)
        var aim = 0
        for ((dir, stepSize) in pairs) {
            when (dir) {
                "forward" -> result += Vector(stepSize, stepSize * aim)
                "up" -> aim -= stepSize
                "down" -> aim += stepSize
            }
        }
        return result.x * result.y
    }

    private fun Sequence<String>.asPairs() = map { it.split(" ") }.map { Pair(it.first(), it.last().toInt()) }

}
