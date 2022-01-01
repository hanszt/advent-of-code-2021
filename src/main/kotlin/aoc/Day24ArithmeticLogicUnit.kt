package aoc

import utils.self

//
// Thanks to William Y. Feng for explaining the solution: See https://www.youtube.com/watch?v=Eswmo7Y7C4U
//
// His repo can be visited here: https://github.com/womogenes/AoC-2021-Solutions/tree/main/day_24
//
//
internal object Day24ArithmeticLogicUnit : ChallengeDay {

    // these values are extracted from the input dataset. See https://www.youtube.com/watch?v=Eswmo7Y7C4U for how these
    // values where obtained
    private val incrementToRequired = listOf(
        8 to null,
        8 to null,
        12 to null,
        null to 8,
        2 to null,
        8 to null,
        null to 11,
        9 to null,
        null to 3,
        3 to null,
        null to 3,
        null to 1,
        null to 10,
        null to 16
    )

    private fun createInputSpace(intProgression: IntProgression) = buildList {
        intProgression.forEach { i1 ->
            intProgression.forEach { i2 ->
                intProgression.forEach { i3 ->
                    intProgression.forEach { i4 ->
                        intProgression.forEach { i5 ->
                            intProgression.forEach { i6 ->
                                intProgression.forEach { i7 -> add(intArrayOf(i1, i2, i3, i4, i5, i6, i7)) }
                            }
                        }
                    }
                }
            }
        }
    }

    @Suppress("KotlinConstantConditions")
    private fun validNr(digits: IntArray): IntArray? {
        var z = 0
        var digitsIndex = 0
        val result = IntArray(14)

        for ((index, pair) in incrementToRequired.withIndex()) {
            val (increment, modReq) = pair
            val constant = 26
            if (increment != null) {
                z = z * constant + digits[digitsIndex] + increment
                result[index] = digits[digitsIndex]
                digitsIndex += 1
            } else if (modReq != null) {
                result[index] = ((z % constant) - modReq)
                z /= constant
                if (result[index] !in 1..9) {
                    return null
                }
            } else throw IllegalStateException("increment or mod must be none null")
        }
        return result
    }

    override fun part1() = solve(9 downTo 1)
    override fun part2() = solve(1..9)

    private fun solve(intProgression: IntProgression) = createInputSpace(intProgression).asSequence()
        .map(::validNr)
        .firstNotNullOf(::self)
        .joinToString("")
        .toLong()
}
