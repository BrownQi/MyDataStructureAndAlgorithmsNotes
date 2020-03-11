package datastructure.linkedlist.single;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-11 22:15
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        PokemonNode p1 = new PokemonNode(1, "妙蛙种子");
        PokemonNode p2 = new PokemonNode(2, "妙蛙草");
        PokemonNode p3 = new PokemonNode(3, "妙蛙花");
        PokemonNode p4 = new PokemonNode(4, "小火龙");
        PokemonNode p5 = new PokemonNode(5, "火恐龙");
        PokemonNode p6 = new PokemonNode(6, "喷火龙");
        PokemonNode p7 = new PokemonNode(7, "杰尼龟");
        PokemonNode p8 = new PokemonNode(8, "卡咪龟");
        PokemonNode p9 = new PokemonNode(9, "水箭龟");

        list.addIgnoreNo(p1);
        list.addIgnoreNo(p2);
        list.addIgnoreNo(p3);
        list.addIgnoreNo(p4);
        list.addIgnoreNo(p5);
        list.addIgnoreNo(p6);
        list.addIgnoreNo(p7);
        list.addIgnoreNo(p8);
        list.addIgnoreNo(p9);

        list.showList();

    }
}

//定义SingleLinkedList 管理PokemonNode
class SingleLinkedList {
    //先初始化一个头节点，头节点不可改动,不存放具体的数据
    private PokemonNode head = new PokemonNode(0, "");

    //添加节点到单向链表
    //思路：当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next指向新的节点
    @Deprecated
    public void addIgnoreNo(PokemonNode pokemonNode) {

        //因为head节点不可动，因此我们需要一个辅助遍历temp
        PokemonNode temp = head;
        //遍历链表，找到最后
        while (temp.next!=null) {
            temp = temp.next;
        }
        temp.next = pokemonNode;
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
        while (temp!=null) {
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
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