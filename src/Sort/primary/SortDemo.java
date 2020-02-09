package Sort.primary;

import Sort.Data;
import org.junit.Test;
import utils.Timer;

import java.util.Random;

public class SortDemo {
    /**
     * 测试三种排序的时间
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        Data[] datas = SortDemo.init(20000);
        Data[] datas1 = new Data[datas.length];
        Data[] datas2 = new Data[datas.length];
        Data[] datas3 = new Data[datas.length];
        for (Data value : datas) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.arraycopy(datas, 0, datas1, 0, datas.length);
        System.arraycopy(datas, 0, datas2, 0, datas.length);
        System.arraycopy(datas, 0, datas3, 0, datas.length);
        SortDemo.timeTest(datas1, "selection");
        SortDemo.timeTest(datas2, "insertion");
        SortDemo.timeTest(datas3, "shell");
        for (Data value : datas1) {
            System.out.print(value + " ");
        }
    }

    private static Data[] init(int size) {
        Data[] datas = new Data[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            datas[i] = new Data(random.nextInt(size * 2));
        }
        return datas;
    }

    private static void timeTest(Data[] datas, String type) {
        switch (type) {
            case "selection":
                Timer.startTimer();
                SelectionSort.sort(datas);
                Timer.stopTimer();
                System.out.println("选择排序所花时间为:" + Timer.eclipseTime());
                break;

            case "insertion":
                Timer.startTimer();
                InsertionSort.sort(datas);
                Timer.stopTimer();
                System.out.println("插入排序所花时间为:" + Timer.eclipseTime());
                break;

            case "shell":
                Timer.startTimer();
                ShellSort.sort(datas);
                Timer.stopTimer();
                System.out.println("希尔排序所花时间为:" + Timer.eclipseTime());
                break;
        }
    }

    @Test
    public void InsertionSortTest() {
        Data[] datas = SortDemo.init(50);
        InsertionSort.sort(datas, 0, 15);
        for (Data value : datas) {
            System.out.print(value + " ");
        }
    }

    @Test
    public void SelectionSortTest() {
        Data[] datas = SortDemo.init(50);
        SelectionSort.sort(datas, 0, 15);
        for (Data value : datas) {
            System.out.print(value + " ");
        }
    }

    @Test
    public void ShellSortTest() {
        Data[] datas = SortDemo.init(50);
        ShellSort.sort(datas, 0, 15);
        for (Data value : datas) {
            System.out.print(value + " ");
        }
    }
}