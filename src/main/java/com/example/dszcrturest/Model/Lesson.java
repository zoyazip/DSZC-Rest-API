package com.example.dszcrturest.Model;

public class Lesson {
    private String name;
    private String time;
    private String instructor;
    private String room;
    private String course;
    private String day;

    public Lesson(String name, String time, String instructor, String room, String course, String day) {
        this.name = name;
        this.time = time;
        this.instructor = instructor;
        this.room = room;
        this.course = course;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", instructor='" + instructor + '\'' +
                ", room='" + room + '\'' +
                ", course='" + course + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
