package com.sample.streams.operations;

import com.sample.collections.Dish;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExtraction {
    public static void main(String args[]){
        StreamExtraction se= new StreamExtraction();
        //se.filter1();
        //se.distinct1();
        //se.sortCollection();
        //se.sortedStream();
        //se.slicingTakeWhileDropWhileStream();

        se.filterMap1();
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
    public Map<Integer, String> populateHosting(){
        Map<Integer, String> hosting = new HashMap<>();
        hosting.put(1, "linode.com");
        hosting.put(2, "heroku.com");
        hosting.put(3, "digitalocean.com");
        hosting.put(4, "aws.amazon.com");
        hosting.put(5, "restful.net");
        hosting.put(6, "cyber.co.in");

        return hosting;
    }
    //filter-All those which are of Type MEAT
    void filter1(){
        List<Dish> menu=populateDish();
        //Filter all those which is a MEAT type
        List<Dish> meatList = menu.stream().filter(s -> Dish.Type.MEAT.equals( s.getType())).collect(Collectors.toList());
        System.out.println("--filter1:meatList--");
        meatList.forEach(System.out::println);
    }
    //distinct-list Type of Dish available in the Menu
    void distinct1(){
        List<Dish> menu=populateDish();
        //Filter all those which is a MEAT type
        List<Dish.Type> meatTypes = menu.stream().map(s -> s.getType()).distinct().collect(Collectors.toList());
        System.out.println("--distinct1:meatTypes--");
        meatTypes.forEach(System.out::println);
    }
    //sort - Sort in increasing order of Calorie . Sorting the collection -> modifies the collection itself.
    void sortCollection(){

        List<Dish> menu=populateDish();
        System.out.println("--sortCollection:collection before sort--");
        menu.forEach(System.out::println);
        Comparator<Dish> c=Comparator.comparing((Dish d) -> d.getCalories());
        menu.sort(c);
        System.out.println("--sortCollection:collection after sort--");
        menu.forEach(System.out::println);

    }
    //sort - Sort in increasing order of Calorie . Sorting the Stream -> No impact on collection itself.
    void sortedStream(){
        List<Dish> menu=populateDish();
        System.out.println("--sortedStream:collection before sort--");
        menu.forEach(System.out::println);
        Comparator<Dish> c=Comparator.comparing((Dish d) -> d.getCalories());
        List<Dish> sortedMenu=menu.stream().sorted(c).collect(Collectors.toList());
        System.out.println("--sortedStream:collection after sort--");
        menu.forEach(System.out::println);
        System.out.println("--sortedStream:stream after sort--");
        sortedMenu.forEach(System.out::println);
    }
    //Slicing:takeWhile
    //Slicing:dropWhile
    //Slicking  - filter Dish that are of Type MEAT and < 750 calories
    void slicingTakeWhileDropWhileStream(){
        // Slicing is not available on Collections. Only on Stream
        List<Dish> menu=populateDish();
        System.out.println("--slicingTakeWhileDropWhile:collection before slicing--");
        menu.forEach(System.out::println);

        Predicate<Dish> selectionPredicate= d -> Dish.Type.MEAT.equals(d.getType());
        Predicate<Dish> rejectionPredicate= d -> d.getCalories() > 750;
        List<Dish> slicedMenu=menu.stream()
                .takeWhile(selectionPredicate)
                .dropWhile(rejectionPredicate)
                .collect(Collectors.toList());
        System.out.println("--slicingTakeWhileDropWhile:collection after sort--");
        menu.forEach(System.out::println);
        System.out.println("--slicingTakeWhileDropWhile:stream after sort--");
        slicedMenu.forEach(System.out::println);
        Comparator<Dish> sortComparator=Comparator.comparing((Dish s)-> s.getCalories());

        //Slicing:limit(x)
        List<Dish> limitedMenu=slicedMenu.stream().sorted( sortComparator).limit(2).collect(Collectors.toList());
        System.out.println("--slicingTakeWhileDropWhile:stream after limit--");
        limitedMenu.forEach(System.out::println);

        //Slicing:skip(x)
        List<Dish> skippedMenu=limitedMenu.stream().sorted( sortComparator).skip(1).collect(Collectors.toList());
        System.out.println("--slicingTakeWhileDropWhile:stream after skip--");
        skippedMenu.forEach(System.out::println);
    }
    void filterMap1(){
        String result="";
        Map<Integer,String> hosting=populateHosting();
        //Filter & collect to Map
        Map<Integer,String> collect1=hosting.entrySet().stream()
                .filter(x-> x.getValue().contains(".com"))
                .collect(Collectors.toMap(x->x.getKey(),x-> x.getValue()));
                //.collect(Collectors.toMap(Map.Entry::getKey(),Map.Entry::getValue()));   // another format
        System.out.println("--filterMap1: collect1="+collect1);

        //Filter & collect to String
        result=hosting.entrySet().stream()
                .filter(x -> {
                    if (!x.getValue().contains("amazon") && !x.getValue().contains("digital")) {
                        return true;
                    }
                    return false;
                })
                .map(map -> map.getValue())
                .collect(Collectors.joining(","));
        System.out.println("--filterMap1: result of joining="+result);

        //Filter using a Predicate - ######## Most useful #####
        //  {1=linode.com}
        Map<Integer, String> filteredMap = filterByValue(hosting, x -> x.contains("linode"));
        System.out.println(filteredMap);

        // {1=linode.com, 4=aws.amazon.com, 5=aws2.amazon.com}
        Map<Integer, String> filteredMap2 = filterByValue(hosting, x -> (x.contains("aws") || x.contains("linode")));
        System.out.println(filteredMap2);

        // {4=aws.amazon.com}
        Map<Integer, String> filteredMap3 = filterByValue(hosting, x -> (x.contains("aws") && !x.contains("aws2")));
        System.out.println(filteredMap3);

        // {1=linode.com, 2=heroku.com}
        Map<Integer, String> filteredMap4 = filterByValue(hosting, x -> (x.length() <= 10));
        System.out.println(filteredMap4);

    }
    public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet()
                .stream()
                .filter(x -> predicate.test(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }




}
