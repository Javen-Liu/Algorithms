package graph.undirectedgraph;

import DataStructure.ImplByLinkedList.Bag;

/**
 * @author 刘建雯
 * 图的实现，使用Bag来作为储存节点连接信息的容器
 */
@SuppressWarnings({"unused","unchecked"})
public class Graph {
    private final int vertexNum;
    private int edgeNum;
    private Bag<Integer>[] adj;

    public Graph(int num){
        this.vertexNum = num;
        this.edgeNum = 0;
        adj = (Bag<Integer>[])new Bag[num];
        for (int i = 0; i < num; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Graph(String content){
        String[] splitContent = content.trim().split(" ");
        int size = Integer.parseInt(splitContent[0]);
        this.vertexNum = size;
        this.edgeNum = Integer.parseInt(splitContent[1]);
        adj = (Bag<Integer>[])new Bag[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new Bag<>();
        }
        for (int i = 2; i < splitContent.length; i += 2) {
            adj[Integer.parseInt(splitContent[i])].add(Integer.parseInt(splitContent[i+1]));
            adj[Integer.parseInt(splitContent[i+1])].add(Integer.parseInt(splitContent[i]));
        }
    }

    public int numberOfVertices(){
        return vertexNum;
    }

    public int numberOfEdges(){
        return edgeNum;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        edgeNum++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
