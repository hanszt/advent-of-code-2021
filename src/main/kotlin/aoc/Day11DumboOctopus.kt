package aoc

import utils.forEachPoint
import utils.forEachPointAndValue
import utils.forEachInGrid
import utils.toGridOf
import java.io.File

object Day11DumboOctopus : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines().toGridOf { Octopus(it.digitToInt()) }
        .simulateEnergyLevels(steps = 100).flatMap(Array<Octopus>::asList).sumOf(Octopus::nrFlashes)

    fun part2(path: String): Int =
        File(path).readLines().toGridOf { Octopus(it.digitToInt()) }.findSynchronizationStep()

    private fun Array<Array<Octopus>>.findSynchronizationStep(): Int {
        var counter = 0
        while (flatMap(Array<Octopus>::asList).any { it.energyLevel != 0 }) {
            simulateStep()
            counter++
        }
        return counter
    }

    override fun part1() = part1("input/day11.txt")
    override fun part2() = part2("input/day11.txt")
}

private fun Array<Array<Octopus>>.simulateEnergyLevels(steps: Int): Array<Array<Octopus>> {
    for (step in 1..steps) {
        simulateStep()
    }
    return this
}

fun Array<Array<Octopus>>.simulateStep() {
    forEachInGrid(Octopus::incrementEnergy)
    updateEnergyLevelsNeighbors()
}

private fun Array<Array<Octopus>>.updateEnergyLevelsNeighbors() {
    val differences = Array(size) { IntArray(this[0].size) }
    forEachPoint { x, y -> updateDifferencesNeighbors(x, y, differences) }
    if (differences.flatMap(IntArray::asList).all { it == 0 }) return
    forEachPointAndValue { x, y, octopus -> octopus.updateEnergyLevel(differences[y][x]) }
    updateEnergyLevelsNeighbors()
}

val dirs = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1)

private fun Array<Array<Octopus>>.updateDifferencesNeighbors(x: Int, y: Int, differences: Array<IntArray>) {
    val octopus = this[y][x]
    if (octopus.isFlashing()) {
        octopus.incrementFlashes()
        for ((dx, dy) in dirs) {
            val ny = y + dy
            val nx = x + dx
            getOrNull(ny)?.getOrNull(nx) ?: continue
            differences[ny][nx]++
        }
    }
}

data class Octopus(var energyLevel: Int) {

    var nrFlashes = 0
        private set
    private var flashed = false

    fun updateEnergyLevel(difference: Int) {
        energyLevel += difference
        if (flashed) energyLevel = 0
    }

    fun isFlashing(): Boolean = energyLevel > 9 && flashed.not()

    fun incrementFlashes() = (nrFlashes++).also { flashed = true }

    fun incrementEnergy() = (energyLevel++).also { flashed = false }

    override fun toString(): String = "$energyLevel $flashed $nrFlashes"
}
