package aoc

import utils.printAsGrid
import java.io.File

object Day11 : ChallengeDay {

    fun part1(path: String): Int {
        return File(path).readLines().toOctopusGrid().simulateEnergyLevels(100)
            .flatMap(Array<Octopus>::asList).also(::println).sumOf(Octopus::nrFlashes)
    }

    fun part2(path: String): Int {
        return File(path).readLines().toOctopusGrid().findSynchronizationStep()
    }

    private fun Array<Array<Octopus>>.findSynchronizationStep(): Int {
        var counter = 0
        while (flatMap { it.asList() }.all { it.energyLevel == 0 }.not()) {
            simulateStep()
            counter++
        }
        return counter
    }

    override fun part1() = part1("input/day11.txt")
    override fun part2() = part2("input/day11.txt")
}

val dirs = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1)

fun Array<Array<Octopus>>.simulateEnergyLevels(steps: Int): Array<Array<Octopus>> {
    for (step in 1..steps) {
        printAsGrid { it.energyLevel.toString() }
        println()
        simulateStep()
    }
    return this
}

fun Array<Array<Octopus>>.simulateStep() {
    for (y in indices) {
        for (x in 0 until this[0].size) {
            val octopus = this[y][x]
            octopus.flashed = false
            octopus.incrementEnergy()
        }
    }
    updateEnergyLevelsNeighbors()
}

fun Array<Array<Octopus>>.updateEnergyLevelsNeighbors() {
    val differences = Array(size) { IntArray(this[0].size) }
    for (y in this.indices) {
        for (x in 0 until this[0].size) {
            val octopus = this[y][x]
            if (octopus.energyLevel > 9 && octopus.flashed.not()) {
                octopus.nrFlashes++
                octopus.flashed = true
                for ((dx, dy) in dirs) {
                    val ny = y + dy
                    val nx = x + dx
                    getOrNull(ny)?.getOrNull(nx) ?: continue
                    differences[ny][nx]++
                }
            }
        }
    }
    if (differences.flatMap { it.asList() }.all { it == 0 }) return
    for (y in this.indices) {
        for (x in 0 until this[0].size) {
            val octopus = this[y][x]
            octopus.energyLevel += differences[y][x]
            if (octopus.flashed) octopus.energyLevel = 0
        }
    }
    updateEnergyLevelsNeighbors()
}

fun Array<Array<Octopus>>.createView(): List<List<Int>> = map { it.map { o -> o.energyLevel } }

fun List<String>.toOctopusGrid(): Array<Array<Octopus>> =
    map { line -> line.map { Octopus(it.digitToInt()) }.toTypedArray() }.toTypedArray()

data class Octopus(var energyLevel: Int) {

    var nrFlashes = 0
    var flashed = false

    fun flashes(): Boolean {
        nrFlashes++
        return energyLevel > 9
    }

    fun incrementEnergy() = energyLevel++

    override fun toString(): String = "$energyLevel $flashed $nrFlashes"
}
