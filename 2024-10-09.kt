class `2024-10-09` {
    /* https://leetcode.com/problems/longest-substring-without-repeating-characters/description/ */
    class Solution3 {
        fun lengthOfLongestSubstring(s: String): Int {
            if(s.isEmpty()) return 0
            val seen = mutableSetOf<Char>()
            var l = 0
            var r = 0
            var maxLength = Int.MIN_VALUE
            while(r <= s.lastIndex) {
                if(!seen.contains(s[r])) {
                    seen.add(s[r])
                    r += 1
                } else {
                    seen.remove(s[l])
                    l += 1
                }
                if(r - l > maxLength) maxLength = r - l
            }
            return maxLength
        }
    }
}