package fhc.qualification

import java.io.File

class LazyLoading {

    val exampleInput = "/Users/mnovik/src/facebook hacker cup/resources/lazy_loading_example_input.txt"
    val exampleOutput = "/Users/mnovik/src/facebook hacker cup/resources/lazy_loading_example_output.txt"
    private val input = "/Users/mnovik/src/facebook hacker cup/resources/lazy_loading.txt"
    private val output = "/Users/mnovik/src/facebook hacker cup/resources/lazy_loading_out.txt"

    fun solve(input: File, outputFile: File) {
        // read input
        val lines = input.readLines()
        val numDays = lines.first().toInt()
        var readSoFar = 1
        val results = mutableListOf<String>()
        mutableListOf<Int>()
        for (i in 1..numDays) {
            val numItems = lines[readSoFar].toInt()
            readSoFar++
            val items = lines.subList(readSoFar, readSoFar + numItems)
                    .map(String::toInt)
            val result = solveTestCase(items)
            results.add("Case #$i: $result")
            readSoFar += numItems
        }
        if (outputFile.exists()) outputFile.delete()
        outputFile.createNewFile()
        outputFile.writeText(results.joinToString(separator = "\n"))
    }

    fun solveTestCase(items: List<Int>): Int {
        var sorted = items.sortedDescending().toMutableList()
        var numBags = 0
        while (sorted.isNotEmpty()) {
            val max = sorted.removeAt(0)
            val numItemsToRemoveFromTail = if (max >= 50) {
                0
            } else {
                (50 + max - 1) / max - 1
            }
            val numItemsToRetain = sorted.size - numItemsToRemoveFromTail
            if (numItemsToRetain >= 0) {
                sorted = sorted.take(numItemsToRetain).toMutableList()
                numBags++
            } else {
                break
            }
        }
        return numBags
    }

    fun main(args: Array<String>) {
        solve(File(input), File(output))
    }
}
