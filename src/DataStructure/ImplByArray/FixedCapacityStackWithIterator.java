package DataStructure.ImplByArray;

import java.util.Iterator;
import java.util.Objects;

/**
 * 定长，带有迭代器的stack
 * 上压堆栈
 * @param <Item> 输入的数据类型
 */
public class FixedCapacityStackWithIterator<Item> implements Iterable<Item>{
    private int size;
    private int length;
    private Item[] content;

    /**
     * 构造方法，创建指定长度的stack
     * @param size stack的最大容量
     */
    public FixedCapacityStackWithIterator(int size){
        this.size = size;
        content = (Item[])new Object[size];
    }

    /**
     * 向stack中压入元素
     * @param item 压入的元素，且被泛型所约束
     * versoion 1.1 添加了扩容判断，使内部容器自动扩容，将stack改为可扩容栈
     */
    public void push(Item item){
        if(length == size){
            resize(size*2);
            size = size*2;
        }
        content[length++] = item;
    }

    /**
     * 从stack中压出元素
     * @return 压出元素，类型同泛型Item相同
     * version 1.1 添加了缩容判断，使内部容器自动缩容，将stack改为可扩容栈
     *             保证stack使用率在 50% 以上
     */
    public Item pop(){
        Item popItem = content[--length];
        content[length] = null;
        if(length < (size/2)){
            resize(size/2);
            size = size/2;
        }
        return popItem;
    }

    /**
     * 检查stack是否为空
     * @return 返回boolean类型数据
     */
    public boolean isEmpty(){
        return length == 0;
    }

    /**
     * 获取当前stack中存储元素的数量
     * @return stack中存储数据的长度
     */
    public int length(){
        return length;
    }

    /**
     * 获取stack最大的容量大小
     * @return stack的容量大小
     */
    public int size(){
        return size;
    }

    /**
     * stack内部容器扩容
     * @param max content扩容后大小
     */
    public void resize(int max){
        Item[] newContent = (Item[]) new Object[max];
        for (int i = 0; i < length; i++) {
            newContent[i] = content[i];
        }
        content = newContent;
    }

    /**
     * 获取Iterator迭代器
     * @return 返回一个实现了Iterator接口的ReverseArrayIterator类对象
     */
    @Override
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    /**
     * 实现了Iterator接口的ReverseArrayIterator类
     */
    private class ReverseArrayIterator implements Iterator<Item>{
        /**
         * 获取当前stack中存储数据的长度length
         */
        private int i = length;

        /**
         * 判断是否迭代到最后一个元素
         * @return boolean类型数据
         */
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        /**
         * 获取当前指针指向的元素，并使指针-1
         * @return Item泛型的数据
         */
        @Override
        public Item next() {
            return content[--i];
        }

    }

    /**
     * test
     */
    public static void main(String[] args) {
        FixedCapacityStackWithIterator<String> stack = new FixedCapacityStackWithIterator<>(10);
        //测试push与pop方法
        for (int i = 0; i < 10; i++) {
            stack.push(""+i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(stack.pop());
        }

        //测试迭代器
        for (int i = 0; i < 10; i++) {
            stack.push(""+i);
        }
        Iterator iterator = stack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
