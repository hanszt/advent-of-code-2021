package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day24ArithmeticLogicUnitTest {

    @Test
    fun `part 1 result`() = assertEquals(99598963999971, Day24ArithmeticLogicUnit.part1().also(::println))

    @Test
    fun `part 2 result`() = assertEquals(93151411711211, Day24ArithmeticLogicUnit.part2().also(::println))

    @Test
    fun `test floor division`() {
        var x = 5
        x /= 3
        println(x)
        assertEquals(1, x)
    }
}
