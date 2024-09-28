class `2024-09-28` {

    /* https://leetcode.com/problems/container-with-most-water/description/ */
    class Solution11 {
        fun maxArea(height: IntArray): Int {
            var l = 0
            var r = height.lastIndex
            var maxArea = 0

            while (l < r) {
                val a = (r - l) * Math.min(height[l], height[r])
                maxArea = Math.max(maxArea, a)
                if (height[l] < height[r]) l++ else r--
            }

            return maxArea
        }
    }

}