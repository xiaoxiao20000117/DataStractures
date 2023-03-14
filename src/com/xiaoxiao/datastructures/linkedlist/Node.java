package com.xiaoxiao.datastructures.linkedlist;

public class Node {

    public int val;

    public Node(int val) {
        this.val = val;
    }

    public Node next;


    //合并两个有序链表
    public static Node mergeTwoLists(Node head1,Node head2){
        Node newNode=new Node(-1); //虚拟节点，数据不具备意义
        Node tmp=newNode;
        while (head1!=null&&head2!=null){
            if (head1.val < head2.val){
                tmp.next=head1;
                tmp=tmp.next;
                head1=head1.next;
            }else{
                tmp.next=head2;
                tmp=tmp.next;
                head2=head2.next;
            }
        }
        if (head1!=null){
            tmp.next=head1;
        }
        if (head2!=null){
            tmp.next=head2;
        }
        return newNode.next;

    }
    public static void main(String[] args) {
        SingleLinkedList1 singleLinkedList1=new SingleLinkedList1();
        singleLinkedList1.addNode(2);
        singleLinkedList1.addNode(7);
        singleLinkedList1.addNode(8);
        SingleLinkedList1 singleLinkedList2=new SingleLinkedList1();
        singleLinkedList2.addNode(1);
        singleLinkedList2.addNode(5);
        singleLinkedList2.addNode(9);
        singleLinkedList2.addNode(12);
        singleLinkedList2.addNode(21);
        Node ret=mergeTwoLists(singleLinkedList1.head,singleLinkedList2.head);
        singleLinkedList1.myToString(ret);
    }
}
class SingleLinkedList1 {
     Node head = new Node(0);//头节点

    //链表尾部添加新的节点
    public void addNode(int num) {
        Node temp = head;
        //必须新建一个节点，否则会有一个bug，如果再新建同样一个链表，第一个链表会正常输出，但是第二个会进入死循环
        //我所理解得到原因是因为，如果过不使用新的节点，第二次就会使用第一次的节点链表地址（此时的他的next属性就有值了）                                                                                                            ），最终导致异常
        Node node = new Node(num);
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void  myToString(Node newNode){         //打印链表里的元素
        Node cur=newNode.next.next;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur=cur.next;
        }
    }
}