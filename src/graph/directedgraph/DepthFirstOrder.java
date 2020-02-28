package graph.directedgraph;

import DataStructure.ImplByLinkedList.StackWithIterator;
import sun.misc.Queue;

/**
 * @author 刘建雯
 * 有向环的基于深度优先搜索的顶点排序
 */
@SuppressWarnings("unused")
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private StackWithIterator<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new StackWithIterator<>();
        marked = new boolean[digraph.numberOfVertices()];
        for (int i = 0; i < digraph.numberOfVertices(); i++) {
            if(!marked[i]){
                dfs(digraph,i);
            }
        }
    }

    private void dfs(Digraph digraph, int v){
        marked[v] = true;
        pre.enqueue(v);
        for(int w:digraph.adj(v)){
            if(!marked[w]){
                dfs(digraph,w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Queue<Integer> pre(){
        return pre;
    }

    public Queue<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
