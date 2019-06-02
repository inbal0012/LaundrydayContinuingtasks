package com.example.laundryday_continuingtasks;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class Task {
    private List<SubTask> subTasks;
    private String title;
    private int duration;       //task duration in minutes

    public Task(String title) {
        this.title = title;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        if (subTasks != null) {
            this.subTasks = subTasks;
            for (int i = 0; i < subTasks.size(); i++) {
                this.duration += this.subTasks.get(i).getDuration();
            }
        }
    }

    public int getDuration() {
        return duration;
    }
}
