package com.sample.ctrl.structure;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class shows difference between foreach-loop-vs-stream-foreach-vs-parallel-stream-foreach
 * Ref: https://www.geeksforgeeks.org/foreach-loop-vs-stream-foreach-vs-parallel-stream-foreach/?ref=rp
 * **/

public class ForLoopExamples {

    public static void main(String[] args)
    {
        ForLoopExamples forLoopExamples=new ForLoopExamples();
        forLoopExamples.demoEnhancedForLoop();
        forLoopExamples.demoSreamsDotForEachMethod();
        forLoopExamples.demoParallelStreamDotForEachMethod();
    }
    private void demoEnhancedForLoop(){
        String[] arr = { "1", "2", "3" };
        int count = 0;

        String[] arr1 = { "Geeks", "For", "Geeks" };
        //1.Lambda operator is not used: foreach loop in Java doesn’t use any lambda operations
        // and thus operations can be applied on any value outside of the list that we are using
        // for iteration in the foreach loop.Ex: "int count" here .
        //2.It can be used for all collections and arrays: It can be used to iterate all collections and arrays in Java.
        for (String str : arr) {
            System.out.println(arr1[count++]);
        }
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.addAll(Arrays.asList("Tom","Dice","Harry"));
        for (String str : arrayList) {
            System.out.println(arr1[count++]);
        }
        //3.The return statements work within the loop: The function can return the value at any point of time within the loop.
        // For example, if we want to print only the first 2 values of any collection or array and then we want to return any value,
        // it can be done in foreach loop in Java. The code below is for printing the 2nd element of an array.
        String secelt = frechlop(arr1);
        System.out.println(secelt); //<-- will return only 2 ( possible since variable outside the loop is accessible).This is
        // not possible in forEach() method as variable out side the loop is not accessible.

        //4.Not  ThreadSafe
    }
    public static String frechlop(String[] geek)
    {
        int count = 0;
        for (String var : geek) {
            if (count == 1)
                return var;
            count++;
        }
        return "";
    }
    private void demoSreamsDotForEachMethod(){
        String[] arr = { "1", "2", "3" };
        int count = 0;
        String[] arr1 = { "Geeks", "For", "Geeks" };
        //1.Lambda operator is used and thus operations cannot be applied on any value outside of the loop.
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.addAll(Arrays.asList("Tom","Dice","Harry"));
        arrayList.stream().forEach (s->{
            System.out.println(s);
            // count++; <---- uncomment this line willl result in ERROR as this variable is outside he Loop.
        });

        //2.It can be used for all collections but not arrays.
        //arr.forEach(s->{System.out.println(s);});

        //3.Return from within loop is not possible as we cant use any outside variable.
        //4.Not  ThreadSafe

    }
    private void demoParallelStreamDotForEachMethod(){
        String[] arr = { "1", "2", "3" };
        int count = 0;
        String[] arr1 = { "Geeks", "For", "Geeks" };
        //1.Lambda operator is used and thus operations cannot be applied on any value outside of the loop.
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.addAll(Arrays.asList("Tom","Dice","Harry"));
        arrayList.parallelStream().forEach (s->{
            System.out.println(s);
            // count++; <---- uncomment this line willl result in ERROR as this variable is outside he Loop.
        });

        //2.It can be used for all collections but not arrays.
        //arr.forEach(s->{System.out.println(s);});

        //3.Return from within loop is not possible as we cant use any outside variable.
        //4.Not  ThreadSafe

    }

}
