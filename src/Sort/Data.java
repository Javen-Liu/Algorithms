package Sort;

/**
 * @author 刘建雯
 */
public class Data implements Comparable<Data> {
    private Integer value;

    public Data(Integer value){
        this.value = value;
    }

    @Override
    public int compareTo(Data that) {
        return this.value.compareTo(that.value);
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }
}
