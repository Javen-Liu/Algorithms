package Sort.PriorityQueue;

import java.lang.reflect.Array;

/**
 * @author 刘建雯
 * 堆排序
 */
@SuppressWarnings({"unused","unchecked","rawtypes"})
public class HeapSort {
    /**
     * 堆排序，分为两步：1.堆的构造
     *                 2.下沉排序
     * @param a 参数
     */
    public static void sort(Comparable[] a){
        int size = a.length;
        Comparable[] newArray = (Comparable[]) Array.newInstance(a.getClass().getComponentType(),size+1);
        System.arraycopy(a,0,newArray,1,size);
        //堆的构造
        for (int i = size /2; i >= 1; i--) {
            sink(newArray,i, size);
        }
        //下沉排序
        while(size > 1){
            exch(newArray,1, size--);
            sink(newArray,1, size);
        }
        System.arraycopy(newArray,1,a,0,a.length);
    }

    /**
     * 辅助工具方法：交换数组元素位置
     * @param a 进行交换操作的数组
     * @param i 待交换的第一个元素索引
     * @param j 待交换的第二个元素索引
     */
    private static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 辅助工具方法：比较指定数组中指定索引元素的大小
     * @param a 待操作的数组
     * @param i 待比较的第一个元素索引
     * @param j 待比较的第一个元素索引
     * @return  如果第一个元素小于第二个元素，则返回true
     */
    private static boolean less(Comparable[] a,int i, int j){
        return a[i].compareTo(a[j]) < 0;
    }

    /**
     * 辅助工具方法：下沉
     * @param a 待操作数组
     * @param k 待操作元素索引
     */
    private static void sink(Comparable[] a,int k,int size){
        while(k*2 <= size){
            int j = k*2;
            if(j < size && less(a,j,j+1)){
                j++;
            }
            if(!less(a,k,j)){
                break;
            }
            exch(a,j,k);
            k = j;
        }
    }
}
