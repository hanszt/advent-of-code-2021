package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class VectorTest {

    @Test
    fun testVectorEqualWhenSameCoordinate() = assertEquals(Vector(2, 4), Vector(2, 4))
}
