package com.blog1;

                            // Different way of calling method

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainUtilSpringSecurity {

    // Method --> 1

//    public static void main(String[] args) {
//        MainUtilSpringSecurity mainUtil = new MainUtilSpringSecurity();
//        mainUtil.test();
//    }
//
//    public void test() {
//        System.out.println(100);
//    }

                            //  Method--> 2

//    public static void main(String[] args) {
//        //MainUtilSpringSecurity mainUtil = new MainUtilSpringSecurity();
//        new MainUtilSpringSecurity().test();
//
//        // above we are not storing the object in the reference variable and then calling
//        // above we are directly creating object and calling
//    }
//
//    public void test() {
//        System.out.println(200);
//    }

                                // Method--> 3

//    public static void main(String[] args) {
//        //MainUtilSpringSecurity mainUtil = new MainUtilSpringSecurity();
//        new MainUtilSpringSecurity().test1().test2();
//
//        // above test1() contain an object and then we can call method test2()
//
//
//    }
//    public MainUtilSpringSecurity test1(){
//        return new MainUtilSpringSecurity();
//
//        // above we are creating an object and returning it, and that object will be taken
//        // by test1() because it will retrn back from where it is called
//    }
//
//    public void test2() {
//
//        System.out.println(300);
//    }

                        // Encode the password --> password testing

    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("testing"));
    }

}
