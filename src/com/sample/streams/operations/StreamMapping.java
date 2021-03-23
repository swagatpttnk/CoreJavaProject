package com.sample.streams.operations;

import com.sample.collections.Dish;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamMapping {
    public static void main(String args[]){
        StreamMapping sm= new StreamMapping();
        //sm.mapCollection();
        sm.mapHashMap();
        //sm.demoFlatMapping1();
        //sm.demoFlatMapping2();
    }
    public List<Dish> populateDish(){
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("Turkey", false, 350, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        return menu;
    }
    //map-//Filter all those which is a MEAT type  & return the names only
    void mapCollection(){
        List<Dish> menu=populateDish();

        List<String> meatList = menu.stream()
                .filter(s -> Dish.Type.MEAT.equals( s.getType()))
                .map(Dish::getName) //<---- this is where  Dish d converts to String name
                .collect(Collectors.toList());
        System.out.println("--map1:meatList--");
        meatList.forEach(System.out::println);
    }
    void mapHashMap(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "linode.com");
        map.put(2, "heroku.com");

        String result = "";
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if("something".equals(entry.getValue())){
                result = entry.getValue();
            }
        }
        System.out.println("--mapHashMap:result--:"+result);
        //Map -> Stream -> Filter -> String
        String filteredResult = map.entrySet().stream()
                .filter(x -> "something".equals(x.getValue()))
                .map(x->x.getValue())
                .collect(Collectors.joining());
        System.out.println("--mapHashMap:filteredResult--:"+filteredResult);

        //Map -> Stream -> Filter -> MAP
        Map<Integer, String> collectedResult = map.entrySet().stream()
                .filter(x -> x.getKey() == 2)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        System.out.println("--mapHashMap:collectedResult--:"+collectedResult);
        // or like this
        Map<Integer, String> collectedResult2 = map.entrySet().stream()
                .filter(x -> x.getKey() == 3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("--mapHashMap:collectedResult2--:"+collectedResult2);
    }
    //Flatmapping
    public void demoFlatMapping1(){
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        Stream<String[]> tempstream = Arrays.stream(data);// Stream of String[].
        //tempstream.forEach(System.out::println); // do not iterate here else it will throw IlegalStateException
        // when we iterate it later for mapping

        //Stream<String> stream = tempstream.flatMap(s -> Arrays.stream(s));
        Stream<String> stream = tempstream.flatMap(Arrays::stream); // (Arrays::stream) is same as lambda (s -> Arrays.stream(s))
        //Arrays::stream --> converted each item of tempstream i.e each String[] --> Stream<String> .
        //flatMap converts Streams<Stream<String>> --> Stream<String> i.e a single stream.

        System.out.println("-- demoFlatMapping:stream---");
        stream.forEach(System.out::println);
    }
    public void demoFlatMapping2(){
        // Example from Java in  Action - Manning publication
        String[] arrayOfWords = {"Goodbye", "World"};
        //ASK here is : given an array of words, return a concatenated word with removed duplicate letters
        //input:{"Goodbye", "World"} , output: {"GodbyeWrld"}
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        List<String> wordWithDistinctLetters=streamOfwords
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        for(String s : arrayOfWords) System.out.println(s) ;
        System.out.println(wordWithDistinctLetters);
    }
}
