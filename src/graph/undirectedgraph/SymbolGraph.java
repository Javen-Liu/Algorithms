package graph.undirectedgraph;

import search.symboltable.impl.OrderedSymbolTableImplByNodeWithBinarySearch;
import java.io.*;

/**
 * @author 刘建雯
 * 符号图的实现
 */
@SuppressWarnings("unused")
public class SymbolGraph {
    private OrderedSymbolTableImplByNodeWithBinarySearch<String,Integer> st;
    private String[] keys;
    private Graph graph;

    public SymbolGraph(String filename, String delim){
        //将所有顶点信息加入到符号表中
        st = new OrderedSymbolTableImplByNodeWithBinarySearch<>();
        File file = new File(filename);
        String[] splitStr = null;
        try(FileReader fr = new FileReader(file)){
            char[] content = new char[(int) file.length()];
            int k = fr.read(content);
            splitStr = new String(content).split("\r\n");
            for(String value:splitStr){
                String[] strs = value.split(delim);
                for (int i = 0; i < 2; i++) {
                    if(!st.contains(strs[i])){
                        st.put(strs[i],st.size());
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        //将反向索引keys数组填满，其中int型数据作为索引，String类型的名称作为值
        Iterable<String> keyList = st.keys();
        keys = new String[st.size()];
        for (String key:keyList) {
            keys[st.get(key)] = key;
        }
        //创建Graph类
        graph = new Graph(st.size());
        if(splitStr == null){
            throw new RuntimeException("文件为空");
        }
        for(String str:splitStr){
            String[] strs = str.split(delim);
            graph.addEdge(st.get(strs[0]),st.get(strs[1]));
        }
    }

    public boolean contains(String key){
        return st.get(key) != null;
    }

    public int index(String key){
        if(!st.contains(key)){
            return -1;
        }
        return st.get(key);
    }

    public String name(int v){
        if(v > st.size() || v < 0){
            return null;
        }
        return keys[v];
    }

    public Graph graph(){
        return graph;
    }
}
