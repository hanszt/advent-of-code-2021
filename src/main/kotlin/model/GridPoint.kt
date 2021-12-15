package model

import kotlin.math.abs
import kotlin.math.sign

internal data class GridPoint(val x: Int, val y: Int) {
    operator fun plus(other: GridPoint) = GridPoint(x + other.x, y + other.y)
    operator fun minus(other: GridPoint) = GridPoint(x - other.x, y - other.y)
    operator fun times(factor: Int) = GridPoint(x * factor, y * factor)
    fun toSignVector() = GridPoint(x.sign, y.sign)
    fun gridDistance(other: GridPoint) = abs(x - other.x).coerceAtLeast(abs(y - other.y))
    fun toPair() = x to y
}
