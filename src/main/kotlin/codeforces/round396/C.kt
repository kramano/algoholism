package codeforces.round396

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

data class Rel(val syn: Boolean, val first: String, val second: String)

private fun solve(scanner: Scanner, out: PrintWriter) {
    val n = scanner.nextInt()
    val m = scanner.nextInt()
    val q = scanner.nextInt()
    scanner.nextLine()
    val dict = scanner.nextLine().split(" ")
    val relations = (1..m).map { scanner.nextLine() }.map {
        val split = it.split(" ")
        val syn = split[0] == "1"
        val first = split[1]
        val second = split[2]
        Rel(syn, first, second)
    }
    val queries = (1..q).map { scanner.nextLine() }.map { Pair(it.split(" ")[0], it.split(" ")[1]) }
    val syn = DisjointSetForest<String>()
    val ant = DisjointSetForest<String>()
    dict.forEach { syn.makeSet(it) }
    dict.forEach { ant.makeSet(it) }

    relations.forEach {
        if (it.syn) {
            if (ant.connected(it.first, it.second)) {
                out.println("NO")
            } else {
                syn.union(it.first, it.second)
























































































































































                out.println("YES")
            }
        } else {
            if (syn.connected(it.first, it.second)) {
                out.println("NO")
            } else {
                ant.union(it.first, it.second)
                out.println("YES")
            }
        }
    }

    queries.forEach {
        when {
            syn.connected(it.first, it.second) -> out.println(1)
            ant.connected(it.first, it.second) -> out.println(2)
            else -> out.println(3)
        }
    }

}

private class DisjointSetForest<T> {

    private data class Node<T>(var parent: T, var rank: Int = 0)

    private val nodeInfo = mutableMapOf<T, Node<T>>()

    fun makeSet(value: T) {
        nodeInfo[value] = Node(parent = value)
    }

    fun union(x: T, y: T) {
        val xrep = findRoot(x)
        val yrep = findRoot(y)
        if (xrep == yrep) return

        val xinfo = nodeInfo[xrep]!!
        val yinfo = nodeInfo[yrep]!!
        if (xinfo.rank > yinfo.rank) {
            yinfo.parent = xrep
        } else {
            xinfo.parent = yrep
            if (xinfo.rank == yinfo.rank)
                yinfo.rank++
        }
    }

    fun connected(x: T, y: T): Boolean = findRoot(x) == findRoot(y)

    fun findRoot(x: T): T {
        val info = nodeInfo[x]!!
        if (info.parent != x)
        // path compression
            info.parent = findRoot(info.parent)
        return info.parent
    }

    fun numSets(): Int {
        return nodeInfo.keys.size
    }

}
