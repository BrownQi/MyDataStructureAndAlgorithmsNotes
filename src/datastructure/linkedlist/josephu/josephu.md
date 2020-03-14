# 约瑟夫问题
## 介绍
Josephu问题为：设编号为1，2，...n的n个人围坐在一圈,约定编号为k(1<=k<=n)的人从1开始报数,数到m的那个人出列,他的下一位又从1开始报数,数到m的那个人又出列,以此类推,知道所有人出列为止。
## 用单向环形链表实现思路
1. 先创建第一个节点，让first指向该节点，并自身形成环形
2. 后面我们每创建一个新的节点，就把该节点，加入到已有的环形链表中即可

## 遍历环形链表
1. 先让一个辅助指针（temp），指向first节点
2. 然后通过一个while循环遍历该环形链表即可
3. 当temp.next == firs，遍历结束

## 出列
1. 需求创建一个辅助指针（helper），事先应指向开始Boy的前一个节点
2. helper向后移动m-1个次
3. 让helper.next出圈（输出）
4. 判断helper.getNext() == helper
    - 如果条件成立跳出循环（此时只剩helper一个节点）
    - 如果条件不成立：helper.setNext(helper.getNext().getNext());
## 实现
1.出列
```java
/**
     * @param startNo  表示从第几个小孩开始
     * @param countNum 表示步长
     * @param nums     表示最初有多少小孩在圈
     */
    public void popBoy(int startNo, int countNum, int nums) {
        //数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.err.println("error");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        while (helper.getNext().getNo() != startNo) {
            helper = helper.getNext();
        }

        while (helper.getNext() != helper){
            for (int i = 1; i < countNum; i++) {
                helper = helper.getNext();
            }
            System.out.printf("Boy No%d 出\n",helper.getNext().getNo());
            helper.setNext(helper.getNext().getNext());
        }
        System.out.printf("最后留在圈中的是 Boy No%d\n",helper.getNo());
    }
```

