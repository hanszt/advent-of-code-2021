package aoc

import java.io.File

object Day6LanternFish {

    const val INTERNAL_TIMER_VAL_NEW_BORN = 8
    const val TIMER_VAL_AFTER_SPAWN = 6

    fun part1(path: String): Int = File(path).readText().split(',')
        .map { LanternFish(it.trim().toInt()) }
        .countAfterDays(days = 80)

    private fun Collection<LanternFish>.countAfterDays(days: Int): Int {
        val lanternFish: MutableList<LanternFish> = ArrayList(this)
        for (day in 1..days) {
            val newBorns: MutableList<LanternFish> = ArrayList()
            for (fish in lanternFish) {
                if (fish.isSpawning()) newBorns.add(LanternFish())
            }
            lanternFish.addAll(newBorns)
        }
        return lanternFish.count()
    }

    private fun Collection<Int>.fishCountAfterDays(days: Int): Long {
        val daysLeftTillNewSpawnCounts = LongArray(INTERNAL_TIMER_VAL_NEW_BORN + 1)
        this.forEach { daysLeft -> daysLeftTillNewSpawnCounts[daysLeft]++ }
        for (day in 1..days) {
            val curDaysLeftTillNewSpawnCounts = daysLeftTillNewSpawnCounts.copyOf()
            val nrOfNewBorns = curDaysLeftTillNewSpawnCounts[0]
            daysLeftTillNewSpawnCounts[INTERNAL_TIMER_VAL_NEW_BORN] = nrOfNewBorns

            for (daysLeft in 1 until curDaysLeftTillNewSpawnCounts.size) {
                daysLeftTillNewSpawnCounts[daysLeft - 1] = curDaysLeftTillNewSpawnCounts[daysLeft]
            }
            daysLeftTillNewSpawnCounts[TIMER_VAL_AFTER_SPAWN] += nrOfNewBorns
        }
        return daysLeftTillNewSpawnCounts.sum()
    }

    fun part2(path: String): Long = File(path).readText().split(',')
        .map { it.trim().toInt() }
        .fishCountAfterDays(days = 256)

    data class LanternFish(var daysLeft: Int) {

        constructor() : this(INTERNAL_TIMER_VAL_NEW_BORN)

        fun isSpawning(): Boolean {
            val readyToSpawn = daysLeft == 0
            if (daysLeft > 0) daysLeft--
            else daysLeft = TIMER_VAL_AFTER_SPAWN
            return readyToSpawn
        }
    }
}

