package utils

import aoc.Day8SevenSegmentSearch.toSignalEntry
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SignalEntryTest {

    @Test
    fun deduceSignalToNumberMatches() {
        val entry = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
        val signalEntry = entry.toSignalEntry()
        val patterns = signalEntry.patternNrsRepresentedByIndex()
        assertEquals(5, patterns.indexOf("cdfbe"))
    }

    @Test
    fun testDecodeDigits() {
        val entry = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
        val signalEntry = entry.toSignalEntry()
        val output = signalEntry.decodeNumber()
        assertEquals(5353, output)
    }
}
