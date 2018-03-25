package com.example.barti.hackathon;

/**
 * Created by barti on 25.11.17.
 */

class Study {

    private String name;
    private String departament;
    private String degree;
    private double similarity;

    Study(String name, String departament, String degree, double similarity) {
        this.name = name;
        this.departament = departament;
        this.degree = degree;
        this.similarity = similarity;
    }

    public double getSimilarity() {
        return similarity;
    }

    public String getDegree() {
        return degree;
    }

    public String getDepartament() {
        return departament;
    }

    public String getName() {
        return name;
    }
}
