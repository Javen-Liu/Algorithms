package UnionFound;

/**
 * @author 刘建雯
 */
public interface IUnionFound {
    /**
     * 将p节点与q节点进行连接
     * @param p 连接的第一个节点
     * @param q 连接的第二个节点
     */
    void union(int p, int q);

    /**
     * 查找指定序号p所在分量的标识符
     * @param p 指定序号p
     * @return  返回所在分量的标识符
     */
    int find(int p);

    /**
     * 查找序号p与序号q是否属于同一连通分量
     * @param p 第一个节点
     * @param q 第二个节点
     * @return  如果属于同一连通分量，则返回true
     *          如果不属于同一连通分量，则返回false
     */
    boolean connected(int p,int q);

    /**
     * 获得当前连通分量的数量
     * @return 连通分量的数量
     */
    int count();
}
