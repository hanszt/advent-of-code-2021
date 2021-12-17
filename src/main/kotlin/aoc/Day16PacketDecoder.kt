package aoc

import utils.toListOf
import java.io.File
import java.math.BigInteger

internal object Day16PacketDecoder : ChallengeDay {

    private const val HEX_NR_RADIX = 16

    fun part1(path: String) = File(path).readText().hexToBinaryPacket().toVersionNrSum()
    fun part2(path: String) = File(path).readText().hexToBinaryPacket().parseBinary()

    fun String.hexToBinaryPacket() = trim().map { BigInteger(it.toString(), HEX_NR_RADIX).toString(2) }
        .joinToString("") { it.padStart(4, '0') }.let(::Packet)

    override fun part1() = part1("input/day16.txt")
    override fun part2() = part2("input/day16.txt")

    class Packet(val binary: String) {

        private var cursor: Int = 0
        private var versionNrSum: Long = 0

        fun toVersionNrSum() = parseBinary().let { versionNrSum }

        fun parseBinary(): Long = kotlin.run {
            val versionNr = bitsAsDecimal(length = 3)
            val typeId = bitsAsDecimal(length = 3)
            versionNrSum += versionNr
            when (typeId) {
                0 -> parseOperation().sum()
                1 -> parseOperation().reduce(Long::times)
                2 -> parseOperation().minOf { it }
                3 -> parseOperation().maxOf { it }
                4 -> parseLiteral()
                5 -> parseOperation().let { (subPacket, other) -> if (subPacket > other) 1 else 0 }
                6 -> parseOperation().let { (subPacket, other) -> if (subPacket < other) 1 else 0 }
                7 -> parseOperation().let { (subPacket, other) -> if (subPacket == other) 1 else 0 }
                else -> 0
            }
        }

        private fun parseOperation(): List<Long> =
            if (currentBit() == "0") {
                val binaryLength = bitsAsDecimal(length = 15)
                val start = cursor
                generateSequence { }.takeWhile { (cursor < start + binaryLength) }.toListOf { parseBinary() }
            } else (0 until bitsAsDecimal(length = 11)).map { parseBinary() }

        private fun parseLiteral(): Long {
            var firstBit = currentBit()
            return (generateSequence { }.takeWhile { firstBit == "1" }
                .joinToString(separator = "") { readBits(length = 4).also { firstBit = currentBit() } }
                .plus(readBits(length = 4)))
                .toLong(radix = 2)
        }

        private fun bitsAsDecimal(length: Int) = readBits(length).toInt(radix = 2)
        private fun currentBit() = readBits(length = 1)
        private fun readBits(length: Int) = binary.substring(cursor, cursor + length).also { cursor += length }
    }
}
