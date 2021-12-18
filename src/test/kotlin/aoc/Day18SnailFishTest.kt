package aoc

import aoc.Day18SnailFish.addSnailNrs
import aoc.Day18SnailFish.calculateMagnitude
import aoc.Day18SnailFish.explodeMostLeftPairNestedIn5Brackets
import aoc.Day18SnailFish.reduceSnailNr
import aoc.Day18SnailFish.splitIfNrGreaterThan10
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class Day18SnailFishTest {

    @Test
    fun `part 1 test input`() = assertEquals(4140, Day18SnailFish.part1("input/day18test.txt"))

    @Test
    fun `part 1 result`() = assertEquals(0, Day18SnailFish.part1().also(::println))

    @Test
    fun `part 2 test input`() = assertEquals(0, Day18SnailFish.part2("input/day18test.txt"))

    @Test
    fun `part 2 result`() = assertEquals(0, Day18SnailFish.part2().also(::println))

    @ParameterizedTest
    @ValueSource(strings = [
        "[[[[4,3],4],4],[7,[[8,4],9]]] + [1,1] = [[[[0,7],4],[[7,8],[6,0]]],[8,1]]",
        "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]] + [7,[[[3,7],[4,3]],[[6,3],[8,8]]]] = [[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"
    ])
    fun `add two snail nrs`(equation: String) {
        val (nrsToAdd, expected) = equation.split(" = ")
        val (first, second) = nrsToAdd.split(" + ")
        val result = addSnailNrs(first, second)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[[[[[9,8],1],2],3],4] -> [[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]] -> [7,[6,[5,[7,0]]]]",
            "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]] -> [[[[0,7],4],[[7,8],[6,0]]],[8,1]]"
        ]
    )
    fun `reduce a snail number`(snailNrToExpected: String) {
        val (snailNr, expected) = snailNrToExpected.split(" -> ")
        val reduced = snailNr.reduceSnailNr()
        assertEquals(expected, reduced)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[[[[[9,8],1],2],3],4] -> [[[[0,9],2],3],4]",
            "[7,[6,[5,[4,[3,2]]]]] -> [7,[6,[5,[7,0]]]]",
            "[[6,[5,[4,[3,2]]]],1] -> [[6,[5,[7,0]]],3]",
            "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]] -> [[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
            "[[[[[7,3],4],4],[7,[[8,4],9]]],[1,1]] -> [[[[0,7],4],[7,[[8,4],9]]],[1,1]]",
            "[[[[0,7],4],[7,[[8,4],9]]],[1,1]] -> [[[[0,7],4],[15,[0,13]]],[1,1]]"
        ]
    )
    fun `explode a snail number`(snailNrToExpected: String) {
        val (snailNr, expected) = snailNrToExpected.split(" -> ")
        val reduced = snailNr.explodeMostLeftPairNestedIn5Brackets()
        assertEquals(expected, reduced)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[[[[0,7],4],[15,[0,13]]],[1,1]] -> [[[[0,7],4],[[7,8],[0,13]]],[1,1]]",
            "[[[[0,7],4],[[7,8],[0,13]]],[1,1]] -> [[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]] -> "
        ]
    )
    fun `split a snail number`(snailNrToExpected: String) {
        val (snailNr, expected) = snailNrToExpected.split(" -> ")
        val reduced = snailNr.splitIfNrGreaterThan10()
        assertEquals(expected, reduced)
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
        val magnitude = snailNr.calculateMagnitude()
        assertEquals(expected.toInt(), magnitude)
    }
}
