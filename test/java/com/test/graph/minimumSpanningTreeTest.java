package com.test.graph;

import graph.minimumspanningtree.EdgeWeightedGraph;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 刘建雯
 * 测试加权无向图
 */
public class minimumSpanningTreeTest {
    EdgeWeightedGraph graph;

    @Before
    public void init(){
        graph = new EdgeWeightedGraph("D:\\Documents\\IDEAWorkSpace\\Algorithms\\src\\graph\\minimumspanningtree\\routes.txt"," ");
    }

    @Test
    public void MSTConstructorTest(){
        System.out.println(graph);
    }
}
