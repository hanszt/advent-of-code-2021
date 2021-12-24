package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day23AmphipodTest {

    private val day23Amphipod = Day23Amphipod(testMode = true)

    @Test
    fun `part 1 test input`() = assertEquals(12521, day23Amphipod.part1("input/day23test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(14348, day23Amphipod.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(44169, day23Amphipod.part2("input/day23test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(40954, day23Amphipod.part2().also(::println))
}
