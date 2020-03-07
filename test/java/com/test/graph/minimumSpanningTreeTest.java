package com.test.graph;

import DataStructure.ImplByLinkedList.QueueWithIterator;
import graph.minimumspanningtree.*;
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

    @Test
    public void lazyPrimTest(){
        LazyPrim lp = new LazyPrim(graph);
        QueueWithIterator<Edge> queue = (QueueWithIterator<Edge>) lp.edges();
        for(Edge e:queue){
            System.out.println(e);
        }
        System.out.println(lp.weight());
    }

    @Test
    public void primTest(){
        Prim p = new Prim(graph);
        QueueWithIterator<Edge> queue = (QueueWithIterator<Edge>) p.edges();
        queue.dequeque();
        for(Edge e:queue){
            System.out.println(e);
        }
        System.out.println(p.weight());
    }

    @Test
    public void kruskalTest(){
        Kruskal kruskalGraph = new Kruskal(graph);
        QueueWithIterator<Edge> queue = (QueueWithIterator<Edge>) kruskalGraph.edges();
        for (Edge e : queue) {
            System.out.println(e);
        }
        System.out.println(kruskalGraph.weight());
    }
}
