package model

import kotlin.math.abs

data class GridPoint3D(val x: Int, val y: Int, val z: Int) {

    operator fun minus(other: GridPoint3D) = GridPoint3D(x - other.x, y - other.y, z - other.z)
    operator fun plus(other: GridPoint3D) = GridPoint3D(x + other.x, y + other.y, z + other.z)
    fun distance(other: GridPoint3D) = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    private fun get(i: Int) = when(i) {
        0 -> x
        1 -> y
        2 -> z
        else -> error("$i")
    }

    fun rotate(times: Int): GridPoint3D {
        val c0 = times % 3
        val c0s = 1 - ((times / 3) % 2) * 2
        val c1 = (c0 + 1 + (times / 6) % 2) % 3
        val c1s = 1 - (times / 12) * 2
        val c2 = 3 - c0 - c1
        val c2s = c0s * c1s * (if (c1 == (c0 + 1) % 3) 1 else -1)
        return GridPoint3D(get(c0) * c0s, get(c1) * c1s, get(c2) * c2s)
    }
}
