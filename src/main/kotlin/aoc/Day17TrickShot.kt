package aoc

import model.GridPoint

internal object Day17TrickShot : ChallengeDay {

    private const val upperSearchBoundY = 1000

    fun part1(targetAreaX: IntRange, targetAreaY: IntRange): Int =
        probesInTargetArea(targetAreaX, targetAreaY).maxOf { it.highestPosition?.y ?: 0 }

    private fun probesInTargetArea(targetAreaX: IntRange, targetAreaY: IntRange) =
        (0..targetAreaX.last).asSequence()
            .flatMap { initVelocityX ->
                (0 until upperSearchBoundY)
                    .map { initVelocityY -> Probe(GridPoint(initVelocityX, initVelocityY)) }
                    .filter { it.endsUpInTargetArea(targetAreaX, targetAreaY) }
            }

    fun part2(targetAreaX: IntRange, targetAreaY: IntRange): Int {
        val successfulInitVelocityVals = mutableSetOf<GridPoint>()
        for (initVelX in 1..targetAreaX.last) {
            for (initVelY in targetAreaY.first until upperSearchBoundY) {
                val probe = Probe(GridPoint(initVelX, initVelY))
                val endsUpInTargetArea = probe.endsUpInTargetArea(targetAreaX, targetAreaY)
                if (endsUpInTargetArea) {
                    successfulInitVelocityVals.add(GridPoint(initVelX, initVelY))
                }
            }
        }
        return successfulInitVelocityVals.size
    }

    override fun part1() = part1(185..221, -122..-74)
    override fun part2() = part2(185..221, -122..-74)

    private class Probe(var velocity: GridPoint = GridPoint(0, 0)) {

        var highestPosition: GridPoint? = null
        var position = GridPoint(0, 0)

        private fun updatePosition(): GridPoint {
            position = position.plus(velocity)
            velocity = velocity.plusX(0.compareTo(velocity.x))
            velocity = velocity.plusY(-1)
            return position
        }

        fun endsUpInTargetArea(targetAreaX: IntRange, targetAreaY: IntRange): Boolean {
            while (position.x < targetAreaX.last && position.y > targetAreaY.first) {
                val pos = updatePosition()
                highestPosition = if (position.y < (highestPosition?.y ?: 0)) highestPosition else position
                if (pos.x in targetAreaX && pos.y in targetAreaY) {
                    return true
                }
            }
            return false
        }

        override fun toString(): String = "Probe(velocity=$velocity, position=$position)"
    }
}
