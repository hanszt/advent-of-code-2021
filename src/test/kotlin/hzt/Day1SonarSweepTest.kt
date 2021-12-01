package hzt

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1SonarSweepTest {

    @Test
    fun testSonarSweepPart1() = assertEquals(1722, Day1SonarSweep.calculatePart1("input/day1.txt"))

    @Test
    fun testSonarSweepPart2() = assertEquals(1748, Day1SonarSweep.calculatePart2("input/day1.txt"))
}
