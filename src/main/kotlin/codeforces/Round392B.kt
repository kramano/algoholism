import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val out = PrintWriter(System.out)
    solve(scanner, out)
    out.close()
}

private fun solve(scanner: Scanner, out: PrintWriter) {
    val lights = scanner.nextLine().toCharArray().toList().map(Char::toString)
    val fours = lights.batch(4)
    val rIndex = findIndex(fours, "R")
    val bIndex = findIndex(fours, "B")
    val yIndex = findIndex(fours, "Y")
    val gIndex = findIndex(fours, "G")
    val pattern = mutableListOf("", "", "", "")
    pattern[rIndex] = "R"
    pattern[bIndex] = "B"
    pattern[yIndex] = "Y"
    pattern[gIndex] = "G"
    val result = mutableMapOf("R" to 0, "B" to 0, "Y" to 0, "G" to 0)
    fours.forEach {
        val diff = pattern.take(it.size) - it
        for (s in diff) {
            val count = result.getOrElse(s, { 0 })
            result[s] = count.inc()
        }
    }
    out.println("${result["R"]} ${result["B"]} ${result["Y"]} ${result["G"]}")
}

private fun findIndex(fours: Sequence<List<String>>, s: String): Int {
    return fours.find { it.contains(s) }?.indexOf(s) ?: -1
}

private class BatchingSequence<out T>(val source: Iterable<T>, val batchSize: Int) : Sequence<List<T>> {
    override fun iterator(): Iterator<List<T>> = object : AbstractIterator<List<T>>() {
        private val iterate = if (batchSize > 0) source.iterator() else emptyList<T>().iterator()
        override fun computeNext() {
            if (iterate.hasNext()) setNext(iterate.asSequence().take(batchSize).toList())
            else done()
        }
    }
}

/**
 * Batch a sequence into a sequence of lists of max N size
 */
fun <T> Iterable<T>.batch(n: Int): Sequence<List<T>> {
    return BatchingSequence(this, n)
}
