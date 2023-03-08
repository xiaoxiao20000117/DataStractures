package com.xiaoxiao.datastructures.queue;

import java.util.Scanner;

//利用数组直接实现队列
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.printf("a(add):添加元素\t");
            System.out.printf("o(out):取出元素\t");
            System.out.printf("s(show):展示队列\t");
            System.out.printf("h(head):获取队首元素\t");
            System.out.println("e(exit):退出");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a' :
                    try {
                        System.out.println("请输入想要添加的数字：");
                        int value = scanner.nextInt();
                        queue.addQueue(value);
                    } catch (Exception e){
                        System.out.printf(e.getMessage());
                    }
                    break;
                case 'o' :
                    try {
                        int frontValue = queue.outQueue();
                        System.out.println(frontValue);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's' :
                    try {
                        queue.showQueue();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h' :
                    try {
                        int headValue  = queue.headQueue();
                        System.out.println(headValue);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
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

class ArrayQueue{
    private int[] arr;
    private int maxsize;
    private int front;
    private int rear;

    public ArrayQueue(int maxsize) {
        this.maxsize = maxsize;
        arr = new int[maxsize];
        front = -1;//表示队列的头指针，实际指向的是队首元素的前一个位置（开始没有元素故为-1）
        rear = -1;//表示队列的尾指针，实际指向的是队尾的最后有个一个元素
    }

    //判断是否为满
    public boolean isFull(){
        return rear == maxsize - 1;
    }
    //判断是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //入队列
    public void addQueue(int num){
        /*if (front == maxsize - 1 & rear == maxsize -1){
            front = -1;
            rear = -1;
        }*/
        if (isFull()){
            throw new RuntimeException("队列已满，添加失败");
        }
        arr[++rear] = num;//注意理解rear的含义与这里的写法
    }

    //出队列
    public int outQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无元素出队列");
        }
        return arr[++front];
    }

    //展示队列
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法显示");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d \n",i ,arr[i]);
        }
    }

    //获取队首元素
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无队首元素");
        }
        return arr[front + 1];
    }
}