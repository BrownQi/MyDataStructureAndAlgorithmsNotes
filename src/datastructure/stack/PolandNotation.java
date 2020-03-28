package datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //说明： 为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 - ";
        //思路
        //1. 先将suffixExpression装入到ArrayList中
        //2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈完成计算。

        List<String> listString = getListString(suffixExpression);
        System.out.println(listString);
        int res = calculate(listString);
        System.out.println(res);
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        return new ArrayList<String>(Arrays.asList(split));
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item :
                ls) {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //压入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        //最后留再stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

}
