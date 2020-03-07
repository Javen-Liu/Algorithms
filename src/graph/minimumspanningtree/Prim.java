package graph.minimumspanningtree;

import DataStructure.ImplByLinkedList.QueueWithIterator;
import Sort.PriorityQueue.IndexMinPriorityQueue;

/**
 * @author 刘建雯
 * 及时删除的Prim算法
 */
public class Prim {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPriorityQueue<Double> queue;
    private int veritiesNum;

    public Prim(EdgeWeightedGraph graph){
        veritiesNum = graph.numberOfVerities();
        edgeTo = new Edge[veritiesNum];
        distTo = new double[veritiesNum];
        marked = new boolean[veritiesNum];
        queue = new IndexMinPriorityQueue<>(veritiesNum);
        for (int i = 0; i < veritiesNum; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        queue.insert(0,0.0);
        while(!queue.isEmpty()){
            visit(graph,queue.delMin());
        }
    }

    private void visit(EdgeWeightedGraph graph, int v){
        marked[v] = true;
        for(Edge edge:graph.adj(v)){
            int w = edge.other(v);
            if(marked[w]){
                continue;
            }
            if(edge.weight() < distTo[w]){
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if (queue.contains(w)){
                    queue.change(w,distTo[w]);
                }else {
                    queue.insert(w,distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges(){
        QueueWithIterator<Edge> edgeQueue = new QueueWithIterator<>();
        for (int i = 0; i < veritiesNum; i++) {
            edgeQueue.enqueue(edgeTo[i]);
        }
        return edgeQueue;
    }

    public double weight(){
        double sum = 0;
        for (int i = 1; i < veritiesNum; i++) {
            sum += distTo[i];
        }
        return sum;
    }
}
