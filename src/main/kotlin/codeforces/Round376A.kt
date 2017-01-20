import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val out = PrintWriter(System.out)
    solve(scanner, out)
    out.close()
}

private fun solve(scanner : Scanner, out: PrintWriter) {
    val name = scanner.next()
    var result = 0
    var prevLetter = 'a'
    for (c in name.toCharArray()) {
        val up = Math.abs(c.toInt() - prevLetter.toInt())
        result += Math.min(up, 26 - up)
        prevLetter = c
    }
    out.println(result)
}