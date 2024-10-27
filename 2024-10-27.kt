class `2024-10-27` {

    /* https://leetcode.com/problems/rotting-oranges/description/ */
    class Solution994 {
        fun orangesRotting(grid: Array<IntArray>): Int {
            var count = 0
            while(grid.any { it.any { a -> a == 1 }}) {
                val changes = mutableListOf<Pair<Int, Int>>()
                for(i in grid.indices) {
                    for (j in grid[i].indices) {
                        if(grid[i][j] == 2) {
                            val nexts = getNext(grid, i to j)
                            nexts.forEach {
                                if(grid[it.first][it.second] == 1) {
                                    changes.add(it)
                                }
                            }
                        }
                    }
                }

                if(changes.isEmpty()) {
                    return -1
                } else {
                    changes.forEach { grid[it.first][it.second] = 2 }
                }
                count += 1
            }
            return count
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