package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day22ReactorRebootTest {

    @Test
    fun `part 1 test input`() = assertEquals(590784, Day22ReactorReboot.part1("input/day22test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(623748, Day22ReactorReboot.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(2758514936282235, Day22ReactorReboot.part2("input/day22test-2.txt"))

    @Test
    fun `part 2 result`() = assertEquals(1227345351869476, Day22ReactorReboot.part2().also(::println))
}
