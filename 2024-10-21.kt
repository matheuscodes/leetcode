class `2024-10-21` {
    /* https://leetcode.com/problems/course-schedule-ii/description/ */
    class Solution210 {
        fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
            val forward = mutableMapOf<Int, MutableList<Int>>()
            for(i in 0 until numCourses) {
                forward[i] = mutableListOf()
            }
            for(p in prerequisites) {
                forward[p[1]]!!.add(p[0])
            }
            val seen = mutableSetOf<Int>()
            val viewing = mutableSetOf<Int>()
            val stack = mutableListOf<Int>()
            for (i in 0 until numCourses) {
                if (i !in seen) {
                    val res = dfs(stack, forward, i, seen, viewing)
                    if (!res) return IntArray(0)
                }
            }
            stack.reverse()
            return stack.toIntArray()

        }

        fun dfs(stack: MutableList<Int>, forward: Map<Int, List<Int>>, node:Int, seen: MutableSet<Int>, viewing: MutableSet<Int>): Boolean {
            val children = forward[node]!!
            viewing.add(node)
            for (c in children) {
                if (c in viewing) {
                    return false
                }
                if (c !in seen) {
                    val res = dfs(stack, forward, c, seen, viewing)
                    if (!res) return false
                }
            }
            stack.add(node)
            viewing.remove(node)
            seen.add(node)
            return true
        }
    }
}