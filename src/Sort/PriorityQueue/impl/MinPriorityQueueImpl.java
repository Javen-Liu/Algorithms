package Sort.PriorityQueue.impl;

import Sort.PriorityQueue.IMinPriorityQueue;
import java.lang.reflect.Array;

/**
 * @author 刘建雯
 * 最小优先队列
 */
@SuppressWarnings({"unused","unchecked"})
public class MinPriorityQueueImpl<Key extends Comparable<Key>> implements IMinPriorityQueue<Key> {
    private Key[] pq;
    public static final int DEFAULT_CAPACITY = 16;
    private int size = 0,capacity;

    public MinPriorityQueueImpl(int initialCapacity){
        capacity = adaptCapacity(initialCapacity);
        pq = (Key[]) new Comparable[capacity];
    }

    public MinPriorityQueueImpl(){
        capacity = DEFAULT_CAPACITY;
        pq = (Key[]) new Comparable[capacity];
    }

    public MinPriorityQueueImpl(Key[] a){
        capacity = adaptCapacity(a.length);
        pq = (Key[]) Array.newInstance(a.getClass().getComponentType(),capacity);
        for(Key value:a){
            insert(value);
        }
    }

    @Override
    public void insert(Key value) {
        if(size == capacity-1){
            extendCapacity(capacity*2+1);
        }
        pq[++size] = value;
        swim(size);
    }

    private void extendCapacity(int capacity){
        Key[] newPq = (Key[]) new Comparable[capacity];
        System.arraycopy(pq,0,newPq,0,pq.length);
        pq = newPq;
    }

    private int adaptCapacity(int capacity){
        if(capacity == 0){
            throw new NegativeArraySizeException("长度不符合规定");
        }
        if(capacity == 1){
            return 1;
        }
        int power = 0;
        while(capacity != 1){
            capacity /= 2;
            power++;
        }
        return (int) Math.pow(2,power);
    }

    @Override
    public Key min() {
        return pq[1];
    }

    @Override
    public Key delMin() {
        exch(1,size);
        Key min = pq[size];
        pq[size--] = null;
        sink(1);
        return min;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void swim(int k){
        while(k > 1 && less(k,k/2)){
            exch(k,k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while(k*2 <= size){
            int j = k*2;
            if(j<size && less(j+1,j)){
                j++;
            }
            if(less(k,j)){
                break;
            }
            exch(k,j);
            k = j;
        }
    }

    /**
     * 辅助工具方法：比较指定数组中指定索引元素的大小
     * @param i 待比较的第一个元素索引
     * @param j 待比较的第一个元素索引
     * @return  如果第一个元素小于第二个元素，则返回true
     */
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 辅助工具方法：交换数组元素位置
     * @param i 待交换的第一个元素索引
     * @param j 待交换的第二个元素索引
     */
    private void exch(int i,int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
