package com.test.graph;

import graph.directedgraph.Digraph;
import graph.directedgraph.DirectedCycle;
import graph.directedgraph.DirectedDepthFirstSearch;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 刘建雯
 * 测试有向图以及其各种操作
 */
public class directedGraphTest {
    public Digraph digraph;
    public String info = "13 22 4 2 2 3 3 2 6 0 0 1 2 0 11 12 12 9 9 10 9 11 8 9 10 12 11 4 4 3 3 5 7 8 8 7 5 4 0 5 6 4 6 9 7 6";

    @Before
    public void init(){
        digraph = new Digraph(info);
    }

    @Test
    public void digraphConstructorTest(){
        System.out.println(digraph);
    }

    @Test
    public void digraphReverseTest(){
        System.out.println(digraph.reverse());
    }

    @Test
    public void digraphAccessibilityTest(){
        DirectedDepthFirstSearch access = new DirectedDepthFirstSearch(digraph,6);
        System.out.println(access.marked(12));
        System.out.println(access.marked(8));
        System.out.println(access.marked(5));
    }

    @Test
    public void digraphCycleCheckTest(){
        DirectedCycle cycleInfo = new DirectedCycle(digraph);
        System.out.println(cycleInfo.hasCycle());
        Iterable<String> cycles = cycleInfo.cycles();
        for(String cycle:cycles){
            System.out.println(cycle);
        }
    }
}
