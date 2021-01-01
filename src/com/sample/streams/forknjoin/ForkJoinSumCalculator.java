package com.sample.streams.forknjoin;

public class ForkJoinSumCalculator
        extends java.util.concurrent.RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 5;
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    /*@Override
    protected Long compute() {
        System.out.printf("compute() called by Thread:%s  [start:%s , end:%s ] \n",Thread.currentThread().getId(),start,end);        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length/2, end);
        rightTask.fork(); // forking both side is also fine & will work but will create more threads to process.
                        //thus is recommended to use compute() recursively for one side thereby forking the other side.
        Long rightResult = rightTask.join();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }*/
    @Override
    protected Long compute() {
        System.out.printf("compute() called by Thread:%s  [start:%s , end:%s ] \n",Thread.currentThread().getId(),start,end);        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length/2, end);

        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }
    private long computeSequentially() {
        System.out.printf("computeSequentially() called by Thread:%s  [start:%s , end:%s ] \n",Thread.currentThread().getId(),start,end);
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

}