package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day24ArithmeticLogicUnitTest {

    @Test
    fun `part 1 result`() = Day24ArithmeticLogicUnit.part1().also(::println).assertEqualTo(99598963999971)

    @Test
    fun `part 1 second input`() = Day24ArithmeticLogicUnit.part1("input/day24-2.txt").assertEqualTo(94992992796199)

    @Test
    fun `part 2 result`() = Day24ArithmeticLogicUnit.part2().also(::println).assertEqualTo(93151411711211)

    @Test
    fun `part 2 second input`() = Day24ArithmeticLogicUnit.part2("input/day24-2.txt").assertEqualTo(11931881141161)

    @Test
    fun `test floor division`() {
        var x = 5
        x /= 3
        println(x)
        assertEquals(1, x)
    }
}
