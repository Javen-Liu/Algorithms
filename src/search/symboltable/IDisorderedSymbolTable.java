package search.symboltable;
/**
 * @author 刘建雯
 * 无序的泛型符号表
 */
@SuppressWarnings("unused")
public interface IDisorderedSymbolTable<Key extends Comparable<Key>, Value> {
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
     * 获取表中所有键key的集合
     * @return  返回Iterable的实现，且其中的数据类型为Key（泛型）
     */
    Iterable<Key> keys();
}
