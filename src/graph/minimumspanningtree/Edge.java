package graph.minimumspanningtree;

/**
 * @author 刘建雯
 * 加权边的实现类
 */
@SuppressWarnings("unused")
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    /**
     * 构造函数
     * @param v         第一个顶点
     * @param w         第二个顶点
     * @param weight    两顶点之间边的权重
     */
    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 边的权重
     * @return  返回double类型数据，代表边的权重
     */
    double weight(){
        return weight;
    }

    /**
     * 边两端的顶点之一
     * @return  返回int类型数据，代表边两端其中一个顶点
     */
    int either(){
        return v;
    }

    /**
     * 另一个顶点
     * @param vertex 指定的顶点
     * @return       返回int类型数据，代表与指定顶点相对的另外一个顶点
     */
    int other(int vertex){
        return vertex == v ? w : v;
    }

    /**
     * 比较这条边与that索引代表的边的权重大小
     * @param that  待比较的边
     * @return      返回int类型数据，若大于，则返回+1；若小于，则返回-1；若等于，则返回0
     */
    @Override
    public int compareTo(Edge that){
        return Double.compare(this.weight, that.weight);
    }

    /**
     * 对象的字符串表示
     * @return  返回String类型数据，表示该对象的字符串表示
     */
    @Override
    public String toString(){
        return v + "-" + w + ":" + weight;
    }
}
