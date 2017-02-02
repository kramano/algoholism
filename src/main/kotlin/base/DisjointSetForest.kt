package base


class DisjointSetForest<T> {

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