package com.test.sort;

import Sort.Data;
import Sort.PriorityQueue.IMinPriorityQueue;
import Sort.PriorityQueue.impl.MinPriorityQueueImpl;
import org.junit.Test;
import java.util.Random;

public class MinPQTest {
    @Test
    //测试默认构造器
    public void MinPQTest1(){
        IMinPriorityQueue<Data> priorityQueue = new MinPriorityQueueImpl<>();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            priorityQueue.insert(new Data(random.nextInt(20)));
        }
        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.delMin()+" ");
        }
    }

    @Test
    //测试默认构造器创建后，扩容添加
    public void MinPQTest2(){
        IMinPriorityQueue<Data> priorityQueue = new MinPriorityQueueImpl<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            priorityQueue.insert(new Data(random.nextInt(40)));
        }
        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.delMin()+" ");
        }
    }

    @Test
    //测试添加Data[]数组构造器
    public void MinPQTest3(){
        Data[] data = new Data[16];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = new Data(random.nextInt(40));
        }
        IMinPriorityQueue<Data> priorityQueue = new MinPriorityQueueImpl<>(data);
        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.delMin()+" ");
        }
    }
}
