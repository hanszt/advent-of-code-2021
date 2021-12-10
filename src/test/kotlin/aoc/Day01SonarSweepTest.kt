package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day01SonarSweepTest {

    @Test
    fun testSumDepthIncreases() =
        assertEquals(1722, Day01SonarSweep.part1().also(::println))

    @Test
    fun testSumDepthRangeIncreases() =
        assertEquals(1748, Day01SonarSweep.part2().also(::println))
}
