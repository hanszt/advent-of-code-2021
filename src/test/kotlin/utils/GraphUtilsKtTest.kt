package utils

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class GraphUtilsKtTest {

    @Test
    fun `convert a associations list to a graph`() {
        val nodeMap = File("input/day12test.txt").readLines().toBiDiGraph("-").onEach(::println)
        val startNode = nodeMap["start"] ?: throw IllegalStateException()
        assertEquals(startNode.neighbors, setOf(nodeMap["A"], nodeMap["b"]))
    }
}
