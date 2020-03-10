package datastructure.queue;

import java.util.Scanner;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-10 17:37
 */
@Deprecated
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取数据");
            System.out.println("h(head):查看队列头部数据");
            key = scanner.next().charAt(0);

            switch (key){
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
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头部的数据是%d\n",res);
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

//使用数组模拟队列
@Deprecated
class ArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头部
    private int rear;//队列尾部
    private int[] arr;//用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，此时指向队列头的前一个位置。
        rear = -1;//指向队列尾部，指向队列的尾的那个数据。即(front,rear]
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据。");
            return;
        }
        rear++;//让rear后移
        arr[rear] = n;
    }

    //获取队列的数据（队首出队列）
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        front++;//front后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空。");
            return;
        }
        for (int i = front + 1; i < rear + 1; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空，不能取数据");
        }
        return arr[front + 1];
    }
}