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
    val numPictures = scanner.nextInt()
    scanner.nextLine()
    val beauties = scanner.nextLine().split(" ").map(String::toInt)
    val mostFrequentCount = beauties
            .groupBy { it }
            .mapValues { it.value.count() }
            .maxBy { it.value }!!.value

    out.println(numPictures - mostFrequentCount)
}


