package datastructure.linkedlist.doubly;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-13 19:21
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试

        DoubleLinkedList list = new DoubleLinkedList();

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

        list.update(p10);
        list.update(p11);

        list.del(9);
        list.del(2);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.showList();
    }
}

class DoubleLinkedList {
    //先初始化一个头节点，头节点不可改动,不存放具体的数据
    private PokemonNode head = new PokemonNode(0, "");

    public PokemonNode getHead() {
        return head;
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

    //添加节点到双向链表（直接添加到最后）
    @Deprecated
    public void addIgnoreNo(PokemonNode pokemonNode) {

        //因为head节点不可动，因此我们需要一个辅助遍历temp
        PokemonNode temp = head;
        //遍历链表，找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = pokemonNode;
        pokemonNode.pre = temp;
    }

    //第二种添加方式
    //在添加Pokemon时，根据No序号将Pokemon插入到指定位置
    // 如果有这个No序号，则添加失败，并给出提示
    public void addOrderByNo(PokemonNode pokemonNode) {

        PokemonNode temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = pokemonNode;
                pokemonNode.pre = temp;
                return;
            } else if (temp.next.no > pokemonNode.no) {
                pokemonNode.next = temp.next;
                pokemonNode.pre = temp;
                pokemonNode.pre.next = pokemonNode;
                pokemonNode.next.pre = pokemonNode;
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
    }

    //删除节点
    //说明
    //1. 对于双向链表，应直接找到要删除的节点
    //temp.pre.next = temp.next;
    //temp.next.pre = temp.pre;
    public void del(int no) {
        PokemonNode temp = head.next;
        while (temp != null) {
            if (temp.no == no) {
                temp.pre.next = temp.next;
                if (temp.next!=null) temp.next.pre = temp.pre;//如果删除的是最后一个节点则不进行这步操作
                return;
            }
            temp = temp.next;
        }
        System.err.printf("未找到No%d,无法删除。\n", no);
    }
}

//定义PokemonNode，每个PokemonNode对象就是一个节点
class PokemonNode {
    public int no;
    public String name;
    public PokemonNode next;//指向下一个节点，默认为null
    public PokemonNode pre;//指向前一个节点，默认为null

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