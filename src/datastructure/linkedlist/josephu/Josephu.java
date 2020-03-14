package datastructure.linkedlist.josephu;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-14 08:10
 */
public class Josephu {

    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.showBoy();

        list.popBoy(1,2,5);
    }

}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //对nums进行数据校验
        if (nums < 1) {
            System.err.println("nums的值不正确");
            return;
        }

        Boy curBoy = null;
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建Boy节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环状
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.err.println("没有任何节点");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("Boy's No.%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    //根据用户的输入，计算出除圈的顺序
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
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}