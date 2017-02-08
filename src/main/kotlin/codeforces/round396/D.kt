package codeforces.round396

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val inputStream = System.`in`
    val outputStream = System.out
    val `in` = FastScanner(inputStream)
    val out = PrintWriter(outputStream)
    solve(`in`, out)
    out.close()
}

private data class Rel(val syn: Boolean, val first: String, val second: String)
private data class Word(val syn: Boolean, val value: String)

private fun solve(scanner: FastScanner, out: PrintWriter) {
    val n = scanner.nextInt()
    val m = scanner.nextInt()
    val q = scanner.nextInt()
    val dict = scanner.nextLine().split(" ")
    val relations = (1..m).map { scanner.nextLine() }.map {
        val split = it.split(" ")
        val syn = split[0] == "1"
        val first = split[1]
        val second = split[2]
        Rel(syn, first, second)
    }
    val queries = (1..q).map { scanner.nextLine() }.map { Pair(it.split(" ")[0], it.split(" ")[1]) }
    val relatedWords = DisjointSetForest<Word>()
    dict.forEach { relatedWords.makeSet(Word(true, it)) }
    dict.forEach { relatedWords.makeSet(Word(false, it)) }

    relations.forEach {
        if (it.syn) {
            if (areAntonyms(relatedWords, it.first, it.second)) {
                out.println("NO")
            } else {
                relatedWords.union(Word(true, it.first), Word(true, it.second))
                relatedWords.union(Word(false, it.first), Word(false, it.second))
                out.println("YES")
            }
        } else {
            if (areSynonyms(relatedWords, it.first, it.second)) {
                out.println("NO")
            } else {
                relatedWords.union(Word(false, it.first), Word(true, it.second))
                relatedWords.union(Word(true, it.first), Word(false, it.second))
                out.println("YES")
            }
        }
    }

    queries.forEach {
        when {
            areSynonyms(relatedWords, it.first, it.second) -> out.println(1)
            areAntonyms(relatedWords, it.first, it.second) -> out.println(2)
            else -> out.println(3)
        }
    }
}

private fun areAntonyms(relatedWords: DisjointSetForest<Word>, first: String, second: String) = (relatedWords.connected(Word(true, first), Word(false, second))
        || relatedWords.connected(Word(true, second), Word(false, first)))

private fun areSynonyms(relatedWords: DisjointSetForest<Word>, first: String, second: String) = (relatedWords.connected(Word(true, first), Word(true, second))
        || relatedWords.connected(Word(false, first), Word(false, second)))

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

class FastScanner(`in`: InputStream) {
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

    fun nextInt(): Long {
        return Integer.parseInt(next()).toLong()
    }
}
