package com.example.englishschedule.model;

import java.util.Calendar;

public class Reminder {
    private int idLesson;
    private String startdate;
    private int numOfDayDone;
    private Phase phase;
    public Reminder(){}
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setNumOfDayDone(int numOfDayDone) {
        this.numOfDayDone = numOfDayDone;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public boolean isFinish(){
        return this.numOfDayDone >= phase.getNumOfDoingDay();

    }

    public Phase getPhase(){
        return  this.phase;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public String getStartdate(){return startdate;}

    public void finishToday(){
        this.numOfDayDone +=1;
        if(isFinish())
            return ;
        else{
            Calendar c = Calendar.getInstance();
            int[] date = Date.splitDate(this.startdate);
            c.set(Calendar.DAY_OF_MONTH, date[0]);
            c.set(Calendar.MONTH, date[1]-1);
            c.set(Calendar.YEAR, date[2]);
            c.add(Calendar.DAY_OF_MONTH, (phase.getSpace()+1));
            String newDate = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) +1) + "/" + c.get(Calendar.YEAR);
            this.setStartdate(newDate);
        }

    }
    public  int getNumOfDayDone(){
        return this.numOfDayDone;
    }

}
