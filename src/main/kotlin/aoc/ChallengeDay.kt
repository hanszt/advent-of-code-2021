package aoc

import utils.camelCaseToSentence


interface ChallengeDay {

    fun results(): List<Result> = listOf(
        runChallengeTimed(::part1, "${name()} part 1"),
        runChallengeTimed(::part2, "${name()} part 2"))

    private fun name(): String = javaClass.simpleName.camelCaseToSentence()

    private fun runChallengeTimed(solution: () -> Number, name: String): Result {
        val start = System.nanoTime()
        val result = solution()
        return Result(name, result, System.nanoTime() - start)
    }

    fun part1(): Number
    fun part2(): Number
}
