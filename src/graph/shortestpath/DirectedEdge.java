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
    private double weigth;

    public DirectedEdge(int from, int to, double weigth) {
        this.from = from;
        this.to = to;
        this.weigth = weigth;
    }

    public int from(){
        return from;
    }

    public int to(){
        return to;
    }

    public double weigth(){
        return weigth;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        return from+"->"+to+" "+nf.format(weigth);
    }
}
