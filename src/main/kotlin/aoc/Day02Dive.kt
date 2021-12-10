package aoc

import utils.GridPoint
import utils.mapSecond
import utils.toEnds
import java.io.File

object Day02Dive : ChallengeDay {

    fun part1(filePath: String) = File(filePath).useLines { lines ->
        lines.toDirAndStepSize()
            .map { (dir, size) -> toVector(dir, size) }
            .reduce(GridPoint::plus)
            .run { x * y }
    }

    private fun toVector(dir: String, size: Int) = when (dir) {
        "up" -> GridPoint(0, -size)
        "down" -> GridPoint(0, size)
        "forward" -> GridPoint(size, 0)
        else -> GridPoint(0, 0)
    }

    fun part2(filePath: String): Int {
        val pairs = File(filePath).useLines { it.toDirAndStepSize().toList() }
        var result = GridPoint(0, 0)
        var aim = 0
        for ((dir, stepSize) in pairs) {
            when (dir) {
                "forward" -> result += GridPoint(stepSize, stepSize * aim)
                "up" -> aim -= stepSize
                "down" -> aim += stepSize
            }
        }
        return result.x * result.y
    }

    private fun Sequence<String>.toDirAndStepSize() = map { it.split(" ").toEnds().mapSecond(String::toInt) }

    override fun part1() = part1("input/day2.txt")
    override fun part2() = part2("input/day2.txt")
}
