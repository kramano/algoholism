import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val inputStream = System.`in`
    val outputStream = System.out
    val `in` = Scanner(inputStream)
    val out = PrintWriter(outputStream)
    solve(`in`, out)
    out.close()
}

// This is a wrong solution
private fun solve(scanner: Scanner, out: PrintWriter) {
    val lights = scanner.nextLine()
    val fours = lights.toList().map(Char::toString).batch(4)
    val badLightsCounts = mutableMapOf("B" to 0, "G" to 0, "R" to 0, "Y" to 0).withDefault { 0 }
    val goodLights = mutableListOf<List<String>>()
    fours.dropLast(1).forEach {
        val sorted = it.sorted()
        val diff = listOf("B", "G", "R", "Y") - sorted
        diff.forEach {
            badLightsCounts[it] = badLightsCounts[it]!!.plus(1)
        }
        goodLights += listOf("B", "G", "R", "Y")
    }
    val full = when (fours.last().size) {
        1 -> goodLights.last() + lights.dropLast(1).takeLast(3)
        2 -> goodLights.last() + lights.dropLast(2).takeLast(2)
        3 -> goodLights.last() + lights.dropLast(3).takeLast(1)
        4 -> goodLights.last()
        else -> throw IllegalStateException()
    }
    val diff = listOf("B", "G", "R", "Y") - full.sorted()
    diff.forEach {
        badLightsCounts[it] = badLightsCounts[it]!!.plus(1)
    }
    out.println("" + badLightsCounts["R"] + " " + badLightsCounts["B"] + " " + badLightsCounts["Y"] + " " + badLightsCounts["G"])

}

fun <T> List<T>.sliding(windowSize: Int): List<List<T>> {
    return this.dropLast(windowSize - 1).mapIndexed { i, s -> this.subList(i, i + windowSize) }
}

fun <T> Iterable<T>.batch(chunkSize: Int) =
        mapIndexed { i, item -> i to item }
                .groupBy { it.first / chunkSize }
                .map { it.value.map { it.second } }