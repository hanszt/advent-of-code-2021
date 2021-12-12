package utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
}
