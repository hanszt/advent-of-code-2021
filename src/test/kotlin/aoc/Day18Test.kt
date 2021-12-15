package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day18Test {

    @Test
    fun `part 1 test input`() = Assertions.assertEquals(0, Day18.part1("input/day18test.txt"))

    @Test
    fun `part 1 result`() = Assertions.assertEquals(0, Day18.part1().also(::println))

    @Test
    fun `part 2 test input`() = Assertions.assertEquals(0, Day18.part2("input/day18test.txt"))

    @Test
    fun `part 2 result`() = Assertions.assertEquals(0, Day18.part2().also(::println))
}
