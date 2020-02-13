package com.test;

import org.junit.Test;
import search.symboltable.IDisorderSymbolTable;
import search.symboltable.impl.DisorderSymbolTableImpl;

import java.util.ArrayList;
import java.util.Random;

public class SymbolTableTest {
    public static final String letter = "abcdefghijklmnopqrstuvwxyz";
    public static Random random = new Random();

    @Test
    //测试无序字符表的构造、get()方法、put()方法、keys()方法
    public void DisorderSTTest(){
        IDisorderSymbolTable<String,Integer> st = new DisorderSymbolTableImpl<>();
        for (int i = 0; i<10; i++){
            st.put(String.valueOf(letter.charAt(random.nextInt(26))),random.nextInt(26));
        }
        ArrayList<String> keys = (ArrayList<String>) st.keys();
        for(String key: keys){
            System.out.print(key+":"+st.get(key)+" ");
        }
    }
}
