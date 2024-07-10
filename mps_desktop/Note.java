package com.example.melphrasuggui;

public class Note {
    private int scaleDegree;
    private int duration;

    // Constructor
    public Note(int scaleDegree, int duration) {
        this.scaleDegree = scaleDegree;
        this.duration = duration;
    }

    // Getters and setters
    public int getScaleDegree() {
        return scaleDegree;
    }

    public void setScaleDegree(int scaleDegree) {
        this.scaleDegree = scaleDegree;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
