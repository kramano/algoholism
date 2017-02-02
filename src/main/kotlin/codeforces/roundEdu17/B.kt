package codeforces.roundEdu17

import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val out = PrintWriter(System.out)
    solve(scanner, out)
    out.close()
}

private fun solve(scanner : Scanner, out: PrintWriter) {

    data class Mouse(val price: Int, val type: String)

    val usb = scanner.nextInt()
    val ps2 = scanner.nextInt()
    val both = scanner.nextInt()

    scanner.nextLine()

    val miceNum = scanner.nextInt()
    scanner.nextLine()
    val mice = mutableListOf<Mouse>()
    for (i in 1..miceNum) {
           mice += Mouse(scanner.nextInt(), scanner.nextLine().trim())
    }
    var usbCounter = 0
    var psCounter = 0
    var bothCounter = 0
    var total = 0L
    mice.sortedBy { it.price }.forEach {
        when {
            it.type == "USB" && usbCounter < usb -> {
                usbCounter++
                total += it.price
            }
            it.type == "PS/2" && psCounter < ps2 -> {
                psCounter++
                total += it.price
            }
            bothCounter < both -> {
                bothCounter++
                total += it.price
            }
        }
    }
    val compCount = usbCounter + psCounter + bothCounter
    out.println("$compCount $total")
}


