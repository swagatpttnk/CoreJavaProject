package com.sample.collections;

import com.sample.ctrl.structure.ForLoopExamples;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ExampleHashMap {
    public static void main(String[] args)
    {
        ExampleHashMap forLoopExamples=new ExampleHashMap();
        HashMap<String,String> hm=new HashMap<>();
        hm.put("x","1");
        hm.put("y","2");
        hm.put("z","4");
        System.out.println(hm.toString());
        long count = Arrays.asList("aaa","bbbs","esese","wertt","w34rty").stream()
                .filter(s -> s.length() > 3)
                .distinct()
                .limit(3)
                .count();
        System.out.println("Count:"+count);
    }

    public void testStream(){
        ExampleHashMap forLoopExamples=new ExampleHashMap();
        HashMap<String,String> hm=new HashMap<>();
        /*hm.put("x","1");
        hm.put("y","2");
        hm.put("z","4");*/
        System.out.println(hm.toString());
        long count = Arrays.asList("aaa","bbbs","esese","wertt","w34rty").stream()
                .filter(s -> s.length() > 3)
                .distinct()
                .limit(3)
                .count();
    }
}
