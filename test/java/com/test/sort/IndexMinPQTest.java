package com.test.sort;

import Sort.PriorityQueue.IndexMinPriorityQueue;
import org.junit.Before;
import org.junit.Test;
import utils.Judge;

/**
 * @author 刘建雯
 * 测试索引最小优先队列
 */
public class IndexMinPQTest {
    public static final int[] index = {1,4,3,6,5,9,8,2,7};
    public static final double[] keys = {0.36,0.98,0.46,0.15,0.84,0.26,0.58,0.79,0.61};
    IndexMinPriorityQueue<Double> minPQ;

    @Before
    public void init(){
        minPQ = new IndexMinPriorityQueue<>(index.length);
        for (int i = 0; i < index.length; i++) {
            minPQ.insert(index[i],keys[i]);
        }
    }

    @Test
    public void ConstructorTest(){
        for (int i = 0; i < 9; i++) {
            System.out.print(minPQ.minIndex()+":"+minPQ.min()+" ");
            minPQ.delMin();
        }
    }

    @Test
    public void isEmptyTest(){
        System.out.println(Judge.judge(minPQ.isEmpty(),Judge.FALSE));
    }

    @Test
    public void containsTest(){
        System.out.println(Judge.judge(minPQ.contains(4),Judge.TRUE));
        System.out.println(Judge.judge(minPQ.contains(11),Judge.FALSE));
    }

    @Test
    public void changeTest(){
        minPQ.change(2,0.63);
        System.out.println("after changing\n"+minPQ.toString());
    }

    @Test
    public void deleteTest(){
        minPQ.delete(4);
        System.out.println("after deleting\n"+minPQ.toString());
    }
}
