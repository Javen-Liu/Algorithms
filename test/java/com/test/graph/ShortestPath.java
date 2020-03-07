package com.test.graph;

import DataStructure.ImplByLinkedList.StackWithIterator;
import graph.shortestpath.DijkstraShortestPath;
import graph.shortestpath.DirectedEdge;
import graph.shortestpath.EdgeWeightedDigraph;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author 刘建雯
 * 测试加权有向图的最短路径算法
 */
public class ShortestPath {
    EdgeWeightedDigraph digraph;

    @Before
    public void init(){
        digraph = new EdgeWeightedDigraph("D:\\Documents\\IDEAWorkSpace\\Algorithms\\src\\graph\\shortestpath\\routes.txt"," ");
    }

    @Test
    public void EWDGConstructor(){
        System.out.println(digraph);
    }

    @Test
    public void DijkstraTest(){
        DijkstraShortestPath dsp = new DijkstraShortestPath(digraph,0);
        searchPathToVertex(dsp,6);
    }

    private void searchPathToVertex(DijkstraShortestPath dsp, int v){
        if(!dsp.hasPathTo(v)){
            System.out.println("不存在到"+v+"的路径");
            return;
        }
        StackWithIterator<DirectedEdge> stack = (StackWithIterator<DirectedEdge>) dsp.pathTo(v);
        StringBuilder path = new StringBuilder();
        while(!stack.isEmpty()){
            path.append(stack.pop().from()).append(" ");
        }
        path.append(v).append(" ");
        System.out.println(path.toString().trim().replace(" ","->"));
    }
}
