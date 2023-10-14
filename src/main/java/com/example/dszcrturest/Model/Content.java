package com.example.dszcrturest.Model;

class Student {
    private String course;
    private String specification;
    private String group;

    public Student(String course, String specification, String group) {
        this.course = course;
        this.specification = specification;
        this.group = group;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "course='" + course + '\'' +
                ", specification='" + specification + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}

class Time {
    private String time;
    private String dayOfTheWeek;
    private String group;

    public Time(String time, String dayOfTheWeek, String group) {
        this.time = time;
        this.dayOfTheWeek = dayOfTheWeek;
        this.group = group;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Time{" +
                "time='" + time + '\'' +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}


class Subject {
    private String subject;
    private String lecturer;
    private String Classroom;
    private String type;
    private String connection;

    public Subject(String subject, String lecturer, String classroom, String type, String connection) {
        this.subject = subject;
        this.lecturer = lecturer;
        Classroom = classroom;
        this.type = type;
        this.connection = connection;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String classroom) {
        Classroom = classroom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject='" + subject + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", Classroom='" + Classroom + '\'' +
                ", type='" + type + '\'' +
                ", connection='" + connection + '\'' +
                '}';
    }
}
public class Content {
    Student student;
    Time time;
    Subject subject;

    public Content(Student student, Time time, Subject subject) {
        this.student = student;
        this.time = time;
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Content{" +
                "student=" + student +
                ", time=" + time +
                ", subject=" + subject +
                '}';
    }
}

/*

    Model:
        Student
            {
                Course(1. or 2.)
                Specialisation(Computer Systems, Information Technologies, Automation, Builders etc..)
                Group(1. or 2.)
            }
        Time
            {
                Time
                Day of the week
                Position(1, 2, 3, etc..)
                Week(odd or even)
            }
        Subject
            {
                Subject(Math, Physics, OS etc..)
                Lecturer(Maria Dobkevicha)
                Classroom(12)
                Type(Lesson, Laboratory work or Test)
                Connection(Remote or not)
            }

 */