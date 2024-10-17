import java.util.PriorityQueue


class `2024-10-17` {

    /* https://leetcode.com/problems/k-closest-points-to-origin/description/ */
    class Solution973 {
        fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
            val list = points
                    .mapIndexed { index, i -> (i[0]*i[0] + i[1]*i[1]) to i  }
                    .sortedBy { it.first }
            return list.subList(0,k).map { it.second }.toTypedArray()
        }
    }

    /* https://leetcode.com/problems/merge-k-sorted-lists/description/ */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class Solution23 {
        fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            if(lists.isEmpty()) return null

            val queue = PriorityQueue<ListNode>( compareByDescending { it.`val` } )
            lists.forEach {
                var item = it
                while(item != null) {
                    queue.add(item)
                    item = item.next
                }
            }

            var head: ListNode? = null
            while(queue.isNotEmpty()) {
                val item = queue.poll()
                item.next = head
                head = item
            }
            return head
        }
    }


    /* https://leetcode.com/problems/subsets/description/ */
    class Solution78 {
        fun subsets(nums: IntArray): List<List<Int>> {
            val asSet = nums.toSet()
            val asArray = asSet.toList()
            val sets = mutableSetOf<Set<Int>>(emptySet())
            asSet.forEach { from ->
                sets.toList().forEach { done ->
                    sets.add(done.plusElement(from))
                }
            }

            return sets.toList().map { it.toList() }
        }
    }

    /* https://leetcode.com/problems/permutations/description/ */
    class Solution46 {
        fun permute(nums: IntArray): List<List<Int>> {
            return recursiveBacktrack(nums, emptyList(), 0)
        }

        fun recursiveBacktrack(nums: IntArray, indices: List<Int>, depth: Int, list: MutableList<List<Int>> = mutableListOf()): List<List<Int>> {
            if(depth == nums.size) {
                if(indices.size == indices.toSet().size) {
                    list.add(indices.map { nums[it] })
                }
            } else {
                for(i in nums.indices) {
                    recursiveBacktrack(nums, indices.plus(i), depth + 1, list)
                }
            }
            return list
        }
    }

    /* https://leetcode.com/problems/combinations/description/ */
    class Solution77 {
        fun combine(n: Int, k: Int): List<List<Int>> {
            return backtrack(n, k)
        }

        fun backtrack(x: Int, k: Int, sol: MutableList<Int> = mutableListOf(), ans: MutableList<List<Int>> = mutableListOf()): List<List<Int>> {
            if (sol.size == k) {
                ans.add(sol.toList())
                return ans
            }
            if (x > k - sol.size) {
                backtrack(x - 1, k, sol, ans)
            }
            sol.add(x)
            backtrack(x - 1, k, sol, ans)
            sol.removeAt(sol.size - 1)
            return ans
        }
    }
}