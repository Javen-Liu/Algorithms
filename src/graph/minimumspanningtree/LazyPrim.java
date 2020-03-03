package graph.minimumspanningtree;

import DataStructure.ImplByLinkedList.QueueWithIterator;
import Sort.PriorityQueue.impl.MinPriorityQueueImpl;

/**
 * @author 刘建雯
 * prim算法的延时实现
 */
@SuppressWarnings("unused")
public class LazyPrim {
    private boolean[] marked;
    private MinPriorityQueueImpl<Edge> pq;
    private QueueWithIterator<Edge> mst;

    /**
     * 延时删除，每次将与当前顶点相连的其他顶点加入最小优先
     * 队列pq中，但并不及时删除，而是在下一步判断该边两端的
     * 顶点是否已经访问过了，如果访问过，则表明该边已经在最
     * 小生成树队列中，删除并检查下一条边。如果不存在，那么
     * 当前这条边就是要找的边，加入最小生成树队列中。
     * @param graph 有权无向图
     */
    public LazyPrim(EdgeWeightedGraph graph){
        marked = new boolean[graph.numberOfVerities()];
        pq = new MinPriorityQueueImpl<>();
        mst = new QueueWithIterator<>();
        visit(graph,0);
        while(!pq.isEmpty()){
            Edge edge = pq.delMin();
            int v = edge.either(), w = edge.other(v);
            if(marked[v] && marked[w]){
                continue;
            }
            mst.enqueue(edge);
            if (marked[v]) {
                visit(graph, w);
            } else {
                visit(graph, v);
            }
        }
    }

    private void visit(EdgeWeightedGraph graph, int v){
        marked[v] = true;
        for(Edge w:graph.adj(v)){
            if(!marked[w.other(v)]){
                pq.insert(w);
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        int sum = 0;
        for(Edge edge:mst){
            sum += edge.weight();
        }
        return sum;
    }
}
