package codeforces

import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val out = PrintWriter(System.out)
    solve(scanner, out)
    out.close()
}

private fun solve(scanner: Scanner, out: PrintWriter) {
    val height = scanner.nextInt()
    val width = scanner.nextInt()
    val questions = scanner.nextLong()
    val x = scanner.nextInt()
    val y = scanner.nextInt()

    val allAskedTimes: Long
    val remaining: Long
    val down: Boolean
    if (questions >= width * height) {
        allAskedTimes = 1 + (questions - (height * width)) / ((height - 1) * width)
        remaining = (questions - (height * width)) % ((height - 1) * width)
        down = allAskedTimes % 2 == 0L
    } else {
        allAskedTimes = 0
        remaining = questions
        down = true
    }

    val counts = LongArray(width * height, { allAskedTimes })
    if (down) {
        counts.fill(allAskedTimes + 1, width, width + remaining.toInt())
    } else {
        counts.reverse()
        counts.fill(allAskedTimes + 1, width, width + remaining.toInt())
        counts.reverse()
    }
    val max = counts.max()
    val min = counts.min()
    val serge = counts[(x - 1) * width + y - 1]

    println("$max $min $serge")

}

