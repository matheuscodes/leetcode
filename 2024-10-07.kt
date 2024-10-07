class `2024-10-07` {



    /* https://leetcode.com/problems/first-bad-version/description/ */
    open class VersionControl {
        fun isBadVersion(version: Int): Boolean = false
        open fun firstBadVersion(n: Int) : Int = 0
    }
    class Solution278: VersionControl() {
        override fun firstBadVersion(n: Int) : Int {
            var scopeTop = n
            var scopeBottom = 1
            while ((scopeTop - scopeBottom) > 1) {
                val version = scopeBottom + (scopeTop - scopeBottom) / 2
                if(isBadVersion(version)) {
                    scopeTop = version
                } else {
                    scopeBottom = version
                }
            }
            return if(isBadVersion(scopeBottom)) scopeBottom else scopeTop
        }
    }

    /* https://leetcode.com/problems/valid-perfect-square/description/ */
    class Solution367 {
        fun isPerfectSquare(num: Int): Boolean {
            if(num == 1) return true
            val numLong = num.toLong()
            var scopeTop = numLong
            var scopeBottom = 1L
            while ((scopeTop - scopeBottom) > 1) {
                val trial = scopeBottom + (scopeTop - scopeBottom) / 2
                if(trial * trial == numLong) {
                    return true
                } else if(trial * trial < num){
                    scopeBottom = trial
                } else {
                    scopeTop = trial
                }
            }
            return false
        }
    }

    /* https://leetcode.com/problems/search-a-2d-matrix/description/ */
    class Solution74 {
        fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
            var scopeTop = matrix.lastIndex
            var scopeBottom = 0
            while ((scopeTop - scopeBottom) > 1) {
                val trial = scopeBottom + (scopeTop - scopeBottom) / 2
                if(matrix[trial][0] > target) {
                    scopeTop = trial
                } else {
                    scopeBottom = trial
                }
            }
            val i = if(matrix[scopeTop][0] > target) scopeBottom else scopeTop
            scopeTop = matrix[i].lastIndex
            scopeBottom = 0
            while ((scopeTop - scopeBottom) > 1) {
                val trial = scopeBottom + (scopeTop - scopeBottom) / 2
                if(matrix[i][trial] > target) {
                    scopeTop = trial
                } else {
                    scopeBottom = trial
                }
            }
            return matrix[i][scopeBottom] == target || matrix[i][scopeTop] == target
        }
    }


    /* https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/ */
    class Solution153 {
        fun findMin(nums: IntArray): Int {
            if(nums[0] < nums[nums.lastIndex]) return nums[0] // No rotation
            var scopeTop = nums.lastIndex
            var scopeBottom = 0
            while ((scopeTop - scopeBottom) > 1) {
                val trial = scopeBottom + (scopeTop - scopeBottom) / 2
                if(nums[trial] > nums[scopeBottom]) {
                    scopeBottom = trial
                } else {
                    scopeTop = trial
                }
            }
            return minOf(nums[scopeTop], nums[scopeBottom])
        }
    }

    /* https://leetcode.com/problems/search-in-rotated-sorted-array/description/ */
    class Solution33 {
        fun search(nums: IntArray, target: Int): Int {
            var scopeTop = nums.lastIndex
            var scopeBottom = 0
            while ((scopeTop - scopeBottom) > 1) {
                val trial = scopeBottom + (scopeTop - scopeBottom) / 2
                if(nums[trial] > nums[scopeBottom]) {
                    scopeBottom = trial
                } else {
                    scopeTop = trial
                }
            }

            if(target >= nums[scopeTop] && target <= nums[nums.lastIndex]) {
                return nums.search(scopeTop, nums.lastIndex, target)
            } else if(target <= nums[scopeBottom] && target >= nums[0]){
                return nums.search(0, scopeTop, target)
            } else {
                return if(nums[scopeBottom] == target) scopeBottom else if(nums[scopeTop] == target) scopeTop else -1
            }
        }

        fun IntArray.search(start: Int, end: Int, target: Int): Int {
            var scopeBottom = start
            var scopeTop = end
            while ((scopeTop - scopeBottom) > 1) {
                val trial = scopeBottom + (scopeTop - scopeBottom) / 2
                if(target == this[trial]) {
                    return trial
                }
                if(target > this[trial]) {
                    scopeBottom = trial
                } else {
                    scopeTop = trial
                }
            }
            return if(this[scopeBottom] == target) scopeBottom else if(this[scopeTop] == target) scopeTop else -1
        }
    }

    /* https://leetcode.com/problems/koko-eating-bananas/description/ */
    class Solution875 {
        fun minEatingSpeed(piles: IntArray, h: Int): Int {
            if(h == piles.size) {
                return piles.max()
            } else if(h > piles.size) {
                var right = piles.max()
                var left = kotlin.math.ceil(right / h.toDouble()).toInt()
                while ((right - left) > 1) {
                    val trial = left + ((right - left) / 2)
                    val hours = piles.calculateHours(trial)
                    if(hours > h) {
                        left = trial
                    } else {
                        right = trial
                    }
                }
                val hleft = piles.calculateHours(left)
                val hright = piles.calculateHours(right)
                if(hleft <= h && hright <= h) return minOf(left, right)
                if(hleft <= h) return left
                if(hright <= h) return right
                return -1
            } else {
                return -1
            }
        }

        fun IntArray.calculateHours(k: Int): Int {
            return this.fold(0) { acc, i ->
                acc + i / k + if (i % k == 0) 0 else 1
            }
        }
    }
}