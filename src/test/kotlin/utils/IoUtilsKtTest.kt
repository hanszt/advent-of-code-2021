package utils

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class IoUtilsKtTest {

    @Test
    fun testReadLines() =
        assertEquals(listOf("hallo", "dit", "is", "een", "test"), File("input/iotest.txt").readLines().map { it.trim() })

    @Test
    fun testUseLines() {
        val actual = File("input/iotest.txt").useLines { it.map { s -> s.trim().length }.toList() }
        assertEquals(listOf(5, 3, 2, 3, 4), actual)
    }

    @Test
    fun testReadFileTextUsingGetResource() {
        val actual = this::class.java.getResource("/testFiles/test.txt")?.readText(Charsets.UTF_8) ?: ""
        assertEquals("hallo dit is een test", actual.trim())
    }

    @Test
    fun testReadFileLinesUsingGetResource() {
        val list = this::class.java.getResource("/testFiles/test.txt")
            ?.let { File(it.file).readLines() }
            ?: emptyList()

        assertEquals(listOf("hallo dit is een test"), list)
    }
}
