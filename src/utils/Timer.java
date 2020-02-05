package utils;

public class Timer {
    private static long start = 0;
    private static long last = 0;

    public static void startTimer(){
        start = System.currentTimeMillis();
    }

    public static void stopTimer(){
        last = System.currentTimeMillis();
    }

    public static double eclipseTime(){
        return (last - start)/1000.0;
    }
}
