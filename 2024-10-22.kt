class `2024-10-22` {
    /* https://leetcode.com/problems/pacific-atlantic-water-flow/description/ 417 */
    class Solution {
        fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            for(i in heights.indices) {
                for(j in heights[i].indices) {
                    if(flow(heights, list, listOf(i to j))){
                        list.add(i to j)
                    }
                }
            }
            return list.map { listOf(it.first, it.second) }
        }

        fun flow(heights: Array<IntArray>, found: List<Pair<Int, Int>>, visiting: List<Pair<Int, Int>>, visited: MutableList<Pair<Int, Int>> = mutableListOf()): Boolean {
            if((visited.any {it.first == 0 || it.second == 0} && visited.any { it.second == heights[0].lastIndex || it.first == heights.lastIndex })
                    || visited.any { found.contains(it) })
                return true
            if(visiting.isEmpty()) return false
            val nextVisiting = mutableListOf<Pair<Int, Int>>()
            visiting.forEach {
                visited.add(it)
                nextVisiting.addAll(getNext(heights, it))
            }
            nextVisiting.removeAll { visited.contains(it) }
            return flow(heights, nextVisiting, visited)
        }

        fun getNext(board: Array<IntArray>, point: Pair<Int, Int>): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            if(point.first < board.lastIndex) {
                if(board[point.first + 1][point.second] <= board[point.first][point.second])
                    list.add(point.first + 1 to point.second)
            }
            if(point.second < board[0].lastIndex) {
                if(board[point.first][point.second + 1] <= board[point.first][point.second])
                    list.add(point.first to point.second + 1)
            }
            if(point.first > 0) {
                if(board[point.first - 1][point.second] <= board[point.first][point.second])
                    list.add(point.first - 1 to point.second)
            }
            if(point.second > 0) {
                if(board[point.first][point.second - 1] <= board[point.first][point.second])
                    list.add(point.first to point.second - 1)
            }
            return list
        }
    }
}