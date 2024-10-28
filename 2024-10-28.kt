import java.util.*
import kotlin.math.abs


class `2024-10-28` {

    /* https://leetcode.com/problems/min-cost-to-connect-all-points/description/ */
    class Solution1584 {
        fun minCostConnectPoints(points: Array<IntArray>): Int {
            var totalCost = 0
            val visited = BooleanArray(points.size)
            val minHeap = PriorityQueue(Comparator.comparingInt { a: IntArray -> a[0] })
            minHeap.offer(intArrayOf(0, 0))


            while (!minHeap.isEmpty()) {
                val edge = minHeap.poll()
                val cost = edge[0]
                val point = edge[1]
                if (!visited[point]) {
                    visited[point] = true
                    totalCost += cost
                    for (i in points.indices) {
                        if (!visited[i]) {
                            val newCost = manhattan(points[point], points[i])
                            minHeap.offer(intArrayOf(newCost, i))
                        }
                    }
                }
            }

            return totalCost
        }

        fun manhattan(a: IntArray, b: IntArray): Int {
            return abs(a[0] - b[0]) + abs(a[1] - b[1])
        }
    }
}