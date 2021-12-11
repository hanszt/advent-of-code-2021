package aoc

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.printAsGrid

import utils.toIntGrid

internal class Day11KtTest {

    @Test
    fun testPart1testInput() = Assertions.assertEquals(1656, Day11.part1("input/day11test.txt"))

    @Test
    fun testPart1() = Assertions.assertEquals(1683, Day11.part1().also(::println))

    @Test
    fun testPart2testInput() = Assertions.assertEquals(195, Day11.part2("input/day11test.txt"))

    @Test
    fun testPart2() = Assertions.assertEquals(788, Day11.part2().also(::println))

    @Test
    fun `test simulate one step`() {
        val grid = """
            11111
            19991
            19191
            19991
            11111
        """.trimIndent().lines()
            .toOctopusGrid()
        grid.simulateStep()
        println("Result:")
        grid.printAsGrid(3)
    }
}
