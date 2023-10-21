package com.example.dszcrturest.Model;

import java.util.ArrayList;

public class Day {
    private ArrayList<Lesson> lessons;

    public Day(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Lesson> getLesson() {
        return lessons;
    }

    public void setLesson(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Day{" +
                "lesson=" + lessons +
                '}';
    }
}
