package com.sample.util.string;

import java.util.*;

public class ExampleString{
    public static void main(String args[]){
        String s1=new String("hello");
        String s2="hello";
        String s3=s1.intern();//returns string from pool, now it will be same as s2
        System.out.println(s1==s2);//false because reference is different
        System.out.println(s2==s3);//true because reference is same
        System.out.println(s2.equals(s3));
        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s3));
        String s5 = "abc";
        StringBuffer s6 = new StringBuffer(s5);
        System.out.println(s5.equals(s6));// false as s5 is String & S6 is StringBuffer, equals
                                          // method returns false when ref Types are different
        String s=50+30+"Sachin"+40+40; //80Sachin4040  --> after a string is encountered all + operator is treated
                                       // as string concatenation.
        System.out.println("Mystrious concatenation: "+s);

    }
    public String ConvertSetOfStringsToCommaSeparatedString(){
        // {"AA","BB","CC"} --> "AA,BB,CC"
        Set<String> setOfString=new HashSet<>(Arrays.asList("Geek","ForGeeks","GeeksForGeeks"));
        List<String> listOfString=new ArrayList<>(Arrays.asList("Geek","ForGeeks","GeeksForGeeks"));
        String result_setToString=String.join(",",setOfString);
        String result_listToString=String.join(" ",listOfString);
        return result_listToString;
    }

}
