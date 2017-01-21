package codeforces.round392

import org.psjava.algo.graph.shortestpath.BellmanFordAlgorithm
import org.psjava.ds.graph.MutableDirectedWeightedGraph
import org.psjava.ds.numbersystrem.IntegerNumberSystem

fun main(args: Array<String>) {
    val l = listOf("Y", "G", "B", "R")
    println(l.sorted() - listOf("B", "G", "!", "!"))
//    3 2 3
//    1 2 4
//    2 3 7
    val graph = MutableDirectedWeightedGraph.create<Int, Int>()
    graph.insertVertex(1)
    graph.insertVertex(2)
    graph.insertVertex(3)
    graph.addEdge(1, 2, 4)
    graph.addEdge(2, 3, 7)

    println(graph)

    val distances = BellmanFordAlgorithm.getInstance().calc(graph, 1, IntegerNumberSystem.getInstance())
    println(distances.getDistance(2))
    println(distances.getDistance(3))
    val path = distances.getPath(3)
    println(path)
}
