package codeforces


import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val out = PrintWriter(System.out)
    solve(scanner, out)
    out.close()
    scanner.close()
}

private fun solve(scanner: Scanner, out: PrintWriter) {
    scanner.nextLine()
    val trainings = scanner.nextLine().split(" ").toList().map(String::toInt)
}

private fun Int.odd(): Boolean = this % 2 == 1
private fun Int.even(): Boolean = this % 2 == 0


fun <T> List<T>.sliding(windowSize: Int): List<List<T>> {
    return this.dropLast(windowSize - 1).mapIndexed { i, s -> this.subList(i, i + windowSize) }
}