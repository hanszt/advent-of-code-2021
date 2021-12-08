package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GridPointTest {

    @Test
    fun testPointsEqualWhenSameCoordinate() = assertEquals(GridPoint(2, 4), GridPoint(2, 4))
}
