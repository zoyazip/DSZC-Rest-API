package com.example.dszcrturest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DszcRtuRestApplication {

    public static void main(String[] args) {

        SpringApplication.run(DszcRtuRestApplication.class, args);
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
                Extra info(Specific Date etc..)
            }

 */