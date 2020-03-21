package graph.shortestpath;

import DataStructure.ImplByLinkedList.StackWithIterator;
import Sort.PriorityQueue.IndexMinPriorityQueue;

/**
 * @author 刘建雯
 * Dijkstra算法，计算加权有向图中所有顶点到指定顶点的最短路径
 */
@SuppressWarnings("unused")
public class DijkstraShortestPath {
    private IndexMinPriorityQueue<Double> queue;
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public DijkstraShortestPath(EdgeWeightedDigraph digraph, int source){
        int size = digraph.numberOfVerities();
        queue = new IndexMinPriorityQueue<>(size);
        distTo = new double[size];
        edgeTo = new DirectedEdge[size];
        for (int i = 0; i < size; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        queue.insert(source,0.0);
        while(!queue.isEmpty()){
            relax(digraph,queue.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v){
        for(DirectedEdge e:digraph.adj(v)){
            int w = e.to();
            if(distTo[v]+e.weight() < distTo[w]){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(queue.contains(w)){
                    queue.change(w,distTo[w]);
                }else{
                    queue.insert(w,distTo[w]);
                }
            }
        }
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
        for( ;edgeTo[v] != null;v = edgeTo[v].from()){
            edgeList.push(edgeTo[v]);
        }
        return edgeList;
    }
}
