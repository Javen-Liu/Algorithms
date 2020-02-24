package DataStructure.ImplByLinkedList;

import java.util.Iterator;

/**
 * @author 刘建雯
 * Bag（背包)类，其只支持添加元素以及获取所有元素，不支持删除
 */
@SuppressWarnings("unused")
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private Node last = null;
    private int size;

    public void add(Item item){
        if(first == null){
            first = new Node();
            first.value = item;
            last = first;
        }else{
            last.next = new Node();
            last.next.value = item;
            last = last.next;
        }
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item>{
        private Node current = first;
        private int i = size;

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
        private Item value;
        private Node next;
    }

    public static void main(String[] args) {
        String[] content = new String[]{"ddf","we","taf","gad","gfhk","hgyt","fdgh","fgdsg","rewr"};
        Bag<String> bag = new Bag<>();
        for(String value:content){
            bag.add(value);
        }
        for(String value:bag){
            System.out.print(value+" ");
        }
        System.out.println("\n"+bag.isEmpty());
        System.out.println(bag.size());
    }
}
