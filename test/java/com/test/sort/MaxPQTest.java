package com.test.sort;

import Sort.Data;
import Sort.PriorityQueue.IMaxPriorityQueue;
import Sort.PriorityQueue.impl.MaxPriorityQueueImpl;
import org.junit.Test;
import java.util.Random;

public class MaxPQTest {
    @Test
    //测试默认构造器
    public void MaxPQTest1(){
        IMaxPriorityQueue<Data> priorityQueue = new MaxPriorityQueueImpl<>();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            priorityQueue.insert(new Data(random.nextInt(20)));
        }
        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.delMax()+" ");
        }
    }

    @Test
    //测试默认构造器创建后，扩容添加
    public void MaxPQTest2(){
        IMaxPriorityQueue<Data> priorityQueue = new MaxPriorityQueueImpl<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            priorityQueue.insert(new Data(random.nextInt(40)));
        }
        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.delMax()+" ");
        }
    }

    @Test
    //测试添加Data[]数组构造器
    public void MaxPQTest3(){
        Data[] data = new Data[16];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = new Data(random.nextInt(40));
        }
        IMaxPriorityQueue<Data> priorityQueue = new MaxPriorityQueueImpl<>(data);
        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.delMax()+" ");
        }
    }
}
