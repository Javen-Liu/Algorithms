package UnionFound;

import DataStructure.ImplByLinkedList.StackWithIterator;
import org.junit.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 刘建雯
 * 总结：
 *      fast-find模型：所有同一连接分量的数组值相同
 *          优点：查找几乎不耗费时间，直接简单查找数组
 *          缺点：将两个节点连接的成本随数据数量呈N2的速度增长
 *      fast-union模型：每个节点所在数组内，存入的是上一个与其连通的节点（类似指针和链表）
 *          优点：将两个节点连接几乎不耗费时间
 *          缺点：查找的成本随数据数量呈2N增长
 */
public class UnionFound implements IUnionFound{
    private int[] id;
    private int[] sz;
    private int count;

    public UnionFound(int num){
        count = num;
        id = new int[num];
        for (int i = 0; i < num; i++) {
            id[i] = i;
        }
        sz = new int[num];
        for (int i = 0; i < num; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if(i == j){
            return;
        }
        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    @Override
    public int find(int p) {
        while(id[p] != p){
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args){
        File file = new File("D:\\Documents\\IDEAWorkSpace\\Algorithms\\src\\UnionFound\\config.txt");
        UnionFound unionFound = null;
        try(FileReader fr = new FileReader(file)) {
            char[] content = new char[(int)file.length()];
            fr.read(content);
            String str = new String(content);
            String[] nums = str.split(" ");
            LinkedList<Integer> list = new LinkedList<>();
            for(String value:nums){
                list.add(Integer.parseInt(value));
            }
            unionFound = new UnionFound(list.pop());
            while(!list.isEmpty()){
                int p = list.pop();
                int q = list.pop();
                if(unionFound.connected(p,q)){
                    continue;
                }
                unionFound.union(p,q);
                System.out.println(p +" "+ q);
            }
            System.out.println(unionFound.count());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
