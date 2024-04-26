package com.blog1;

// We have two MainUtil class --> one is below and other is at last which is
// connected to Movie.java, MovieYear.java, A.java, PostDto.java, Employee.java -->
// they all are under com.blog1 directly
// Now we will create New MainUtil class:

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainUtil {
    public static void main(String[] args) {

        // Ex: 1     Find Maximum and Minimum number in the list

        List<Integer> numbers = Arrays.asList(100, 500, 5, 4000, 5000, 10000, 2000);
        Integer maxVal = numbers.stream().max(Integer::compareTo).get();
        Integer minVal = numbers.stream().min(Integer::compareTo).get();
        // get() method is used to capture the value
        // After finding the maximum value  get( ) method will receive the maximum value and we will
        // store that value into the variable  val
        System.out.println(maxVal);
        System.out.println(minVal);

//        // create                Employee1.java                     under com.bog1
//
//        List<Employee1> name = Arrays.asList(new Employee1(2, "xyz"), new Employee1(1, "abc"));
//        Employee1 employee1 = name.stream().max(Employee1::compareTo).get();
//        // because of compareTo we will go to Employee1.java in compareTo method
//
//        System.out.println(employee1.getId());
//        System.out.println(employee1.getName());
//        // it will give the record whose id number is bigger

        // Ex: 2

        // you can find highest marks among 5 student

        // in same Employee1.java create another new variable name and salary

        List<Employee1> data = Arrays.asList(
                new Employee1("mike", 5000),
                new Employee1("stallin", 10000),
                new Employee1("adam", 5000),
                new Employee1("mike", 8000),
                new Employee1("mike", 6000)
        );
        Map<Double, List<Employee1>> groups = data.stream().collect(Collectors.groupingBy(Employee1::getSalary));
        System.out.println(groups);
        // above line will give me salary and the employee object name getting that salary:
        // {10000.0=[com.blog1.Employee1@6e5e91e4], 5000.0=[com.blog1.Employee1@2cdf8d8a, com.blog1.Employee1@30946e09]}

        // One way to get the salary and Employee pair

//        groups.forEach((salary, employees) -> {
//            System.out.println("Employee with salarty : "+salary+ ":");
//            employees.forEach(emp -> {
//                System.out.println("Name : "+emp.getName()+"");
//            });
//            System.out.println();
//        });

        // Second way to get the salary and Employee pair

        for (Map.Entry<Double, List<Employee1>> entry : groups.entrySet()) {
            double salary = entry.getKey();
            List<Employee1> employeeList = entry.getValue();
            System.out.println("Employee with salary " + salary + ":");
            for (Employee1 employee1: employeeList) {
                System.out.println("\t" +employee1.getName());

                // groups.entrySet()) --> here we have key value pair, key is salary and
                // value is employee name so it is stored in a pair with "entry"
                // entry.getKey(); --> it will give salary and stored in --> salary
                // entry.getValue(); --> it will give employee name and stored in -> employeeList
                // for (Employee1 employee1: employeeList) { --> one by one object address will
                // get copied into it (employee1)
                // Detail of Above code in --> page - 168 --> Pankaj sir 5
            }
        }

        // Ex: 3         Find the salary based on names

        Map<String, List<Employee1>> groupedByName = data.stream().collect(Collectors.groupingBy(Employee1::getName));

        groupedByName.forEach((name, Emp1) -> {
            System.out.println("Employee with name :" +name+ ":");
            Emp1.forEach(e1 -> {
                System.out.println("Salary of "+name+" is "+e1.getSalary()+"");
            });
        });

        // above we are grouping the object based on name, salary etc

    }
}



//import java.lang.reflect.Array;
//import java.util.*;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class MainUtil {
//    public static void main(String[] args) {
//
//        //         First we have Employee object, then Post object
//
//        //                        Java 8 features
//
//        // Ex: 1 - comparing
//
////        Predicate<String> condition = x->x.equals("mike");
////        boolean val = condition.test("mike");
////        System.out.println(val);
//
//        //        Above we are supplying the value using test method
//
//        // Ex: 2  -      sorting the record  but shortcut in Ex: 4
//
////        List<Integer> data = Arrays.asList(10, 20, 30, 50, 100);
////        List<Integer> newData = data.stream().filter(x -> x > 20).collect(Collectors.toList());
////        System.out.println(newData);
////    }
//
//        //    Above data.stream() is taking data one by one and then we are performing a task using filter
//        //    filter(x -> x > 20) here x is taking the value one by one and performing a task
//        //    Collectors.toList() - here we are creating a list
//        //    collect(Collectors.toList()) - here we are collecting the value after task
//        //    and storing it in newData, so in one line we did the sorting
//
//        // Ex: 3  -  starts with and ends with
//
////        List<String> data = Arrays.asList("mike", "stallin", "mithun", "adam", "micheal");
////        List<String> newData = data.stream().filter(x -> x.startsWith("m")).collect(Collectors.toList());
////        System.out.println(newData);
//
//        // x -> x.endsWith("m") wil give - adam
//
//        // Ex: 4       -       How many times the name “mike” is present
//
////        List<String> data = Arrays.asList("mike", "stallin", "mike", "adam", "micheal");
////        List<String> newData = data.stream().filter(x -> x.equals("mike")).collect(Collectors.toList());
////        System.out.println(newData.size());
//
//        // System.out.println(newData.size()); - it will give 2
//        // System.out.println(newData);   -  it will give mike  mike
//
//
//        // Ex: 5   compare salary so created Employee.java
//
////        Employee emp1 = new Employee();
//
//        //here we have set the value of the variables in the Employee.java class
//        //and at last we will get the value according to the condition
//
////        emp1.setName("mike");
////        emp1.setCity("Bangaluru");
////        emp1.setSalary(10000);
////
////        Employee emp2 = new Employee();
////
////        emp2.setName("stallin");
////        emp2.setCity("Chennai");
////        emp2.setSalary(5000);
////
////        Employee emp3 = new Employee();
////
////        emp3.setName("smith");
////        emp3.setCity("Bangaluru");
////        emp3.setSalary(10000);
////
////        Employee emp4 = new Employee();
////
////        emp4.setName("adam");
////        emp4.setCity("Delhi");
////        emp4.setSalary(8000);
////
////        Employee emp5 = new Employee();
////
////        emp5.setName("radhe");
////        emp5.setCity("Vrindavan");
////        emp5.setSalary(5000);
//
//        // another way to initialize the variables of Employee class:
//        // below we have created an object which is calling a constructor
//
////        List<Employee> data = Arrays.asList(
////                new Employee("mike", "Bangaluru", 10000),
////                new Employee("stallin", "Chennai", 5000),
////                new Employee("smith", "Bangaluru", 10000),
////                new Employee("adam", "Delhi", 8000),
////                new Employee("radhe", "Vrindavan", 5000)
////        );
//
//        // But for above code we need to create a constructor in Employee.java:
//
////        public Employee(String name, String city, int salary) {
////            this.name = name;
////            this.city = city;
////            this.salary = salary;
////        }
//
//
////        List<Employee> data = Arrays.asList((emp1), (emp2), (emp3), (emp4), (emp5));
//
//        //    QUESION                     Group the object based on salary
//
////        Map<Integer, List<Employee>> groupedBySalary = data.stream()
////                .collect(Collectors.groupingBy(Employee::getSalary));
//
//        // groupedBySalary --> grouping Employee object by their salary so in 'employees'
//        // object grouped By Salary will send emp1, emp2 etc according to the salary
//        // if two Employee will have same salary groupedBySalary will have these two oibjects
//        //   So in short values (employees) represent lists of employees who have that salary.
//
////        groupedBySalary.forEach((salary, employees) -> {
////            System.out.println("Employees with salary " + salary + ":");
////            employees.forEach(employee -> {
////                System.out.println("Name: " + employee.getName() + ", City: " + employee.getCity());
////            });
////            System.out.println();
////        });
//
//        //In the expression Employee::getSalary, the :: operator refers to the getSalary method of
//        //the Employee class. This is equivalent to using a lambda expression like
//        //(Employee employee) -> employee.getSalary()
//        // groupedBySalary.forEach((salary, employees) -> { --> here '->' is the arrow notation used
//        // to separate the lambda expression's parameters from its body
//        //Yes, emp1, emp2, emp3  etc., are instances of the Employee class.
//        // When you call data.stream(), you're creating a stream of Employee objects from the data list.
//        // emp1, emp2 are going to Employee object one by one
//
//        // groupedBySalary.forEach((salary, employees) --> here salary contains the salary and employees
//        // contain the list of employees with corresponding salary
//
//
//        //               Above Lines are Explanation of Above Code
//
//
////        List<Employee> newData = data.stream().filter(x -> x.getSalary() == 10000).collect(Collectors.toList());
////        //below will return 2 objects
////        System.out.println(newData);
////        System.out.println(newData.size());
////
////        for(Employee employee:newData) {
////
////            System.out.println(employee.getName());
////            System.out.println(employee.getCity());
////            System.out.println(employee.getSalary());
////        }
//
//
//        //Above we have created 4 objects of Employee then we will pass these objects to dataStream\
//        // and store it into x then one by one we will compare salary x.getSalary() and if condition is true store the object(emp1,emp2...) into list
//        // and it will return the 2 0bject to newData because 2 employee has 10000 salary
//        // now use forEach loop and get the record of the employee by using employee.getSalary()
//
//
//        // Ex: 6  - Give me the employees name who stays in Delhi
//
////        data.stream().filter(x -> x.getCity().equals("Delhi") )
//
//        // above we will use equals but this is case sensitive so use below code
//
////        data.stream().filter(x -> x.getCity().equalsIgnoreCase("Delhi"))
//
//        //                             Map Method
//
//        // filter should be used when you are filtering the record of your data structure for which  Predicate syntax is added
//        // So for getting the square of all numbers  we will use  map method
//        // And a map uses a function  functional interface
//
//        //                           Builtin Interface
//
//        //                       Function Functional interface:
//
//        //                Now we have converted the class Employee to class A
//
//        // Ex: 1
//
////        Function<Integer, String> myFunction = i -> "Result:" + i;
////        String val = myFunction.apply(100);
////        System.out.println(val);
//
//        // Ex: 2       if i want output as Double
//
////        Function<Integer, Double> myFunction = i -> i*1.3;
////        Double val = myFunction.apply(100);
////        System.out.println(val);
////
//        // Above we are using Function Functional interface, we are not using filter  because filter are based on Boolean values (true and false)
//        //Here we are only processing the input and generating the output
//        // Give me odd number or even numberes  filter - because here we need to apply condition
//        // Multiply every number by 10  map  because here we don’t have any condition
//
//        // Ex: 3      Find the square of numbers - use map because there is no condition only n*n
//
////        List<Integer> data = Arrays.asList(10, 20, 30, 40);
////        List<Integer> newData = data.stream().map(n -> n * n).collect(Collectors.toList());
////        //List<Integer> newData = data.stream().map(n -> n + 5).collect(Collectors.toList());
////        //List<Double> newData = data.stream().map(n -> n/2.0).collect(Collectors.toList());
////        System.out.println(newData);
////
//        // Ex: 4      sorting the numbers
////
////        List<Integer> datas = Arrays.asList(2, 10, 8, 5, 20);
////        List<Integer> newDatas = datas.stream().sorted().collect(Collectors.toList());
////        System.out.println(newDatas);
////
////        // Ex: 5      sorting the name
////
////        List<String> data1 = Arrays.asList("mike", "adam", "stallin", "mithun");
////        List<String> newData1 = data1.stream().sorted().collect(Collectors.toList());
////        System.out.println(newData1);
////
////        // Ex: 6      remove Duplicate element
////
////        List<Integer> datas1 = Arrays.asList(10, 2, 10, 34, 21, 23, 5, 2);
////        List<Integer> newDatas1 = datas1.stream().distinct().collect(Collectors.toList());
////        System.out.println(newDatas1);
//
//        //                                     Supplier in A.java
//
//        // Above we have      Predicate       and        Function Functional Interface
//
////                               Use Post.java
//
//        //         Convert Entity into dto and create Post.java in com.blog1
//
//        Post post1 = new Post();
//        post1.setId(1);
//        post1.setTitle("aaa");
//        post1.setContent("aaaa");
//
//        Post post2 = new Post();
//        post2.setId(2);
//        post2.setTitle("bbb");
//        post2.setContent("bbbb");
//
//        Post post3 = new Post();
//        post3.setId(3);
//        post3.setTitle("ccc");
//        post3.setContent("cccc");
//
//        // now we have to convert these three object to Dto
//
////        PostDto dto1 = mapToDto(post1);
////        PostDto dto2 = mapToDto(post2);
////        PostDto dto3 = mapToDto(post3);

//         // mapToDto method is below around line number --> 475

////
////        List<PostDto> data = Arrays.asList(dto1, dto2, dto3);
//        // System.out.println(data); --> it will give me three objects
////        for(PostDto dto:data){
////            System.out.println(dto.getId());
////            System.out.println(dto.getTitle());
////            System.out.println(dto.getContent());
////        }
////        System.out.println();
//
//        // Above we are calling mapToDto many times so it is not good now use stream Api
//
//        List<Post> posts = Arrays.asList(post1, post2, post3);
////        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
//
//        //  Shortcut way of writing lambda expression --> .map(MainUtil::mapToDto)
//
//        List<PostDto> dtos = posts.stream().map(MainUtil::mapToDto).collect(Collectors.toList());
//
//        // Above MainUtil is the class in which i am calling mapToDto method
//        // and automatically object address (dtos) will go to this (post) in below method (mapToDto)
//
//        // When you pass MainUtil::mapToDto to the map method, you're telling it to apply the
//        // mapToDto method to each element of the stream. elements are - post1, post2, post3
//
//
//
//        System.out.println(dtos);
//
//        for(PostDto dto : dtos) {
//
//            System.out.println(dto.getId());
//            System.out.println(dto.getTitle());
//            System.out.println(dto.getContent());
//        }
//
//        // System.out.println(dtos); it will give three object's address of Employee because of three records
//
//
//        //                         Use Movie.java              it implements Comparable
//
//        //                    Comparator & Comparable
//
//        // You need to sort these object based on year, based on names, based on rating 
//        // it is sorting of objects  not sorting of number or sorting of string
//
//        // So comparable is an interface which has only one incomplete method
//
//        Movie m1 = new Movie("bbb", 8, 1999);
//        Movie m2 = new Movie("aaa", 11, 1986);
//        Movie m3 = new Movie("ccc", 9, 2000);
//
//        ArrayList<Movie> list = new ArrayList<Movie>();
//        list.add(m1);
//        list.add(m2);
//        list.add(m3);
//        // Above we are adding the object in the Array list
//
////                Collections.sort(list);
//
//        // sorting based on movie year:
////        Use                    MovieYear.java    it implements Comparator
//
//        // Comparator interface has a feature which can give you all sorting option in one program
//
//        MovieYear movieYear = new MovieYear();
//        Collections.sort(list, movieYear);
//        // above line means we are sorting the list based on movie year
//        // because this (new MovieYear()) has a method in MovieYear.java which is compareTo
//
//        // above code details in page 150 pankaj sir 5
//
//
//        // Collections is a class in java but Collection is an interface
//        // Above we are sorting based on year
//        // Collections.sort  you are actually calling  compareTo method in (Movie.java):
//        // Compare to method sort all the objects based on year
//
//
//
//        for (Movie m : list) {
//            System.out.println(m.getName());
//            System.out.println(m.getRating());
//            System.out.println(m.getYear());
//        }
//        // this object (m) based on year
//
////        Use                    MovieRating.java    it implements Comparator
//
//        MovieRating movieRating = new MovieRating();
//        Collections.sort(list, movieRating);
//
//        for (Movie m : list) {
//            System.out.println(m.getName());
//            System.out.println(m.getRating());
//            System.out.println(m.getYear());
//        }
//
//        // Above we can sort based on Movie year and rating means same program has two features
//        // which comparable can not do
//
////        Use                    MovieName.java    it implements Comparator
//
//        MovieName movieName = new MovieName();
//        Collections.sort(list, movieName);
//
//        for (Movie m : list) {
//            System.out.println(m.getName());
//            System.out.println(m.getRating());
//            System.out.println(m.getYear());
//        }
//
//    }
//
//    //                    Below method is for Post

//        static PostDto mapToDto(Post post){
//
//            PostDto dto = new PostDto();
//            dto.setId(post.getId());
//            dto.setTitle(post.getTitle());
//            dto.setContent(post.getContent());
//
//            // So, for each Post object (post1, post2, post3) in the posts list,
//            // the mapToDto method is called to convert it to a PostDto object.
//
//            return dto;
//
//            // Above sorting is happening due to Comparator interface
//
//        }
//
//
//
//}


// find the maximum number in the list --> Here we use  max() method

// Notes

// Comparable sort the object on any one parameter and comparator can sort
// the object on any parameter

// How to remember the program:

// 1. implements Comparable OR Comparator in Movie.java according to situation
// 2. Comparable<Movie> -- here we have added generic
// 3. then implement the method (alt + enter on Coparable OR Comparator)
// 4. Collections.sort(list,movieName);


//                                   Interview Question:

//Group the object based on marks?
//Group the object based on names?
//Group the object based on city ?
