package aoc

import java.io.File
import java.util.*
import kotlin.math.abs

// I've not solved day 23 myself. This solution is from the repo from Elizarov. All credits go to him.
//
// I refactored and renamed it to understand what is going on.
//
// It is very educational to see how such a solution can be solved. Many thanks to Roman Elizarov
//
// full credits to Roman Elizarov
//
internal class Day23Amphipod(private val testMode: Boolean = false) : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines().subList(1, 4)
        .let { input -> Burrow(input.map(String::toCharArray).toTypedArray()) }.also { if (testMode) println(it) }
        .let(::calculateMinimumUsedEnergy)

    // adapted form of Dijkstra
    private fun calculateMinimumUsedEnergy(start: Burrow): Int {
        val priorityQueue = PriorityQueue(compareBy(Burrow::usedEnergy))
        val checkedBurrows = mutableSetOf<Burrow>()

        priorityQueue.enqueue(start)
        val minimumUsedEnergy: Int
        while (true) {
            val burrow = priorityQueue.remove()
            if (burrow !in checkedBurrows) {
                checkedBurrows.add(burrow)
                val usedEnergy = Burrow.burrowToUsedEnergyMap[burrow] ?: Int.MAX_VALUE
                checkedBurrows.printIfInTestMode(usedEnergy, priorityQueue)
                if (burrow.inWantedConfiguration()) {
                    if (testMode) println(burrow)
                    minimumUsedEnergy = burrow.usedEnergy
                    break
                }
                burrow.ifPossibleMoveInHallWay(usedEnergy, priorityQueue)
                burrow.ifPossibleMoveToTargetRoom(usedEnergy, priorityQueue)
            }
        }
        return minimumUsedEnergy
    }

    private fun Burrow.ifPossibleMoveToTargetRoom(usedEnergy: Int, priorityQueue: PriorityQueue<Burrow>) {
        for (x in 1..hallWayLength) {
            val amphipod = grid[0][x]
            if (amphipod.isAmphipod()) {
                val targetRoomNr = (amphipod - 'A')
                val targetX = roomX(targetRoomNr)
                if (noOtherAmphipodInWay(x, targetX) && allWantedAmphipodTypeOrEmpty(targetX, amphipod)) {
                    val firstEmptyY = (roomDepth downTo 1).first { roomY -> grid[roomY][targetX] == '.' }
                    val distanceMoved = abs(targetX - x) + firstEmptyY
                    val copy = copy(usedEnergy + amphipod.energyToMove() * distanceMoved)
                    copy.grid[0][x] = '.'
                    copy.grid[firstEmptyY][targetX] = amphipod
                    priorityQueue.enqueue(copy)
                }
            }
        }
    }

    private fun Burrow.ifPossibleMoveInHallWay(usedEnergy: Int, priorityQueue: Queue<Burrow>) {
        for (room in 0..lastRoomNr) {
            for (curY in 1..roomDepth) {
                val roomX = roomX(room)
                ifPossibleMoveInHallWay(roomX, curY, usedEnergy, priorityQueue)
            }
        }
    }

    private fun Burrow.ifPossibleMoveInHallWay(roomX: Int, curY: Int, usedEnergy: Int, priorityQueue: Queue<Burrow>) {
        val amphipod = grid[curY][roomX]
        if (amphipod.isAmphipod() && freePassageInRoomToTarget(curY, roomX)) {
            for (hallWayX in 1..hallWayLength) {
                if (notAboveRoom(hallWayX) && freePassageInHallWay(hallWayX, roomX)) {
                    val distanceMoved = abs(hallWayX - roomX) + curY
                    val copy = copy(usedEnergy + amphipod.energyToMove() * distanceMoved)
                    copy.grid[curY][roomX] = '.'
                    copy.grid[0][hallWayX] = amphipod
                    priorityQueue.enqueue(copy)
                }
            }
        }
    }

    private fun Queue<Burrow>.enqueue(burrow: Burrow) {
        val usedEnergy = Burrow.burrowToUsedEnergyMap[burrow] ?: Int.MAX_VALUE
        if (burrow.usedEnergy < usedEnergy) {
            Burrow.burrowToUsedEnergyMap[burrow] = burrow.usedEnergy
            add(burrow)
        }
    }

    private fun Set<Burrow>.printIfInTestMode(leastUsedEnergy: Int, priorityQueue: Queue<Burrow>) {
        if (testMode && size % 10_000 == 0) {
            println("least used energy=$leastUsedEnergy, queue size=${priorityQueue.size}, set size=$size")
        }
    }

    private fun Char.isAmphipod() = this in 'A'..'D'

    private fun Char.energyToMove(): Int = when (this) {
        'A' -> 1
        'B' -> 10
        'C' -> 100
        'D' -> 1000
        else -> error("$this")
    }

    fun part2(path: String): Int = File(path).readLines().subList(1, 4)
        .let { (firstRow, secondRow, bottomRow) -> createInputPart2(firstRow, secondRow, bottomRow) }
        .let { input -> Burrow(input.map(String::toCharArray).toTypedArray()) }.also { if (testMode) println(it) }
        .let(::calculateMinimumUsedEnergy)

    private fun createInputPart2(firstRow: String, secondRow: String, bottomRow: String): List<String> = listOf(
        firstRow,
        secondRow,
        "  #D#C#B#A#  ",
        "  #D#B#A#C#  ",
        bottomRow
    )

    override fun part1() = part1("input/day23.txt")
    override fun part2() = part2("input/day23.txt")

    //
    //    The Burrow
    //    #############
    //    #...........#
    //    ###D#A#D#C###
    //      #B#C#B#A#
    private data class Burrow(val grid: Array<CharArray>, val usedEnergy: Int = 0) {

        val roomDepth = grid.size - 1
        val hallWayLength = 11
        val lastRoomNr = 3

        fun notAboveRoom(hallWayX: Int) =
            ((hallWayX - lastRoomNr) % 2 == 0 && (hallWayX - lastRoomNr) / 2 in 0..lastRoomNr).not()

        fun roomX(roomNr: Int) = 2 * roomNr + lastRoomNr

        fun inWantedConfiguration(): Boolean {
            for (roomY in 1..roomDepth) {
                for (room in 0..lastRoomNr) {
                    if (grid[roomY][roomX(room)] != 'A' + room) {
                        return false
                    }
                }
            }
            return true
        }

        fun allWantedAmphipodTypeOrEmpty(roomX: Int, targetAmphipod: Char) =
            (1..roomDepth).all { y -> grid[y][roomX] == '.' || grid[y][roomX] == targetAmphipod }

        fun noOtherAmphipodInWay(hallWayX: Int, roomX: Int) =
            (minOf(hallWayX, roomX) + 1 until maxOf(hallWayX, roomX)).all { x -> grid[0][x] == '.' }

        fun freePassageInHallWay(hallWayX: Int, roomX: Int) =
            (minOf(hallWayX, roomX)..maxOf(hallWayX, roomX)).all { x -> grid[0][x] == '.' }

        fun freePassageInRoomToTarget(y: Int, roomX: Int) = (1 until y).all { roomY -> grid[roomY][roomX] == '.' }

        override fun equals(other: Any?): Boolean =
            other is Burrow && (0..roomDepth).all { y -> grid[y].contentEquals(other.grid[y]) }

        override fun hashCode(): Int = (0..roomDepth)
            .fold(0) { result, depth -> result + grid[depth].contentHashCode() }

        fun copy(usedEnergy: Int) = Burrow(grid.map(CharArray::copyOf).toTypedArray(), usedEnergy)

        override fun toString(): String = buildList {
            add("#".repeat(13))
            addAll(grid.map(CharArray::concatToString))
        }.joinToString("\n")

        companion object {
            val burrowToUsedEnergyMap = HashMap<Burrow, Int>()
        }
    }
}