package graph.undirectedgraph;

import sun.misc.Queue;
import java.util.Stack;

/**
 * @author 刘建雯
 * 图的广度优先搜索，并找出源点到目标顶点的路径
 */
@SuppressWarnings("unused")
public class BreadFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

    public BreadFirstPath(Graph graph, int s){
        marked = new boolean[graph.numberOfVertices()];
        edgeTo = new int[graph.numberOfVertices()];
        this.source = s;
        try {
            bfp(graph,s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void bfp(Graph graph, int s) throws InterruptedException {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w:graph.adj(v)){
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int w){
        return marked[w];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        while(v != source){
            stack.push(v);
            v = edgeTo[v];
        }
        stack.push(source);
        return stack;
    }
}
