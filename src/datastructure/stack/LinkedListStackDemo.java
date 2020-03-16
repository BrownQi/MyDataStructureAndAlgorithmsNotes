package datastructure.stack;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-16 08:48
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();
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

class LinkedListStack {
    Node head = new Node();

    //判断栈空
    public boolean isEmpty() {
        return head.getNext() == null;
    }

    //入栈
    public void push(int value) {
        Node node = new Node(value);
        node.setNext(head.getNext());
        head.setNext(node);
    }

    //出栈
    public int pop() {
        if (head.getNext() == null) {
            throw new RuntimeException("栈空");
        }
        int value = head.getNext().getValue();
        head.setNext(head.getNext().getNext());
        return value;
    }

    //显示栈内数据
    public void show() {
        Node temp = head.getNext();
        if (temp == null) System.err.println("栈为空");
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }
}

class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}