package aoc

import utils.rotatedClockWise
import utils.splitByEmptyLine
import utils.toIntGrid
import java.io.File

object Day4GiantSquid : ChallengeDay {

    fun part1(path: String): Int {
        val (boards, allNrsToDrawList) = toBoardsAndNrsToDrawList(path)
        val drawnNrs = ArrayList(allNrsToDrawList.slice(0..3))
        var firstWinning: List<List<Int>> = emptyList()
        while (firstWinning.isEmpty()) {
            drawnNrs.add(allNrsToDrawList[drawnNrs.size])
            firstWinning = boards.firstOrNull { it.isWinningBoard(drawnNrs) } ?: emptyList()
        }
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
        return boardsWon.last().sumUnmarkedNrs(drawnNrs) * drawnNrs.last()
    }

    private fun List<List<Int>>.sumUnmarkedNrs(drawnNrs: ArrayList<Int>) = asSequence()
        .flatMap(List<Int>::toList)
        .filter { drawnNrs.contains(it).not() }.sum()

    private fun toBoardsAndNrsToDrawList(path: String): Pair<List<List<List<Int>>>, List<Int>> =
        File(path).readText(Charsets.UTF_8)
            .splitByEmptyLine()
            .run { Pair(slice(1 until size).map(String::toIntGrid), nrsToDrawList()) }

    private fun List<String>.nrsToDrawList() = first().split(",").map(String::toInt)

    override fun part1() = part1("input/day4.txt")
    override fun part2() = part2("input/day4.txt")
}
