class `2024-09-23` {

    /* https://leetcode.com/problems/merge-intervals/description/ */
    class Solution56 {
        fun merge(intervals: Array<IntArray>): Array<IntArray> {
            val starts = IntArray(10001)
            val ends = IntArray(10001)
            var lastIndex = 0
            var firstIndex = 10001

            for (interval in intervals) {
                val start = interval[0]
                val end = interval[1]
                starts[start] += 1
                ends[end] -= 1
                lastIndex = maxOf(lastIndex, end)
                firstIndex = minOf(firstIndex, start)
            }

            val result = mutableListOf<IntArray>()
            var lastStartedIndex = -1
            var activeIntervals = 0
            for (index in firstIndex..lastIndex) {
                val started = starts[index]
                val ended = ends[index]
                if (started != 0 || ended != 0) {
                    if (started != 0) {
                        if (lastStartedIndex < 0) {
                            lastStartedIndex = index
                        }
                        activeIntervals += started
                    }
                    if (ended != 0) {
                        activeIntervals += ended
                    }
                    if (activeIntervals == 0) {
                        result.add(intArrayOf(lastStartedIndex, index))
                        lastStartedIndex = -1
                    }
                }
            }

            return result.toTypedArray()
        }
    }

    /* https://leetcode.com/problems/spiral-matrix/description/ */
    class Solution54 {

        enum class Direction(val value: IntArray) {
            LEFT(intArrayOf(0,-1)),
            RIGHT(intArrayOf(0,1)),
            UP(intArrayOf(1,0)),
            DOWN(intArrayOf(-1,0))
        }
        fun spiralOrder(matrix: Array<IntArray>): List<Int> {
            return spiralExtract(matrix)
        }

        fun spiralExtract(
                matrix: Array<IntArray>,
                extracted: MutableList<Int> = mutableListOf(),
                i: Int = 0, j: Int = 0,
                direction: Direction = Direction.RIGHT ): MutableList<Int> {
            extracted.add(matrix[i][j])
            matrix[i][j] = -666
            if(canGo(matrix, i + direction.value[0], j + direction.value[1])) {
                return spiralExtract(matrix, extracted, i + direction.value[0], j + direction.value[1], direction)
            } else {
                val nextDirection = listOf(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN)
                        .find { canGo(matrix, i + it.value[0], j + it.value[1] ) }
                if(nextDirection != null) {
                    return spiralExtract(matrix, extracted, i + nextDirection.value[0], j + nextDirection.value[1], nextDirection)
                }
            }
            return extracted
        }

        fun canGo(matrix: Array<IntArray>, i: Int, j: Int): Boolean {
            if(i in matrix.indices) {
                if(j in matrix[i].indices) {
                    return matrix[i][j] > -666
                }
            }
            return false
        }
    }

    /* https://leetcode.com/problems/rotate-image/description/ */
    class Solution48 {
        fun rotate(matrix: Array<IntArray>) {
            val n = matrix.size

            // Transpose
            for (i in 0 until n) {
                for (j in i + 1 until n) {
                    val temp = matrix[i][j]
                    matrix[i][j] = matrix[j][i]
                    matrix[j][i] = temp
                }
            }

            // Reflection
            for (i in 0 until n) {
                for (j in 0 until n / 2) {
                    val temp = matrix[i][j]
                    matrix[i][j] = matrix[i][n - j - 1]
                    matrix[i][n - j - 1] = temp
                }
            }
        }
    }
}