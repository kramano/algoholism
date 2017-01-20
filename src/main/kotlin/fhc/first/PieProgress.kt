package fhc.first

import java.io.File

class PieProgress {

    private val sampleInput = "/Users/mnovik/src/facebook hacker cup/resources/pie_progress_example_input.txt"
    private val sampleOutput = "/Users/mnovik/src/facebook hacker cup/resources/pie_progress_example_output.txt"
    private val input = "/Users/mnovik/src/facebook hacker cup/resources/pie_progress.txt"
    private val output = "/Users/mnovik/src/facebook hacker cup/resources/pie_progress_output.txt"

    data class TestCase(val numDays: Int, val numPies: Int, val prices: Map<Int, MutableList<Int>>)


    private fun readInput(input: File): List<TestCase> {
        val lines = input.readLines()
        val numTestCases = lines.first().toInt()
        var readSoFar = 1
        val testCases = mutableListOf<TestCase>()
        for (i in 1..numTestCases) {
            val numDaysAndNumPies = lines[readSoFar].split(" ")
            val numDays = numDaysAndNumPies[0].toInt()
            val numPies = numDaysAndNumPies[1].toInt()
            readSoFar++
            val prices = lines.subList(readSoFar, readSoFar + numDays)
                    .mapIndexed { i, s ->
                        i to s.split(" ")
                                .map(String::toInt)
                                .toMutableList()
                    }
                    .toMap()
            testCases.add(TestCase(numDays, numPies, prices))
            readSoFar += numDays
        }
        return testCases
    }

     fun solve(testCase: TestCase): Int {
        // calculate prices
        val pricesWithTax = testCase.prices.mapValues {
            it.value.sorted()
                    .mapIndexed { i, v -> v + 2 * i + 1 }
        }
        var result = 0
        var piesRemaining = mutableListOf<Int>()
        for ((i, p) in pricesWithTax) {
            piesRemaining = (piesRemaining + p).sorted().toMutableList()
            result += piesRemaining.removeAt(0)
        }
        return result
    }


    fun main(args: Array<String>) {
        val outputFile = File(output)
        if (outputFile.exists()) outputFile.delete()
        outputFile.createNewFile()
        val readInput = readInput(File(input))
        val results = readInput.map { solve(it) }.mapIndexed { i, answer -> "Case #${i + 1}: $answer" }
        outputFile.writeText(results.joinToString(separator = "\n"))
    }
}