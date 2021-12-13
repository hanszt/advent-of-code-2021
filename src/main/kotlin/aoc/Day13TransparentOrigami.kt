package aoc

import utils.*
import java.io.File
import kotlin.math.max

internal object Day13TransparentOrigami : ChallengeDay {

    fun part1(path: String): Int {
        val (coordinates, foldInstr) = File(path).readText().splitByBlankLine()
        return toGrid(coordinates)
            .foldGrid(foldInstr.lines().first())
            .flatMap(Array<Char>::toList)
            .count { it == '#' }
    }

    private fun Array<Array<Char>>.foldGrid(instr: String): Array<Array<Char>> {
        var foldedGrid = this
        val (dir, value) = instr.split(' ').last().split('=')
        foldedGrid = when (dir) {
            "x" -> foldedGrid.foldAlongX(value.toInt())
            "y" -> foldedGrid.foldAlongY(value.toInt())
            else -> throw IllegalStateException()
        }
        return foldedGrid
    }

    private fun Array<Array<Char>>.foldAlongY(value: Int) = let { grid ->
        val top = grid.sliceArray(0 until value)
        val mirroredBottom = grid.sliceArray(value + 1 until grid.size).mirrored()
        return@let top.mapByPoint { x, y -> if (top[y][x] == '.') mirroredBottom[y][x] else '#' }
    }

    private fun Array<Array<Char>>.foldAlongX(value: Int) = rotated().foldAlongY(value).rotated(true)

    private fun toGrid(coordinates: String): Array<Array<Char>> {
        val points = coordinates.trim().lines()
            .map { it.split(',').map(String::toInt) }
            .map { (x, y) -> x to y }
        val (maxX, maxY) = points.reduce { (x, y), (xo, yo) -> max(x, xo) to max(y, yo) }
        val grid = Array(size = maxY + 1) { Array(size = maxX + 1) { '.' } }
        points.forEach { (x, y) -> grid[y][x] = '#' }
        return grid
    }

    fun part2GridAsString(path: String): String {
        val (coordinates, foldInstr) = File(path).readText().splitByBlankLine()
        return toGrid(coordinates)
            .foldByInstructions(foldInstr.lines())
            .gridAsString(1)
    }

    fun part2(path: String) = part2GridAsString(path).toTextOrElseThrow()

    internal fun String.toTextOrElseThrow(): String {
        val expected = """
            .##..###..####.#....###..####.####.#....
            #..#.#..#....#.#....#..#.#.......#.#....
            #....#..#...#..#....#..#.###....#..#....
            #....###...#...#....###..#.....#...#....
            #..#.#....#....#....#....#....#....#....
            .##..#....####.####.#....#....####.####.
        """.trimIndent().trim()
        val text = "CPZLPFZL"
        if (this == expected) return text else {
            val cause = "The expected grid for $text is: \n\n$expected\n\n but found \n\n${this}\n\n"
            throw IllegalArgumentException("No matching text found", IllegalArgumentException(cause))
        }
    }


    private fun Array<Array<Char>>.foldByInstructions(instructions: List<String>): Array<Array<Char>> {
        var foldedGrid = this
        for (instr in instructions) {
            foldedGrid = foldedGrid.foldGrid(instr)
        }
        return foldedGrid
    }

    override fun part1() = part1("input/day13.txt")
    override fun part2() = part2("input/day13.txt")
}
