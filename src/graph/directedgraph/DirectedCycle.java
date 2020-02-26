package graph.directedgraph;

import java.util.Stack;

/**
 * @author 刘建雯
 * 查找指定有向图中是否含有有向环，如果有则保存
 */
@SuppressWarnings("unused")
public class DirectedCycle {
    private boolean[] marked;
    private boolean[] currentCalledVertices;
    private int[] edgeTo;
    private Stack<String> cycles;

    public DirectedCycle(Digraph digraph){
        int size = digraph.numberOfVertices();
        marked = new boolean[size];
        currentCalledVertices = new boolean[size];
        edgeTo = new int[size];
        cycles = new Stack<>();
        for (int i = 0; i < size; i++) {
            if(!marked[i]){
                dfs(digraph,i);
            }
        }
    }

    private void dfs(Digraph digraph, int v){
        marked[v] = true;
        currentCalledVertices[v] = true;
        for(int w:digraph.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(digraph,w);
            }else if(currentCalledVertices[w] && edgeTo[v] != w){
                addCycle(v,w);
            }
        }
        currentCalledVertices[v] = false;
    }

    private void addCycle(int v, int w){
        StringBuilder cycle = new StringBuilder();
        for (int i = v; i != w; i = edgeTo[i]) {
            cycle.append(i).append("-");
        }
        cycle.append(w);
        String cycleRoute = cycle.toString();
        cycles.push(cycleRoute);
    }

    public boolean hasCycle(){
        return !cycles.isEmpty();
    }

    public Iterable<String> cycles(){
        return cycles;
    }
}
