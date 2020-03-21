package graph.shortestpath;

import java.text.NumberFormat;

/**
 * @author 刘建雯
 * 加权有向边
 */
@SuppressWarnings("unused")
public class DirectedEdge {
    private int from;
    private int to;
    private double weight;

    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int from(){
        return from;
    }

    public int to(){
        return to;
    }

    public double weight(){
        return weight;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        return from+"->"+to+" "+nf.format(weight);
    }
}
