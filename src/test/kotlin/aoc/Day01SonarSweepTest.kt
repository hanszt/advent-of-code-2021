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

    @Test
    fun `part 2 v2 gives same answer`() {
        val filePath = "input/day1.txt"
        assertEquals(Day01SonarSweep.part2(filePath), Day01SonarSweep.part2V2(filePath))
    }
}
