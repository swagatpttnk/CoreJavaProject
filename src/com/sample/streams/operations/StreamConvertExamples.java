package com.sample.streams.operations;

import com.sample.collections.Dish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class StreamConvertExamples {
    public static void main(String args[]){
        StreamConvertExamples sm= new StreamConvertExamples();
        //sm.convertStream2List();
        //sm.convertStream2Array();
        //sm.convertStringArrayToMap(); // convert Array to Stream & then collect in Collectors.toMap()
        //sm.convertListToMap();
        sm.convertListToMapWithDuplicateKey();

    }

    private void convertStringArrayToMap() {
        Map<String,Double> myMap1=Arrays.asList("a:1.0", "b:2.0", "c:3.0").stream()  //<-- Using Arrays.asList & Collectors.toMap
                .map(x-> x.split(":",2))
                .collect(Collectors.toMap(x->x[0],x->Double.parseDouble(x[1]) ) );
        System.out.println("--convertStringArrayToMap:myMap1--"+ myMap1);

        //Recommended solution ( as it will throw error on erronous input )
        String[] strArray={"a:1.0", "b:2.0", "c:3.0","d:33"}; // Erronous input {"a:1.0", "b:2.0", "c:3.0:33","d33"};
        Map<String,Double> myMap2=Arrays.stream(strArray) //<--- using Arrays.stream(String[]) & Collectors.toMap
                .map(x-> x.split(":",2)) //<---- this will throw error if there is no or more than 2 :
                .collect(Collectors.toMap(x->x[0],x->Double.parseDouble(x[1]) ) );
        System.out.println("--convertStringArrayToMap:myMap2--"+ myMap2);

        //Happy solution ( as it will filter out erronous input)
        String[] strArray1={"a:1.0", "b:2.0", "c:3.0:33","d:33"};
        Map<String,Double> myMap3=Arrays.stream(strArray) //<--- using Arrays.stream(String[]) & Collectors.toMap
                .map(x-> x.split(":")) //<---- this just split
                .filter(x-> x.length==2) //<---- this will filter out erronous input i.e no colons or >2 colons
                .collect(Collectors.toMap(x->x[0],x->Double.parseDouble(x[1]) ) );
        System.out.println("--convertStringArrayToMap:myMap3--"+ myMap3);
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
    void convertStream2Array(){
        List<Dish> menu=populateDish();

        String[] meatArray = menu.stream()
                .filter(s -> Dish.Type.MEAT.equals( s.getType()))
                .map(Dish::getName) //<---- this is where  Dish d converts to String name
                .toArray(String[]::new); // <----- there is a function available . No need of Collectors.
        System.out.println("--convertStream2Array:meatArray--"+meatArray);
        for(String meat:meatArray){ System.out.println(meat);}
    }
    void convertStream2List(){
        List<Dish> menu=populateDish();

        List<String> meatList = menu.stream()
                .filter(s -> Dish.Type.MEAT.equals( s.getType()))
                .map(Dish::getName) //<---- this is where  Dish d converts to String name
                .collect(Collectors.toList());
        System.out.println("--convertStream2List:meatList--");
        meatList.forEach(System.out::println);
    }
    void convertListToMap(){
        List<Dish> menu=populateDish();

        List<Dish> meatList = menu.stream()
                .collect(Collectors.toList());
        System.out.println("--convertListToMap:meatList:"+meatList);
        Map<String,Integer> meatMap = menu.stream()
                .collect(Collectors.toMap((Dish s)->s.getName(),(Dish s)->s.getCalories()));
        System.out.println("--convertStream2List:meatMap:"+meatMap);

    }
    void convertListToMapWithDuplicateKey(){
        List<Dish> menu=populateDish();

        List<Dish> meatList = menu.stream()
                .collect(Collectors.toList());
        meatList.add(new Dish("Turkey", false, 560, Dish.Type.MEAT));
        System.out.println("--convertListToMap:meatList:"+meatList);
        /*
        Map<String,Integer> meatMap = meatList.stream()
                .collect(Collectors.toMap((Dish s)->s.getName(),(Dish s)->s.getCalories()) );
                //will throw IllegalStateException: Duplicate key Turkey
         */
        Map<String,Integer> meatMap = meatList.stream()
                .collect(Collectors.toMap((Dish s)->s.getName(),(Dish s)->s.getCalories(),
                        (oldValue,newValue)->newValue));// if duplicate keys encountered => will take new key/value.
        System.out.println("--convertStream2List:meatMap:"+meatMap);

    }
    
    
}
