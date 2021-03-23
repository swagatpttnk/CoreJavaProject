package com.sample.streams.operations;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

    public static void main(String args[]){
        BuildingStreams bs=new BuildingStreams();
        bs.streamFromValues();
        bs.streamFromArrays();
        bs.streamOfNullable();
        bs.streamOfNullable2();
        bs.streamFromFile();
        bs.streamUsingStreamDotGenerate();
        bs.streamUsingStreamDotIterate();
    }
    public void streamFromValues(){
        //Streams from values
        Stream<String> streamFromValues = Stream.of("Modern ", "Java ", "In ", "Action"); // is expensive
        System.out.println("--BuildingStreams:Stream from values--");
        streamFromValues.map(String::toUpperCase).forEach(System.out::println);
    }
    public void streamFromArrays(){
        //Stream from Array
        String[] stringArr={"Modern ", "Java ", "In ", "Action"};
        Stream<String> streamFromArray=Arrays.stream(stringArr);
        System.out.println("--BuildingStreams:Stream from Array--");
        streamFromArray.map(String::toUpperCase).forEach(System.out::println);

        int[] intArr={1,3,5,7};
        //Stream<Integer> streamFromArr=Arrays.stream(intArr); doesnt works
        IntStream streamFromArr=Arrays.stream(intArr); // watch the behaviour with primitive[]
        System.out.println("--BuildingStreams:Stream from Array--");
        streamFromArray.map(String::toUpperCase).forEach(System.out::println);

    }


    public void streamOfNullable(){
        //"explicit checking for null" Vs "Streams from nullable"
        //System.setProperty("home","MyHome");
        String homeValue = System.getProperty("home");
        //Stream<String> homeValueStream1 =Stream.of(homeValue); // will throw error if homeValue is null
        Stream<String> homeValueStream1 = homeValue == null ? Stream.empty() : Stream.of(homeValue); //explicit checking for null
        System.out.println("--BuildingStreams:explicit checking for null--");
        homeValueStream1.map(String::toUpperCase).forEach(System.out::println);
        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));
        System.out.println("--BuildingStreams:Streams from nullable--");
        homeValueStream2.map(String::toUpperCase).forEach(System.out::println);
        //Empty Stream
        Stream<String> emptyStream = Stream.empty(); // will give
    }
    public void streamOfNullable2(){
        System.setProperty("home","MyHome2");
        Stream<String> values =
                Stream.of("config", "home", "user")
                        .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        System.out.println("--BuildingStreams:Streams from nullable2--");
        values.map(String::toUpperCase).forEach(System.out::println);
    }
    //Stream from File
    public void streamFromFile(){
        long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get("/Users/swagatpattnaik/Projects/CoreJavaProject/src/com/sample/streams/operations/","data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        catch(IOException e){
            System.out.println("Exception:"+e.getMessage());
        }
        System.out.println("--BuildingStreams:Streams from File:uniqueWords:" + uniqueWords);
    }
    public void streamUsingStreamDotIterate(){
        //Generate Fibonacci series using stream.iterate()
        System.out.println("--BuildingStreams:streamUsingStreamDotIterate--");
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
    public void streamUsingStreamDotGenerate(){
        //Generate Fibonacci series using stateful supplier with stream.generate().
        System.out.println("--BuildingStreams:streamUsingStreamDotGenerate--");
        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
