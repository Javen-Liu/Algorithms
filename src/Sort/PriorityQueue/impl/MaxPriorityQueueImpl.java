package Sort.PriorityQueue.impl;

import Sort.PriorityQueue.IMaxPriorityQueue;
import java.lang.reflect.Array;

/**
 * @author 刘建雯
 */
@SuppressWarnings({"unchecked","unused"})
public class MaxPriorityQueueImpl<Key extends Comparable<Key>> implements IMaxPriorityQueue<Key> {
    private Key[] pq;
    private static final int DEFAULT_CAPACITY = 16;
    private int size = 0,capacity;

    /**
     * 创建默认大小的容器pq
     */
    public MaxPriorityQueueImpl(){
        pq = (Key[]) new Comparable[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    /**
     * 创建指定大小的容器pq，会自动调节最近的2的指数
     * @param initialCapacity 创建容器的大小
     */
    public MaxPriorityQueueImpl(int initialCapacity){
        if(initialCapacity <= 0){
            throw new NegativeArraySizeException("长度不符合长度");
        }
        capacity = adaptCapacity(initialCapacity);
        pq = (Key[]) new Comparable[capacity];
    }

    /**
     * 根据传入的数组a来创建大小相同的容器，并将元素全部传入进pq容器中
     * @param a 创建容器所需的数组
     */
    public MaxPriorityQueueImpl(Key[] a){
        capacity = adaptCapacity(a.length);
        pq = (Key[]) Array.newInstance(a.getClass().getComponentType(),capacity);
        for(Key value:a){
            insert(value);
        }
    }

    /**
     * 插入元素
     * @param value 元素
     */
    @Override
    public void insert(Key value) {
        if(size == capacity-1){
            expandCapacity(capacity*2+1);
        }
        pq[++size] = value;
        swim(size);
    }

    /**
     * 查看键值最大的元素
     * @return 键值最大的元素，类型为Key
     */
    @Override
    public Key max() {
        return pq[1];
    }

    /**
     * 将键值最大的元素弹出（在容器中删除键值最大的元素）
     * @return 键值最大的元素，类型为Key
     */
    @Override
    public Key delMax() {
        exch(1,size);
        Key max = pq[size];
        pq[size--] = null;
        sink(1);
        return max;
    }

    /**
     * 查看容器是否为空
     * @return 若为空，则返回true
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * 查看当前容器内元素个数
     * @return 当前容器内元素个数，类型为int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 辅助工具方法：计算出与指定数据最近的2的指数
     *          例：输入5，输出8（2的3次幂）
     *              输入14，输出16（2的4次幂）
     * @param capacity 输入的长度
     * @return         适配的2次幂长度
     */
    private int adaptCapacity(int capacity){
        if(capacity == 0){
            throw new NegativeArraySizeException("长度不符合长度");
        }
        if (capacity == 1){
            return 1;
        }
        int power = 0;
        while(capacity != 1){
            capacity = capacity/2;
            power++;
        }
        return (int)Math.pow(2,power+1);
    }

    /**
     * 辅助工具方法：扩充当前容器pq的大小
     * @param capacity 容器需扩充的大小
     */
    private void expandCapacity(int capacity){
        Key[] newPq = (Key[]) new Comparable[capacity];
        System.arraycopy(pq,0,newPq,0,this.capacity);
        this.capacity = capacity;
        pq = newPq;
    }

    /**
     * 辅助工具方法：上浮（由下至上的堆有序化）
     *        作用：用来实现insert()方法
     * @param k 出现变化的元素的索引
     */
    private void swim(int k){
        while(k > 1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }

    /**
     * 辅助工具方法：下沉（由上至下的堆有序化）
     *        作用：用来实现delMax()方法
     * @param k 出现变化的元素的索引
     */
    private void sink(int k){
        while(k*2 <= size){
            int j = k*2;
            if(j < size && less(j,j+1)){
                j++;
            }
            if(!less(k,j)){
                break;
            }
            exch(j,k);
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
