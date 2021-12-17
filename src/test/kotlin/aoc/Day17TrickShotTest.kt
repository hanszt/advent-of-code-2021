package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day17TrickShotTest {

    @Test
    fun `part 1 test input`() = assertEquals(45, Day17TrickShot.part1(20..30, -10..-5))

    @Test
    fun `part 1 result`() = assertEquals(7381, Day17TrickShot.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(112, Day17TrickShot.part2(20..30, -10..-5))

    @Test
    fun `part 2 result`() = assertEquals(3019, Day17TrickShot.part2().also(::println))
}
