# 双向链表

## [单链表](http://www.brownqi.cn/2020/03/11/single-linked-list/)

## 双向链表介绍

- 双向链表与单链表的区别
  1. 单向链表，查找的方向只能是一个方向，而双向链表可以**向前或者向后查找**。
  2. 单链表不能自我删除，需要靠著辅助接点，而双向链表则可以**自我删除**。

## 双向链表的常用操作

### 分析

1. **遍历**：方式与单链表相同

2. **添加**：

   - 添加到最后
     - 先找到双向链表的最后这个节点
     - temp.next = newNode;
     - newNode.pre = temp;
   - 按序号添加
     - 按序找到应插入的位置，temp：新节点应在位置的前一个节点
     - newNode.next = temp.next;
     - newNode.pre = temp;
     - newNode.pre.next = newNode;
     - newNode.next.pre = newNode;

3. **修改**：方式与单向链表相同

4. **删除**：

   - 找到要删除的节点temp

   - temp.pre.next = temp.next; 
   - temp.next.pre = temp.pre;

### 实现

2. 添加

   - 添加到最后

     ```java
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
     ```

   - 按序添加

     ```java
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
     ```

3. 删除

   ```java
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
   ```

   

