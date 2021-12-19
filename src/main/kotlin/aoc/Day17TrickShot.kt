package aoc

import model.GridPoint2D

internal object Day17TrickShot : ChallengeDay {

    private const val upperSearchBoundY = 1000

    fun part1(targetAreaX: IntRange, targetAreaY: IntRange): Int =
        probesInTargetArea(targetAreaX, targetAreaY).maxOf { it.highestPosition?.y ?: 0 }

    private fun probesInTargetArea(targetAreaX: IntRange, targetAreaY: IntRange) =
        (0..targetAreaX.last).asSequence()
            .flatMap { initVelocityX ->
                (0 until upperSearchBoundY)
                    .map { initVelocityY -> Probe(GridPoint2D(initVelocityX, initVelocityY)) }
                    .filter { it.endsUpInTargetArea(targetAreaX, targetAreaY) }
            }

    fun part2(targetRangeX: IntRange, targetRangeY: IntRange): Int {
        val successfulInitVelocityVals = mutableSetOf<GridPoint2D>()
        for (initVelX in 1..targetRangeX.last) {
            for (initVelY in targetRangeY.first until upperSearchBoundY) {
                val probe = Probe(GridPoint2D(initVelX, initVelY))
                val endsUpInTargetArea = probe.endsUpInTargetArea(targetRangeX, targetRangeY)
                if (endsUpInTargetArea) {
                    successfulInitVelocityVals.add(GridPoint2D(initVelX, initVelY))
                }
            }
        }
        return successfulInitVelocityVals.size
    }

    override fun part1() = part1(185..221, -122..-74)
    override fun part2() = part2(185..221, -122..-74)

    private class Probe(var velocity: GridPoint2D = GridPoint2D(0, 0)) {

        var highestPosition: GridPoint2D? = null
        var position = GridPoint2D(0, 0)

        private fun updatePosition(): GridPoint2D {
            position = position.plus(velocity)
            velocity = velocity.plusX(0.compareTo(velocity.x))
            velocity = velocity.plusY(-1)
            highestPosition = if (position.y < (highestPosition?.y ?: 0)) highestPosition else position
            return position
        }

        fun endsUpInTargetArea(targetRangeX: IntRange, targetRangeY: IntRange): Boolean {
            while (position.x < targetRangeX.last && position.y > targetRangeY.first) {
                val nextPosition = updatePosition()
                if (nextPosition.x in targetRangeX && nextPosition.y in targetRangeY) {
                    return true
                }
            }
            return false
        }

        override fun toString(): String = "Probe(velocity=$velocity, position=$position)"
    }
}
