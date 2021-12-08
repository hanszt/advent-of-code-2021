package utils

data class SignalEntry(val uniqueSignalPatterns: List<String>, val fourDigitOutput: List<String>) {

    fun decodeFourDigitOutPut(): Int {
        val patternsToDigits: Map<String, Int> = patternToNr()
        return fourDigitOutput.map { digits -> findDigits(patternsToDigits, digits) }
            .joinToString("") { "$it" }.toInt()
    }

    private fun findDigits(patternsToDigits: Map<String, Int>, output: String): Int? {
        val match = patternsToDigits.keys
            .first { (it.length == output.length) and (it.toSet().containsAll(output.toSet())) }
        return patternsToDigits[match]
    }

    // A representation of all the segments with nr 0 to 6. They are tracked in the segments array in this method
    //      0  xxxx
    //      1 x    x 2
    //        x    x
    //      3  xxxx
    //      4 x    x 5
    //        x    x
    //      6  xxxx
    fun patternToNr(): Map<String, Int> {
        val segments: Array<Char> = Array(7) { '0' }
        val patternsToDigits: MutableMap<String, Int> = HashMap()
        val onePattern = uniqueSignalPatterns.first { it.length == 2 }
        patternsToDigits[onePattern] = 1
        val fourPattern = uniqueSignalPatterns.first { it.length == 4 }
        patternsToDigits[fourPattern] = 4
        val sevenPattern = uniqueSignalPatterns.first { it.length == 3 }
        patternsToDigits[sevenPattern] = 7
        val eightPattern = uniqueSignalPatterns.first { it.length == 7 }
        patternsToDigits[eightPattern] = 8

        segments[0] = sevenPattern.first { onePattern.contains(it).not() }

        val placeOneAndThree = fourPattern.filter { onePattern.contains(it).not() }
        val twoThreeFive = uniqueSignalPatterns.filter { it.length == 5 }
        val zeroSixNine = uniqueSignalPatterns.filter { it.length == 6 }

        val twoThreeFivePartition = twoThreeFive.partition { it.toSet().containsAll(onePattern.toSet()) }

        val threePattern = twoThreeFivePartition.first.first()
        patternsToDigits[threePattern] = 3
        val twoFive = twoThreeFivePartition.second

        val partitionZeroSixNine = zeroSixNine.partition { it.toSet().containsAll(threePattern.toSet())}
        val ninePattern = partitionZeroSixNine.first.first()
        patternsToDigits[ninePattern] = 9
        val zeroSix = partitionZeroSixNine.second

        segments[1] = ninePattern.first { threePattern.contains(it).not() }
        segments[3] = placeOneAndThree.first { it != segments[1] }

        val partitionZeroSix = zeroSix.partition { it.contains(segments[3]) }
        patternsToDigits[partitionZeroSix.first.first()] = 6
        patternsToDigits[partitionZeroSix.second.first()] = 0

        val partitionTwoFive = twoFive.partition { it.contains(segments[1]) }
        patternsToDigits[partitionTwoFive.first.first()] = 5
        patternsToDigits[partitionTwoFive.second.first()] = 2
        return patternsToDigits
    }
}
