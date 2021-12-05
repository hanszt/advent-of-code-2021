package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LineTest {

    @Test
    fun testIntersections() {
        val line = Line(Vector(3, 7), Vector(7, 7))
        val line1 = Line(Vector(5, 7), Vector(9, 7))
        val line2 = Line(Vector(5, 7), Vector(9, 7))
        val lineDiag = Line(Vector(4, 7), Vector(7, 4))

        val allIntersections: MutableSet<Vector> = HashSet()
        allIntersections.addAll(line.intersections(line1))
        allIntersections.addAll(line.intersections(line2))
        allIntersections.addAll(line.intersections(lineDiag))
        assertEquals(4, allIntersections.size)
    }
}
