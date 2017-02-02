package uva

import org.funktionale.utils.constant
import org.funktionale.utils.identity
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.util.*

private val exampleInput = "./src/main/resources/network_connections.txt"
private val exampleOutput = "./src/main/resources/network_connections_out.txt"

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
    `in`.nextLine()
    for (i in 1..testCount)
        solve(i, `in`, out)
    out.close()
}

private fun solve(testCaseNumber: Int, scanner: Scanner, out: PrintWriter) {
    val compNumber = scanner.nextInt()
    scanner.nextLine()
    val unionFind = UnionFind(compNumber)
    var success = 0
    var failure = 0
    val reader = BufferedReader(FileReader(exampleInput))
    reader.readLine()
    while (scanner.hasNextLine()) {
        val s = scanner.nextLine()
        if (s.isEmpty()) {
            break
        }
        val split = s.split(" ")
        val e = Entry(split[0], split[1].toInt() - 1, split[2].toInt() - 1)
        if (e.type == "c") {
            unionFind.union(e.first, e.second)
        } else {
            if (unionFind.isSameSet(e.first, e.second)) {
                success++
            } else {
                failure++
            }
        }
    }
    out.println("$success,$failure")
    out.println()
}

private data class Entry(val type: String, val first: Int, val second: Int)

private class UnionFind(N: Int) {
    private var rank = IntArray(N, constant(0))
    private var p = IntArray(N, identity())
    private var setSize = IntArray(N, constant(1))
    private var numSets: Int = 0

    fun findSet(i: Int): Int {
        if (p[i] == i) {
            return i
        } else {
            val res = findSet(p[i])
            p[i] = res
            return res
        }
    }

    fun isSameSet(i: Int, j: Int): Boolean = findSet(i) == findSet(j)

    fun union(i: Int, j: Int) {
        if (!isSameSet(i, j)) {
            numSets--
            val x = findSet(i)
            val y = findSet(j)
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x
                setSize[x] = setSize[x] + setSize[y]
            } else {
                p[x] = y
                setSize[y] = setSize[y] + setSize[x]
                if (rank[x] == rank[y]) rank[y] = rank[y] + 1
            }
        }
    }
}



