package utils

import org.junit.jupiter.api.Test

internal class JavaAocUtilsTest {

    @Test
    fun diagonals() {
        val diagonals = JavaAocUtils.diagonal(Vector(0, 8), Vector(8, 0))
        diagonals.forEach(::println)
    }
}
