package com.xiaoxiao.datastructures.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleNode hero1 = new DoubleNode(1, "陈楚潇", "潇潇");
        DoubleNode hero2 = new DoubleNode(2, "赵佳山", "卡卡");
        DoubleNode hero3 = new DoubleNode(3, "王一", "老王");
        DoubleNode hero4 = new DoubleNode(4, "杨传奇", "游戏王");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.printList();
    }
}

class DoubleLinkedList{
    private DoubleNode head = new DoubleNode(0,"","");//头节点

    //双向链表尾部添加新的节点
    public void addNode(DoubleNode doubleNode){
        DoubleNode temp = head;
        //必须新建一个节点，否则会有一个bug，如果再新建同样一个链表，第一个链表会正常输出，但是第二个会进入死循环
        //我所理解得到原因是因为，如果过不使用新的节点，第二次就会使用第一次的节点链表地址（此时的他的next属性就有值了）                                                                                                            ），最终导致异常
        DoubleNode node = new DoubleNode(doubleNode.no, doubleNode.name, doubleNode.nikeName);
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    //修改节点信息
    public void update(DoubleNode newDoubleNode){
        boolean flag = false;
        DoubleNode temp = head.next;//注意这里的temp值
        while (temp != null){
            if (temp.no == newDoubleNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newDoubleNode.name;
            temp.nikeName = newDoubleNode.nikeName;
        } else {
            System.out.printf("需要修改的%d号，不存在", newDoubleNode.no);
        }
    }

    //双向链表删除对应编号的节点
    public void delete(int no){
        boolean flag = false;
        DoubleNode temp = head.next;//注意这里的temp值
        while (temp != null){
            if (temp.no == no){//判断节点编号
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){//找到编号之后重新赋值
            temp.pre.next = temp.next;
            if (temp.next != null){//防止删除的节点为最后一个节点是发生空指针
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("需要删除的%d号，不存在", no);
        }
    }

    //通过节点编号大小排序，并防止重复
    public void  addByOrder(DoubleNode doubleNode){
        boolean flag = false;//标志位用于判断是否编号重复
        DoubleNode temp = head;
        DoubleNode node = new DoubleNode(doubleNode.no, doubleNode.name, doubleNode.nikeName);
        while (true){
            if (temp.next == null){//判断是否为末尾节点
                break;
            }
            if (temp.next.no > node.no){//判断no的大小，如果temp的下一个节点的编号大于新节点的编号则，新节点就应该放在temp后面
                break;
            }else if (temp.next.no == node.no){//判断no是否重复
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.printf("添加的编号%d重复了，添加失败\n", node.no);
            return;
        }
        node.next = temp.next;
        node.pre = temp;
        if (temp.next != null ){
            temp.next.pre = node;
        }
        temp.next = node;
    }

    //遍历链表元素
    public void printList(){
        DoubleNode temp = head.next;
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//双向链表
class DoubleNode{
    public int no;
    public String name;
    public String nikeName;
    public DoubleNode next;
    public DoubleNode pre;//上一个节点的地址

    public DoubleNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nikeName='" + nikeName +
                "'}";
    }
}