package aoc

import java.io.File

object Day3BinaryDiagnostic {

    fun part1(path: String): Int = File(path).readLines()
        .map(String::toCharArray)
        .let(::calculatePowerConsumption)

    private fun calculatePowerConsumption(binaries: List<CharArray>): Int {
        val gammaRateBinary = binaries.sumOnesBinaryDigits().map { if (it * 2 > binaries.size) '1' else '0' }
        val gammaRate = gammaRateBinary.joinToString("").toInt(radix = 2)
        val epsilonRate = gammaRateBinary.map { if (it == '1') '0' else '1' }.joinToString("").toInt(radix = 2)
        return gammaRate * epsilonRate
    }

    private fun List<CharArray>.sumOnesBinaryDigits(): List<Int> = map { binary -> binary.map { it.digitToInt() } }
        .reduce { result, curBinary -> result.indices.map { result[it] + curBinary[it]} }

    fun part2(path: String): Int = File(path).readLines()
        .map(String::toCharArray)
        .let { it.toMutableList().toLiftSupportRating() * it.toMutableList().toCo2ScrubbingRating() }

    private fun MutableList<CharArray>.toLiftSupportRating() = calculate { ones, zeros -> ones >= zeros }

    private fun MutableList<CharArray>.toCo2ScrubbingRating() = calculate { ones, zeros -> ones < zeros }

    private fun MutableList<CharArray>.calculate(biPredicate: (Int, Int) -> Boolean): Int {
        var index = 0
        while (size > 1) {
            val sumAtIndex = sumOnesBinaryDigits()[index]
            val binaryDigit = if (biPredicate.invoke(sumAtIndex * 2, size)) '1' else '0'
            removeIf { it[index] != binaryDigit }
            index++
        }
        return first().joinToString("").toInt(radix = 2)
    }
}
