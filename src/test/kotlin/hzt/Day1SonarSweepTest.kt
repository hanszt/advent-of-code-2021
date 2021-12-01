package hzt

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1SonarSweepTest {

    @Test
    fun testSumDepthIncreases() = assertEquals(1722, Day1SonarSweep.calculatePart1("input/day1.txt"))

    @Test
    fun testSumDepthRangeIncreases() = assertEquals(1748, Day1SonarSweep.calculatePart2("input/day1.txt"))
}
