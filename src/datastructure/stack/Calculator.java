package datastructure.stack;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-16 23:26
 */
public class Calculator {
    public static void main(String[] args) {
        //表达式
        String expression = "3+2*6-2";

        //创建两个栈，数栈、符号栈
        StackCal numStack = new StackCal(10);
        StackCal operStack = new StackCal(10);

        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch

        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么然后做相应的处理
            if (operStack.isOper(ch)) {
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作
                    //如果当前操作符的优先级小于或者等于栈中的操作符，就需要进行一次运算
                    //反之直接压入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把结果入数栈

                    }
                } else {
                    //为空直接压入栈
                }
            }
        }
    }
}

//创建一个栈，直接使用ArrayStack
class StackCal extends ArrayStack {

    public StackCal(int maxSize) {
        super(maxSize);
    }

    //返回栈顶的值，不出栈
    public int peek() {
        return super.getStack()[super.getTop()];
    }

    //返回运算符的优先级，优先级用数字表示
    //数字越大，则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定表达式中只有 + ，- ，* ，/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return priority(val) != -1;
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

}