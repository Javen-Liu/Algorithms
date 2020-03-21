package graph.shortestpath;

import DataStructure.ImplByLinkedList.StackWithIterator;
import graph.directedgraph.Digraph;
import graph.directedgraph.DirectedCycle;
import graph.directedgraph.Topological;

import java.text.NumberFormat;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/8 13:58
 * @github https://github.com/Javen-Liu
 * 无环加权有向图的最短路径算法，先将加权有向图进行拓扑
 * 排序，然后按照拓扑排序后的顺序进行顶点的放松（relax）
 * 其时间复杂度为E+V（E为加权边的数量，V为顶点的数量）
 */
public class AcyclicShortestPath {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private int source;

    public AcyclicShortestPath(EdgeWeightedDigraph weightedDigraph){
        int size = weightedDigraph.numberOfVerities();
        distTo = new double[size];
        edgeTo = new DirectedEdge[size];
        Digraph digraph = weightedDigraph.castToDigraph();
        if(new DirectedCycle(digraph).hasCycle()){
            throw new RuntimeException("提供的加权有向图包含有环，无法进行该算法寻找最短路径");
        }
        StackWithIterator<Integer> order = (StackWithIterator<Integer>) new Topological(digraph).order();
        for (int i = 0; i < size; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        source = order.pop();
        distTo[source] = 0.0;
        order.push(source);
        for(int v:order){
            relax(weightedDigraph,v);
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v){
        for(DirectedEdge e:digraph.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public int digraphSource(){
        return source;
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] <= Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        StackWithIterator<DirectedEdge> edgeList = new StackWithIterator<>();
        for( ;v != source;v = edgeTo[v].from()){
            edgeList.push(edgeTo[v]);
        }
        return edgeList;
    }

    @Override
    public String toString(){
        StringBuilder content = new StringBuilder();
        StackWithIterator<DirectedEdge> stack;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        for (int i = 0; i < distTo.length; i++) {
            content.append(source).append(" to ").append(i).append("(").append(nf.format(distTo[i])).append("): ");
            stack = (StackWithIterator<DirectedEdge>) pathTo(i);
            for(DirectedEdge e:stack){
                content.append(e).append(" ");
            }
            content.append("\n");
        }
        return content.toString();
    }
}
