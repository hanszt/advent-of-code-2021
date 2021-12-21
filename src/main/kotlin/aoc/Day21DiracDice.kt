package aoc

import utils.wrapBack
import java.io.File
import kotlin.math.min

internal object Day21DiracDice : ChallengeDay {

    fun part1(path: String): Int {
        val (player1, player2) = File(path).toStartingPositions()
        return playGame(player1, player2, 1000)
    }

    fun playGame(player1: Player, player2: Player, winningScore: Int): Int {
        var round = 0
        val dice = Dice()
        while (player1.score < winningScore && player2.score < winningScore) {
            if (round != 0 && round % 3 == 0) {
                if (player1Playing(round - 1)) player1.updateScore() else player2.updateScore()
            }
            if (player1Playing(round)) player1.move(dice.roll()) else player2.move(dice.roll())
            round++
        }
        return (dice.timesRolled - 1) * min(player1.score, player2.score)
    }

    private fun File.toStartingPositions() = readLines().map { it.last().digitToInt() }.map { Player(0, it) }

    fun part2(path: String): Long {
        val (player1, player2) = File(path).toStartingPositions()
        val winningCounts = Winnings(0 , 0)
        for (newDiceValue in 1..3) {
            playGameUsingDiracDice(player1.copy(), player2.copy(), 1, newDiceValue, winningCounts)
        }
        println("winningCounts = ${winningCounts}")
        return maxOf(winningCounts.player1Winnings, winningCounts.player2Winnings)
    }

    fun playGameUsingDiracDice(
        player1: Player,
        player2: Player,
        round: Int,
        diceValue: Int,
        winningCounts: Winnings
    ) {
//        println("round = ${round}")
        val winningThreshold = 12
        if (player1.score >= winningThreshold || player2.score >= winningThreshold) {
            if (player1.score > player2.score) winningCounts.player1Winnings++ else winningCounts.player2Winnings++
            return
        }
        if (round != 0 && round % 3 == 0) {
            if (player1Playing(round - 1)) player1.updateScore() else player2.updateScore()
        }
        if (player1Playing(round)) player1.move(diceValue) else player2.move(diceValue)
        for (newDiceValue in 1..3) {
            playGameUsingDiracDice(player1.copy(), player2.copy(), round + 1, newDiceValue, winningCounts)
        }
    }

    fun player1Playing(round: Int): Boolean = ((round) / 3) % 2 == 0

    override fun part1() = part1("input/day21.txt")
    override fun part2() = part2("input/day21.txt")

    data class Player(var score: Int, var position: Int) {

        fun move(steps: Int) {
            position = (position + steps).wrapBack(10, 1)
        }

        fun updateScore() {
            score += position
        }

        fun copy() = Player(score, position)
    }

    data class Dice(var value: Int = 0, var timesRolled: Int = 0) {

        fun roll(): Int {
            timesRolled++
            value++
            value = value.wrapBack(100, 1)
            return value
        }
    }

    data class Winnings(var player1Winnings: Long, var player2Winnings: Long)
}

