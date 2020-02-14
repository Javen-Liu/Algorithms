package search.symboltable.impl;

import search.symboltable.IOrderedSymbolTable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 刘建雯
 * 有序字符表：
 *        通过两个数组来实现，一个数组用来装键key，一个数组用来
 *        装值value，并且保证每次操作后，key与value一一对应。该
 *        数据结构最重要的一个方法就是rank()，即找出key在当前键
 *        数组keys中的排序位置，同时也代表了比该键key小的键的数
 *        量。其get()与put()方法全用到了rank()方法来定位其在键
 *        数组中的索引。rank()方法使用了经典的二分查找(Binary
 *        Search)来定位其索引，二分查找的速度非常快，其比较次数
 *        是小于logN+1的，比链表的顺序查找的N要快出许多。这也就
 *        使得该数据结构进行get()操作非常简单。
 *        同时该有序字符表有两种构造方法，支持定长创建以及使用默
 *        认大小的字符表。
 *        但是缺点也很明显，这也和数组的特性有关，那就是对数组进
 *        行插入时非常的麻烦，最坏的情况下，插入N个元素，需要访
 *        问数组的次数为N的平方次。因此其put()以及delete()方法
 *        效率比较的低下。
 *  查找方式：
 *        二分查找
 */
@SuppressWarnings({"unused","unchecked"})
public class OrderedSymbolTableImplByBinarySearch<Key extends Comparable<Key>, Value> implements IOrderedSymbolTable<Key,Value> {
    private Key[] keys;
    private Value[] vals;
    private int size = 0,capacity;
    public static final int DEFAULT_CAPACITY = 15;

    public OrderedSymbolTableImplByBinarySearch(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
        this.capacity = capacity;
    }

    public OrderedSymbolTableImplByBinarySearch(){
        keys = (Key[]) new Comparable[DEFAULT_CAPACITY];
        vals = (Value[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void put(Key key, Value val) {
        if(size == capacity){
            resize(capacity*2);
        }
        int index = rank(key);
        if(index < size && keys[index].compareTo(key) == 0){
            vals[index] = val;
            return;
        }
        System.arraycopy(keys, index, keys, index+1, size - index);
        System.arraycopy(vals, index, vals, index+1, size - index);
        keys[index] = key;
        vals[index] = val;
        size++;
    }

    /**
     * 辅助工具方法：扩容
     * @param capacity  int型数据
     *                  扩容大小
     */
    private void resize(int capacity){
        Key[] newKeys = (Key[]) new Comparable[capacity];
        Value[] newVals = (Value[]) new Object[capacity];
        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(vals, 0, newVals, 0, size);
        keys = newKeys;
        vals = newVals;
        this.capacity = capacity;
    }

    @Override
    public Value get(Key key) {
        if(isEmpty()){
            return null;
        }
        return contains(key)? vals[rank(key)]: null;
    }

    @Override
    public boolean delete(Key key) {
        if(!contains(key) || isEmpty()){
            return false;
        }
        int index = rank(key);
        System.arraycopy(keys, index+1, keys, index, size - index);
        System.arraycopy(vals, index+1, vals, index, size - index);
        keys[size] = null;
        vals[size--] = null;
        return true;
    }

    @Override
    public boolean contains(Key key) {
        int index = rank(key);
        return keys[index].compareTo(key) == 0;
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
    public int size(Key lo, Key hi) {
        int lowIndex = rank(lo), highIndex = rank(floor(hi));
        boolean containsLow = contains(lo), containsHigh = contains(hi);
        if(highIndex == size && lowIndex == 0){
            return size;
        }
        if (lo.compareTo(hi) == 0) {
            return 1;
        }
        return highIndex - lowIndex + 1;
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[size-1];
    }

    @Override
    public Key floor(Key key) {
        return contains(key)? keys[rank(key)]: keys[rank(key)-1];
    }

    @Override
    public Key ceiling(Key key) {
        return keys[rank(key)];
    }

    @Override
    public int rank(Key key) {
        int lo = 0, hi = size-1;
        while(lo <= hi){
            int mid = (hi - lo + 1)/2 + lo;
            int res = keys[mid].compareTo(key);
            if(res < 0){
                lo = mid + 1;
            }else if(res > 0){
                hi = mid - 1;
            }else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public Key select(int k) {
        if(k > size){
            return null;
        }
        return keys[k-1];
    }

    @Override
    public void deleteMin() {
        delete(keys[0]);
    }

    @Override
    public void deleteMax() {
        delete(keys[size-1]);
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if(lo.compareTo(hi) > 0){
            return null;
        }
        int highIndex = rank(floor(hi)), lowIndex = rank(ceiling(lo));
        return new ArrayList<>(Arrays.asList(keys).subList(lowIndex, highIndex + 1));
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(),max());
    }

}
