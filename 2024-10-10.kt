class `2024-10-10` {

    /* https://leetcode.com/problems/longest-repeating-character-replacement/description/ */
    class Solution424 {
        fun characterReplacement(s: String, k: Int): Int {
            val counts = IntArray(26)
            var l = 0
            var longest = 0
            var maxCount = 0

            for (r in s.indices) {
                maxCount = Math.max(maxCount, ++counts[s[r] - 'A'])
                while (r - l + 1 - maxCount > k) {
                    counts[s[l] - 'A']--
                    l++
                }
                longest = maxOf(longest, r - l + 1)
            }

            return longest
        }
    }
}