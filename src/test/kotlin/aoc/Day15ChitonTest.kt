package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day15ChitonTest {

    @Test
    fun `part 1 test input`() = assertEquals(40, Day15Chiton.part1("input/day15test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(592, Day15Chiton.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(315, Day15Chiton.part2("input/day15test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(2897, Day15Chiton.part2().also(::println))
}
