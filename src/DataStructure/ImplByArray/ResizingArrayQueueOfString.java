package DataStructure.ImplByArray;

import java.util.Iterator;

/**
 * @author 刘建雯
 * 通过数组实现可变长的队列
 */
public class ResizingArrayQueueOfString implements Iterable<String>{
    private int size;
    private int first = -1;
    private int last = -1;
    private String[] contents;

    public ResizingArrayQueueOfString(int size){
        this.size = size;
        contents = new String[size];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return first == -1;
    }

    public void resize(){
        String[] newContent;
        if((last-first+1) < size){
            newContent = new String[size];
            System.arraycopy(contents,first,newContent,0,last-first+1);
            contents = newContent;
            last = last - first;
            first = 0;
        }else {
            newContent = new String[size*2];
            System.arraycopy(contents,0,newContent,0,size);
            size = size*2;
            contents = newContent;
        }
    }

    public void enqueue(String str){
        if(first == -1){
            contents[0] = str;
            first = 0;
            last = first;
        }else{
            if(last+1 == size){
                this.resize();
            }
            contents[++last] = str;
        }
    }

    public String dequeue(){
        String content;
        if(first == last && first != -1){
            content = contents[first];
            first = -1;
            last = first;
        }else{
            content = contents[first++];
        }
        return content;
    }

    @Override
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<String>{
        private int start = first;
        private int end = last;

        @Override
        public boolean hasNext() {
            return start <= end;
        }

        @Override
        public String next() {
            return contents[start++];
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfString queue = new ResizingArrayQueueOfString(10);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(""+i);
        }

        //测试迭代器
        for(String value:queue){
            System.out.println(value);
        }

        //测试出列
        for (int i = 0; i < 9; i++) {
            String content = queue.dequeue();
            System.out.println(content);
        }

        //测试自动缩减size
        queue.enqueue(""+11);

        //测试扩容
        for (int i = 0; i < 11; i++) {
            queue.enqueue(""+i);
        }
    }
}
