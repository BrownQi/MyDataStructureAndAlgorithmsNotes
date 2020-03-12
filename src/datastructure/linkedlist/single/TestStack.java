package datastructure.linkedlist.single;

import java.util.Stack;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-12 20:45
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("Tom");
        stack.add("Jerry");
        stack.add("Tuffy");
        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//pop就是将栈顶的数据取出
        }
    }
}
