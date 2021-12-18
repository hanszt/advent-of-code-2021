package aoc

import java.io.File

internal object Day18SnailFish : ChallengeDay {

    fun part1(path: String): Int = File(path).readLines().reduce(::addSnailNrs).calculateMagnitude()

    fun addSnailNrs(current: String, other: String): String = "[$current,$other]".reduceSnailNr()

    fun String.reduceSnailNr(): String {
        var counter = 0
        var prevSnailNr = this
        var newSnailNr = explodeMostLeftPairNestedIn5Brackets().splitIfNrGreaterThan10()
        while (counter < 100 && newSnailNr != prevSnailNr) {
            println("newSnailNr = ${newSnailNr}")
            prevSnailNr = newSnailNr
            newSnailNr = prevSnailNr.explodeMostLeftPairNestedIn5Brackets().splitIfNrGreaterThan10()
            counter++
        }
        return newSnailNr
    }

    fun String.explodeMostLeftPairNestedIn5Brackets(): String {
        var nesting = 0
        var cursor = 0
        var digitToLeft: Pair<Int, Int>? = null
        for ((index, c) in toList().withIndex()) {
            if (c == '[') {
                nesting++
                if (nesting > 4) {
                    break
                }
            }
            if (c == ']') nesting--

            if (c.isDigit()) digitToLeft = index to c.digitToInt()
            cursor++
        }
        if (nesting <= 4) return this
        val sb = StringBuilder()
        while (this[cursor] != ']') {
            val char = this[cursor]
            if (char != '[') sb.append(char)
            cursor++
        }
        val oldPair = "[$sb]"
        val (left, right) = sb.split(',')
        val newLeft = digitToLeft?.let { it.second + left.toInt() } ?: 0

        val digitToRight: Int? = (cursor until length).asSequence()
            .map { this[it] }
            .firstOrNull { it.isDigit() }
            ?.digitToInt()
        val newRight = digitToRight?.let { it + right.toInt() } ?: 0
        val index = digitToLeft?.first ?: 0
        return slice(0 until index) + substring(index)
            .replaceFirst("${digitToLeft?.second}", "$newLeft")
            .replaceFirst("$digitToRight", "$newRight")
            .replaceFirst(oldPair, "0")
    }

    fun String.splitIfNrGreaterThan10(): String {
        var doubleDigit: Int? = null
        for (c in 0 until lastIndex) {
            val cur = this[c]
            val next = this[c + 1]
            if (cur.isDigit() && next.isDigit()) {
                doubleDigit = "$cur$next".toInt()
                break
            }
        }
        if (doubleDigit == null) return this
        val newLeft = doubleDigit.div(2)
        val newRight = doubleDigit.div(2) + 1
        return replaceFirst("$doubleDigit", "[$newLeft,$newRight]")
    }

    fun part2(path: String): Int {
        return 0
    }

    fun String.calculateMagnitude(): Int {
        println(this)
        TODO("Not yet implemented")
    }

    override fun part1() = part1("input/day18.txt")
    override fun part2() = part2("input/day18.txt")
}
