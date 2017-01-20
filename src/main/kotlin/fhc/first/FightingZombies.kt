package fhc.first

import java.io.File

class FightingZombies {

    val sampleInput = "/Users/mnovik/src/facebook hacker cup/resources/fighting_the_zombies_example_input.txt"
    val sampleOutput = "/Users/mnovik/src/facebook hacker cup/resources/fighting_the_zombies_example_output.txt"
    val input = "/Users/mnovik/src/facebook hacker cup/resources/fighting_the_zombies.txt"
    val output = "/Users/mnovik/src/facebook hacker cup/resources/fighting_the_zombies_output.txt"

    data class TestCase(val squareSide: Long, val coordinates: List<Pair<Long, Long>>)

     fun readInput(input: File): List<TestCase> {
        val lines = input.readLines()
        val numTestCases = lines.first().toInt()
        var readSoFar = 1
        val testCases = mutableListOf<TestCase>()
        for (i in 1..numTestCases) {
            val t = lines[readSoFar]
            val numZombies = t.split(" ")[0].toInt()
            val squareSide = t.split(" ")[1].toLong()
            readSoFar++
            val coordinates = lines.subList(readSoFar, readSoFar + numZombies)
                    .map { it.split(" ")[0].toLong() to it.split(" ")[1].toLong() }
            testCases += TestCase(squareSide, coordinates)
            readSoFar += numZombies
        }
        return testCases
    }

     fun findMaxPointsInSquare(testCase: TestCase): MutableList<Pair<Long, Long>> {
        var result = 0
        var optimalSubset = mutableListOf<Pair<Long, Long>>()
        val sortedByX = testCase.coordinates.sortedBy { it.first }
        var right = 0
        for (left in 0..sortedByX.size - 1) {
            while (right < sortedByX.size && sortedByX[right].first <= sortedByX[left].first + testCase.squareSide) ++right
            var column = sortedByX.subList(left, right).sortedBy { it.second }

            var bottom = 0
            for (top in 0..column.size - 1) {
                while (bottom < column.size && column[bottom].second <= column[top].second + testCase.squareSide) ++bottom
                if (bottom - top > result) {
                    result = bottom - top
                    optimalSubset = column.subList(top, bottom).toMutableList()
                }
                if (bottom == column.size) break
            }
            if (right == sortedByX.size) break
        }
        return optimalSubset

    }

    fun solveTestCase(testCase: TestCase): Int {
        val first = findMaxPointsInSquare(testCase)
        val firstPointsRemoved = testCase.coordinates.toMutableList()
        firstPointsRemoved.removeAll(first)
        val second = findMaxPointsInSquare(testCase.copy(coordinates = firstPointsRemoved))
        return first.size + second.size

    }

}

fun main(args: Array<String>) {
    val fz = FightingZombies()
    val outputFile = File(fz.output)
    if (outputFile.exists()) outputFile.delete()
    outputFile.createNewFile()
    val readInput = fz.readInput(File(fz.input))
    val results = readInput.map { fz.solveTestCase(it) }
            .mapIndexed { i, answer -> "Case #${i + 1}: $answer" }
    outputFile.writeText(results.joinToString(separator = "\n"))

}
