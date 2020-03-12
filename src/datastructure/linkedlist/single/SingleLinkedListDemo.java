package datastructure.linkedlist.single;

import java.util.Stack;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-11 22:15
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        PokemonNode p1 = new PokemonNode(1, "妙蛙种子");
        PokemonNode p2 = new PokemonNode(2, "妙蛙草1");
        PokemonNode p3 = new PokemonNode(3, "妙蛙花");
        PokemonNode p4 = new PokemonNode(4, "小火龙");
        PokemonNode p5 = new PokemonNode(5, "火恐龙");
        PokemonNode p6 = new PokemonNode(6, "喷火龙");
        PokemonNode p7 = new PokemonNode(7, "杰尼龟");
        PokemonNode p8 = new PokemonNode(8, "卡咪龟");
        PokemonNode p9 = new PokemonNode(9, "水箭龟");
        PokemonNode p10 = new PokemonNode(2, "妙蛙草");
        PokemonNode p11 = new PokemonNode(18, "妙蛙草");

//        list.addIgnoreNo(p1);
//        list.addIgnoreNo(p2);
//        list.addIgnoreNo(p3);
//        list.addIgnoreNo(p4);
//        list.addIgnoreNo(p5);
//        list.addIgnoreNo(p6);
//        list.addIgnoreNo(p7);
//        list.addIgnoreNo(p8);
//        list.addIgnoreNo(p9);


        list.addOrderByNo(p1);
        list.addOrderByNo(p9);
        list.addOrderByNo(p4);
        list.addOrderByNo(p2);
        list.addOrderByNo(p3);
        list.addOrderByNo(p3);
        list.addOrderByNo(p2);
        list.addOrderByNo(p5);
        list.addOrderByNo(p7);
        list.addOrderByNo(p7);
        list.addOrderByNo(p8);
        list.addOrderByNo(p6);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.showList();

        list.update(p10);
        list.update(p11);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("**************************");

//        list.del(1);
//        list.del(2);
//        list.del(3);
//        list.del(4);
//        list.del(5);
//        list.del(6);
//        list.del(7);
//        list.del(8);
//        list.del(9);
//        list.del(99);

        SingleLinkedList.reverseList(list.getHead());

        list.showList();

        System.out.println(SingleLinkedList.getLength(list.getHead()));

        System.out.println(SingleLinkedList.findLastIndex(list.getHead(), 18));

        SingleLinkedList.showRerverseList(list.getHead());

        System.out.println("merge*********************************");
        PokemonNode n1 = new PokemonNode(1, "妙蛙种子");
        PokemonNode n3 = new PokemonNode(3, "妙蛙花");
        PokemonNode n4 = new PokemonNode(4, "小火龙");
        PokemonNode n5 = new PokemonNode(5, "火恐龙");
        PokemonNode n7 = new PokemonNode(7, "杰尼龟");
        PokemonNode n9 = new PokemonNode(9, "水箭龟");

        PokemonNode n2 = new PokemonNode(2, "妙蛙草");
        PokemonNode n6 = new PokemonNode(6, "喷火龙");
        PokemonNode n8 = new PokemonNode(8, "卡咪龟");

        SingleLinkedList list2 = new SingleLinkedList();
        list2.addOrderByNo(n1);
        list2.addOrderByNo(n3);
        list2.addOrderByNo(n4);
        list2.addOrderByNo(n5);
        list2.addOrderByNo(n7);
        list2.addOrderByNo(n9);

        SingleLinkedList list3 = new SingleLinkedList();
        list3.addOrderByNo(n2);
        list3.addOrderByNo(n6);
        list3.addOrderByNo(n8);

        PokemonNode newHead = SingleLinkedList.mergeTwoList(list2.getHead(), list3.getHead());
        PokemonNode temp = newHead.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }


    }
}

//定义SingleLinkedList 管理PokemonNode
class SingleLinkedList {
    //先初始化一个头节点，头节点不可改动,不存放具体的数据
    private PokemonNode head = new PokemonNode(0, "");

    public PokemonNode getHead() {
        return head;
    }

    //添加节点到单向链表（第一种）
    //思路：当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next指向新的节点
    @Deprecated
    public void addIgnoreNo(PokemonNode pokemonNode) {

        //因为head节点不可动，因此我们需要一个辅助遍历temp
        PokemonNode temp = head;
        //遍历链表，找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = pokemonNode;
    }

    //第二种添加方式
    //在添加Pokemon时，根据No序号将Pokemon插入到指定位置
    // 如果有这个No序号，则添加失败，并给出提示
    public void addOrderByNo(PokemonNode pokemonNode) {
        //因为头节点不能动，因此通过一个辅助变量来找到添加的位置
        //因为是单链表，我们找的temp是位于添加位置的前一个节点。否则插入不了
        PokemonNode temp = head;
        boolean flag = false; //flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                temp.next = pokemonNode;
                return;
            } else if (temp.next.no > pokemonNode.no) {
                pokemonNode.next = temp.next;
                temp.next = pokemonNode;
                return;
            } else if (temp.next.no == pokemonNode.no) {
                System.err.println("No" + pokemonNode.no + "已经存在，添加失败。");
                return;
            }
            temp = temp.next;//后移
        }
    }

    //修改节点的信息，根据编号No修改，即No编号不能修改。
    //说明：根据newPokemonNode的No来修改
    //注：这里采用更改原有节点值，如果修改的数据量比较大可以直接替换节点
    public void update(PokemonNode newPokemonNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.err.println("链表为空");
            return;
        }
        //修改原有节点值
        PokemonNode temp = head.next;
        while (true) {
            if (temp == null) {
                System.err.printf("未找到No%d，无法修改。\n", newPokemonNode.no);
                return;
            } else if (temp.no == newPokemonNode.no) {
                temp.name = newPokemonNode.name;
                return;
            }
            temp = temp.next;
        }

        //替换节点的方式
//        PokemonNode temp = head;
//        while (temp.next != null) {
//            if (temp.next.no == newPokemonNode.no) {
//                newPokemonNode.next = temp.next.next;
//                temp.next = newPokemonNode;
//                return;
//            }
//            temp = temp.next;
//        }
//        System.err.printf("未找到No%d，无法修改。\n", newPokemonNode.no);
    }

    //删除节点
    public void del(int no) {
        PokemonNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        System.err.printf("未找到No%d,无法删除。\n", no);
    }

    //显示链表[遍历]
    public void showList() {
        //判断是否为空
        if (head.next == null) {
            System.err.println("链表为空");
            return;
        }
        //创建一个辅助变量来遍历
        PokemonNode temp = head.next;
        while (temp != null) {
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    /**
     * @param head 链表的头节点
     * @return 返回的是有效节点个数
     * @description 求单链表中有效节点的个数(如果是带头节点的链表 ， 不计头节点)
     */
    public static int getLength(PokemonNode head) {
        int length = 0;
        //定义一个辅助变量
        PokemonNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * @param head
     * @param index
     * @return PokemonNode
     * @description 查找单链表中的倒数第K个节点
     * 1. 编写一个方法接收head节点，同时接收一个index
     * 2. index 表示倒数第index个节点
     * 3. 先把链表从头到尾遍历，得到链表的总长度 getLength
     * 4. 得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
     * <p>
     * 思路二：利用两个指针第一个指针先遍历链表，第二个指针在与第一个指针相差k-1时开始遍历
     * 效率应该相差不大？都是遍历了2n-k+1次。（待验证）
     */
    public static PokemonNode findLastIndex(PokemonNode head, int index) {
        //判断如果列表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一个遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历 size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量
        PokemonNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }


    /**
     * @param head 要反转的单链表的头节点
     * @description 反转单链表
     */
    public static void reverseList(PokemonNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        PokemonNode temp = head.next;
        PokemonNode next = null;//指向当前节点[temp]的下一个节点
        PokemonNode reverseHead = new PokemonNode(0, "");
        //遍历原来的链表，每遍历一个节点就将其取出，并放在新得链表reverseHead的最前端
        while (temp != null) {
            next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * @param head
     * @description 从尾到头打印单链表(利用栈 ， 不改变链表的结构)
     */
    public static void showRerverseList(PokemonNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<PokemonNode> stack = new Stack<>();
        PokemonNode temp = head.next;
        //将链表的所有节点压入栈
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * @param head1 第一个单链表的头节点
     * @param head2 第二个单链表的头节点
     * @return 合并后的单链表的头节点
     * @description 合并两个有序的单链表，合并之后的链表以然有序
     */
    public static PokemonNode mergeTwoList(PokemonNode head1, PokemonNode head2) {
        PokemonNode newHead = new PokemonNode(0, "");
        PokemonNode temp1 = head1.next;
        PokemonNode temp2 = head2.next;
        PokemonNode tempNew = newHead;
        while (temp1 != null && temp2 != null) {
            if (temp1.no <= temp2.no) {
                tempNew.next = temp1;
                temp1 = temp1.next;
            } else {
                tempNew.next = temp2;
                temp2 = temp2.next;
            }
            tempNew = tempNew.next;
        }
        while (temp1 != null) {
            tempNew.next = temp1;
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            tempNew.next = temp2;
            temp2 = temp2.next;
        }
        return newHead;
    }


}

//定义PokemonNode，每个PokemonNode对象就是一个节点
class PokemonNode {
    public int no;
    public String name;
    public PokemonNode next;//指向下一个节点

    //构造器
    public PokemonNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PokemonNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}