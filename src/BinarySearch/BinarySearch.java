package BinarySearch;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author 刘建雯
 * 二分查找
 */

public class BinarySearch {
    public static int rank(int key,int[] num){
        int low = 0;
        int high = num.length-1;
        while(low <= high){
            int mid = (high-low)/2+low;
            if(key < num[mid]){
                high = mid-1;
            }else if(key > num[mid]){
                low = mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int size = 60000;
        int[] nums = new int[size];
        int[] nums2;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(150000);
        }
        System.out.println(Arrays.toString(nums));
        nums2 = nums;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input key:");
        int key = scanner.nextInt();
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            if(key == nums2[i]){
                System.out.println(i+1);
            }
        }
        long time = System.currentTimeMillis();
        System.out.println("暴力查找一共用时:"+(time-time1)*1000000);
        long time2 = System.currentTimeMillis();
        int serial = BinarySearch.rank(key,nums);
        System.out.println(serial+1);
        long time3 = System.currentTimeMillis();
        System.out.println("二分法一共用时:"+(time3-time2)*1000000);

    }
}
