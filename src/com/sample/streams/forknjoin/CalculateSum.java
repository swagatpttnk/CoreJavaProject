package com.sample.streams.forknjoin;

import java.time.Duration;
import java.time.Instant;
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
        Instant start = Instant.now();
        CalculateSum cs=new CalculateSum();
        System.out.println("Result:"+cs.forkJoinSum(40));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.println("Time elapsed(in millis):"+ timeElapsed );
    }
}
