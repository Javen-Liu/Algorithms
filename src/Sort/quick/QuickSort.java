package Sort.quick;

import Sort.Data;
import Sort.primary.InsertionSort;
import utils.Timer;

import java.util.*;

/**
 * @author 刘建雯
 * 快速排序
 */
public class QuickSort {
    private static final int SIZE_OF_INSERTION_SORT = 12;

    /**
     * 静态排序sort方法，直接依赖于QuickSort类，且不使用插入排序
     * @param a 待排序的数组
     */
    public static void sort(Data[] a){
        shuffle(a);
        sort(a,0,a.length-1);
    }

    /**
     * 内部排序sort方法，且不使用插入排序
     * @param a     待排序的数组
     * @param low   排序的起始索引
     * @param high  排序的终止索引
     */
    private static void sort(Data[] a, int low, int high){
        if(low >= high){
            return;
        }
        int j = partition(a,low,high);
        sort(a,low,j-1);
        sort(a,j+1,high);
    }

    /**
     * 静态排序sort方法，直接依赖于QuickSort类
     * 在需要排序的数组大小稍微小时，改为用插入排序
     * @param a 待排序的数组
     */
    public static void sortWithInsSort(Data[] a){
        shuffle(a);
        sortWithInsSort(a,0,a.length-1);
    }

    /**
     * 内部排序sort方法
     * 在需要排序的数组大小稍微小时，改为用插入排序
     * @param a     待排序的数组
     * @param low   排序的起始索引
     * @param high  排序的终止索引
     */
    private static void sortWithInsSort(Data[] a, int low, int high){
        if(low >= high){
            return;
        }
//      手动实现插入排序
//        if(high - low <= SIZE_OF_INSERTION_SORT){
//            for (int i = low + 1; i <= high; i++) {
//                for (int j = i; j > low && less(a[j],a[j-1]) ; j--) {
//                        exch(a,j,j-1);
//                }
//            }
//            return;
//        }
        if(high - low <= SIZE_OF_INSERTION_SORT){
            InsertionSort.sort(a,low,high);
        }
        int j = partition(a,low,high);
        sortWithInsSort(a,low,j-1);
        sortWithInsSort(a,j+1,high);
    }

    /**
     * 内部切分partition工具方法
     * @param a     待切分的数组
     * @param low   起始索引
     * @param high  终止索引
     * @return      返回切分元素的索引位置
     */
    private static int partition(Data[] a,int low,int high){
        int start = low,end = high+1;
        Data value = a[low];
        while(true){
            while(less(a[++start],value)){
                if(start == high){
                    break;
                }
            }
            while(less(value,a[--end])){
                if(end == low){
                    break;
                }
            }
            if(start >= end){
                break;
            }
            exch(a,start,end);
        }
        exch(a,low,end);
        return end;
    }

    /**
     * 将传入数组中索引为i和j的两个元素位置进行互换
     * @param a 传入的数组
     * @param i 第一个元素位置索引
     * @param j 第二个元素位置索引
     */
    private static void exch(Data[] a,int i,int j){
        Data t = a[i];
        a[i] = a[j];
        a[j] = t;
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
     * 将数组顺序打乱，并以新的Data[]数组返回
     * @param a 待打乱的数组
     */
    private static void shuffle(Data[] a){
        List<Data> list = new ArrayList<>(Arrays.asList(a));
        Collections.shuffle(list);
        System.arraycopy(list.toArray(new Data[a.length]),0,a,0,a.length);
    }

    /**
     * 测试快速排序
     * @param args 参数
     */
    public static void main(String[] args) {
        int size = 100000, ranSize = size * 2;
        Data[] data = new Data[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = new Data(random.nextInt(ranSize));
        }
        Data[] data1 = new Data[size];
        System.arraycopy(data,0,data1,0,data.length);

        utils.Timer.startTimer();
        sort(data);
        utils.Timer.stopTimer();

        System.out.println("本次快速排序所耗时间:"+ Timer.eclipseTime()+"s");
        utils.Timer.startTimer();
        sortWithInsSort(data1);
        utils.Timer.stopTimer();
        System.out.println("本次快速排序所耗时间:"+ Timer.eclipseTime()+"s");
    }
}
