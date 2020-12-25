package com.sample.collections;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class TestStreams {
    public enum CaloricLevel { DIET, NORMAL, FAT }
    public static void main(String[] args)
    {
        TestStreams ts=new TestStreams();
        ts.test6_3_3();
    }
    public List<Dish> populateDish(){
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        return menu;
    }
    public void test6_3_3(){
        List<Dish> menu=populateDish();
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println("test6_3_3:menu="+typesCount);

        //getting Dish having maxCalorie from each Dish.Type
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));
        System.out.println("test6_3_3:mostCaloricByType="+mostCaloricByType);

        // to over come the Optional<Dish> use Collectors.collectingAndThen()
        Map<Dish.Type, Dish> mostCaloricByType2 =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));
        System.out.println("test6_3_3:mostCaloricByType2="+mostCaloricByType2);
        //OTHER EXAMPLES OF COLLECTORS USED IN CONJUNCTION WITH GROUPINGBY
        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println("test6_3_3:totalCaloriesByType="+totalCaloriesByType);

        //OTHER EXAMPLES OF COLLECTORS USED IN CONJUNCTION WITH GROUPINGBY
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT; },
                                toSet() )));
        System.out.println("test6_3_3:caloricLevelsByType="+caloricLevelsByType);
    }

}
