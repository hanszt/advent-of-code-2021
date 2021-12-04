package aoc

import utils.rotatedClockWise
import utils.toIntGrid
import java.io.File

object Day4GiantSquid {

    fun part1(path: String): Int {
        val (boards, allNrToBeDrawn) = toBoardsAndNrsToDrawList(path)
        val drawnNrs = ArrayList(allNrToBeDrawn.subList(0, 3))
        var firstWinning: List<List<Int>> = emptyList()
        while (firstWinning.isEmpty()) {
            drawnNrs.add(allNrToBeDrawn[drawnNrs.size])
            firstWinning = boards.firstOrNull { it.isWinningBoard(drawnNrs) } ?: emptyList()
        }
        println("drawnNrs = $drawnNrs")
        println("winningBoard = $firstWinning")
        return firstWinning.sumUnmarkedNrs(drawnNrs) * drawnNrs.last()
    }

    fun List<List<Int>>.isWinningBoard(drawnNumbers: List<Int>): Boolean =
        any { row -> drawnNumbers.containsAll(row) }
            .or(rotatedClockWise().any { col -> drawnNumbers.containsAll(col.toList()) })

    fun part2(path: String): Int {
        val (boards, allNrToBeDrawn) = toBoardsAndNrsToDrawList(path)
        val drawnNrs = ArrayList(allNrToBeDrawn.slice(0..3))
        val boardsInGame = ArrayList(boards)
        val boardsWon = LinkedHashSet<List<List<Int>>>()
        while (boardsWon.size != boards.size) {
            drawnNrs.add(allNrToBeDrawn[drawnNrs.size])
            boardsInGame.filterTo(boardsWon) { it.isWinningBoard(drawnNrs) }
            boardsInGame.removeAll(boardsWon)
        }
        val lastWinningBoard = boardsWon.last()
        println("drawnNrs = $drawnNrs")
        println("lastWinningBoard = $lastWinningBoard")
        return lastWinningBoard.sumUnmarkedNrs(drawnNrs) * drawnNrs.last()
    }

    private fun List<List<Int>>.sumUnmarkedNrs(drawnNrs: ArrayList<Int>) = asSequence().flatMap(List<Int>::toList)
        .filter { drawnNrs.contains(it).not() }.sum()

    private fun toBoardsAndNrsToDrawList(path: String): Pair<List<List<List<Int>>>, List<Int>> = File(path)
        .readText(Charsets.UTF_8)
        .split(Regex("(?m)^\\s*$"))
        .map(String::trim)
        .run { Pair(slice(1 until size).map(String::toIntGrid), nrsToDrawList()) }

    private fun List<String>.nrsToDrawList(): List<Int> = first().split(",").map(String::toInt)

}
