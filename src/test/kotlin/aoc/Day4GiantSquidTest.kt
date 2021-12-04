package aoc

import aoc.Day4GiantSquid.isWinningBoard
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class Day4GiantSquidTest {

    @Test
    fun testPart1testInput() = assertEquals(4512, Day4GiantSquid.part1("input/day4test.txt"))

    @Test
    fun testPart1() = assertEquals(50008, Day4GiantSquid.part1("input/day4.txt").also(::println))

    @Test
    fun testPart2testInput() = assertEquals(1924, Day4GiantSquid.part2("input/day4test.txt"))

    @Test
    fun testPart2() = assertEquals(17408, Day4GiantSquid.part2("input/day4.txt").also(::println))

    @Test
    fun testWinningBoardTrueIfPresentInRow() {
        val nrsDrawn = listOf(1, 4, 5, 7)
        val winningBoard = listOf(
            listOf(7, 4, 5, 1),
            listOf(4, 5, 4, 3),
            listOf(5, 7, 6, 5),
            listOf(4, 5, 6, 3)
        )
        assertTrue(winningBoard.isWinningBoard(nrsDrawn))
    }

    @Test
    fun testWinningBoardTrueIfPresentInCol() {
        val nrsDrawn = listOf(1, 4, 5, 7)
        val winningBoard = listOf(
            listOf(1, 4, 5, 8),
            listOf(4, 5, 4, 3),
            listOf(7, 7, 6, 5),
            listOf(5, 2, 6, 3)
        )
        assertTrue(winningBoard.isWinningBoard(nrsDrawn))
    }

}
