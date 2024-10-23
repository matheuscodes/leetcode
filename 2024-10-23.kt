class `2024-10-23` {

    /* https://leetcode.com/problems/clone-graph/description/ */
    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList()
    }
    class Solution133 {
        fun cloneGraph(node: Node?): Node? {
            val nodes = mutableMapOf<Int, Node>()
            for(i in 1..100) {
                nodes.put(i, Node(i))
            }
            copyNeighbors(nodes, node)
            return nodes[node?.`val`]
        }

        fun copyNeighbors(map: Map<Int, Node>, node: Node?) {
            map[node?.`val`]?.let { current ->
                if(current.neighbors.isEmpty()) {
                    node?.neighbors?.forEach {
                        current.neighbors.add(map[it?.`val`])
                    }
                    node?.neighbors?.forEach {
                        copyNeighbors(map, it)
                    }
                }
            }
        }
    }
}