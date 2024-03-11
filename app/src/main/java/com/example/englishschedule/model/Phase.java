package com.example.englishschedule.model;

public class Phase {
    private int id;
    private String content;
    private int space;
    private int numOfDoingDay;

    public Phase(int id, String content, int space, int numOfDoingDay){
        this.id = id;
        this.content = content;
        this.space = space;
        this.numOfDoingDay = numOfDoingDay;
    }

    public Phase(int id, int numOfDoingDay){
        this.id = id;
        this.numOfDoingDay = numOfDoingDay;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getSpace() {
        return space;
    }

    public int getNumOfDoingDay() {
        return numOfDoingDay;
    }
}
