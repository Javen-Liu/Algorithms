package search.symboltable.impl;

import search.symboltable.IDisorderSymbolTable;

import java.util.ArrayList;

/**
 * @author 刘建雯
 * 无序的符号表：
 *          其中容器是使用链表来实现，在该类中定义了一个内部类Node，即
 *          节点，使用节点Node来实现链表。这种无序的符号表不允许有重复
 *          的键出现。插入一个元素，是通过在链表的首部加入节点。插入节
 *          点的操作非常简单，但为了保证没有重复的键，每次插入都需要进
 *          行一次遍历，也就是说插入N个元素，需要进行 N的平方/2 次的
 *          比较，因此插入以及查找的效率非常低。唯一的优点则是实现简单，
 *          并且删除元素快，这些优点也是链表的优点。但是符号表的删除操
 *          作是有目的性的，在删除指定的元素（节点）时，仍然需要进行遍
 *          历，最坏的情况需要N次比较，效率仍然低，因此不是一种很好的
 *          实现方式。
 */
@SuppressWarnings({"unused","unchecked"})
public class DisorderSymbolTableImpl<Key extends Comparable<Key>,Value> implements IDisorderSymbolTable<Key,Value> {
    private Node first,next;
    private int size = 0;

    private class Node{
        Key key;
        Value val;
        Node next;

        public Node(Key key,Value val,Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    @Override
    public void put(Key key, Value val) {
        if(val == null){
            delete(key);
        }
        for(Node node = first; node != null; node = node.next){
            if (key.equals(node.key)){
                node.val = val;
                return;
            }
        }
        first = new Node(key,val,first);
        size++;
    }

    @Override
    public Value get(Key key) {
        for(Node node = first; node != null; node = node.next){
            if(key.equals(node.key)){
                return node.val;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        if(key.equals(first.key)){
            first = first.next;
        }
        Node node = first;
        for (int i = 0; i < size; i++) {
            if(key.equals(node.next.key)){
                node.next = node.next.next;
            }
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        ArrayList<Key> keys = new ArrayList<>();
        for(Node node = first; node != null; node = node.next){
            keys.add(node.key);
        }
        return keys;
    }

}
