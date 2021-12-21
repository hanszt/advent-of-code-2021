package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day21DiracDiceTest {

    @Test
    fun `part 1 test input`() = assertEquals(739785, Day21DiracDice.part1("input/day21test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(604998, Day21DiracDice.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(444356092776315, Day21DiracDice.part2("input/day21test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(0, Day21DiracDice.part2().also(::println))

    @Test
    fun player1Playing() {
        for (i in 0 until 100) {
            println(Day21DiracDice.player1Playing(i))
        }
    }
}
