class `2024-10-05` {

    /* https://leetcode.com/problems/trapping-rain-water/description/ */
    class Solution42 {
        fun trap(height: IntArray): Int {
            var pool = mutableListOf<Int>()
            var sum = 0
            var i = 0

            if(!hasPools(height)) return 0

            while(i <= height.lastIndex) {
                pool.add(height[i])
                if(pool.size > 1 && (pool.last() >= pool.first())) {
                    if(pool.first() > 0 && pool.last() > 0) {
                        val min = minOf(pool.first(), pool.last())
                        sum += pool.filter { it <= min }.fold(0) { acc, j -> acc + (min - j)}
                    }
                    pool = mutableListOf(pool.last())
                }
                i += 1
            }
            if(pool.isNotEmpty()) {
                val max = pool.max()
                val nexts = pool.filter { it != max }
                if(nexts.isNotEmpty()) {
                    pool[0] = nexts.max()
                    return sum + trap(pool.toIntArray())
                }
            }
            return sum
        }

        fun hasPools(height: IntArray): Boolean {
            val deltas = height.mapIndexed{ index, i ->
                if(index == 0)
                    0
                else
                    height[index] - height[index - 1]
            }
            val downs = deltas.count { it < 0 }
            val ups = deltas.count { it > 0 }
            return !(downs == 0 || ups == 0)
        }
    }
}