package graph.directedgraph;

import DataStructure.ImplByLinkedList.Bag;

/**
 * @author 刘建雯
 * 有向图的实现，使用Bag作为与其连接顶点储存容器
 */
@SuppressWarnings({"unused","unchecked"})
public class Digraph {
    private int vertexNum;
    private int edgeNum;
    private Bag<Integer>[] adj;

    public Digraph(int num){
        this.vertexNum = num;
        adj = (Bag<Integer>[]) new Bag[num];
        for (int i = 0; i < num; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Digraph(String content){
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
        }
    }

    public int numberOfVertices(){
        return vertexNum;
    }

    public int numberOfEdges(){
        return edgeNum;
    }

    public void addEdge(int v, int w){
        if(v>vertexNum || w>vertexNum){
            throw new ArrayIndexOutOfBoundsException("输入索引超出范围");
        }
        if(v<0 || w<0){
            throw new NegativeArraySizeException("输入索引小于0");
        }
        adj[v].add(w);
        edgeNum++;
    }
    
    public Iterable<Integer> adj(int v){
        if(v>vertexNum || v<0){
            throw new ArrayIndexOutOfBoundsException("输入索引超出范围");
        }
        return adj[v];
    }
    
    public Digraph reverse(){
        Digraph reverse = new Digraph(numberOfVertices());
        for (int v = 0; v < numberOfVertices(); v++) {
            for(int w:adj[v]){
                reverse.addEdge(w,v);
            }
        }
        return reverse;
    }
    
    @Override
    public String toString(){
        StringBuilder digraphInfo = new StringBuilder();
        for (int v = 0; v < vertexNum; v++) {
            digraphInfo.append(v).append(":");
            if(!adj[v].isEmpty()){
                for(int w:adj[v]){
                    digraphInfo.append(w).append(" ");
                }
            }else {
                digraphInfo.append("none");
            }
            digraphInfo.append("\n");
        }
        return digraphInfo.toString();
    }
}
