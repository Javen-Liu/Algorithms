package graph.minimumspanningtree;

import DataStructure.ImplByLinkedList.Bag;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 刘建雯
 * 加权边的无向图
 */
@SuppressWarnings({"unused","unchecked"})
public class EdgeWeightedGraph {
    private int vertexNum;
    private int edgeNum;
    private Bag<Edge>[] adj;

    /**
     * 创建一副含有指定顶点数量的空图
     * @param initialCapacity   初始化容量大小
     */
    public EdgeWeightedGraph(int initialCapacity){
        vertexNum = initialCapacity;
        edgeNum = 0;
        adj = (Bag<Edge>[]) new Bag[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(String path, String delim){
        this(new File(path),delim);
    }

    public EdgeWeightedGraph(File file, String delim){
        String content = null;
        try(FileReader fr = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];
            int i = fr.read(chars);
            content = new String(chars);
        }catch (IOException e){
            e.printStackTrace();
        }
        if(content == null){
            throw new RuntimeException("no content found in this file");
        }
        String[] info = content.split("\r\n");
        vertexNum = Integer.parseInt(info[0]);
        edgeNum = Integer.parseInt(info[1]);
        adj = (Bag<Edge>[]) new Bag[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adj[i] = new Bag<>();
        }
        String[] splits;
        for (int i = 2; i < info.length; i++) {
            splits = info[i].split(delim);
            addEdge(new Edge(Integer.parseInt(splits[0]),Integer.parseInt(splits[1]),Double.parseDouble(splits[2])));
        }
    }

    public int numberOfVerities(){
        return vertexNum;
    }

    public int numberOfEdge(){
        return edgeNum;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        edgeNum++;
    }

    public Iterable<Edge> adj(int v){
        ArrayList<Edge> edgeList = new ArrayList<>();
        for(Edge edge:adj[v]){
            edgeList.add(edge);
        }
        return edgeList;
    }

    public Iterable<Edge> edges(){
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < vertexNum; i++) {
            for(Edge edge:adj[i]){
                if(!edgeList.contains(edge)){
                    edgeList.add(edge);
                }
            }
        }
        return edgeList;
    }

    @Override
    public String toString(){
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < vertexNum; i++) {
            content.append(adj[i].toString()).append("\n");
        }
        return content.toString();
    }
}
