package utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

internal class AocUtilsKtTest {

    @ParameterizedTest
    @ValueSource(strings = ["sdfsd", "hallo"])
    fun `test input contains no digits`(input: String) = assertTrue(input.containsNoDigits())

    @ParameterizedTest
    @ValueSource(strings = ["sdfs2d", "hallo3", "HAns#4"])
    fun `test input contains digits`(input: String) = assertFalse(input.containsNoDigits())

    @ParameterizedTest
    @ValueSource(strings = ["345", "2342342424234", "234234234", "3453"])
    fun `test input is natural nr`(input: String) = assertTrue(input.isNaturalNumber())

    @ParameterizedTest
    @ValueSource(strings = ["sdfs2d", "hallo3", "HAns#4", "-345345", "345,345"])
    fun `test input is not a natural nr`(input: String) = assertFalse(input.isNaturalNumber())

    @Test
    fun wrapBackTo() {
        val wrapBackTo2After9 = (0..20).map { it.wrapBack(2, 9) }
        val wrapBackTo1After10 = (0..20).map { it.wrapBack(1, 10) }
        val wrapBackTo7After16 = (0..20).map { it.wrapBack(7, 16) }

        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4), wrapBackTo2After9)
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), wrapBackTo1After10)
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 7, 8, 9, 10), wrapBackTo7After16)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1..10 -> 55", "1..100 -> 5050", "1..1000 -> 500500"])
    fun `test sum natural nrs`(intRangeToSum: String) {
        val (start, end, expected) = intRangeToSum.split("..", " -> ").map(String::toInt)
        assertEquals(expected, sumNaturalNrs(start, end))
    }
}
