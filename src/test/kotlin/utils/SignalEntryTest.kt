package utils

import aoc.Day8SevenSegmentSearch.toSignalEntry
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SignalEntryTest {

    @Test
    fun deduceSignalToNumberMatches() {
        val entry = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
        val signalEntry = entry.toSignalEntry()
        val signalToNr = signalEntry.patternToNr()
        assertEquals(5, signalToNr["cdfbe"])
    }

    @Test
    fun testDecodeFourDigitOutput() {
        val entry = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
        val signalEntry = entry.toSignalEntry()
        val output = signalEntry.decodeFourDigitOutPut()
        assertEquals(5353, output)
    }
}
