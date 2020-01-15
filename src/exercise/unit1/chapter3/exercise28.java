package exercise.unit1.chapter3;
import java.util.Random;

/**
 * @author 刘建雯
 * 寻找链表中最大数据
 */
public class exercise28 {
    public static void main(String[] args) {
        Node first = new Node();
        Node last = first;
        Random random = new Random();
        first.value = random.nextInt(80);
        for (int i = 0; i < 15; i++) {
            Node node = new Node();
            node.value = random.nextInt(80);
            last.next = node;
            last = node;
        }
        Node iterator = first;
        while(iterator.next != null){
            System.out.print(iterator.value+" ");
            iterator = iterator.next;
        }
        System.out.println();
        int max = 0;
        max = findMaxValue(first,max);
        System.out.println(max);
    }

    public static int findMaxValue(Node first,int max){
        if(first.value > max){
            max = first.value;
        }
        if(first.next != null){
            max = findMaxValue(first.next,max);
        }
        return max;
    }

    private static class Node{
        public int value;
        public Node next;
    }
}
