package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day24ArithmeticLogicUnitTest {

    @Test
    fun `part 1 test input`() = assertEquals(0, Day24ArithmeticLogicUnit.part1("input/day24test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(0, Day24ArithmeticLogicUnit.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(0, Day24ArithmeticLogicUnit.part2("input/day24test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(0, Day24ArithmeticLogicUnit.part2().also(::println))
}
