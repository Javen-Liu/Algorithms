package com.test;

import org.junit.Test;
import search.symboltable.impl.OrderedSymbolTableImplByRBTreeWithBinarySearch;

/**
 * @author 刘建雯
 * 测试由红黑树实现的有序字符表
 * 测试数据结构类：OrderedSymbolTableImplByRBTreeWithBinarySearch
 */
public class OrderedSymbolTableTest3 {
    OrderedSymbolTableImplByRBTreeWithBinarySearch<String,Integer> st = new OrderedSymbolTableImplByRBTreeWithBinarySearch<>();
    static final String[] keys = new String[]{"g","e","r","x","v","h","k","t","u","r","p","c","z","m","t","y","b","l"};
    static final int[] vals = new int[]{12,84,34,4,17,48,36,96,455,78,16,43,49,79,13,152,6,73};

    @Test
    //测试插入，以及获取所有键并打印，以此来验证
    public void putTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }
        Iterable<String> keyList = st.keys();
        for(String key:keyList){
            System.out.print(key+" ");
        }
    }

    @Test
    public void deleteMinTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }
        st.deleteMin();
        Iterable<String> keyList = st.keys();
        for(String key:keyList){
            System.out.print(key+" ");
        }
    }
}
