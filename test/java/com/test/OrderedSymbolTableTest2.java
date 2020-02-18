package com.test;

import org.junit.Test;
import search.symboltable.impl.OrderedSymbolTableImplByNodeWithBinarySearch;
import java.util.Arrays;

/**
 * @author 刘建雯
 * 测试由数组实现的有序字符表
 * 测试数据结构类：OrderedSymbolTableImplByNodeWithBinarySearch
 */
public class OrderedSymbolTableTest2 {
    OrderedSymbolTableImplByNodeWithBinarySearch<String,Integer> st = new OrderedSymbolTableImplByNodeWithBinarySearch<>();
    static final String[] keys = new String[]{"g","e","r","x","v","h","k","t","u","r","p","c","z","m","t","y","b","l"};
    static final int[] vals = new int[]{12,84,34,4,17,48,36,96,455,78,16,43,49,79,13,152,6,73};

    @Test
    public void putTest(){
        for (int i = 0; i < 17; i++) {
            st.put(keys[i],vals[i]);
        }
    }

    @Test
    //测试keys()方法（无参，获取所有键key）
    public void IterableKeysTest1(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }

        Iterable<String> keyList = st.keys();
        String target = Arrays.toString(keys);
        for(String key: keyList){
            if(!target.contains(key)){
                System.out.println("fail");
                return;
            }
        }
        System.out.println("pass");
    }

    @Test
    //测试keys()方法（有参，获取指定范围内的键key）
    public void IterableKeysTest2(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }

        Iterable<String> keys = st.keys("d","x");
        for(String key: keys){
            System.out.print(key+" ");
        }
    }

    @Test
    //测试用delete()删除一个元素
    public void deleteTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }

        st.delete("x");

        Iterable<String> keys = st.keys();
        for(String key: keys){
            System.out.print(key+":"+st.get(key)+" ");
        }
    }

    @Test
    //测试用delete()删除所有元素
    public void deleteAllTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }
        Iterable<String> keys = st.keys();
        for(String key: keys){
            st.delete(key);
        }
        System.out.println(st.size());
    }

    @Test
    public void deleteMaxOrMinTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }

        st.deleteMax();
        st.deleteMin();
        Iterable<String> keys2 = st.keys();
        for(String key: keys2){
            System.out.print(key+" ");
        }
    }

    @Test
    public void containsTest(){
        for (int i = 0; i < keys.length; i++) {
            st.put(keys[i],vals[i]);
        }
        System.out.println(st.contains("e") ? "pass" : "fail");
        System.out.println(!st.contains("d") ? "pass" : "fail");
    }
}
