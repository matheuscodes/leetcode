import java.util.PriorityQueue

class `2024-10-14` {

    /* https://leetcode.com/problems/validate-binary-search-tree/description/ */
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class Solution98 {
        fun isValidBST(root: TreeNode?): Boolean {
            return isValid(root, null, null)
        }

        fun isValid(node: TreeNode?, min: Int?, max: Int?): Boolean {
            return if (node == null) true
            else if ((max != null && node.`val` >= max) || (min != null && node.`val` <= min)) false
            else isValid(node.left, min, node.`val`) && isValid(node.right, node.`val`, max)
        }
    }

    /* https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/ */
    class Solution235 {
        fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
            return if(root == null) null
            else if(
                (root.`val` == p!!.`val` || root.`val` == q!!.`val`) ||
                root.`val` > minOf(p.`val`, q.`val`) && root.`val` < maxOf(p.`val`, q.`val`)
            ) root
            else if(root.`val` > q.`val` && root.`val` > p.`val`) lowestCommonAncestor(root.left, p, q)
            else lowestCommonAncestor(root.right, p, q)
        }
    }


    /* https://leetcode.com/problems/implement-trie-prefix-tree/description/ */
    class Trie() {
        private class TrieNode(
                val next: Array<TrieNode?> = arrayOfNulls(26),
                var final: Boolean = false,
        )

        private val root = TrieNode()

        fun insert(word: String) {
            var current = root
            for (i in word.indices) {
                val index = word[i] - 'a'
                current.next[index]?.let { current = it }
                        ?: run {
                            val newNode = TrieNode()
                            current.next[index] = newNode
                            current = newNode
                        }
            }
            current.final = true
        }

        fun search(word: String): Boolean {
            return find(word)?.final ?: false
        }

        fun startsWith(prefix: String): Boolean {
            return find(prefix)?.let { true } ?: false
        }

        private fun find(word: String): TrieNode? {
            var current = root
            for (i in word.indices) {
                val index = word[i] - 'a'
                current = current.next[index] ?: return null
            }
            return current
        }
    }

    /* https://leetcode.com/problems/last-stone-weight/description/ */
    class Solution1046 {
        fun lastStoneWeight(stones: IntArray): Int {

            val queue = PriorityQueue<Int>( compareByDescending { it } )

            stones.forEach { stone ->
                queue.add(stone)
            }

            while(queue.size > 1) {

                val firstMax = queue.poll()
                val secondMax = queue.poll()

                if(secondMax != firstMax) {
                    queue.add(firstMax - secondMax)
                }
            }
            return if (queue.isEmpty()) 0 else queue.poll()
        }
    }

    /* https://leetcode.com/problems/kth-largest-element-in-an-array/description/ */
    class Solution215 {
        fun findKthLargest(nums: IntArray, k: Int): Int {
            val minValue = nums.min()
            val maxValue = nums.max()
            val valueSize = maxValue - minValue
            val allValues = IntArray(valueSize + 1)
            nums.forEach{
                val curValue = it - minValue
                allValues[curValue] = (allValues[curValue])+1
            }
            var remaining = k
            for(value in valueSize downTo 0) {
                remaining -= allValues[value]
                if(remaining <= 0) return value + minValue
            }
            return -1
        }
    }
}