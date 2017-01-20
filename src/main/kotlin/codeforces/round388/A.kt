package codeforces.round388

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
    val num = scanner.nextInt()
    out.println(num / 2)
    if (num % 2 == 0) {
        out.println((1..num /2).map { 2 }.joinToString(separator = " "))
    } else {
        out.println(((1..num /2).map { 2 }.dropLast(1).toMutableList() + 3).joinToString(separator = " "))
    }
}


