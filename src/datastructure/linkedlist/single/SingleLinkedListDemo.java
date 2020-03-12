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

        list.del(1);
        list.del(2);
        list.del(3);
        list.del(4);
        list.del(5);
        list.del(6);
        list.del(7);
        list.del(8);
        list.del(9);
        list.del(99);

        list.showList();

    }
}

//定义SingleLinkedList 管理PokemonNode
class SingleLinkedList {
    //先初始化一个头节点，头节点不可改动,不存放具体的数据
    private PokemonNode head = new PokemonNode(0, "");

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
        while (true){
            if (temp == null){
                System.err.printf("未找到No%d，无法修改。\n",newPokemonNode.no);
                return;
            }else if (temp.no == newPokemonNode.no){
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
    public void del(int no){
        PokemonNode temp = head;
        while (temp.next!=null){
            if (temp.next.no == no){
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