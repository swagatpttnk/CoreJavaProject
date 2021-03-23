package com.sample.functionalinterface.customfi;
@FunctionalInterface//Optional.If used,gives compile time error if there are multiple non-Object public abstract method.
public interface DisplayMsg {
    String display(String s); //1.Should have only one non-Object public abstract method.

    //Object clone();         //2.Should not have non public methods of Object class.
    /*public default Object clone(){ //3.Can have default methods overriding the methods from Object class.
        return new Object();
    }*/
    boolean equals(Object obj);//4.Can have public methods of Object class.
    int hashCode();
}
