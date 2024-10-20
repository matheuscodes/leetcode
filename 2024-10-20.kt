class `2024-10-20` {
    /* https://leetcode.com/problems/number-of-islands/description/ */
    class Solution200 {
        fun numIslands(grid: Array<CharArray>): Int {
            var numIslands = 0

            for (r in 0 .. grid.lastIndex) {
                for (c in 0 .. grid[r].lastIndex) {
                    if (grid[r][c] == '1') {
                        mark(r, c, grid)
                        numIslands++
                    }
                }
            }

            return numIslands
        }

        fun mark(r: Int, c: Int, grid: Array<CharArray>) {
            if (r < 0 || r > grid.lastIndex ||
                c < 0 || c > grid[0].lastIndex ||
                grid[r][c] == '0') return

            grid[r][c] = '0'

            mark(r - 1, c, grid)
            mark(r + 1, c, grid)
            mark(r, c + 1, grid)
            mark(r, c - 1, grid)
        }
    }

    /* https://leetcode.com/problems/max-area-of-island/description/ */
    class Solution695 {
        fun maxAreaOfIsland(grid: Array<IntArray>): Int {
            var maxSize = 0

            for (r in 0 .. grid.lastIndex) {
                for (c in 0 .. grid[r].lastIndex) {
                    if (grid[r][c] == 1) {
                        val size = markCounting(r, c, grid)
                        if(size > maxSize) maxSize = size
                    }
                }
            }

            return maxSize
        }

        fun markCounting(r: Int, c: Int, grid: Array<IntArray>): Int {
            if (r < 0 || r > grid.lastIndex ||
                    c < 0 || c > grid[0].lastIndex ||
                    grid[r][c] == 0) return 0

            grid[r][c] = 0

            return markCounting(r - 1, c, grid) +
                    markCounting(r + 1, c, grid) +
                    markCounting(r, c + 1, grid) +
                    markCounting(r, c - 1, grid) +
                    1
        }
    }

    /* https://leetcode.com/problems/course-schedule/description/ */
    class Solution207 {
        fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
            val graph = mutableMapOf<Int, ArrayList<Int>>()
            for (pair in prerequisites) {
                graph.computeIfAbsent(pair[0]) { k: Int? -> ArrayList() }.add(pair[1])
            }
            val states = IntArray(numCourses)
            for (i in 0 until numCourses) {
                if (!dfs(i, graph, states)) {
                    return false
                }
            }
            return true
        }

        private fun dfs(node: Int, graph: Map<Int, ArrayList<Int>>, states: IntArray): Boolean {
            if (states[node] == 1) return false // Cycle detected
            if (states[node] == 2) return true // Already visited
            states[node] = 1 // Mark as visiting
            if (graph.containsKey(node)) {
                for (neighbor in graph[node]!!) {
                    if (!dfs(neighbor, graph, states)) {
                        return false
                    }
                }
            }
            states[node] = 2 // Mark as visited
            return true
        }
    }
}