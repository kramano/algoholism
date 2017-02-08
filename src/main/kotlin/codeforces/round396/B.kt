package codeforces.round396

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

private fun solve(scanner: Scanner, out: PrintWriter) {
    val n = scanner.nextInt()
    scanner.nextLine()
    val segments = scanner.nextLine().split(" ").map(String::toLong)
    val triangle = segments.sorted().sliding(3).find {
                it[0] + it[1] > it[2] &&
                it[1] + it[2] > it[0] &&
                it[2] + it[0] > it[1]
    }
    if (triangle == null) {
        out.println("NO")
    } else {
        out.println("YES")
    }
}

fun <T> List<T>.sliding(windowSize: Int): List<List<T>> {
    return this.dropLast(windowSize - 1).mapIndexed { i, s -> this.subList(i, i + windowSize) }
}
