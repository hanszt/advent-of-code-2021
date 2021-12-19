package model

import aoc.Day19BeaconScanner.toPoint3D
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

internal class GridPoint3DTest {

    @ParameterizedTest
    @ValueSource(strings = [
        "1,2,3 times: 1 -> 2,3,1",
        "1,2,3 times: 2 -> 3,1,2",
        "1,2,3 times: 3 -> -1,2,-3",
        "1,2,3 times: 4 -> -2,3,-1",
        "1,2,3 times: 5 -> -3,1,-2",
        "1,2,3 times: 6 -> 1,3,-2",
        "1,2,3 times: 7 -> 2,1,-3",
        "1,2,3 times: 8 -> 3,2,-1"
    ])
    fun rotateClockWise(inputToExpected: String) {
        val (input, expected) = inputToExpected.split(" -> ")
        val (pointAsString, times) = input.split(" times: ")
        val rotated = toPoint3D(pointAsString).rotate(times.toInt())
        assertEquals(toPoint3D(expected), rotated)
    }
}
