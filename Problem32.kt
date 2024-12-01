import kotlin.math.max

class Problem32 {
    /* https://leetcode.com/problems/longest-valid-parentheses/description/ */
    class Solution {

        // Runs 300x slower than the best solution on rank.
        fun longestValidParentheses(s: String): Int {
            var processed = s
            for(i in 0..s.length) {
                val replacement = Array(i) { '*' }.joinToString(separator = "")
                processed = processed.replace("($replacement)", "*$replacement")
            }
            return processed.split("(",")").map { it.length }.max() * 2
        }
    }
}