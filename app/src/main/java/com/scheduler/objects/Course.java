package com.scheduler.objects;

public class Course {
    private int id;
    private String title;
    private int crn;
    private String instructor;
    private String courseCode;
    private int startTime, endTime;
    private int[] days;
    private String section;

    public Course(int id, String title, int crn, String instructor, String courseCode, int startTime, int endTime, int[] days, String section){
        this.id = id;
        this.title = title;
        this.crn = crn;
        this.instructor = instructor;
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.section = section;
    }

    @Override
    public String toString(){
        String returnString = String.format("{id: %d, crn: %d, courseCode: %s, section: %s}", 
                                    this.id, this.crn, this.courseCode, this.section);
        return returnString;
    }

    public String getSection(){
        return this.section;
    }

    public int[] getDaysArr(){
        return this.days;
    }

    public int getEndTime(){
        return this.endTime;
    }

    public int getStartTime(){
        return this.startTime;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public int getCrn(){
        return this.crn;
    }

    public String getInstructor(){
        return this.instructor;
    }

    public String getCourseCode(){
        return this.courseCode;
    }
}
