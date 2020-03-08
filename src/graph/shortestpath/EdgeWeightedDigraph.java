package graph.shortestpath;

import DataStructure.ImplByLinkedList.Bag;
import graph.directedgraph.Digraph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘建雯
 * 加权有向图
 */
@SuppressWarnings({"unused","unchecked"})
public class EdgeWeightedDigraph {
    private int vertexNum;
    private int edgeNum;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int initialCapacity){
        this.vertexNum = initialCapacity;
        this.edgeNum = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[initialCapacity];
        for (int i = 0; i < vertexNum; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(String path, String delim){
        this(new File(path), delim);
    }

    public EdgeWeightedDigraph(File file, String delim){
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
        adj = (Bag<DirectedEdge>[]) new Bag[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            adj[i] = new Bag<>();
        }
        String[] splits;
        for (int i = 2; i < info.length; i++) {
            splits = info[i].split(delim);
            addEdge(new DirectedEdge(Integer.parseInt(splits[0]),Integer.parseInt(splits[1]),Double.parseDouble(splits[2])));
        }
    }

    public int numberOfVerities(){
        return vertexNum;
    }

    public int numberOfEdges(){
        return edgeNum;
    }

    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        edgeNum++;
    }

    public Iterable<DirectedEdge> adj(int v){
        List<DirectedEdge> list = new ArrayList<>();
        for(DirectedEdge e:adj[v]){
            list.add(e);
        }
        return list;
    }

    public Iterable<DirectedEdge> edges(){
        List<DirectedEdge> list = new ArrayList<>();
        for (int i = 0; i < vertexNum; i++) {
            for(DirectedEdge e:adj[i]){
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < vertexNum; i++) {
            for(DirectedEdge e:adj[i]){
                content.append(e).append("\n");
            }
        }
        return content.toString().trim();
    }

    public Digraph castToDigraph(){
        Digraph digraph = new Digraph(vertexNum);
        for (int i = 0; i < vertexNum; i++) {
            for(DirectedEdge e:adj[i]){
                digraph.addEdge(i,e.to());
            }
        }
        return digraph;
    }
}
