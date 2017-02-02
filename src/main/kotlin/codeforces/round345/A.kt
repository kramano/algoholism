package codeforces.round345

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
    var first = scanner.nextInt()
    var second = scanner.nextInt()

    var totalTime = 0

    while (first > 0 && second > 0) {
        if (first > second) {
            first -= 2
            second++
            if (first < 0) break
        } else {
            second -= 2
            first++
            if (second < 0) break
        }
        totalTime++
    }
    out.println(totalTime)
}


