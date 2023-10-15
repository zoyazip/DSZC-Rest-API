package com.example.dszcrturest.Model;

import java.util.List;
import java.util.Map;

public class Schedule {
    Map<String, List<Day>> schedule;

    public Schedule(Map<String, List<Day>> schedule) {
        this.schedule = schedule;
    }

    public Map<String, List<Day>> getSchedule() {
        return schedule;
    }

    public void setSchedule(String s, Map<String, List<Day>> schedule) {
        this.schedule = schedule;
    }

    public static class Day {
       private Map<String, List<Lesson>> day;

        public Day(Map<String, List<Lesson>> day) {
            this.day = day;
        }

        public Map<String, List<Lesson>> getDay() {
            return day;
        }

        public void setDay(Map<String, List<Lesson>> day) {
            this.day = day;
        }
    }

    public static class Lesson {
        private String course;
        private String subject;
        private String instructor;
        private String type;
        private String location;
        private String time;

        public Lesson(String course, String subject, String instructor, String type, String location, String time) {
            this.course = course;
            this.subject = subject;
            this.instructor = instructor;
            this.type = type;
            this.location = location;
            this.time = time;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getInstructor() {
            return instructor;
        }

        public void setInstructor(String instructor) {
            this.instructor = instructor;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
/*
        { -> Monday
            { -> First
                {
                    IT,
                    Diskrētās struktūras datorzinātnēs,
                    R. Smirnova,
                    Lekcija,
                    408,
                    8:00 - 10:00
                }
            },
            { -> Second
                {
                    IT,
                    Datorgrafikas un attēlu apstr. pam.,
                    A. Sisojevs,
                    Lekcija,
                    Zoom,
                    10:00 - 12:00
                },
                {
                    IT,
                    Sistēmu model. un imitācijas pamati,
                    R. Smirnova ,
                    Lekcija,
                    408,
                    10:00 - 12:00
                },
                {
                    Automobiļu transports,
                    Materiālu pret.[1/2],
                    O.Kononova ,
                    Lekcija,
                    Zoom,
                    10:00 - 12:00
                }
            },
            { -> Third 12:00 - 14:00

            },
            { -> Fourth 14:00 - 16:00

            },
            { -> Fifth 16:00 - 18:00

            }
        },

 */