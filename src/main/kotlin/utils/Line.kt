package utils

data class Line(val begin: Vector, val end: Vector) {

    val coordinates: Set<Vector>

    init {
        coordinates = createCoordinates(begin, end)
    }

    private fun createCoordinates(begin: Vector, end: Vector): Set<Vector> {
        val coordinates: MutableSet<Vector> = LinkedHashSet()
        when {
            isHorizontal() -> {
                val rangeX = if (begin.x < end.x) begin.x..end.x else end.x..begin.x
                rangeX.mapTo(coordinates) { Vector(it, begin.y) }
            }
            isVertical() -> {
                val rangeY = if (begin.y < end.y) begin.y..end.y else end.y..begin.y
                rangeY.mapTo(coordinates) { Vector(begin.x, it) }
            }
            else -> coordinates.addAll(JavaAocUtils.diagonal(begin, end))
        }
        return coordinates
    }

    fun isHorizontal(): Boolean = begin.y == end.y

    fun isVertical(): Boolean = begin.x == end.x

    override fun toString(): String = "Line(begin=$begin, end=$end, coordinates=$coordinates)"
}
