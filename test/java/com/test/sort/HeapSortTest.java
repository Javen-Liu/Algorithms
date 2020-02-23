package com.test.sort;

import Sort.Data;
import Sort.PriorityQueue.HeapSort;
import org.junit.Test;

import java.util.Random;

public class HeapSortTest {
    @Test
    public void Test(){
        //创建测试Data数组
        int size = 200;
        Random random = new Random();
        Data[] data = new Data[size];
        for (int i = 0; i < size; i++) {
            data[i] = new Data(random.nextInt(size*2));
        }
        //将测试数组打印
        for(Data value:data){
            System.out.print(value+" ");
        }
        System.out.println();
        //进行排序
        HeapSort.sort(data);
        //将排序好的数组进行打印
        for(Data value:data){
            System.out.print(value+" ");
        }
        System.out.println();
    }
}
