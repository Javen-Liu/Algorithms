package graph.minimumspanningtree;

import DataStructure.ImplByLinkedList.QueueWithIterator;
import Sort.PriorityQueue.impl.MinPriorityQueueImpl;
import UnionFound.UnionFound;

/**
 * @author 刘建雯
 * Kruskal算法，要使用到UnionFound连通算法
 */
public class Kruskal {
    private QueueWithIterator<Edge> mst;

    public Kruskal(EdgeWeightedGraph graph){
        mst = new QueueWithIterator<>();
        MinPriorityQueueImpl<Edge> queue = new MinPriorityQueueImpl<>(graph.numberOfVerities());
        for(Edge edge:graph.edges()){
            queue.insert(edge);
        }
        UnionFound uf = new UnionFound(graph.numberOfVerities());
        while(!queue.isEmpty() && mst.size()<graph.numberOfVerities()-1){
            Edge edge = queue.delMin();
            int v = edge.either(), w = edge.other(v);
            if(uf.connected(v,w)){
                continue;
            }
            uf.union(v,w);
            mst.enqueue(edge);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double weight = 0;
        for(Edge edge:mst){
            weight += edge.weight();
        }
        return weight;
    }
}
