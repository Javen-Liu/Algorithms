package Sort.primary;

import Sort.Data;
import utils.Timer;
import java.util.Random;

/**
 * @author 刘建雯
 * 选择排序
 */
public class SelectionSort {
    /**
     * 排序方法sort()
     * @param a 待排序的数组，且实现了Comparable接口
     */
    public static void sort(Comparable[] a){
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i+1; j < len; j++) {
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

    /**
     * 检查第一个元素q是否比第二个元素p小
     * @param q 第一个元素
     * @param p 第二个元素
     * @return  如果q比p小，则返回true
     */
    private static boolean less(Comparable q,Comparable p){
        return q.compareTo(p) < 0;
    }

    /**
     * 将传入数组中索引为i和j的两个元素位置进行互换
     * @param a 传入的数组
     * @param i 第一个元素位置索引
     * @param j 第二个元素位置索引
     */
    private static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 将传入数组中的元素进行打印
     * @param a 传入的数组
     */
    private static void show(Comparable[] a){
        for(Comparable value:a){
            System.out.print(value+" ");
        }
        System.out.println();
    }

    /**
     * 检查数组是否已经排序
     * @param a 传入数组
     * @return  如果已经按照顺序进行了排序，则返回true
     */
    public static boolean isSort(Comparable[] a){
        int len = a.length;
        for (int i = 1; i < len; i++) {
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }

    /**
     * 测试选择排序
     * @param args
     */
    public static void main(String[] args) {
        int size = 5000;
        Data[] datas = new Data[size];
        Random random = new Random();
        int len = datas.length;
        for (int i = 0; i < len; i++) {
            datas[i] = new Data(random.nextInt(size*2));
        }
        SelectionSort.show(datas);
        assert SelectionSort.isSort(datas);
        Timer.startTimer();
        SelectionSort.sort(datas);
        Timer.stopTimer();
        SelectionSort.show(datas);
        System.out.println("本次排序花费时间为:"+ String.valueOf(Timer.eclipseTime()));
    }
}
