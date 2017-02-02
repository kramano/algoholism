package codeforces.roundEdu17

import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val out = PrintWriter(System.out)
    solve(scanner, out)
    out.close()
}

private fun solve(scanner : Scanner, out: PrintWriter) {
    val n = scanner.nextLong()
    val k = scanner.nextInt()
    val firstHalf = mutableListOf<Long>()
    val secondHalf = mutableListOf<Long>()
    val limit = Math.sqrt(n.toDouble()).toLong()
    (1..limit)
            .asSequence()
            .filter { n % it == 0L }
            .forEach {
                if (n / it == it) {
                    firstHalf += it
                } else {
                    firstHalf += it
                    secondHalf += n / it
                }
            }
    val divisors = firstHalf + secondHalf.reversed()
    if (divisors.size >= k) {
        out.println(divisors[k - 1])
    }
    else {
        out.println(-1)
    }

}


