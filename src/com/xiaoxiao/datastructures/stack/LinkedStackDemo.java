package com.xiaoxiao.datastructures.stack;

import java.util.Scanner;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack(4);
        String key = "";//接收用户输入
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.printf("push:添加入栈元素\t");
            System.out.printf("pop:取出元素\t");
            System.out.printf("show:展示队列\t");
            System.out.println("exit:退出");
            key = scanner.next();
            switch (key) {
                case "push" :
                    System.out.println("请输入想要添加的数字：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop" :
                    try {
                        StackNode frontValue = stack.pop();
                        System.out.println(frontValue);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "show" :
                    stack.showStack();
                    break;
                case "exit" :
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入不合法，请重新输入");
                    break;
            }
        }
        System.out.println("已退出系统");
    }
}

//链表构成的栈结构
class LinkedStack{
    private int maxSize;
    private StackNode head = new StackNode(-1);//头节点，相当与top节点
    private int length = 0;//直接记录链表长度和元素个数

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return length == 0;
    }

    //判断栈是否已满
    public boolean isFull(){
        return length == maxSize;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈已满，添加失败");
            return;
        }
        StackNode stackNode = new StackNode(value);
        stackNode.setNext(head.getNext());
        head.setNext(stackNode);
        length++;
    }

    //出栈
    public StackNode pop(){
        if (isEmpty()){
            throw new RuntimeException("栈数据为空");
        }
        StackNode valueNode = head.getNext();
        head.setNext(valueNode.getNext());
        valueNode.setNext(null);
        length--;
        return valueNode;
    }

    //遍历栈数据
    public void showStack(){
        if (isEmpty()){
            System.out.println("栈为空");
            return;
        }
        StackNode temp = head.getNext();
        while (temp != null){
            System.out.println("节点值" + temp.getNo());
            temp = temp.getNext();
        }
    }
}

//链表节点
class StackNode{
    private int no;
    private StackNode next;//节点的地址

    public StackNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "no=" + no +
                '}';
    }
}