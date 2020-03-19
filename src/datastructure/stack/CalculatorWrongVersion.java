package datastructure.stack;

/**
 * @Description: 10以内加减乘除错误示例。（中缀）
 * 分割表达式应该用正则
 * 计算顺序有误，读取符号之后若其优先度小于或等于栈顶符号，进行数值操作后应依次比较，直至符号栈为空，再将其压入栈中
 * @Author: BrownQi
 * @date: 2020-03-16 23:26
 */
@Deprecated
public class CalculatorWrongVersion {
    public static void main(String[] args) {
        //表达式
//        String expression = "70+2*6";
        String expression = "3-2*6-2";//错误：因为计算顺序错误

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
        String keepNum ="";//用于拼接

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
                        res = numStack.cal(num1, num2, oper);
                        //把结果压入数栈
                        numStack.push(res);

                        if (operStack.isEmpty()){
                            //把当前的操作符压入符号栈
                            operStack.push(ch);
                        }else{
                            index--;
                        }

                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，直接压入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //为空直接压入栈
                    operStack.push(ch);
                }
            } else {
//                numStack.push(ch - 48);//ch是char型
//                分析思路
                //1.当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                //2.在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3.因此需要定义一个变量字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字,如果是数字,就唏嘘扫描,如果是迅速安抚,则入栈
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //让index + 1，并判断是否扫描到expression最后。
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出响应的数和符号，并运行。
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字（结果）
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("%s = %d",expression,res);
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