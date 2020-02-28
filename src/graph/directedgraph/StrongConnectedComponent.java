package graph.directedgraph;

import java.util.ArrayList;

/**
 * @author 刘建雯
 * 强连通分量检测
 */
@SuppressWarnings("unused")
public class StrongConnectedComponent {
    private boolean[] marked;
    int[] id;
    int count;

    public StrongConnectedComponent(Digraph digraph){
        marked = new boolean[digraph.numberOfVertices()];
        id = new int[digraph.numberOfVertices()];
        DepthFirstOrder order = new DepthFirstOrder(digraph.reverse());
        for(int v:order.reversePost()){
            if(!marked[v]){
                dfs(digraph,v);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph, int v){
        marked[v] = true;
        id[v] = count;
        for(int w:digraph.adj(v)){
            if(!marked[w]){
                dfs(digraph,w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int componentCount(){
        return count;
    }

    public Iterable<String> getAllComponent(){
        StringBuilder[] container = new StringBuilder[count];
        for (int i = 0; i < count; i++) {
            container[i] = new StringBuilder();
        }
        for (int i = 0; i < marked.length; i++) {
            container[id[i]].append(i).append(" ");
        }
        ArrayList<String> componentList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            componentList.add(container[i].toString().trim());
        }
        return componentList;
    }
}
