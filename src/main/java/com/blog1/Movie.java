package com.blog1;

//                           Comparator & Comparable
public class Movie implements Comparable<Movie> {

    private String name;
    private int rating;
    private int year;

    // So comparable is an interface which has only one incomplete method
    // here we have implemented Comparable  alt +Enter  Implement methods  ok
    // now override because we have to complete the incomplete method

    // use constructor with argument

    public Movie(String name, int rating, int year) {
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Movie o) {
        // sort based on year
//        return this.year-o.year;
        // sort based on rating
//        return this.rating-o.rating;
        // sort based on movie
        return this.name.compareTo(o.name);

        // above line will subtract 1999-1986 if it is positive it means first value is
        // greater then it will automatically interchange
    }
}

// Main Program is MainUtil.java