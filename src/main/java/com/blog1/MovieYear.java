package com.blog1;

import java.util.Comparator;

public class MovieYear implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getYear()-o2.getYear();
    }
}

// Main Program is MainUtil.java