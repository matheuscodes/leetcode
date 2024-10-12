import kotlin.math.abs

class `2024-10-12` {

    /* https://leetcode.com/problems/invert-binary-tree/description/ */
     class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
     }
    class Solution226 {
        fun invertTree(root: TreeNode?): TreeNode? {
            root?.let {
                root.left?.let { invertTree(it) }
                root.right?.let { invertTree(it) }
                val pivot = root.left
                root.left = root.right
                root.right = pivot
            }
            return root
        }
    }

    /* https://leetcode.com/problems/maximum-depth-of-binary-tree/description/ */
    class Solution104 {
        fun maxDepth(root: TreeNode?): Int {
            return dfs(root)
        }

        fun dfs(root: TreeNode?, depth: Int = 0): Int {
            root?.let {
                return maxOf(dfs(root.left, depth + 1), dfs(root.right, depth + 1))
            } ?: run {
                return depth - 1
            }
        }
    }


    /* https://leetcode.com/problems/balanced-binary-tree/description/ */
    class Solution110 {
        fun isBalanced(root: TreeNode?): Boolean {
            var isBalanced = true
            fun dfs(root: TreeNode?): Int {
                if (root == null) return 0

                val left = dfs(root.left)
                val right = dfs(root.right)

                if (abs(right - left) > 1) {
                    isBalanced = false
                }

                return 1 + maxOf(left, right)
            }
            dfs(root)
            return isBalanced
        }
    }

    /* https://leetcode.com/problems/diameter-of-binary-tree/description/ */
    class Solution543 {
        private var diameter = 0

        fun diameterOfBinaryTree(root: TreeNode): Int {
            dfs(root)
            return diameter
        }

        private fun dfs(node: TreeNode?): Int {
            if (node == null) {
                return 0
            }

            val left = dfs(node.left)
            val right = dfs(node.right)

            diameter = maxOf(diameter, left + right)

            return maxOf(left, right) + 1
        }
    }

    /* https://leetcode.com/problems/same-tree/description/ */
    class Solution100 {
        fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            if(p == null && q == null) return true
            if(p?.`val` != q?.`val`) return false
            return isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
        }
    }


    /* https://leetcode.com/problems/symmetric-tree/description/ */
    class Solution101 {
        fun isSymmetric(root: TreeNode?): Boolean {
            return isSameTreeInverted(root?.left, root?.right)
        }

        fun isSameTreeInverted(p: TreeNode?, q: TreeNode?): Boolean {
            if(p == null && q == null) return true
            if(p?.`val` != q?.`val`) return false
            return isSameTreeInverted(p?.left, q?.right) && isSameTreeInverted(p?.right, q?.left)
        }
    }

    /* https://leetcode.com/problems/path-sum/description/ */
    class Solution112 {
        fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
            return dfs(root, targetSum)
        }

        fun dfs(root: TreeNode?, targetSum: Int, currentSum: Int = 0): Boolean {
            if(root == null) return false
            if(root.left == null && root.right == null) {
                return targetSum == currentSum + root.`val`
            }
            else {
                return dfs(root.left, targetSum, currentSum + root.`val`) ||
                       dfs(root.right, targetSum, currentSum + root.`val`)
            }
        }
    }

    /* https://leetcode.com/problems/subtree-of-another-tree/description/ */
    class Solution572 {
        fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
            return dfs(root, subRoot)
        }

        fun dfs(root: TreeNode?, target: TreeNode?): Boolean {
            if(root == null) return false
            if(root.`val` == target?.`val`) {
                val same = isSameTree(root, target)
                if(same) return true
            }
            return dfs(root.left, target) || dfs(root.right, target)
        }

        fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            if(p == null && q == null) return true
            if(p?.`val` != q?.`val`) return false
            return isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
        }
    }

    /* https://leetcode.com/problems/binary-tree-level-order-traversal/description/ */
    class Solution102 {
        fun levelOrder(root: TreeNode?): List<List<Int>> {
            return dfs(root).values.toList()
        }

        fun dfs(root: TreeNode?, map: MutableMap<Int, MutableList<Int>> = mutableMapOf(), depth: Int = 0): MutableMap<Int, MutableList<Int>> {
            root?.let {
                val list = map.getOrPut(depth) { mutableListOf() }
                list.add(it.`val`)
                dfs(root.left, map, depth + 1)
                dfs(root.right, map, depth + 1)
            }
            return map
        }
    }

    /* https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/ */
    class Solution230 {
        fun kthSmallest(root: TreeNode?, k: Int): Int {
            return goDeep(root, k = k)[k - 1]
        }

        fun goDeep(root: TreeNode?, list: MutableList<Int> = mutableListOf(), k: Int): MutableList<Int> {
            if(root == null) return list
            val smalls = goDeep(root.left, list, k)
            smalls.add(root.`val`)
            if(list.size < k) {
                return goDeep(root.right, list, k)
            } else return list
        }
    }

    /* https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/ */
    class Solution530 {
        var minDiff = Int.MAX_VALUE

        fun getMinimumDifference(root: TreeNode?): Int {
            dfs(root, -100000, 200000)
            return minDiff
        }

        private fun dfs(node: TreeNode?, min: Int, max: Int) {
            node?.let {
                minDiff = minOf(minDiff, abs(it.`val` - min), abs(it.`val` - max))

                if (it.left != null) {
                    dfs(it.left, min, it.`val`)
                }
                if (it.right != null) {
                    dfs(it.right, it.`val`, max)
                }
            }
        }
    }

    /* https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/ */
    class Solution783 {
        fun minDiffInBST(root: TreeNode?): Int {
            val solution = Solution530()
            return solution.getMinimumDifference(root)
        }
    }
}