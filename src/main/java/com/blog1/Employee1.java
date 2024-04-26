package com.blog1;

public class Employee1{
    private String name;
    private double salary;

    public Employee1(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}



//public class Employee1 implements Comparable<Employee1>{
//
//    private int id;
//    private String name;
//
//    public Employee1(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public int compareTo(Employee1 o) {
//        return this.id-o.id;
//        // This compare to method comes from  Employee object
//    }
//}
