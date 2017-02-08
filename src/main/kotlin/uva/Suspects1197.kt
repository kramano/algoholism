package uva

import base.DisjointSetForest
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.util.*

private val exampleInput = "./src/main/resources/suspects.txt"
private val exampleOutput = "./src/main/resources/suspects_out.txt"

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
    var counter = 0
    while (`in`.hasNextLine()) {
        solve(counter++, `in`, out)
    }
    out.close()
}

private fun solve(testCaseNumber: Int, scanner: Scanner, out: PrintWriter) {
    val numStudents = scanner.nextInt()
    val numGroups = scanner.nextInt()
    if (numStudents == 0 && numGroups == 0) {
        return
    }
    val dsu = DisjointSetForest<Int>()
    scanner.nextLine()
    val allStudents = mutableListOf<Int>()
    for (i in 1..numGroups) {
        val numStudentsInGroup = scanner.nextInt()
        val group = scanner.nextLine().trim().split(" ").map(String::toInt)
        allStudents.addAll(group)
        group.forEach { dsu.makeSet(it) }
        group.forEach { dsu.union(group.first(), it) }
    }
    val suspectsCount = allStudents.count { dsu.connected(0, it)}
    out.println(suspectsCount)
}

