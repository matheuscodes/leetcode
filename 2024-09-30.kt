import java.util.Stack
import kotlin.collections.HashMap

class `2024-09-30` {

    /* https://leetcode.com/problems/valid-parentheses/description/ */
    class Solution20 {
        fun isValid(s: String): Boolean {
            val hashmap: MutableMap<Char, Char> = HashMap()
            hashmap[')'] = '('
            hashmap['}'] = '{'
            hashmap[']'] = '['

            val stk = Stack<Char>()

            for (c in s.toCharArray()) {
                if (!hashmap.containsKey(c)) {
                    stk.push(c)
                } else {
                    if (stk.isEmpty() || stk.pop() != hashmap[c]) {
                        return false
                    }
                }
            }

            return stk.isEmpty()
        }
    }
}