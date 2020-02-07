package Sort.merge;

import Sort.Data;
import utils.Timer;

import java.util.Random;

/**
 * @author 刘建雯 
 * 自底向上的归并排序
 */
public class MergeByDownTop {
    /**
     * 数组做归并时的辅助数组
     */
    private static Data[] aux;
    
    public static void sort(Data[] a){
        int len = a.length;
        aux = new Data[len];
        for(int size = 1 ;size < len; size*=2){
            for(int i = 0;i < len-size;i += size*2){
                merge(a,i,i + size - 1,Math.min(i+2*size-1,len-1));
            }
        }
    }

    /**
     * 归并方法：将由low、mid以及high所分割为两部分的数组进行归并
     *          先将两部分的元素从左边拿出第一个，进行比较大小
     *          小的元素写入原来的a数组中，并将其索引start+1
     *          大的元素不做任何操作
     *          如果数组左边部分的元素全部取完，则将右边部分的元素依次放入a数组中
     *          如果数组右边部分的元素被取完，同理
     * @param a     待归并的数组
     * @param low   数组左边部分的起始索引
     * @param mid   数组左边部分的终止索引
     * @param high  数组右边部分的终止索引（数组右边部分的起始索引为mid+1）
     */
    private static void merge(Data[] a,int low,int mid,int high){
        int start = low, end = mid+1;
        System.arraycopy(a,low,aux,low,high-low+1);
        for (int i = low; i <= high; i++) {
            if(start > mid){
                a[i] = aux[end++];
            }else if(end > high){
                a[i] = aux[start++];
            }else {
                a[i] = less(aux[start],a[end]) ? aux[start++] : aux[end++];
            }
        }
    }

    /**
     * 比较两个数据大小的工具方法
     * @param q 第一个元素
     * @param p 第二个元素
     * @return  如果q小于p，则返回false
     *          否则返回true
     */
    private static boolean less(Data q,Data p){
        return q.compareTo(p) <= 0;
    }

    /**
     * 测试自顶向下的归并排序
     * @param args 参数
     */
    public static void main(String[] args) {
        int size = 10000, ranSize = size * 2;
        Data[] data = new Data[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = new Data(random.nextInt(ranSize));
        }
        for(Data value:data){
            System.out.print(value+" ");
        }
        System.out.println();
        Timer.startTimer();
        sort(data);
        Timer.stopTimer();
        for(Data value:data){
            System.out.print(value+" ");
        }
        System.out.println("\n本次归并排序所耗时间:"+ Timer.eclipseTime());
    }
}
