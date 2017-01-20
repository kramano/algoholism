package fhc.old

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.util.*

private val exampleInput = "/Users/Maksim_Novik/src/algoholism/src/main/resources/lazy_sort_example_input.txt"
private val exampleOutput ="/Users/Maksim_Novik/src/algoholism/src/main/resources/lazy_sort_example_output.txt"

fun main(args: Array<String>) {
    val inputStream: InputStream
    try {
        inputStream = FileInputStream(exampleInput)
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
    val outputStream: OutputStream
    try {
        outputStream = FileOutputStream(exampleOutput)
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
    val `in` = Scanner(inputStream)
    val out = PrintWriter(outputStream)
    val testCount = `in`.nextInt()
    `in`.nextLine()
    for (i in 1..testCount) solve(i, `in`, out)
    out.close()
}

fun solve(testCaseNumber: Int, scanner: Scanner, out: PrintWriter) {
    scanner.nextLine()
    val students = scanner.nextLine().split(" ").map(String::toInt)
    val firstPile = ArrayDeque(students)
    val secondPile = ArrayDeque<Int>()
    out.println("Case #$testCaseNumber: yes")
}