package com.example.laundryday_continuingtasks;

import android.util.Log;

public class SubTask {
    private boolean isWork;     //true = work action, false = wait action
    private int duration;       //task duration in minutes
    private String title;
    //TODO is delayable

    public SubTask(String title, int duration, boolean isWork) {
        Log.d("SubTask", "SubTask: new SubTask");
        this.isWork = isWork;
        this.duration = duration;
        this.title = title;
    }

    public boolean getWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String durationToString() {
        String returnStr;
        if(duration < 60) {
            returnStr =  duration + "min";
        }
        else {
            returnStr = duration / 60 + "h";

            if(duration%60 != 0) {
                returnStr += ", " + duration % 60 + "m";
            }
        }
        return returnStr;
    }

    @Override
    public String toString() {
        return title + " for " + durationToString() + ", isWork? " + isWork;
    }
}
