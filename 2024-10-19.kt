class `2024-10-19` {
    /* https://leetcode.com/problems/find-if-path-exists-in-graph/description/ */
    class Solution {
        fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
            if (source == destination) return true

            val graph: MutableMap<Int, MutableList<Int>> = HashMap()
            for (edge in edges) {
                graph.computeIfAbsent(edge[0]) { k: Int? -> ArrayList() }.add(edge[1])
                graph.computeIfAbsent(edge[1]) { k: Int? -> ArrayList() }.add(edge[0])
            }

            val seen: MutableSet<Int> = HashSet()
            seen.add(source)

            return dfs(source, destination, graph, seen)
        }


        fun dfs(node: Int, destination: Int, graph: Map<Int, List<Int>>, seen: MutableSet<Int>): Boolean {
            if (node == destination) return true
            for (neighbor in graph[node] ?: emptyList()) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor)
                    if (dfs(neighbor, destination, graph, seen)) return true
                }
            }
            return false
        }
    }
}