package com.xiaoxiao.datastructures.linkedlist;

public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addNode(125);

        list.josephus(125, 10,20);
    }
}

//环形单向链表
class CircleSingleLinkedList {
    //第一个节点
    CircleSingleNode first = null;

    //添加元素，这里是直接什么含有sum个节点的元素
    public void addNode(int sum){
        CircleSingleNode temp = null;

        //判断节点个数是否满足要求
        if (sum <= 0){
            System.out.println("输入的总数不和法");
            return;
        }

        for (int i = 1; i <= sum; i++){
            CircleSingleNode cNode = new CircleSingleNode(i);
            if (i == 1){
                first = cNode;
                first.setNext(first);
                temp = first;
            }else {
                temp.setNext(cNode);
                cNode.setNext(first);
                temp = cNode;
            }
        }
    }

    //遍历循环单链表
    public void outPrintList(){
        CircleSingleNode temp = first;
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        while (true){
            System.out.printf("输出编号%d\n", temp.getNo());
            if (temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 解决约瑟夫问题
     * @param sum 一共有多少个节点
     * @param start 从那个节点开始数
     * @param out 数多少个输出
     */
    public void josephus(int sum, int start, int out){
        CircleSingleNode temp = first;

        if (first == null || start > sum || start < 1 || out < 1){
            System.out.println("输入数据不合法");
        }
        //辅助节点需要满足为first节点的前一个节点
        while (true){
            if (temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }

        //定位好first和temp节点后需要移位到开始的节点
        for (int i = 0; i < start - 1; i++){
            first = first.getNext();
            temp = temp.getNext();
        }

        //开始向外输入满足条件的节点
        while (true){
            if (first == temp){
                break;
            }
            //向下移动
            for (int i = 0; i < out - 1; i++){
                first = first.getNext();
                temp = temp.getNext();
            }
            System.out.printf("输出编号%d\n", first.getNo());
            first = first.getNext();
            temp.setNext(first);
        }
        System.out.printf("最终输出编号%d\n", first.getNo());
    }
}

//环形单链表的节点类
class CircleSingleNode{
    private int no;
    private CircleSingleNode next;

    public CircleSingleNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public CircleSingleNode getNext() {
        return next;
    }

    public void setNext(CircleSingleNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "CircleSingleNode{" +
                "no=" + no +
                '}';
    }
}