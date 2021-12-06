package utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AocUtilsKtTest {

    @Test
    fun testRotatedClockWise() {
        val input = listOf(
            listOf(1, 2),
            listOf(3, 4)
        )
        val expected = arrayOf(
            intArrayOf(3, 1),
            intArrayOf(4, 2)
        )
        assertArrayEquals(expected, input.rotatedClockWise())
    }
}
