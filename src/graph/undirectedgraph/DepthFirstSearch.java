package graph.undirectedgraph;

/**
 * @author 刘建雯
 * 图的深度优先搜索
 */
@SuppressWarnings("unused")
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int source){
        marked = new boolean[graph.numberOfVertices()];
        dfs(graph,source);
    }

    private void dfs(Graph graph, int v){
        marked[v] = true;
        count++;
        for(int w:graph.adj(v)){
            if(!marked(w)) {
                dfs(graph, w);
            }
        }
    }

    public int count(){
        return count;
    }

    public boolean marked(int v){
        return marked[v];
    }
}
