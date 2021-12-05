package utils

data class Line(val begin: Vector, val end: Vector) {

    val coordinates: Set<Vector>

    init {
        coordinates = createCoordinates(begin, end)
    }

    private fun createCoordinates(begin: Vector, end: Vector): Set<Vector> {
        val coordinates: MutableSet<Vector> = LinkedHashSet()
        val rangeX = if (begin.x < end.x) begin.x..end.x else end.x..begin.x
        val rangeY = if (begin.y < end.y) begin.y..end.y else end.y..begin.y
        if (isHorizontal()) {
            for (x in rangeX) {
                coordinates.add(Vector(x, begin.y))
            }
        } else if (isVertical()) {
            for (y in rangeY) {
                coordinates.add(Vector(begin.x, y))
            }
        } else {
            coordinates.addAll(JavaUtils.diagonals(begin, end))
        }
        return coordinates
    }

    fun isHorizontal(): Boolean = begin.y == end.y

    fun isVertical(): Boolean = begin.x == end.x

    fun intersections(other: Line): Set<Vector> = coordinates.intersect(other.coordinates)

    override fun toString(): String = "Line(begin=$begin, end=$end, coordinates=$coordinates)"
}
