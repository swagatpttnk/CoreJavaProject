package com.sample.collections.mkyoung;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DemoMkyong {
    List<String> lines = Arrays.asList("spring", "node", "mkyong","mkyong");
    List<Person> personList=Arrays.asList(new Person("Akask","Batra",23),
            new Person("Rob","Fanag",23),
            new Person("Bill","James",24),
            new Person("Donald","Ferrera",29),
            new Person("Donald","Ferrera",29));
    public void demoSorting(){
        List<String> result = lines.stream().sorted().collect(toList());
        System.out.println("-- 1 ---");
        result.forEach(System.out::println);                //output : spring, node

        //Sort with comparator
        List<String> result2 = lines.stream().sorted().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length()==o2.length())?0:(o1.length()>o2.length())?1:-1;
            }
        }).collect(toList());
        System.out.println("-- 2 ---");
        result2.forEach(System.out::println);

        //Sort with comparator
        Comparator<String> myComparator=Comparator.comparing(String::length).thenComparing(String::valueOf);
        List<String> result2_1 = lines.stream().sorted().sorted(myComparator).collect(toList());
        System.out.println("-- 2.1 ---");
        result2_1.forEach(System.out::println);

    }
    public void demoFiltering(){
        //Filtering - print all element not equals to mkyong
        List<String> result3 = lines.stream().filter(s->!s.equals("mkyong")).collect(toList());
        System.out.println("-- 3 ---");
        result3.forEach(System.out::println);
        //Filtering - print all the distinct element in the stream
        List<String> result3n1=lines.stream().distinct().collect(toList());
        System.out.println("-- 3n1 ---");result3n1.forEach(System.out::println);
        //Filtering - print all the distinct element in the stream , for objects the comparison takes place as per the equals method

        List<Person> result3n2=personList.stream().distinct().collect(toList());
        System.out.println("-- 3n1 ---");result3n2.forEach(System.out::println);

    }
    public void demoMapping(){
        //Matching
        Boolean noMkyong = lines.stream().allMatch(s->!s.equals("mkyong")); //will return true if all the element of the stream !="mkyong"
        System.out.println("-- 4 --- noMkyong:"+noMkyong);//should print false

        //1.Mapping - used to trans form
        //to convert a list of Strings to upper case.
        List<String> result5 = lines.stream()
                .filter(s->!s.equals("mkyong"))
                .map(String::toUpperCase).collect(toList());
        System.out.println("-- 5 ---");
        result5.forEach(System.out::println);

        //2.List of objects -> List of String
        // Get all the name values from a list of the person objects.
        List<String> nameList=personList.stream().map(S->S.firstName).collect(Collectors.toList());
        System.out.println("-- 6 ---");
        nameList.forEach(System.out::println);

        //3.List of objects -> List of other Object
        // Convert list of  all the name values from a list of the person objects.
        List<Employee> nameList2=personList.stream().map(S->{
            Employee emp=new Employee();
            emp.setFirstName(S.firstName);
            emp.setLastName(S.lastName);
            return emp;
        }).collect(Collectors.toList());
        System.out.println("-- 7 ---");
        nameList2.forEach(System.out::println);
    }
    public void demoFlatMapping(){
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);
        System.out.println("-- demoFlatMapping ---"+temp);
        //temp.forEach(s-> System.out.println(s));
        //filter a stream of string[], and return a string[]?
        Stream<String[]> stream = temp.filter(x -> "a".equals(x.toString()));
        System.out.println("-- demoFlatMapping 2---");
        stream.forEach(System.out::println);
    }
    public static void main(String[] args) {

        DemoMkyong dm=new DemoMkyong();
        //dm.demoSorting();
        //dm.demoFiltering();
        //dm.demoMapping();
        dm.demoFlatMapping();
    }
}
