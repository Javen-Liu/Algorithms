package Sort.PriorityQueue;

public interface IMinPriorityQueue<Key extends Comparable<Key>> {
    /**
     * 插入元素
     * @param value 元素
     */
    void insert(Key value);

    /**
     * 获取优先队列中的键值最大元素
     * @return  键值最大的元素
     */
    Key min();

    /**
     * 返回优先队列中键值最大的元素，并将其从队列中删除
     * @return  键值最大的元素
     */
    Key delMin();

    /**
     * 查询优先队列是否为空
     * @return  若为空，则返回true
     *          boolean型
     */
    boolean isEmpty();

    /**
     * 获取优先队列中元素的数量
     * @return  优先队列中元素的数量
     *          int型
     */
    int size();
}

