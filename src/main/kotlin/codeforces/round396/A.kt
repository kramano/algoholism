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
    val a = scanner.nextLine()
    val b = scanner.nextLine()

    if (a == b) {
        out.println(-1)
    } else {
        out.println(Math.max(a.length, b.length))
    }
}


