package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day24Test {

    @Test
    fun `part 1 test input`() = Assertions.assertEquals(0, Day24.part1("input/day24test.txt"))

    @Test
    fun `part 1 result`() = Assertions.assertEquals(0, Day24.part1().also(::println))

    @Test
    fun `part 2 test input`() = Assertions.assertEquals(0, Day24.part2("input/day24test.txt"))

    @Test
    fun `part 2 result`() = Assertions.assertEquals(0, Day24.part2().also(::println))
}
