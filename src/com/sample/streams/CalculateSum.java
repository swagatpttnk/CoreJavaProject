package com.sample.streams;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class CalculateSum {
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
    public static void main(String[] args){
        CalculateSum cs=new CalculateSum();
        System.out.println("Result:"+cs.forkJoinSum(7));
    }
}
