
import java.util.*


class `2024-09-29` {

    /* https://leetcode.com/problems/baseball-game/description/ */
    class Solution682 {
        fun calPoints(operations: Array<String>): Int {
            val stack = Stack<Int>()

            for (op in operations) {
                if (op == "+") {
                    val top = stack.pop()
                    val newTop = top + stack.peek()
                    stack.push(top)
                    stack.push(newTop)
                } else if (op == "D") {
                    stack.push(stack.peek() * 2)
                } else if (op == "C") {
                    stack.pop()
                } else {
                    stack.push(op.toInt())
                }
            }

            var sum = 0
            while (!stack.isEmpty()) {
                sum += stack.pop()
            }

            return sum
        }
    }
}