package Sort.PriorityQueue;

/**
 * @author 刘建雯
 * 索引最小优先队列
 */
@SuppressWarnings({"unused","unchecked"})
public class IndexMinPriorityQueue<Key extends Comparable<Key>> {
    /**
     * 用于进行堆排序的索引数组，数组内存储的是
     * 一个索引k（也可以看作指针），指向了keys
     * 数组中索引为k的值。
     */
    private int[] index;

    /**
     * index数组中存储的是pq的逆序，其对应关系为：
     * index[i]         = k
     * InverseIndex[k]  = i
     * 该逆序数组的作用是可以快速检测指定的键k是否
     * 存在，即实现contains()方法。默认如果k不在
     * 队列中，则InverseIndex[k] = -1。
     */
    private int[] inverseIndex;

    /**
     * 用于存储具体值的数组。
     */
    private Key[] keys;
    public static final int DEFAULT_CAPACITY = 16;
    private int size = 0,capacity;

    public IndexMinPriorityQueue(int initialCapacity){
        this.capacity = initialCapacity;
        index = new int[capacity+1];
        inverseIndex = new int[capacity+1];
        keys = (Key[]) new Comparable[capacity+1];
        for (int i = 0; i < capacity + 1; i++) {
            inverseIndex[i] = -1;
        }
    }

    public IndexMinPriorityQueue(){
        this(DEFAULT_CAPACITY);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(int k){
        if(k > capacity){
            return false;
        }
        return inverseIndex[k] != -1;
    }

    public void insert(int k, Key key){
        index[++size] = k;
        inverseIndex[k] = size;
        keys[k] = key;
        swim(size);
    }

    public Key min(){
        return keys[index[1]];
    }

    /**
     * 删除最小元素，并返回该元素的索引k
     * @return  最小元素的索引
     */
    public int delMin(){
        int min = index[1];
        exch(1,size--);
        sink(1);
        keys[index[size+1]] = null;
        inverseIndex[index[size+1]] = -1;
        return min;
    }

    public int minIndex(){
        return index[1];
    }

    public void change(int k, Key key){
        keys[k] = key;
        swim(inverseIndex[k]);
        sink(inverseIndex[k]);
    }

    public void delete(int k){
        int delIndex = index[k];
        exch(k,size--);
        swim(delIndex);
        sink(delIndex);
        keys[index[size+1]] = null;
        inverseIndex[index[size+1]] = -1;
    }

    private void swim(int n){
        while(n > 1){
            if(less(n/2,n)){
                break;
            }
            exch(n,n/2);
            n = n/2;
        }
    }

    private void sink(int n){
        while(n*2 <= size){
            int j = n*2;
            if(j<size && less(j+1,j)){
                j++;
            }
            if(less(n,j)){
                break;
            }
            exch(n,j);
            n = j;
        }
    }

    private boolean less(int i, int j){
        return keys[index[i]].compareTo(keys[index[j]]) <= 0;
    }

    private void exch(int i, int j){
        int temp = index[i];
        index[i] = index[j];
        inverseIndex[index[i]] = i;
        index[j] = temp;
        inverseIndex[temp] = j;
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            content.append(index[i]).append(":").append(keys[index[i]]).append(" ");
        }
        return content.toString().trim();
    }
}
