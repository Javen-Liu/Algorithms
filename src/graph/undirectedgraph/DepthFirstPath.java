package graph.undirectedgraph;

import java.util.Stack;

/**
 * @author 刘建雯
 * 图的广度优先搜索，并找出源点到目标顶点的路径
 */
@SuppressWarnings("unused")
public class DepthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int source;

    public DepthFirstPath(Graph graph,int s){
        marked = new boolean[graph.numberOfVertices()];
        edgeTo = new int[graph.numberOfVertices()];
        this.source = s;
        dfp(graph,s);
    }

    private void dfp(Graph graph, int s){
        marked[s] = true;
        for(int w:graph.adj(s)){
            if(!marked[w]){
                marked[w] = true;
                edgeTo[w] = s;
                dfp(graph,w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        Stack<Integer> stack = new Stack<>();
        while(v != source){
            stack.push(v);
            v = edgeTo[v];
        }
        stack.push(source);
        return stack;
    }
}
