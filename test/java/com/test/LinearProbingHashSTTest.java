package com.test;

import org.junit.Test;
import search.symboltable.LinearProbingHashSymbolTable;

/**
 * @author 刘建雯
 * 测试线性探测散列符号表
 * 测试数据结构类：LinearProbingHashSymbolTable
 */
public class LinearProbingHashSTTest {
    LinearProbingHashSymbolTable<String,Integer> st = new LinearProbingHashSymbolTable<>();
    static final String[] keys = new String[]{"g","e","r","x","v","h","k","t","u","r","p","c","z","m","t","y","b","l"};
    static final int[] vals = new int[]{12,84,34,4,17,48,36,96,455,78,16,43,49,79,13,152,6,73};

    @Test
    public void putTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }
        System.out.println(st.toString());
    }

    @Test
    public void deleteTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }
        st.delete("r");
        System.out.println(st.toString());
    }
}
