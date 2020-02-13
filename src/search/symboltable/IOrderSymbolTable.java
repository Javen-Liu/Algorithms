package search.symboltable;

/**
 * @author 刘建雯
 * 有序的泛型符号表
 */
@SuppressWarnings("unused")
public interface IOrderSymbolTable<Key extends Comparable<Key>,Value> {
    /**
     * 将键值对存入表中（若值为空，则将键key从表中删除）
     * @param key   键
     * @param val 值
     */
    void put(Key key,Value val);

    /**
     * 获取键key对应的值
     * @param key   键
     * @return      若键key不存在，则返回null
     */
    Value get(Key key);

    /**
     * 从表中删去键key，及其对应的值value
     * @param key   键
     */
    void delete(Key key);

    /**
     * 键key在表中是否有对应的值value
     * @param key   键
     * @return      若不存在对应的值value，则返回null
     */
    boolean contains(Key key);

    /**
     * 表是否为空
     * @return  若表为空，则返回true
     */
    boolean isEmpty();

    /**
     * 表中的键值对数量
     * @return  返回数据类型为int
     *          为键值对数量
     */
    int size();

    /**
     * [lo..hi]之间键的数量
     * @param lo    指定范围的下界
     * @param hi    指定范围的上界
     * @return      返回数据类型为int
     */
    int size(int lo, int hi);

    /**
     * 获取最小的键
     * @return  返回数据类型为泛型Key 最小的键
     */
    Key min();

    /**
     * 获取最小的键
     * @return  返回数据类型为泛型Key 最大的键
     */
    Key max();

    /**
     * 获取小于等于key的最大键
     * 向下取整
     * @param key   指定的键
     * @return      返回数据类型为泛型Key
     *              小于等于指定键的最大键
     */
    Key floor(Key key);

    /**
     * 获取大于等于key的最小键
     * 向上取整
     * @param key   指定的键
     * @return      返回数据类型为泛型Key
     *              大于等于指定键的最小键
     */
    Key ceiling(Key key);

    /**
     * 获取指定键的排序位置（即小于key的键的数量）
     * @param key   指定的键
     * @return      返回数据类型为int
     *              小于key的间的数量
     */
    int rank(Key key);

    /**
     * 获取排名为k的键
     * @param k 指定排名
     * @return  返回类数据型为泛型Key
     *          排名为k的键
     */
    Key select(int k);

    /**
     * 删除最小的键
     */
    void deleteMin();

    /**
     * 删除最大的键
     */
    void deleteMax();

    /**
     * [lo..hi]之间的所有键，已排序
     * @param lo    指定范围的下界
     * @param hi    指定范围的上界
     * @return      返回Iterable的实现，且其中的数据类型为Key（泛型）
     */
    Iterable<Key> keys(int lo, int hi);

    /**
     * 获取表中所有键key的集合
     * @return  返回Iterable的实现，且其中的数据类型为Key（泛型）
     */
    Iterable<Key> keys();
}
