package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1SonarSweepTest {

    @Test
    fun testSumDepthIncreases() =
        assertEquals(1722, Day1SonarSweep.part1("input/day1.txt").also(::println))

    @Test
    fun testSumDepthRangeIncreases() =
        assertEquals(1748, Day1SonarSweep.part2("input/day1.txt").also(::println))
}
