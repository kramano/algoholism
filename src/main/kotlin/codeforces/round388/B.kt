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
    out.println(3)
    val points = (1..3).map {
        val point = scanner.nextInt() to scanner.nextInt()
        scanner.nextLine()
        point
    }
    listOf(points[1] + points[2] - points[0],
            points[0] + points[1] - points[2],
            points[2] + points[0] - points[1])
            .forEach{ out.println("${it.first} ${it.second}") }
}

private infix operator fun  Pair<Int, Int>.plus(that: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first + that.first) to (this.second + that.second)
}

private infix operator fun  Pair<Int, Int>.minus(that: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first - that.first) to (this.second - that.second)
}