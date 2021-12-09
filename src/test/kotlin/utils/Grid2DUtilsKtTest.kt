package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Grid2DUtilsKtTest {

    @Test
    fun `test rotated rotates int matrix by 90 deg clockwise`() {
        val input = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val expected = arrayOf(
            intArrayOf(4, 1),
            intArrayOf(5, 2),
            intArrayOf(6, 3)
        )
        val rotatedClockWise = input.rotated()
        rotatedClockWise.printAsGrid()
        assertArrayEquals(expected, rotatedClockWise)
    }

    @Test
    fun `test rotated counter clockwise rotates int matrix by 90 deg counter clockwise`() {
        val input = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val expected = arrayOf(
            intArrayOf(3, 6),
            intArrayOf(2, 5),
            intArrayOf(1, 4)
        )
        val rotatedCounterClockWise = input.rotated(true)
        rotatedCounterClockWise.printAsGrid()
        assertArrayEquals(expected, rotatedCounterClockWise)
    }

    @Test
    fun `test rotate rotates generic matrix by 90 deg clockwise`() {
        val input = listOf(
            listOf(GridPoint(1, 1), GridPoint(2, 2))
        )
        val expected = listOf(
            listOf(GridPoint(1, 1)),
            listOf(GridPoint(2, 2))
        )
        val rotatedClockWise = input.rotate()
        rotatedClockWise.forEach(::println)
        assertEquals(expected, rotatedClockWise)
    }

    @Test
    fun `test rotated rotates generic 2d array by 90 deg clockwise`() {
        val input = arrayOf(
            arrayOf(GridPoint(1, 1), GridPoint(2, 2)),
            arrayOf(GridPoint(3, 3), GridPoint(4, 4))
        )
        val expected = arrayOf(
            arrayOf(GridPoint(3, 3), GridPoint(1, 1)),
            arrayOf(GridPoint(4, 4), GridPoint(2, 2))
        )
        val rotatedClockWise = input.rotated()
        rotatedClockWise.printAsGrid()
        assertArrayEquals(expected, rotatedClockWise)
    }

    @Test
    fun `test rotated rotates String list by 90 deg clockwise`() {
        val input = listOf(
            "123q",
            "456w"
        )
        val expected = listOf(
            "41",
            "52",
            "63",
            "wq"
        )
        val rotatedClockWise = input.rotated()
        rotatedClockWise.forEach(::println)
        assertEquals(expected, rotatedClockWise)
    }
}
