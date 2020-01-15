package exercise.unit1.chapter3;

import DataStructure.ImplByLinkedList.QueueWithIterator;

import java.util.Scanner;

/**
 * @author 刘建雯
 * josephus问题
 * 一共有N个人，他们围成一圈，依次开始报数，报到M的人死亡，输出死亡的顺序
 * 例：输入 7，2（即7个人，报到2的人死亡）
 *    输出 1 3 5 0 4 2 6
 */
public class exercise37 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[ please input number of people ]");
        int num = scanner.nextInt();
        System.out.println("[ please input the death number ]");
        int deathNum = scanner.nextInt();
        sequentialKill(num,deathNum);
    }

    public static void sequentialKill(int num,int deathNum){
        QueueWithIterator<Integer> queue = new QueueWithIterator<>();
        QueueWithIterator<Integer> newQueue = new QueueWithIterator<>();
        for (int i = 0; i < num; i++) {
            queue.enqueue(i);
        }
        int count = 1;
        while(!queue.isEmpty()){
            if(count == deathNum){
                System.out.print(queue.dequeque()+" ");
                count = 1;
            }else{
                newQueue.enqueue(queue.dequeque());
                count++;
            }
            if(queue.isEmpty() && !newQueue.isEmpty()){
                queue = newQueue;
            }
        }
    }
}
