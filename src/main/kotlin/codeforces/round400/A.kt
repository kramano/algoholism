package codeforces.round400.a



import java.io.*
import java.util.*

fun main(args: Array<String>) {
    val inputStream = System.`in`
    val outputStream = System.out
    val `in` = FastScanner(inputStream)
    val out = PrintWriter(outputStream)
    solve(`in`, out)
    out.close()
}

private fun solve(scanner : FastScanner, out: PrintWriter) {
    val n = scanner.nextInt();
    val stuarts = scanner.nextLine().split(" ").map(String::toInt)

    if (n <= 2) {
        out.println(0)
    } else {
        val max = stuarts.max()
        val min = stuarts.min();
        val result = stuarts.filter { it != max && it != min }.size
        out.println(result)
    }
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
