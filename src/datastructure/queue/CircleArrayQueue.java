package datastructure.queue;

import java.util.Scanner;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-10 20:14
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArray arrayQueue = new CircleArray(4);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("测试数组模拟环形队列：");
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取数据");
            System.out.println("h(head):查看队列头部数据");
            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.print("请输入一个数字：");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头部的数据是%d\n", res);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出...");
    }
}

class CircleArray {
    private int maxSize;//表示数组的最大容量
    //front变量的含义改为：front指向队列的第一个元素，初始值为0。
    private int front;//队列头部
    //rear变量的含义改为：rear指向队列的最后一个元素的后一个位置，初始值为0。
    private int rear;//队列尾部
    private int[] arr;//用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize + 1;//因为队列的实际长度为数组长度-1
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.err.println("队列已满，不能加入数据。");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，需要考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据（队首出队列）
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        //1.先把front对应的值保存到一个临时变量
        int tempValue = arr[front];
        //2.front往后移，需考虑取模
        front = (front + 1) % maxSize;
        //3.将临时变量返回
        return tempValue;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.err.println("队列为空。");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空，不能取数据");
        }
        return arr[front];
    }
}