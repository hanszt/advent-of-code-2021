package model

import kotlin.math.abs

data class Line(val begin: GridPoint, val end: GridPoint) {

    val coordinates: Set<GridPoint> = toLineCoordinates(begin, end)

    fun isHorizontal(): Boolean = begin.y == end.y

    fun isVertical(): Boolean = begin.x == end.x

    private fun isDiagonal(): Boolean = abs(begin.x - end.x) == abs(begin.y - end.y)

    private fun toLineCoordinates(begin: GridPoint, end: GridPoint): Set<GridPoint> {
        if (isVertical() or isHorizontal() or isDiagonal()) {
            val dir = (end - begin).toSignVector()
            return (0..begin.gridDistance(end))
                .map { step -> begin + dir * step }
                .toSet()
        } else throw UnsupportedOperationException("Only horizontal, vertical or diagonal lines supported")
    }

    override fun toString(): String = "Line(begin=$begin, end=$end, coordinates=$coordinates)"
}
