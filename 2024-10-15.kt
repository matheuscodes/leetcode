class `2024-10-15` {

    /* https://leetcode.com/problems/top-k-frequent-elements/description/ */
    class Solution347 {
        fun topKFrequent(nums: IntArray, k: Int): IntArray {
            val minValue = nums.min()
            val maxValue = nums.max()
            val valueSize = maxValue - minValue
            val allValues = IntArray(valueSize + 1)
            nums.forEach{
                val curValue = it - minValue
                allValues[curValue] = (allValues[curValue])+1
            }
            val list = allValues.mapIndexed { index, i ->  i to index }.sortedByDescending { it.first }
            return list.subList(0, k).map { minValue + it.second }.toIntArray()
        }
    }
}