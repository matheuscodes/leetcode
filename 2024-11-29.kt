import java.util.PriorityQueue

class `2024-11-29` {

    /* https://leetcode.com/problems/network-delay-time/description/ */
    class Solution743 {
        fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
            val map = mutableMapOf<Int, MutableMap<Int, Int>>()
            times.forEach { node ->
                map[node[0]]?.let {
                    it[node[1]] = node[2]
                } ?: run {
                    map[node[0]] = mutableMapOf(node[1] to node[2])
                }
            }

            val distances = mutableMapOf<Int, Int>()
            for(i in 1..n) {
                distances[i] = if(i != k) Int.MAX_VALUE else 0
            }

            val queue = PriorityQueue<Int>( compareBy { distances[it] } )
            for(i in 1..n) {
                queue.add(i)
            }
            while(queue.isNotEmpty()) {
                val current = queue.poll()
                map[current]?.keys?.forEach {
                    val currentDistance = distances[current]!! + (map[current]?.get(it) ?: Int.MAX_VALUE)
                    if(currentDistance < distances[it]!!) {
                        distances[it] = currentDistance
                        queue.add(it)
                    }
                }
            }


            val max = distances.entries.maxBy { it.value }.value
            return if(max == Int.MAX_VALUE) -1 else max
        }
    }


    /* https://leetcode.com/problems/fibonacci-number/description/ */
    class Solution509 {
        fun fib(n: Int): Int {
            if (n == 0) {
                return 0
            } else if (n == 1) {
                return 1
            }

            val dp = IntArray(n + 1)
            dp[0] = 0
            dp[1] = 1

            for (i in 2..n) {
                dp[i] = dp[i - 1] + dp[i - 2]
            }

            return dp[n]
        }
    }

    /* https://leetcode.com/problems/climbing-stairs/description/ */
    class Solution70 {
        fun climbStairs(n: Int): Int {
            if (n == 0) {
                return 0
            } else if (n == 1) {
                return 1
            } else if (n == 2) {
                return 2
            }

            val dp = IntArray(n + 1)
            dp[0] = 0
            dp[1] = 1
            dp[2] = 2

            for (i in 3..n) {
                dp[i] = dp[i - 1] + dp[i - 2]
            }

            return dp[n]
        }
    }

    /* https://leetcode.com/problems/min-cost-climbing-stairs/description/ */
    class Solution746 {
        fun minCostClimbingStairs(cost: IntArray): Int {
            var prev = 0
            var curr = 0

            for (i in 2..cost.size) {
                val next = minOf(cost[i - 2] + prev, cost[i - 1] + curr)
                prev = curr
                curr = next
            }

            return curr
        }
    }

    /* https://leetcode.com/problems/house-robber/description/ */
    class Solution198 {
        fun rob(nums: IntArray): Int {
            val n = nums.size
            if (n == 1) return nums[0]
            if (n == 2) return maxOf(nums[0], nums[1])

            var prev = nums[0]
            var curr = maxOf(nums[0], nums[1])

            for (i in 2 until n) {
                val temp = curr
                curr = maxOf(nums[i] + prev, curr)
                prev = temp
            }

            return curr
        }
    }

    /* https://leetcode.com/problems/unique-paths/description/ */
    class Solution62 {
        fun uniquePaths(m: Int, n: Int): Int {
            val downs = m - 1
            val rights = n - 1
            return countPaths(downs, rights)
        }

        fun countPaths(downs: Int, rights: Int, cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): Int {
            if(cache.containsKey(downs to rights)) {
                return cache[downs to rights]!!
            }
            var count = 0
            if(downs <= 0 && rights <= 0) {
                count += 1
            }
            if(downs > 0) {
                count += countPaths(downs - 1, rights)
            }
            if(rights > 0) {
                count += countPaths(downs, rights - 1)
            }
            cache.put(downs to rights, count)
            return count
        }
    }

    /* https://leetcode.com/problems/maximum-subarray/description/ */
    class Solution53 {
        fun maxSubArray(nums: IntArray): Int {
            var maxSum = Int.MIN_VALUE
            var currentSum = 0
            for(i in nums.indices) {
                currentSum = maxOf(nums[i], currentSum + nums[i])
                maxSum = maxOf(currentSum, maxSum)
            }
            return maxSum
        }
    }
}