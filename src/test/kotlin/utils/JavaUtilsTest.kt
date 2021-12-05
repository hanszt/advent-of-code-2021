package utils

import org.junit.jupiter.api.Test

internal class JavaUtilsTest {

    @Test
    fun diagonals() {
        val diagonals = JavaUtils.diagonals(Vector(0, 8), Vector(8, 0))
        diagonals.forEach(::println)
    }
}
