package com.sample.collections.java8collectionAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCollectionAPI {
    public static void main(String[] args){
        List<String> friendsList = Arrays.asList("Raphael", "Olivia", "Thibaut");// returns MUTABLE but FIXED-Length list.
        System.out.println("List friendsList:"+ friendsList);
        friendsList.set(0,"Corso");// friendList = MUTABLE
        System.out.println("Replaced Raphael with Corso to Set friendsList:"+friendsList);
        //friendsList.add("Steve"); // friendList = FIXED LENGTH as formed out of Arrays.asList, can't add .
                                    // throws UnsupportedOperationException.
        //System.out.println("Added Steve to Set friendsList:"+friendsList);

        Set<String> friends = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
        System.out.println("Set friends:"+friends);
        friends.add("Brandy");
        System.out.println("Added Brandy to Set friends:"+friends);

        Set<String> friendsFromStreamOf= Stream.of("Raphael", "Olivia", "Thibaut").collect(Collectors.toSet());
        System.out.println("Set friendsFromStreamOf:"+friendsFromStreamOf);
        friendsFromStreamOf.add("Smith");
        System.out.println("Added Smith to Set friendsFromStreamOf:"+friendsFromStreamOf);

        //###Factory Methods for List
        List<String> friendsFact = List.of("Raphael", "Olivia", "Thibaut"); //<------- Factory method creating a List.
        System.out.println("List friendsFact:"+friendsFact);

        /*friendsFact.set(0,"Chih-Chun"); //<----- throw UnsupportedOperationException
        System.out.println("Replaced 'Raphael' with 'Chih-Chun' to friendsFact:"+friendsFact);

        friendsFact.add("Romario"); //<----- throw UnsupportedOperationException
        System.out.println("Added 'Romario' to friendsFact:"+friendsFact);*/
        List<String> friendsFactoryVararg = List.of("Raphael", "Olivia", "Thibaut","Raphael1", "Olivia1", "Thibaut1",
                "Raphael2", "Olivia2", "Thibaut2","Raphael3", "Olivia3", "Thibaut3");
        System.out.println("List friendsFactoryVararg:"+friendsFactoryVararg);

        //###Factory Methods for Set
        Set<String> friendsSet = Set.of("Tom", "Dik", "Harry"); //<----- returns an IMMUTABLE set.
        System.out.println("List friendsSet:"+friendsSet);
        //Set<String> friendsSet = Set.of("Tom", "Dik", "Harry","Harry); //<--- throw IllegalArgumentException because of Duplicate

        //friendsSet.add("Olivia"); //<----- throw UnsupportedOperationException
        //System.out.println("Added 'Olivia' to friendsSet:"+friendsFact);

        //### Factory Methods for Map //<----- returns an IMMUTABLE Map
        Map<String, Integer> ageOfFriends
                = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println("Map ageOfFriends:"+ageOfFriends);
        //ageOfFriends.put("Olivia", 29);
        //System.out.println("Set Olivia to 29 in map ageOfFriends:"+ageOfFriends); //<----- throw UnsupportedOperationException

    }
}
