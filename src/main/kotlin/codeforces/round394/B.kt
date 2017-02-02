package codeforces.round394

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

private fun <T> List<T>.sliding(windowSize: Int): List<List<T>> {
    return this.dropLast(windowSize - 1).mapIndexed { i, s -> this.subList(i, i + windowSize) }
}

private fun solve(scanner: Scanner, out: PrintWriter) {
    val n = scanner.nextInt()
    val L = scanner.nextInt()
    scanner.nextLine()
    val kef = scanner.nextLine().split(" ").map(String::toInt)
    val sash = scanner.nextLine().split(" ").map(String::toInt)

    val m = kef.sliding(2).map { it[1] - it[0] }.toMutableList()
    val r = sash.sliding(2).map { it[1] - it[0] }.toMutableList()
    val kefDiffs = m + (L - m.sum())
    val sashDiffs = r + (L - r.sum())

    val s = kefDiffs.joinToString(transform = Int::toString, separator = "")
    val res = (s + s).contains(sashDiffs.joinToString(transform = Int::toString, separator = ""))

    if (res) {
        out.println("YES")
    } else {
        out.println("NO")
    }
}


