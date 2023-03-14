package com.xiaoxiao.datastructures.linkedlist;


//单链表实现
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "陈楚潇", "潇潇");
        HeroNode hero2 = new HeroNode(2, "赵佳山", "卡卡");
        HeroNode hero3 = new HeroNode(3, "王一", "老王");
        HeroNode hero4 = new HeroNode(4, "杨传奇", "游戏王");

/*        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNode(hero1);
        singleLinkedList.addNode(hero2);
        singleLinkedList.addNode(hero3);
        singleLinkedList.addNode(hero4);

        singleLinkedList.printList();
 */

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero4);
        singleLinkedList1.addByOrder(hero2);
        singleLinkedList1.addByOrder(hero3);


        //singleLinkedList1.reverseList();
        singleLinkedList1.printList();
    }
}

//链表类
class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");//头节点

    //链表尾部添加新的节点
    public void addNode(HeroNode heroNode){
        HeroNode temp = head;
        //必须新建一个节点，否则会有一个bug，如果再新建同样一个链表，第一个链表会正常输出，但是第二个会进入死循环
        //我所理解得到原因是因为，如果过不使用新的节点，第二次就会使用第一次的节点链表地址（此时的他的next属性就有值了）                                                                                                            ），最终导致异常
        HeroNode node = new HeroNode(heroNode.no, heroNode.name, heroNode.nikeName);
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    //通过节点编号大小排序，并防止重复
    public void  addByOrder(HeroNode heroNode){
        boolean flag = false;//标志位用于判断是否编号重复
        HeroNode temp = head;
        HeroNode node = new HeroNode(heroNode.no, heroNode.name, heroNode.nikeName);
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
        temp.next = node;

    }

    //修改节点信息
    public void update(HeroNode newHeroNode){
        boolean flag = false;
        HeroNode temp = head.next;//注意这里的temp值
        while (temp != null){
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nikeName = newHeroNode.nikeName;
        } else {
            System.out.printf("需要修改的%d号，不存在", newHeroNode.no);
        }
    }

    //删除对应编号的节点
    public void delete(int no){
        boolean flag = false;
        HeroNode temp = head;//注意这里的temp值
        while (temp.next != null){
            if (temp.next.no == no){//判断节点编号
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){//找到编号之后重新赋值
            temp.next = temp.next.next;
        } else {
            System.out.printf("需要删除的%d号，不存在", no);
        }
    }

    //获取有效链表数据个数
    public int size(){
        int sum = 0;
        HeroNode temp = head.next;//注意是不包含头节点的
        while (temp != null){
            sum++;
            temp = temp.next;
        }
        return sum;
    }

    //遍历链表元素
    public void printList(){
        HeroNode temp = head.next;
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //获取倒数第K的元素
    public HeroNode getCountdownNode(int k){
        HeroNode temp = head.next;
        int size = size();

        if (head.next == null){//链表为空时
            return null;
        }

        if (k <=0 || k > size){//k的范围不合理时
            return null;
        }

        for (int i = 0; i < size - k; i++){//结合temp的值（不包含头节点）这里的循环次数就是：链表长度 - 倒数第K位
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
    public void reverseList(){
        HeroNode newHead = new HeroNode(1, "", "");
        HeroNode temp = head.next;
        HeroNode nextNode = null;//因为是单链表防止断链，所以用该节点对象存储temp的下一个节点
        //如果链表为空或者链表只有一个元素的情况
        if (temp == null || temp.next == null){
            return;
        }
        while (temp != null){
            nextNode = temp.next;//暂存好下一个节点
            /*注意这里的temp就不要想原来的链表了，而是要想做一个独立的节点，因为他的next已经被其他节点记录，它就相当于是孤立的一个节点
            * 因为是在链表前端添加元素，则首先是当前节点的next指向反转链表的第一个节点 temp.next = newHead.next
            * 然后反转链表的头节点再指向当前节点 newHead.next = temp 即可添加成功
            * 逆序打印时可以使用该方法，但是不建议，因为这改变了原来的链表结构，更建议使用栈的数据结构特点来实现反转
            */
            temp.next = newHead.next;
            newHead.next = temp;
            temp = nextNode;//节点向下走一位
        }
        head.next = newHead.next;//反转完成后重新让原来的head节点指向反转后的链表
    }
}


//链表节点类
class HeroNode{
    public int no;
    public String name;
    public String nikeName;
    public HeroNode next;

    public HeroNode(int no, String name, String nikeName) {
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
