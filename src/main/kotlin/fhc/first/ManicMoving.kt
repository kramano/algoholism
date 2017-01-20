package fhc.first

import java.io.File

class ManicMoving {

    private val sampleInput = "/Users/mnovik/src/facebook hacker cup/resources/manic_moving_example_input.txt"
    private val sampleOutput = "/Users/mnovik/src/facebook hacker cup/resources/manic_moving_example_output.txt"
    private val input = "/Users/mnovik/src/facebook hacker cup/resources/manic_moving.txt"
    private val output = "/Users/mnovik/src/facebook hacker cup/resources/manic_moving_output.txt"

    data class Road(val from: Int, val to: Int, val gas: Int)
    data class Family(val origin: Int, val destination: Int)

    data class TestCase(val numTowns: Int, val roads: List<Road>, val families: List<Family>)

    fun readInput(input: File): List<TestCase> {
        val lines = input.readLines()
        val numTestCases = lines.first().toInt()
        var readSoFar = 1
        val testCases = mutableListOf<TestCase>()
        for (i in 1..numTestCases) {
            val t = lines[readSoFar]
            val numTowns = t.split(" ")[0].toInt()
            val numRoads = t.split(" ")[1].toInt()
            val numFamilies = t.split(" ")[2].toInt()
            readSoFar++
            val roads = lines.subList(readSoFar, readSoFar + numRoads).map {
                val sRoad = it.split(" ")
                Road(sRoad[0].toInt(), sRoad[1].toInt(), sRoad[2].toInt())
            }
            readSoFar += numRoads
            val families = lines.subList(readSoFar, readSoFar + numFamilies).map {
                val sFamily = it.split(" ")
                Family(sFamily[0].toInt(), sFamily[1].toInt())
            }
            readSoFar += numFamilies
            testCases += TestCase(numTowns, roads, families)
        }
        return testCases
    }

    fun main(args: Array<String>) {
        val readInput = readInput(File(sampleInput))
        readInput.forEach(::println)
    }
}