package DataStructure.ImplByArray;

/**
 * 定长，但可以传入多种类型数据的stack
 * @param <Item> 传入数据的泛型
 */
public class FixedCapacityStackOfItem<Item> {
    private int capacity;
    private int size;
    private Item[] content;

    public FixedCapacityStackOfItem(int capacity){
        this.capacity = capacity;
        content = (Item[])new Object[capacity];
    }

    public void push(Item item){
        content[size++] = item;
    }

    public Item pop(){
        Item popItem = content[--size];
        content[size] = null;
        return popItem;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public int capacity(){
        return capacity;
    }
}
