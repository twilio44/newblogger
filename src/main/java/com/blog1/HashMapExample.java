package com.blog1;

// we use Hashmap to store --> key-Value pair

    import java.util.HashMap;
import java.util.Map;

    public class HashMapExample {
        public static void main(String[] args) {
            // Creating a HashMap
            Map<String, Integer> hashMap = new HashMap<>();

            // Adding key-value pairs to the HashMap
            hashMap.put("John", 25);
            hashMap.put("Alice", 30);
            hashMap.put("Bob", 28);
            hashMap.put("Emily", 35);

            // Accessing elements from the HashMap
            System.out.println("Age of John: " + hashMap.get("John"));
            System.out.println("Age of Alice: " + hashMap.get("Alice"));

            // Iterating over the HashMap using keySet()
            System.out.println("Iterating over the HashMap:");
            for (String name : hashMap.keySet()) {
                System.out.println("Name: " + name + ", Age: " + hashMap.get(name));
            }

            // Checking if a key exists in the HashMap
            String nameToCheck = "Bob";
            if (hashMap.containsKey(nameToCheck)) {
                System.out.println(nameToCheck + " is present in the HashMap.");
            } else {
                System.out.println(nameToCheck + " is not present in the HashMap.");
            }

            // Removing an element from the HashMap
            String nameToRemove = "Emily";
            hashMap.remove(nameToRemove);
            System.out.println("Removed " + nameToRemove + " from the HashMap.");

            // Size of the HashMap
            System.out.println("Size of the HashMap: " + hashMap.size());
        }

}

                                        // OutPut

//    Age of John: 25
//    Age of Alice: 30

//    Iterating over the HashMap:
//    Name: John, Age: 25
//    Name: Alice, Age: 30
//    Name: Bob, Age: 28
//    Name: Emily, Age: 35

//    Bob is present in the HashMap.
//    Removed Emily from the HashMap.

//    Size of the HashMap: 3
