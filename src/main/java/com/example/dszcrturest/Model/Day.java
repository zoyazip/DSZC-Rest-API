package com.example.dszcrturest.Model;

import java.util.ArrayList;

public class Day {
    private ArrayList<Lesson> lesson;

    public Day(ArrayList<Lesson> lesson) {
        this.lesson = lesson;
    }

    public ArrayList<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(ArrayList<Lesson> lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Day{" +
                "lesson=" + lesson +
                '}';
    }
}
