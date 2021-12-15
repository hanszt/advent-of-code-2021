package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day16Test {

    @Test
    fun `part 1 test input`() = Assertions.assertEquals(0, Day16.part1("input/day16test.txt"))

    @Test
    fun `part 1 result`() = Assertions.assertEquals(0, Day16.part1().also(::println))

    @Test
    fun `part 2 test input`() = Assertions.assertEquals(0, Day16.part2("input/day16test.txt"))

    @Test
    fun `part 2 result`() = Assertions.assertEquals(0, Day16.part2().also(::println))
}
