package aoc

import aoc.Day04GiantSquid.isWinningBoard
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day04GiantSquidTest {

    @Test
    fun testPart1testInput() = assertEquals(4512, Day04GiantSquid.part1("input/day4test.txt"))

    @Test
    fun testPart1() = assertEquals(50008, Day04GiantSquid.part1().also(::println))

    @Test
    fun testPart2testInput() = assertEquals(1924, Day04GiantSquid.part2("input/day4test.txt"))

    @Test
    fun testPart2() = assertEquals(17408, Day04GiantSquid.part2().also(::println))

    @Test
    fun testWinningBoardTrueIfPresentInRow() {
        val nrsDrawn = listOf(1, 4, 5, 7)
        val winningBoard = arrayOf(
            intArrayOf(7, 4, 5, 1),
            intArrayOf(2, 3, 6, 8),
            intArrayOf(12, 11, 10, 9),
            intArrayOf(13, 14, 15, 16)
        )
        assertTrue(winningBoard.isWinningBoard(nrsDrawn))
    }

    @Test
    fun testWinningBoardTrueIfPresentInCol() {
        val nrsDrawn = listOf(1, 4, 5, 7)
        val winningBoard = arrayOf(
            intArrayOf(1, 6, 9, 8),
            intArrayOf(4, 2, 3, 10),
            intArrayOf(7, 11, 12, 13),
            intArrayOf(5, 16, 15, 14)
        )
        assertTrue(winningBoard.isWinningBoard(nrsDrawn))
    }

    @Test
    fun testWinningBoardFalseIfNotPresentInRowOrCol() {
        val nrsDrawn = listOf(1, 4, 5, 7)
        val winningBoard = arrayOf(
            intArrayOf(6, 4, 5, 1),
            intArrayOf(2, 3, 7, 8),
            intArrayOf(12, 11, 10, 9),
            intArrayOf(13, 14, 15, 16)
        )
        assertFalse(winningBoard.isWinningBoard(nrsDrawn))
    }
}
