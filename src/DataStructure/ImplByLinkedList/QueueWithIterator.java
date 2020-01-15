package DataStructure.ImplByLinkedList;

import java.util.Iterator;

/**
 * @author 刘建雯
 * 通过链表实现的FIFO(先入先出)队列
 */
public class QueueWithIterator<Item> implements Iterable<Item> {
    /**
     * 记录头节点
     */
    private Node first;

    /**
     * 记录尾节点，方便队列添加元素
     */
    private Node last;

    /**
     * 队列长度
     */
    private int length;

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    /**
     * 迭代器Iterator
     */
    private class LinkedListIterator implements Iterator<Item>{
        private int i = length;
        private Node current = first;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            Item content = current.value;
            current = current.next;
            i--;
            return content;
        }
    }

    private class Node{
        public Item value;
        public Node next;
    }

    /**
     * 判断queue是否为空
     * @return 返回boolean类型数据，以此来判断队列是否为空
     */
    public boolean isEmpty(){
        return length == 0;
    }

    /**
     * 获取queue的长度
     * @return 返回queue的长度，数据类型为int
     */
    public int size(){
        return length;
    }

    /**
     * 向队列中加入新的元素
     * 在链表尾部加入新数据，并将last指针指向新加入的元素
     * @param value 向队列中加入的数据
     */
    public void enqueue(Item value){
        if(first == null){
            first = new Node();
            first.value = value;
            last = first;
        }else{
            Node newLast = new Node();
            newLast.value = value;
            last.next = newLast;
            last = newLast;
        }
        length++;
    }

    /**
     * 从队列中取出元素
     * 取出链表头的数据，并将链表头指针指向下一个Node节点
     * 如果只剩一个元素时，即first=last时，取完后需将last置为null
     * @return 队列中的数据
     */
    public Item dequeque(){
        Item content = first.value;
        first = first.next;
        length--;
        if(isEmpty()){
            last = null;
        }
        return content;
    }

    public static void main(String[] args) {
        QueueWithIterator<String> queue = new QueueWithIterator<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(""+i);
        }

        //测试实现了Iterable接口的实现
        for(String value:queue){
            System.out.println(value);
        }

        //测试内部迭代器
        Iterator<String> iterator = queue.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //测试从队列中取出元素
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeque());
        }
    }
}
