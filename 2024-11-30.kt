class `2024-11-30` {

    /* https://leetcode.com/problems/jump-game/description/ */
    class Solution55 {
        fun canJump(nums: IntArray): Boolean {
            var target = nums.lastIndex
            for (i in (nums.lastIndex - 1) downTo 0) {
                if (nums[i] + i >= target ) {
                    target = i
                }
            }
            return target == 0
        }
    }

    /* https://leetcode.com/problems/coin-change/description/ */
    class Solution322 {
        fun coinChange(coins: IntArray, amount: Int): Int {
            val dp = IntArray(amount + 1) { amount + 1 }
            dp[0] = 0

            for (coin in coins) {
                for (total in coin..amount) {
                    dp[total] = minOf(dp[total], dp[total - coin] + 1)
                }
            }

            return if (dp[amount] != amount + 1) dp[amount] else -1
        }
    }

    /* https://leetcode.com/problems/longest-increasing-subsequence/description/ */
    class Solution300 {
        fun lengthOfLIS(nums: IntArray): Int {
            val n: Int = nums.size
            val dp = IntArray(n) { 1 }

            for (i in 1 until nums.size) {
                for (j in 0 until i) {
                    if (nums[i] > nums[j]) {
                        dp[i] = maxOf(dp[i], dp[j] + 1)
                    }
                }
            }

            var maxLength = 0
            for (length in dp) {
                maxLength = maxOf(maxLength, length)
            }

            return maxLength
        }
    }

    /* https://leetcode.com/problems/longest-common-subsequence/description/ */
    class Solution1143 {
        fun longestCommonSubsequence(text1: String, text2: String): Int {
            val dp = Array(text1.length + 1) { IntArray(text2.length + 1) }

            for (i in text1.indices.reversed()) {
                for (j in text2.indices.reversed()) {
                    dp[i][j] = if (text1[i] == text2[j]) {
                        1 + dp[i + 1][j + 1]
                    } else {
                        maxOf(dp[i + 1][j], dp[i][j + 1])
                    }
                }
            }
            return dp[0][0]
        }
    }

    /* https://leetcode.com/problems/palindrome-number/description/ */
    class Solution9 {
        fun isPalindrome(x: Int): Boolean {
            if(x < 0) return false
            val asString = x.toString()
            for (i in 0..asString.lastIndex / 2) {
                if(asString[i] != asString[asString.lastIndex - i]) return false
            }
            return true
        }
    }
}