package DataStructure.ImplByLinkedList;

import java.util.Iterator;

/**
 * @author 刘建雯
 * 下压堆栈
 */
public class StackWithIterator<Item> implements Iterable<Item>{
    /**
     * first节点，表明该stack当前第一个元素
     */
    private Node first;

    /**
     * 记录链表长度
     */
    private int length = 0;

    /**
     * 向下压堆栈中压入一个元素，并使头指针指向新加入的Node节点
     * 链表头指向从头加入的Node节点
     * @param value 压入的数据
     */
    public void push(Item value){
        Node oldFirst = first;
        first = new Node();
        first.value = value;
        first.next = oldFirst;
        length++;
    }

    /**
     * 弹出下压堆栈中的一个元素，并使头指针指向链表指向前一个Node节点
     * 链表头指向下一个Node节点
     * @return 返回Item类元素
     */
    public Item pop(){
        Item content = first.value;
        first = first.next;
        length--;
        return content;
    }

    /**
     * 判断该stack是否为空
     * 也就是链表是否为空，链表头是否为null
     * @return 返回boolean类元素，代表是否为stack是否为空
     */
    public boolean isEmpty(){
        return length == 0;
    }

    /**
     * 查询该stack中元素个数
     * @return 返回链表的长度length
     */
    public int size(){
        return length;
    }

    /**
     * Node类为链表中的节点
     */
    private class Node{
        /**
         * value数据类型为Item
         * value为节点保存的数据
         */
        private Item value;

        /**
         * next数据类型为Node
         * next为该节点指向的下一个Node节点
         * 若为null，则表示该节点为链表最后一个节点
         */
        private Node next;
    }

    /**
     * 获取stack内部的迭代器
     * @return 返回一个实现了Iterator类接口的迭代器
     */
    public Iterator<Item> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item>{
        int i = length;

        /**
         * 判断是否迭代到最后一个元素
         * @return boolean类型数据
         */
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        /**
         * 获取当前指针指向的元素，并使指针-1
         * @return Item泛型的数据
         */
        @Override
        public Item next() {
            Item content = first.value;
            first = first.next;
            i--;
            return content;
        }
    }

    public static void main(String[] args) {
        StackWithIterator<String> stack = new StackWithIterator<>();
        //测试push和pop方法
        for (int i = 0; i < 10; i++) {
            stack.push(""+i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(stack.pop());
        }

        //测试迭代器
        for (int i = 0; i < 10; i++) {
            stack.push(""+i);
        }
        Iterator<String> iterator = stack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
