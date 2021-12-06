package aoc


interface ChallengeDay {

    fun results(): List<Result> = listOf(
        runChallengeTimed(::part1, "${name()} part 1"),
        runChallengeTimed(::part2, "${name()} part 2"))

    private fun name(): String = javaClass.simpleName

    private fun runChallengeTimed(solution: () -> Number, name: String): Result {
        val start = System.currentTimeMillis()
        val result = solution.invoke()
        return Result(name, result, System.currentTimeMillis() - start)
    }

    fun part1(): Number
    fun part2(): Number
}
