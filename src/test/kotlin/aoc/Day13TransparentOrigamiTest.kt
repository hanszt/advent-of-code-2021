package aoc

import aoc.Day13TransparentOrigami.toTextOrElseThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day13TransparentOrigamiTest {

    @Test
    fun `part 1 test input`() = assertEquals(17, Day13TransparentOrigami.part1("input/day13test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(607, Day13TransparentOrigami.part1().also(::println))

    @Test
    fun `part 2 test input`() {
        val expected = """
            #####
            #...#
            #...#
            #...#
            #####
            .....
            .....
        """.trimIndent()
        println(expected)
        assertEquals(expected, Day13TransparentOrigami.part2GridAsString("input/day13test.txt"))
    }

    @Test
    fun `part 2 result as grid and as string`() {
        val grid = Day13TransparentOrigami.part2GridAsString("input/day13.txt")
        println(grid)
        val text = grid.toTextOrElseThrow()
        println(text)
        assertEquals("CPZLPFZL", text)
    }
}
