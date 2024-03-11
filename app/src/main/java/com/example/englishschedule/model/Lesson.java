package com.example.englishschedule.model;

import java.util.ArrayList;

public class Lesson {
    private int id;
    private String name;
    private String startDate;
    private boolean isFinish;
    private ArrayList<String> words;
    private ArrayList<Reminder> reminders;

    public Lesson(){
        words = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public void setWord(String word) {
        this.words.add(word);
    }

    public void setWords(ArrayList<String> words){
        this.words = words;
    }

    public void setReminders(ArrayList<Reminder> reminders) {
        this.reminders = reminders;
    }

    public void setReminder(Reminder reminder) {
        this.reminders.add(reminder);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", isFinish=" + isFinish +
                ", words=" + words +
                ", reminders=" + reminders +
                '}';
    }
}

