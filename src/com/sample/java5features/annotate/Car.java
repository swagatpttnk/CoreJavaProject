package com.sample.java5features.annotate;

/** The custom annotation @JsonField is applied to the field of this class **/
public class Car {

    @JsonField("manufacturer")
     final String make;

    @JsonField("modelName")
     final String model;


     final String year;


    public Car(String make, String model, String year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
    public static  void main(String args[]) throws JsonSerializeException {
        Car car=new Car("Ford","F150","2020");
        new JsonSerializer().serialize(car);
    }
}
