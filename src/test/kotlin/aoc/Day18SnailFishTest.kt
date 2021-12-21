package aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class Day18SnailFishTest {

    @Test
    fun `part 1 test input`() = assertEquals(4140, Day18SnailFish.part1("input/day18test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(3486, Day18SnailFish.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(3993, Day18SnailFish.part2("input/day18test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(4747, Day18SnailFish.part2().also(::println))

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[[[[[9,8],1],2],3],4] -> [[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]] -> [7,[6,[5,[7,0]]]]",
            "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]] -> [[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
        ]
    )
    fun `test reduce nr`(snailNrToExpected: String) {
        val (snailNrAsString, expected) = snailNrToExpected.split(" -> ")
        val snailNr = Day18SnailFish.toSnailNr(snailNrAsString)
        val reducedNr = snailNr.reduce()
        assertEquals(expected, reducedNr.toString())
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[[1,2],[[3,4],5]] -> 143",
            "[[[[0,7],4],[[7,8],[6,0]]],[8,1]] -> 1384",
            "[[[[1,1],[2,2]],[3,3]],[4,4]] -> 445",
            "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]] -> 3488"]
    )
    fun `calculate magnitude of snail numbers`(snailNrToExpected: String) {
        val (snailNr, expected) = snailNrToExpected.split(" -> ")
        val magnitude = Day18SnailFish.toSnailNr(snailNr).magnitude()
        assertEquals(expected.toInt(), magnitude)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[[[[4,3],4],4],[7,[[8,4],9]]] + [1,1] = [[[[0,7],4],[[7,8],[6,0]]],[8,1]]",
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]] + [7,[[[3,7],[4,3]],[[6,3],[8,8]]]] = [[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]",
            "[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]] + [2,9] = [[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]"
        ]
    )
    fun `add two snail nrs`(equation: String) {
        val (nrsToAdd, expected) = equation.split(" = ")
        val (first, second) = nrsToAdd.split(" + ")
        val snailNr = Day18SnailFish.toSnailNr(first)
        val other = Day18SnailFish.toSnailNr(second)
        val result = snailNr.add(other)
        assertEquals(expected, result.toString())
    }

    @Test
    fun `snail nrs as string`() {

    }
}
