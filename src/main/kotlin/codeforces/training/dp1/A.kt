package codeforces.training.dp1.a

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Math.max
import java.util.*

fun main(args: Array<String>) {
    val inputStream = FileInputStream("ladder.in")
    val outputStream = FileOutputStream("ladder.out")
    val `in` = FastScanner(inputStream)
    val out = PrintWriter(outputStream)
    solve(`in`, out)
    out.close()
}

private fun solve(scanner: FastScanner, out: PrintWriter) {
    val n = scanner.nextInt()
    val numbers = scanner.nextLine().split(" ").map(String::toInt)

    var prev = 0
    var current = numbers[0]

    for (i in 1..n - 1) {
        val next = numbers[i] + max(prev, current)
        prev = current
        current = next
    }
    out.print(current)
}

private class FastScanner(`in`: InputStream) {
    private var reader: BufferedReader? = null
    private var tokenizer: StringTokenizer? = null

    init {
        reader = BufferedReader(InputStreamReader(`in`))
        tokenizer = null
    }

    operator fun next(): String {
        if (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
            try {
                tokenizer = StringTokenizer(reader!!.readLine())
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

        }
        return tokenizer!!.nextToken()
    }

    fun nextLine(): String {
        if (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
            try {
                return reader!!.readLine()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

        }

        return tokenizer!!.nextToken("\n")
    }

    fun nextLong(): Long {
        return java.lang.Long.parseLong(next())
    }

    fun nextInt(): Int {
        return Integer.parseInt(next())
    }
}


