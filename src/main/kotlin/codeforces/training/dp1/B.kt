package codeforces.training.dp1.b

import java.io.*
import java.lang.Math.max
import java.util.*

fun main(args: Array<String>) {
//    val inputStream = FileInputStream("lepus.in")
//    val outputStream = FileOutputStream("lepus.out")
    val inputStream = System.`in`
    val outputStream = System.out
    val `in` = FastScanner(inputStream)
    val out = PrintWriter(outputStream)
    solve(`in`, out)
    out.close()
}

private fun solve(scanner: FastScanner, out: PrintWriter) {
    val n = scanner.nextInt()
    val path = scanner.nextLine().toCharArray()

    val dp = IntArray(n, init = { it -> -1 })
    dp[0] = 0
    for (i in 1..n - 1) {
        if (path[i] == 'w') continue

        when {
            i == 1 -> if (path[i] == '"') 1 else 0
            i < 5 -> dp[i] = max(dp[i - 1], dp[i - 3])
            else -> dp[i] = listOf(dp[i - 1], dp[i - 3], dp[i - 5]).max()!!
        }
        if (path[i] == '"') dp[i]++
    }
    out.println(dp[n - 1])
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