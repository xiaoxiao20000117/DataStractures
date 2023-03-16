package com.xiaoxiao.datastructures.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
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
                        int frontValue = stack.pop();
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

//栈结构
class ArrayStack{
    private int top = -1;
    private int size;
    private int[] stack;

    //构造方法
    public ArrayStack(int size) {
        this.size = size;
        stack = new int[size];
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //判断栈满
    public boolean isFull(){
        return top == size - 1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈结构已满，无法加入");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空，出栈异常");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈数据
    public void showStack(){
        if (isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i > -1; i--){
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}