import kotlin.math.max

class Problem32 {
    /* https://leetcode.com/problems/longest-valid-parentheses/description/ */
    class Solution {

        fun longestValidParentheses(s: String): Int {
            var left = 0
            var right = 0
            var max = 0
            for (i in s.indices) {
                when (s[i]) {
                    '(' -> left++
                    ')' -> right++
                }
                if (left == right) {
                    max = max(max, left * 2)
                } else if (right > left) {
                    left = 0
                    right = 0
                }
            }

            left = 0
            right = 0
            for (i in s.indices.reversed()) {
                when (s[i]) {
                    '(' -> left++
                    ')' -> right++
                }
                if (left == right) {
                    max = max(max, left * 2)
                } else if (left > right) {
                    left = 0
                    right = 0
                }
            }
            return max
        }
    }
}