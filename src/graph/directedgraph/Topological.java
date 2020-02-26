package graph.directedgraph;

/**
 * @author 刘建雯
 * 拓扑排序
 */
@SuppressWarnings("unused")
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph digraph){
        DirectedCycle cycleInfo = new DirectedCycle(digraph);
        if(!cycleInfo.hasCycle()){
            order = new DepthFirstOrder(digraph).reversePost();
        }
    }

    public boolean isDAG(){
        return order != null;
    }

    public Iterable<Integer> order(){
        return order;
    }
}
