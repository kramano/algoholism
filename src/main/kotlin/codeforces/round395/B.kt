package codeforces.round395

import java.io.PrintWriter
import java.util.*

//7
//4 3 7 6 9 1 2
//выходные данные
//2 3 9 6 7 1 4
//входные данные
//8
//6 1 4 2 5 6 9 2
//выходные данные
//2 1 6 2 5 4 9 6


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
    val cubes = scanner.nextLine().split(" ").map(String::toInt).toMutableList()

    for (i in 1..n / 2) {
        if (i % 2 == 1) {
            val temp = cubes[i - 1]
            cubes[i - 1] = cubes[n - i]
            cubes[n - i] = temp
        }
    }
    out.println(cubes.joinToString(separator = " "))



}

