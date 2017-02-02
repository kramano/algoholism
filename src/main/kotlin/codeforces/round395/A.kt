package codeforces.round395

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
    val n = scanner.nextBigInteger()
    val m = scanner.nextBigInteger()
    val z = scanner.nextInt()

    val gcd = n.gcd(m)
    val x = n * m / gcd

    out.println(z / x.toInt())
}


