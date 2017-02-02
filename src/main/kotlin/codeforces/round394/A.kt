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

private fun solve(scanner: Scanner, out: PrintWriter) {
    val even = scanner.nextInt()
    val odd = scanner.nextInt()

    if (Math.abs(even - odd) > 1 || (even == 0) && (odd == 0)) {
        out.println("NO")
    } else {
        out.println("YES")
    }
}
