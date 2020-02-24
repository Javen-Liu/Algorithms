package com.test.graph;

import DataStructure.ImplByLinkedList.Bag;
import graph.undirectedgraph.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Stack;

/**
 * @author 刘建雯
 * 测试无向图的各种操作
 */
public class undirectedGraphTest {
    public Graph graph;
    public String info = "13 13 0 5 4 3 0 1 9 12 6 4 5 4 0 2 11 12 9 10 0 6 7 8 9 11 5 3";

    @Before
    public void init(){
        graph = new Graph(info);
    }

    @Test
    public void graphConstructorTest(){
        for (int i = 0; i < 13; i++) {
            Bag<Integer> vertex = (Bag<Integer>) graph.adj(i);
            System.out.print(i+":"+vertex.size()+" ");
        }
    }

    @Test
    public void dfsTest(){
        DepthFirstSearch dfs = new DepthFirstSearch(graph,5);
        System.out.println(dfs.count() == 7 ? "pass" : "fail");
        //此图分为三个子图，其中7和11都不与5相连，2与5相连，以此来测试深度优先搜索是否成功
        System.out.println(!dfs.marked(7) ? "pass" : "fail");
        System.out.println(!dfs.marked(11) ? "pass" : "fail");
        System.out.println(dfs.marked(2) ? "pass" : "fail");
    }

    @Test
    public void dfpTest(){
        DepthFirstPath dfp = new DepthFirstPath(graph,0);
        //test1：测试3是否与0相连
        System.out.println(dfp.hasPathTo(3) ? "pass" : "fail");
        //test2：测试9是否不与0相连
        System.out.println(!dfp.hasPathTo(9) ? "pass" : "fail");
        //test3：测试由0到4的路径为 0-5-4
        StringBuilder path = new StringBuilder();
        Stack<Integer> stack = (Stack<Integer>) dfp.pathTo(4);
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            path.append(stack.pop());
            if(i != size - 1){
                path.append("-");
            }
        }
        System.out.println("0-5-4".equals(path.toString()) ? "pass" : "fail");
    }

    @Test
    public void bfpTest(){
        BreadFirstPath bfp = new BreadFirstPath(graph,0);
        //test1：测试3是否与0相连
        System.out.println(bfp.hasPathTo(3) ? "pass" : "fail");
        //test2：测试9是否不与0相连
        System.out.println(!bfp.hasPathTo(9) ? "pass" : "fail");
        //test3：测试由0到4的路径为 0-5-4
        StringBuilder path = new StringBuilder();
        Stack<Integer> stack = (Stack<Integer>) bfp.pathTo(4);
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            path.append(stack.pop());
            if(i != size - 1){
                path.append("-");
            }
        }
        System.out.println("0-5-4".equals(path.toString()) ? "pass" : "fail");
    }

    @Test
    public void symbolGraphTest(){
        SymbolGraph sg = new SymbolGraph("D:\\Documents\\IDEAWorkSpace\\Algorithms\\src\\graph\\undirectedgraph\\routes.txt"," ");
        System.out.println(sg.contains("HOU"));
        System.out.println(sg.contains("LKS"));
        Graph routesGraph = sg.graph();
        for (int i = 0; i < routesGraph.numberOfVertices(); i++) {
            System.out.print(i+" "+sg.name(i)+":");
            for(int v:routesGraph.adj(i)){
                System.out.print(sg.name(v)+" ");
            }
            System.out.println();
        }
        int source = sg.index("JFK"), target = sg.index("LAX");
        if(source < 0 || target < 0){
            System.out.println("索引出现错误");
            return;
        }
        BreadFirstPath bfp = new BreadFirstPath(routesGraph,source);
        Stack<Integer> stack = (Stack<Integer>) bfp.pathTo(target);
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if(sg.name(stack.peek()) != null){
                System.out.print(sg.name(stack.pop()));
                if(i < size-1){
                    System.out.print("-");
                }
            }
        }
    }
}
