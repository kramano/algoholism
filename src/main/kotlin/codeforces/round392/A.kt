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
    val n = scanner.nextInt()
    scanner.nextLine()
    val belongings = scanner.nextLine().split(" ").map(String::toInt)
    val max = belongings.max()!!
    val result = belongings.sumBy { max - it }
    out.println(result)
}