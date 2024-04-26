package com.blog1;

//                        Turnary Operator

public class PracticeMainUtil {

    public static void main(String[] args) {

        int x = 10;
        int y = 20;

        // result = Condition ? expression1 : expression2;

        int max = (x>y)? x:y;
        System.out.println(max);
    }
}

// if Condition is true then expression1 will store in result But if condition is false
// then expression2 will store in result

// so we are using this Turnary operator for sorting and it is shorcut to If else