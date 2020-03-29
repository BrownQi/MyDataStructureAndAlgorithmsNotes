package datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //demo v2 完成中缀表达式转后缀表达式的功能
        //1. 因为直接对str进行操作，不方便，先将中缀表达式转成相应的list
        //2. 将得到的中缀表达式对应的List => 后缀表达式对应的后缀表达式 List

        String expression = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression);
        System.out.println(strings);
        List<String> strings1 = parseSuffixExpreesionList(strings);
        System.out.println(parseSuffixExpreesionList(strings));

        System.out.println(calculate(strings1));

        /*
        //demo v1
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

         */

    }

    //demo v2

    public static List<String> parseSuffixExpreesionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        List<String> s2 = new ArrayList<>();//储存中间结果的Lists2

        //遍历ls
        for (String item :
                ls) {
            //如果是一个数，入s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                // 如果是右括号 ")" ，则依次弹出s1栈顶的运算符，并压入s2.直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将"("弹出s1栈
            }else{
                //当item的优先级小于或等于s1栈顶运算符的优先级，将s1栈顶的运算符弹出并压入到s2中，再次转到（4-1）与s1中新的栈顶运算符比较；
                while (s1.size()!=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放到List
    }

    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的数据
        List<String> ls = new ArrayList<>();
        int i = 0; //这相当于是一个指针，用于遍历中缀表达式String s
        String str;//对多位数的拼接
        char c;//每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，就需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//i需要后移
            } else {//如果是一个数，需要考虑多位数
                str = ""; //先将str置成空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //demo v1

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

//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}