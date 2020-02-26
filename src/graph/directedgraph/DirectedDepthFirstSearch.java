package graph.directedgraph;

/**
 * @author 刘建雯
 * 有向图的单点可达性检测，用于检查输入的源顶点是否可以到达指定的顶点
 */
@SuppressWarnings("unused")
public class DirectedDepthFirstSearch {
    private boolean[] marked;

    public DirectedDepthFirstSearch(Digraph digraphm, int s){
        marked = new boolean[digraphm.numberOfVertices()];
        dfs(digraphm,s);
    }

    public DirectedDepthFirstSearch(Digraph digraph, Iterable<Integer> sources){
        marked = new boolean[digraph.numberOfVertices()];
        for(int v:sources){
            if(!marked[v]){
                dfs(digraph,v);
            }
        }
    }

    private void dfs(Digraph digraph, int s){
        marked[s] = true;
        for(int v:digraph.adj(s)){
            if(!marked[v]){
                dfs(digraph,v);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }
}
