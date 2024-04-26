package com.blog1;

//public class Employee {
//
//    private String name;
//    private int salary;
//    private String city;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getSalary() {
//        return salary;
//    }
//
//    public void setSalary(int salary) {
//        this.salary = salary;
//    }
//
//    public String getCity() {
//        return City;
//    }
//
//    public void setCity(String city) {
//        City = city;
//    }
//}


import java.util.function.Consumer;
import java.util.function.Supplier;

public class A {

    public static void main(String[] args) {

        //                                     Supplier
        // Ex: 1

        Supplier<String> mysupplier = () -> "Supplied value";
        String s = mysupplier.get();
        System.out.println(s);

        // Above "Supplied value" is going to s so output is --> Supplied value
        // Ex:2       Bad Returns

        Supplier<A> mysuppliers = () -> new A();
        A a = mysuppliers.get();
        System.out.println(a);

        // here object address of A (new A()) is going to - a, so output is --> com.blog1.A@421faab1

        //                      Consumer


        Consumer<String> myConsumer = c -> System.out.println("Consumed: " + c);
        myConsumer.accept("Hello");

        // Above c is taking input (Hello) and then printing

        // So In Consumer you are giving  input
        // In Supplier you are not giving  input  it is just producing Output
        // Function takes the  input  and produces the  output
        // Predicate generates  Boolean value




        // we will create another class name Post.java because we are not converting A.java to Post.java because too much coding we have done here in A.java
    }

}

//                                       Homework:

//Imagine there are  5 objects
//Group the object based on salary ( means employee having salary 5000  one group)( employee having salary 10000  one group)(and so on)
//How will you group the employee based on  salary, name, city
//How do we find minimum and maximum salary
