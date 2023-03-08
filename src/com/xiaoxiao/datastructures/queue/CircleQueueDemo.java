package com.xiaoxiao.datastructures.queue;

import java.util.Scanner;

//环形链表
public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4);
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

class CircleQueue{
    private int[] arr;
    private int maxsize;
    private int front;
    private int rear;

    public CircleQueue(int maxsize) {
        this.maxsize = maxsize;
        arr = new int[maxsize];
        front = 0;//表示队列的头指针，实际指向的是队首元素
        rear = 0;//表示队列的尾指针，实际指向的是队尾元素的后一个位置（开始没有元素故为0）
    }

    //判断是否为满
    /*
    * 因为它是环形的，会有复用，rear指针是有可能指向队的前面来的，
    * 所以它要取模才能准确的判断是否为满。条件为(rear + 1) % maxSize == front。
    * rear+1是因为当队满的时候，rear与front是紧挨着的所以加1
    */
    public boolean isFull(){
        return (rear + 1) % maxsize == front;
    }
    //判断是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //入队列
    public void addQueue(int num){
        if (isFull()){
            throw new RuntimeException("队列已满，添加失败");
        }
        arr[rear] = num;//注意理解rear的含义与这里的写法,+1表示下一个位置，取模是为了循环
        rear = (rear + 1) % maxsize;
    }

    //出队列
    public int outQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无元素出队列");
        }
        int value = arr[front];
        front = (front + 1) % maxsize;
        return value;
    }

    //展示队列
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法显示");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d \n",i % maxsize ,arr[i % maxsize]);
        }
    }

    //获取队首元素
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无队首元素");
        }
        return arr[front];
    }

    //获取队列中实际的元素个数
    /*
    * 有效元素个数的算法为：(rear + maxSize - front) % maxSize，因为是环形的，
    * rear是有可能走到front前面的，所以需要先加上maxSize，
    * 以防止rear-front是一个负数，然后在取模，就可以得到有效元素的个数。
    */
    public int size() {
        return (rear + maxsize - front) % maxsize;
    }
}
