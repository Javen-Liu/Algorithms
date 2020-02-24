package search.symboltable;

import java.util.ArrayList;

/**
 * @author 刘建雯
 * 线性探测散列符号表
 */
@SuppressWarnings({"unused","unchecked"})
public class LinearProbingHashSymbolTable<Key ,Value> {
    private Key[] keys;
    private Value[] vals;
    private int size = 0, capacity;
    public final static int DEFAULT_CAPACITY = 16;

    public LinearProbingHashSymbolTable(int initialCapacity){
        keys = (Key[]) new Object[initialCapacity];
        vals = (Value[]) new Object[initialCapacity];
        this.capacity = initialCapacity;
    }

    public LinearProbingHashSymbolTable(){
        this(DEFAULT_CAPACITY);
    }

    private int hash(Key key){
        return key.hashCode() & 0x7fffffff % capacity;
    }

    public void put(Key key, Value val){
        if(size == capacity/2){
            resize(2*capacity);
        }
        int i;
        for(i = hash(key); keys[i] != null; i = (i+1) % capacity){
            if(key.equals(keys[i])){
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        size++;
    }

    private void resize(int cap){
        LinearProbingHashSymbolTable<Key, Value> st = new LinearProbingHashSymbolTable<>(cap);
        for (int i = 0; i < capacity; i++) {
            if(keys[i] != null){
                st.put(keys[i],vals[i]);
            }
        }
        keys = st.keys;
        vals = st.vals;
        capacity = cap;
    }

    public Value get(Key key){
        for(int i = hash(key); keys[i] != null; i = (i+1)%capacity){
            if(key.equals(keys[i])){
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key){
        if(!contains(key)){
            return;
        }
        int i = hash(key);
        while(!key.equals(keys[i])){
            i = (i+1)%capacity;
        }
        keys[i] = null;
        vals[i] = null;
        while(keys[i] != null){
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            put(keyToRedo,valToRedo);
            i = (i+1)%capacity;
        }
        if(--size == capacity/8 && size > 0){
            resize(capacity/2);
        }
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if(keys[i] != null){
                content.append(keys[i]).append("-").append(vals[i]).append(" ");
            }
        }
        return content.toString();
    }
}
