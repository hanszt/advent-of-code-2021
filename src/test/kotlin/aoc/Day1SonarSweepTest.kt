package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1SonarSweepTest {

    @Test
    fun testSumDepthIncreases() {
        val result = Day1SonarSweep.calculatePart1("input/day1.txt")
        println(result)
        assertEquals(1722, result)
    }

    @Test
    fun testSumDepthRangeIncreases() {
        val result = Day1SonarSweep.calculatePart2("input/day1.txt")
        println(result)
        assertEquals(1748, result)
    }
}
