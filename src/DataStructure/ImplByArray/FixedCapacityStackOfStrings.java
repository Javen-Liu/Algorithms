package DataStructure.ImplByArray;

/**
 * @author 刘建雯
 * 定长stack的实现
 */
public class FixedCapacityStackOfStrings {
    private int capacity;
    private int size = 0;
    private String[] content;

    public FixedCapacityStackOfStrings(int capacity){
        this.capacity = capacity;
        content = new String[capacity];
    }

    public void push(String item){
        content[size++] = item;
    }

    public String pop(){
        return content[--size];
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
