package aoc

import java.io.File
import java.math.BigInteger

object Day6LanternFish : ChallengeDay {

    private const val INIT_TIMER_VAL_NEW_BORN = 8
    private const val TIMER_VAL_AFTER_SPAWN = 6

    fun part1(path: String): BigInteger = File(path).toFishCount(days = 80)

    fun part2(path: String): BigInteger = File(path).toFishCount(days = 256)

    private fun File.toFishCount(days: Int): BigInteger {
        val daysLeftTillNewSpawnCounts = toCountsArray()
        for (day in 1..days) {
            val curDaysLeftTillNewSpawnCounts = daysLeftTillNewSpawnCounts.copyOf()
            val nrOfNewBorns = curDaysLeftTillNewSpawnCounts[0]
            daysLeftTillNewSpawnCounts[INIT_TIMER_VAL_NEW_BORN] = nrOfNewBorns

            for (daysLeft in 1 until curDaysLeftTillNewSpawnCounts.size) {
                daysLeftTillNewSpawnCounts[daysLeft - 1] = curDaysLeftTillNewSpawnCounts[daysLeft]
            }
            daysLeftTillNewSpawnCounts[TIMER_VAL_AFTER_SPAWN] += nrOfNewBorns
        }
        return daysLeftTillNewSpawnCounts.sumOf { it }
    }

    private fun File.toCountsArray(): Array<BigInteger> {
        val daysLeftTillNewSpawnCounts = Array(INIT_TIMER_VAL_NEW_BORN + 1) { 0.toBigInteger() }
        readText().split(',').map { it.trim().toInt() }
            .forEach { daysLeft -> daysLeftTillNewSpawnCounts[daysLeft]++ }
        return daysLeftTillNewSpawnCounts
    }

    override fun part1() = part1("input/day6.txt")
    override fun part2() = part2("input/day6.txt")
}
