package com.example.mars.httpapp;

/**
 * Created by Mars on 3/23/16.
 */
public class StudyGroup {

        public int id;
        public String department;
        public int classnumber;
        public String time;
        public String date;
        public String description;

        public StudyGroup(int ID, String dept, int classnum, String t, String descr, String datetime){
            this.id = ID;
            this.department = dept;
            this.classnumber = classnum;
            this.time = t;
            this.description = descr;
            this.date = datetime;
        }
}
