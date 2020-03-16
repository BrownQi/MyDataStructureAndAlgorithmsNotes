package datastructure.stack;

import java.util.Scanner;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-15 08:48
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("*************************");
            System.out.println("show");
            System.out.println("push");
            System.out.println("pop");
            System.out.println("exit");
            System.out.printf("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.printf("请输入要添加的值；");
                    int val = scanner.nextInt();
                    stack.push(val);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出。");
    }

}

//定义一个 ArrayStack 表示栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈。数据就放在该数组
    private int top = -1; //top表示栈顶，初始化为-1

    public int[] getStack() {
        return stack;
    }

    public int getTop() {
        return top;
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.err.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop,将栈顶数据返回
    public int pop() {
        //判断栈是否空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈存放的数据，遍历时，需要从栈顶开始显示数据
    public void show() {
        if (isEmpty()) {
            System.err.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
