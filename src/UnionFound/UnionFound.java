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
    private int count;

    private UnionFound(int num){
        count = num;
        id = new int[num];
        for (int i = 0; i < num; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q){
        /**
         * fast-union模型中的union()建立连通方法
         */
        id[q] = p;
        count--;

        /**
         * fast-find模型中的union()建立连通方法
         */
//        int idP = find(p);
//        int idQ = find(q);
//        if(connected(p,q)){
//            return;
//        }
//        for (int i = 0; i < id.length; i++) {
//            if(id[i] == idQ){
//                id[i] = idP;
//            }
//        }
//        count--;
    }

    @Override
    public int find(int p) {
        /**
         * fast-union模型中的find()查找方法
         */
        while(id[p] != p){
            p = id[p];
        }
        return p;

        /**
         * fast-find模型中的find()查找方法
         */
//        return id[p];
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
